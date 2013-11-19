/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.modelexecution.fuml.extlib.helper.ActionHelper;
import org.modelexecution.fuml.extlib.helper.ActivityHelper;
import org.modelexecution.fuml.extlib.helper.EventHelper;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;

import fUML.Semantics.Actions.BasicActions.CallOperationActionActivation;
import fUML.Semantics.Actions.BasicActions.InputPinActivation;
import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Actions.IntermediateActions.AddStructuralFeatureValueActionActivation;
import fUML.Semantics.Actions.IntermediateActions.CreateObjectActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Actions.IntermediateActions.ValueSpecificationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Element;
import fUML.Syntax.Classes.Kernel.ElementList;
import fUML.Syntax.Classes.Kernel.LiteralBoolean;
import fUML.Syntax.Classes.Kernel.LiteralInteger;
import fUML.Syntax.Classes.Kernel.LiteralString;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

/**
 * Implementation of the {@link IntegrationLayer}
 * 
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerImpl implements IntegrationLayer {

	private final ExecutionContext executionContext = ExecutionContext.getInstance();

	private HashMap<String, DynamicClassLoader> dynamicClassLoaderMap = new HashMap<String, DynamicClassLoader>();
	
	private HashMap<Object_, Object> fUmlJavaMap = new HashMap<Object_, Object>();
	private HashMap<Object, Object_> javaFUmlMap = new HashMap<Object, Object_>();
	
	// Keep track of occurring events
	private List<Event> eventList = new ArrayList<Event>();

	private ExtensionalValueList previousExtensionalValueList = new ExtensionalValueList();

	/*
	 * Whenever the {@link IntegrationLayer} is added as an {@link
	 * ExecutionEventListener} to the {@link ExecutionContext}, this method is
	 * called on each {@link Event}.
	 * 
	 * @see org.modelexecution.fumldebug.core.ExecutionEventListener#notify(org.
	 * modelexecution.fumldebug.core.event.Event)
	 */
	@Override
	public void notify(Event event) {
		/*
		 * Whenever this method is reached, the execution is stopped s.t. the IL
		 * can do its job. Afterwards, the execution can be resumed
		 * (ExecutionContext.resume(int executionID)).
		 */
		Debug.out(this, "Received Event: " + event.toString());
		
		if (!(event instanceof ExtensionalValueEvent)) {
			eventList.add(event);
		}
		
		if (EventHelper.isExternalCreateObjectActionEntry(event)) {
			Debug.out(this, "Storing current ExecutionContext's ExtensionalValueList for later comparison");
			// store current ExtensionalValueList for later comparison
			previousExtensionalValueList = (ExtensionalValueList) executionContext.getExtensionalValues().clone();

		} else if (EventHelper.isExternalCreateObjectActionExit(event)) {
			Debug.out(this, "Event is an external CreateObjectAction's ActivityNodeExitEvent");
			handleExternalCreateObjectAction( (ActivityNodeExitEvent) event );

		} else if (EventHelper.isExternalCallOperationActionEntry(event)) {
			Debug.out(this, "Event is an external CallOperationAction's ActivityNodeEntryEvent (skip)");
			// do nothing

		} else if (EventHelper.isExternalCallOperationActionExit(event)) {
			Debug.out(this, "Event is an external CallOperationAction's ActivityNodeExitEvent (skip)");
			// do nothing

		} else if (EventHelper.isExternalActivityEntryEvent(event)) {
			Debug.out(this, "ENTERING ACTIVITY: " + ((ActivityEntryEvent) event).getActivity().name);
			handleExternalCallOperationAction( (ActivityEntryEvent) event );

		} else if (EventHelper.isExternalActivityExitEvent(event)) {
			Debug.out(this, "EXITING ACTIVITY: " + ((ActivityExitEvent) event).getActivity().name);
			// do nothing 
			
		} else if (EventHelper.isExternalAddStructuralFeatureValueActionEntryEvent(event)) {
			handleExternalAddStructuralFeatureValueAction( (ActivityNodeEntryEvent) event );

		}
		
	}// notify

	private void handleExternalCreateObjectAction(ActivityNodeExitEvent event) {
		Debug.out(this, "* Handling external CreateObjectAction *");
		
		Object_ fUmlPlaceholderObject = obtainFUmlPlaceholderObject();
		CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(event);
		Object_ fUmlObject = null;
		Object javaObject = null;
		
		try {
			String[] classJarPaths = ActionHelper.getClassJarPaths(createObjectAction.classifier.ownedComment);
			String classNamespaceAndName = ActionHelper.getClassNamespaceAndName(createObjectAction.classifier);
			javaObject = obtainJavaObject(classJarPaths, classNamespaceAndName);
			Object_Transformer object_Transformer = new Object_Transformer(fUmlPlaceholderObject, javaObject, this);
			fUmlObject = object_Transformer.getObject_();

			replaceLocusObject(fUmlPlaceholderObject, fUmlObject);
			assignObject_ToCreateObjectActionOutputPin(event, fUmlObject);
			assignTargetInputPinToCallOperationAction(event);
			
			Debug.out(this, "*** Successfully handled CreateObjectAction ***");
		} catch (Exception e) {
			Debug.out(this, "!!! Failed to handle external CreateObjectAction !!! Exception: " + e);
		}

		addObjects(fUmlObject, javaObject);
	}// handleExternalCreateObjectAction

	/**
	 * Handles the {@link AddStructuralFeatureValueAction} accessing an external library
	 * 
	 * Details:
	 * - Initially, the {@link AddStructuralFeatureValueAction} is obtained from an {@link AddStructuralFeatureValueActionEntryEvent}
	 * - Then, the {@link StructuralFeature} {@link Property} is replaced with the value on the value {@link InputPin} from the {@link AddStructuralFeatureValueAction}
	 * - Then, the Java {@link Field} value that corresponds to the {@link AddStructuralFeatureValueAction}'s value is also replaced with the newly obtained value
	 * 
	 * Capabilities:
	 * Can set StructuralFeatureValues of type {@link LiteralBoolean}, {@link LiteralInteger} and {@link LiteralString}
	 * 
	 * @param event an {@link ActivityNodeEntryEvent} containing an AddStructuralFeatureValueAction 
	 */
	private void handleExternalAddStructuralFeatureValueAction(ActivityNodeEntryEvent event) {
		Debug.out(this, "* Handling external AddStructuralFeatureValueAction *");
		
		AddStructuralFeatureValueAction addStructuralFeatureValueAction = EventHelper.getExternalAddStructuralFeatureValueAction(event);
		
		try {
			Object_ fUmlObject = obtainFUmlObjectFromAddStructuralFeatureValueActivationInputPin(event, addStructuralFeatureValueAction);
			Property property = (Property) addStructuralFeatureValueAction.structuralFeature;
			
			Object javaObject = getJavaObject(fUmlObject);
			Class<?> javaClass = javaObject.getClass();
			Field javaField = javaClass.getField(property.name);
			
			// ------------------------------------------------
			
			// Replace Structural Feature (Property) value entirely with the value on AddStructuralFeatureValue's value InputPin
			InputPin valueInputPin = addStructuralFeatureValueAction.value;
			ValueSpecificationAction valueSpecificationAction = (ValueSpecificationAction) valueInputPin.incoming.get(0).source.owner;
			
			if (valueSpecificationAction.value instanceof LiteralBoolean) {
				LiteralBoolean literal = (LiteralBoolean) valueSpecificationAction.value;
				BooleanValue value = new BooleanValue();
				value.value = literal.value;
				
				ValueList valueList = new ValueList();
				valueList.add(value);
				fUmlObject.setFeatureValue(property, valueList, 0);
				
				// Replace Java field value
				javaField.setBoolean(javaObject, value.value);
				
			} else if (valueSpecificationAction.value instanceof LiteralInteger) {
				LiteralInteger literal = (LiteralInteger) valueSpecificationAction.value;
				IntegerValue value = new IntegerValue();
				value.value = literal.value;
				
				ValueList valueList = new ValueList();
				valueList.add(value);
				fUmlObject.setFeatureValue(property, valueList, 0);
				
				// Replace Java field value
				javaField.setInt(javaObject, value.value);
				
			} else if (valueSpecificationAction.value instanceof LiteralString) {
				LiteralString literal = (LiteralString) valueSpecificationAction.value;
				StringValue value = new StringValue();
				value.value = literal.value;
				
				ValueList valueList = new ValueList();
				valueList.add(value);
				fUmlObject.setFeatureValue(property, valueList, 0);
				
				// Replace Java field value
				javaField.set(javaObject, value.value);
				
			} else {
				// CASE: Complex Type			
				// Not implemented
				
			}
			
			// ------------------------------------------------
			
			Debug.out(this, "*** Successfully handled external AddStructuralFeatureValueAction ***");
			
		} catch (Exception e) {
			Debug.out(this, "!!! Failed to handle external AddStructuralFeatureValueAction !!! Exception: " + e);
		}
		
	}// handleExternalAddStructuralFeatureValueAction
	
	
	/**
	 * Obtains the fUML Object_ out of a {@link CallOperationAction} and the
	 * current {@link Event} (which must be an {@link ActivityNodeEntryEvent}
	 * 
	 * @param event
	 *            a CallOperationAction {@link ActivityNodeEntryEvent}
	 * @param callOperationAction
	 *            the {@link CallOperationAction} from which to obtain the fUML
	 *            Object_
	 * @return fUML {@link Object_} or null if not found
	 * @throws Exception
	 *             When {@code callOperationActionEntryEvent} is not of type
	 *             {@link ActivityNodeEntryEvent}
	 */
	private Object_ obtainFUmlObjectFromCallOperationAction(ActivityNodeEntryEvent event, CallOperationAction callOperationAction)
			throws Exception {

		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {
			if (extensionalValue instanceof ActivityExecution && extensionalValue.hashCode() == event.getActivityExecutionID()) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
				/*
				 * Arrived at the correct ActivityExecution, next: obtain
				 * CallOperationAction's target InputPin
				 */
				CallOperationActionActivation callOperationActionActivation = (CallOperationActionActivation) activityExecution.activationGroup
						.getNodeActivation(callOperationAction);
	
				InputPinActivation inputPinActivation = (InputPinActivation) callOperationActionActivation
						.getPinActivation(callOperationAction.target);
	
				for (Token token : inputPinActivation.heldTokens) {
					if (token.holder.equals(inputPinActivation)) {
						/*
						 * Arrived at the correct ObjectToken, next: return
						 * referring fUML Object_
						 */
						ObjectToken objectToken = (ObjectToken) token;
						Reference reference = (Reference) objectToken.value;
	
						return reference.referent;
	
					}
	
				}// Token loop
	
			}
		}// ExtensionalValue loop

		// If no prior return occurred, throw an Exception
		throw new Exception("Error occured while trying to obtain the fUML object.");
			
	}// obtainFUmlObjectFromCallOperationActionEntryEvent

	/**
	 * Obtains the previously created fUML {@link Object_}
	 * 
	 * @return the previously created fUML {@link Object_}
	 */
	private Object_ obtainFUmlPlaceholderObject() {
		@SuppressWarnings("unchecked")
		Collection<ExtensionalValueList> extValList = CollectionUtils.subtract(executionContext.getExtensionalValues(), previousExtensionalValueList);
		Object_ fUmlPlaceholderObject = (Object_) extValList.toArray()[0];

		return fUmlPlaceholderObject;
	}// obtainFUmlPlaceholderObject

	/**
	 * Replaces {@code fUmlPlaceholderObject} with {@code fUmlObject} in the
	 * ExecutionContext Locus
	 * 
	 * @param fUmlPlaceholderObject
	 *            {@link Object_} to be replaced
	 * @param fUmlObject
	 *            {@link Object_} that replaces the other {@link Object_}
	 */
	private void replaceLocusObject(Object_ fUmlPlaceholderObject, Object_ fUmlObject) throws Exception {
		ExtensionalValueList extensionalValues = executionContext.getLocus().extensionalValues;

		for (int i = 0; i < extensionalValues.size(); i++) {
			if (extensionalValues.getValue(i).hashCode() == fUmlPlaceholderObject.hashCode()) {
				extensionalValues.setValue(i, fUmlObject);
				Debug.out(this, "Successfully replaced Locus Object.");
				return; // exit
			}
		}

		throw new Exception("Failed to replace Locus Object.");

	}// replaceLocusObject


	
	
	/**
	 * Obtains a Java {@link Object} from a given {@code event} using the
	 * {@link DynamicClassLoader}
	 * @param jarPaths JAR paths from which to load the classes from
	 * @param classNamespaceAndName Namespace and Class name of the Class from which the Object is obtained
	 * @param event
	 *            from which the {@link Object} is obtained
	 * 
	 * @return Java {@link Object} instance if found, null otherwise
	 */
	private Object obtainJavaObject(String[] jarPaths, String classNamespaceAndName) {
		Object javaObject = null;
		DynamicClassLoader dynamicClassLoader = null;
		Arrays.sort(jarPaths);

		if(dynamicClassLoaderMap.containsKey(Arrays.toString(jarPaths)))
			dynamicClassLoader = dynamicClassLoaderMap.get(Arrays.toString(jarPaths));
		else {
			dynamicClassLoader = new DynamicClassLoader(jarPaths);
			dynamicClassLoaderMap.put(Arrays.toString(jarPaths), dynamicClassLoader);
		}
		
		try {
			ClassLoader classLoader = dynamicClassLoader.getClassLoader();

			Class<?> clazz = classLoader.loadClass(classNamespaceAndName);
			javaObject = clazz.newInstance();

		} catch (Exception e) {
			Debug.out(this, "Error occured while trying to obtain the Java object. " + e);
		}

		return javaObject;
	}// obtainJavaObject from CreateObjectAction

	private void handleExternalCallOperationAction(ActivityEntryEvent event) {
		Debug.out(this, "* Handling external CallOperationAction *");
		
		// Obtain the Activity for modifications
		Activity activity = EventHelper.getExternalActivity(event);

		try {

			// Obtain fUML Object_ from InputPinActivation in
			// ActivityExecution
			Object_ fUmlObject = obtainFUmlObjectFromActivityExecution(event);

			// Obtain corresponding Java Object
			Object javaObject = fUmlJavaMap.get(fUmlObject);

			String methodName = ActivityHelper.getOperationName(activity);
			String classNamespaceAndName = ActivityHelper.getOperationNamespaceAndName(activity);

			// ------------------------------------------------
			
			LinkedHashMap<Parameter, ParameterValue> fUmlParameterWithValueMap = obtainfUmlInputParameters(activity.specification.ownedElement, executionContext.getLocus().extensionalValues);

			// ------------------------------------------------
			
			LinkedHashMap<Object, Class<?>> javaInputParameterWithValueMap = obtainJavaInputParameters(fUmlParameterWithValueMap);
			
			// ------------------------------------------------

			// Step 1: Call method on Java Object and store the return value
			Class<?> javaClass = javaObject.getClass();
			Method javaMethod = null;
			Object javaMethodReturnValue = null;
			
			if (javaInputParameterWithValueMap.size() > 0) {
				// Case: With Input Parameter(s)
				
				if (javaInputParameterWithValueMap.values().toArray().length == 1 && 
						!javaInputParameterWithValueMap.values().toArray()[0].toString().contains("boolean") &&
						!javaInputParameterWithValueMap.values().toArray()[0].toString().contains("int") &&
						!javaInputParameterWithValueMap.values().toArray()[0].toString().contains("String")) {
					// Case: SINGLE complex input parameter (no multiple or combination with primitive parameters)
					
					Method[] availableJavaMethods = javaClass.getMethods();
					for (Method availableJavaMethod : availableJavaMethods) {
						if (availableJavaMethod.getName().equals(methodName) && 
								availableJavaMethod.getParameterTypes()[0].equals(javaInputParameterWithValueMap.keySet().toArray()[0].getClass())) {
							
							Class<?> complexMethodInputParameterType = availableJavaMethod.getParameterTypes()[0];
							javaMethod = javaClass.getMethod(methodName, complexMethodInputParameterType);
							
							// Warning: the following invocation alters javaObject (!)		
							javaMethodReturnValue = javaMethod.invoke(javaObject, javaInputParameterWithValueMap.keySet().toArray()[0]);
							
							break; // method call has already been made, go to step 2
						}						
					}
					
				} else {
					// Case: Any number of primitive input parameter combination (int, boolean, String)
					
					javaMethod = javaClass.getMethod(methodName, javaInputParameterWithValueMap.values().toArray(new Class[0]));
					// Warning: the following invocation alters javaObject (!)
					javaMethodReturnValue = javaMethod.invoke(javaObject, javaInputParameterWithValueMap.keySet().toArray());
				}
			
			} else {
				// Case: Without Input Parameter(s)
				Class<?> parameterType = null;
				
				javaMethod = javaClass.getMethod(methodName, parameterType);

				// Warning: the following invocation alters javaObject (!)
				javaMethodReturnValue = javaMethod.invoke(javaObject, parameterType);
			}
			
			// ------------------------------------------------

			// Step 2: Update the corresponding fUML Object_ using the
			// Object_Transfomer
			Object_Transformer object_Transformer = new Object_Transformer(fUmlObject, javaObject, this);
			Object_ newFUmlObject = object_Transformer.getObject_();

			// Step 3: Update the HashMaps
			// Not necessary since the HashMap fields just keep references to the objects and not the objects itself
			
			// Step 4: Translate return value into fUML Parameter
			Parameter outputParameter = ActivityHelper.getReturnParameter(activity);

			ParameterValue outputParameterValue = new ParameterValue();
			outputParameterValue.parameter = outputParameter;

			if (javaMethodReturnValue instanceof java.lang.Boolean) {

				BooleanValue booleanValue = new BooleanValue();
				booleanValue.value = (boolean) javaMethodReturnValue;
				outputParameterValue.values.add(booleanValue);

			} else if (javaMethodReturnValue instanceof java.lang.Integer) {

				IntegerValue integerValue = new IntegerValue();
				integerValue.value = (int) javaMethodReturnValue;
				outputParameterValue.values.add(integerValue);

			} else if (javaMethodReturnValue instanceof java.lang.String) {

				StringValue stringValue = new StringValue();
				stringValue.value = (String) javaMethodReturnValue;
				outputParameterValue.values.add(stringValue);

			} else if (javaMethodReturnValue != null) {
				
				// NOTE the following lines are not tested with anything different than Object_ (e.g. Enum or Array is not tested)
				 
				if (activity.specification != null && activity.specification instanceof Operation && ((Operation) activity.specification).type != null && ((Operation) activity.specification).type instanceof Class_) {
					Operation operation = (Operation) activity.specification;
					Class_ returnType = (Class_) operation.type;
					Object_ newComplexFUmlObject = new Object_();
					newComplexFUmlObject.types.add(returnType);
					
					Object_Creator object_Creator = new Object_Creator(newComplexFUmlObject, javaMethodReturnValue, executionContext);
					newComplexFUmlObject = object_Creator.getfUmlObject();
					newFUmlObject.locus.add(newComplexFUmlObject); // add new complex Object_ to Locus
					newComplexFUmlObject.locus = newFUmlObject.locus; // same Locus ?
					
					Reference reference = new Reference();
					reference.referent = object_Creator.getfUmlObject();
					outputParameterValue.values.add(reference);
					
					// Replace with new complex fUML Object
					newFUmlObject = newComplexFUmlObject;

					// Add objects to internal map
					addObjects(newFUmlObject, javaMethodReturnValue);
				}
				
			}

			Debug.out(this, "Java method return value from calling " + classNamespaceAndName + "." + methodName + " is '" + javaMethodReturnValue + "'");
			Debug.out(this, "Java Object : " + ToStringBuilder.reflectionToString(getJavaObject(newFUmlObject)));
			Debug.out(this, "fUML Object_: " + getFUmlObject(javaObject));
			
			// Step 5.1: Plugging output parameter to Placeholder Activity (if not null)
			if (outputParameterValue.parameter != null) {
				for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {
					if (extensionalValue.hashCode() == ((ActivityEntryEventImpl) event).getActivityExecutionID()) {
						ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
						activityExecution.setParameterValue(outputParameterValue);
						Debug.out(this, "*** Successfully handled external CallOperationAction ***");
						return; // exit
					}
				}
			}

		} catch (Exception e) {
			Debug.out(this, "!!! Failed to handle external CallOperationAction !!! Exception: " + e);
		}

	}// handleExternalCallOperationAction

	/**
	 * Obtains an ordered map of {@link Class} (i.e. Java parameter type) and its corresponding {@link Object} (i.e. Java parameter value)
	 * 
	 * @param fUmlParameterWithValueMap a {@link LinkedHashMap} (ordered) on the {@link Parameter} and its corresponding {@link ParameterValue}
	 * @return a {@link LinkedHashMap} (ordered) on the {@link Object} (i.e. Java parameter value) and its {@link Class} (i.e. Java parameter type) found in {@code fUmlParameterWithValueMap}
	 */
	private LinkedHashMap<Object, Class<?>> obtainJavaInputParameters(LinkedHashMap<Parameter, ParameterValue> fUmlParameterWithValueMap) {
		LinkedHashMap<Object, Class<?>> javaParameterWithValueMap = new LinkedHashMap<Object, Class<?>>();
		
		if (fUmlParameterWithValueMap.size() > 0) {
			for (Map.Entry<Parameter, ParameterValue> fUMLParameterWithValueMapEntry : fUmlParameterWithValueMap.entrySet()) {
				Parameter fUmlInputParameter = fUMLParameterWithValueMapEntry.getKey();
				ParameterValue fUmlInputParameterValue = fUMLParameterWithValueMapEntry.getValue();
				
				if (fUmlInputParameter.type.name.toString().equals("boolean")) {
					boolean javaInputParameterValue = (boolean) ((BooleanValue) fUmlInputParameterValue.values.get(0)).value;					
					javaParameterWithValueMap.put(javaInputParameterValue, Boolean.TYPE);
				} else if (fUmlInputParameter.type.name.toString().equals("int")) {
					int javaInputParameterValue = (int) ((IntegerValue) fUmlInputParameterValue.values.get(0)).value;					
					javaParameterWithValueMap.put(javaInputParameterValue, Integer.TYPE);
				} else if (fUmlInputParameter.type.name.toString().equals("String")) {
					String javaInputParameterValue = (String) ((StringValue) fUmlInputParameterValue.values.get(0)).value;					
					javaParameterWithValueMap.put(javaInputParameterValue, String.class);
				} else {
					// Case: Complex Input Parameter
					Reference reference = (Reference) fUmlInputParameterValue.values.get(0);
					Object javaObject = fUmlJavaMap.get(reference.referent);
					javaParameterWithValueMap.put(javaObject, javaObject.getClass());
				}
			}// for loop (fUmlParameterWithValueMap)
		}// if (fUmlParameterWithValueMap not empty)
		
		return javaParameterWithValueMap;
	}// obtainJavaInputParameters
	
	
	/**
	 * Obtains an ordered map of {@link Parameter} and its corresponding {@link ParameterValue}
	 * 
	 * @param elementList {@link ElementList} from which to to obtain input {@link Parameter}s
	 * @param extensionalValueList {@link ExtensionalValueList} from the Locus from which to obtain the {@link ParameterValue} for a corresponding {@link Parameter} in {@code parameterList}
	 * @return a {@link LinkedHashMap} (ordered) on the {@link Parameter} and its corresponding {@link ParameterValue} found in the {@code parameterList}
	 */
	private LinkedHashMap<Parameter, ParameterValue> obtainfUmlInputParameters(ElementList elementList, ExtensionalValueList extensionalValueList) {
		
		LinkedHashMap<Parameter, ParameterValue> parameterWithParameterValueMap = new LinkedHashMap<Parameter, ParameterValue>();
		
		// Go through the list of Elements
		for (Element element : elementList) {
			if (element instanceof Parameter) {
				Parameter parameter = (Parameter) element;
			
				if (parameter.direction == ParameterDirectionKind.in && parameter.name != null && parameter.type != null) {
//					if (parameter.type.name.toString().equals("boolean") || parameter.type.name.toString().equals("int")
//							|| parameter.type.name.toString().equals("String") || parameter.type.name.toString().equals("Object")) {
						
						// Find a corresponding ParameterValue for this Parameter in the Locus
						for (ExtensionalValue extensionalValue : extensionalValueList) {
							if (extensionalValue instanceof ActivityExecution) {
								ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
	
								for (ParameterValue parameterValue : activityExecution.parameterValues) {
									if (parameterValue.parameter != null && parameterValue.parameter.qualifiedName != null
											&& parameterValue.parameter.qualifiedName.equals(parameter.qualifiedName)) {
										parameterWithParameterValueMap.put(parameter, parameterValue);
										break; // since corresponding ParameterValue has been found
									}
								}// for loop (parameterValueList)
	
							}
						}// for loop (executionContext.getLocus().extensionalValues)					
						
					}
				//}
				
			}// element instanceof Parameter
			
		}// for loop (elementList)
		return parameterWithParameterValueMap;
	}// obtainfUmlInputParameters
	

	private Object_ obtainFUmlObjectFromActivityExecution(ActivityEntryEvent event) throws Exception {
		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {
			if (extensionalValue.hashCode() == event.getActivityExecutionID()) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
				Object_ fUmlObject = activityExecution.context;
				return fUmlObject;
			}
		}
	
		// If no prior return has been occurred, throw an Exception
		throw new Exception("Failed to obtain the fUML Object_ from ActivityExecution.");
	}// obtainFUmlObjectFromActivityExecution
	
	/**
	 * Obtains the referring {@link Object_} from an {@link AddStructuralFeatureValueAction}'s object {@link InputPin}
	 * 
	 * @param event {@link Event} of type {@link ActivityNodeEntryEvent} before firing {@link AddStructuralFeatureValueAction}
	 * @param addStructuralFeatureValueAction {@link AddStructuralFeatureValueAction} to obtain the object {@link InputPin} from
	 * @return {@link Object_} referenced at the {@link AddStructuralFeatureValueAction}'s object InputPin
	 * @throws Exception
	 */
	private Object_ obtainFUmlObjectFromAddStructuralFeatureValueActivationInputPin(ActivityNodeEntryEvent event, AddStructuralFeatureValueAction addStructuralFeatureValueAction) throws Exception {

		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {
			if (extensionalValue.hashCode() == event.getActivityExecutionID()) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
				
				AddStructuralFeatureValueActionActivation activation = (AddStructuralFeatureValueActionActivation) activityExecution.activationGroup.getNodeActivation(addStructuralFeatureValueAction);
				InputPinActivation inputPinActivation = (InputPinActivation) activation.getPinActivation(addStructuralFeatureValueAction.object);
				
				ObjectToken objectToken = (ObjectToken) inputPinActivation.heldTokens.get(0);
				Reference object_Reference = (Reference) objectToken.value;
				Object_ fUmlObject = object_Reference.referent;
				return fUmlObject;
			}
		}

		throw new Exception("Failed to obtain the fUML Object_ from AddStructuralFeatureValueActivation's object InputPin.");
	}// obtainFUmlObjectFromAddStructuralFeatureValueActivationInputPin
	

	/**
	 * Assigns a given {@link Object_} as an {@link ObjectToken} to the
	 * {@link CreateObjectAction}'s result {@link OutputPin}
	 * 
	 * @param event
	 *            must be an instance of {@link ActivityNodeExitEvent} and
	 *            it's node must be an instance of {@link CreateObjectAction}
	 * @param fUmlObject
	 *            fUML {@link Object_} to set at the {@link ObjectToken}
	 */
	private void assignObject_ToCreateObjectActionOutputPin(ActivityNodeExitEvent event, Object_ fUmlObject) throws Exception {
		CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(event);
		/*
		 * Obtained the CreateObjectAction, next: navigate to the correct node
		 * that is equal to the obtained CreateObjectAction
		 */

		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {
			if (extensionalValue instanceof ActivityExecution && extensionalValue.hashCode() == event.getActivityExecutionID()) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
				/*
				 * Arrived at the correct ActivityExecution, next: obtain
				 * CreateObjectAction's result OutputPin
				 */
				CreateObjectActionActivation createObjectActionActivation = (CreateObjectActionActivation) activityExecution.activationGroup
						.getNodeActivation(createObjectAction);
				OutputPinActivation outputPinActivation = (OutputPinActivation) createObjectActionActivation
						.getPinActivation(createObjectAction.result);

				/**
				 * Create a new ObjectToken that references the Object_ and
				 * add it to the CreateObjectActionActivation's
				 * OutputPinActivation
				 */
				ObjectToken objectToken = new ObjectToken();

				Reference reference = new Reference();
				reference.referent = fUmlObject;

				objectToken.value = reference;

				outputPinActivation.addToken(objectToken);
				Debug.out(this, "Successfully assigned Object_ Reference to CreateObjectAction's OutputPin.");
				return; // exit

			}

		}// ExtensionalValue loop

		// If prior return did not occur, throw Exception
		throw new Exception("Error occured while trying to assign Object_ to CreateObjectAction's result OutputPin.");

	}// assignObject_ToCreateObjectActionOutputPin

	/**
	 * Assigns the CallOperationAction's InputPin based on the
	 * CreateObjectAction's OutputPin ObjectFlow
	 * 
	 * @param event
	 *            must be an instance of {@link ActivityNodeExitEvent} and
	 *            it's node must be an instance of {@link CreateObjectAction}
	 */
	private void assignTargetInputPinToCallOperationAction(ActivityNodeExitEvent event) throws Exception {
		CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(event);

		/**
		 * Obtain the CreateObjectAction's OutputPin's ObjectFlow for comparison
		 */
		OutputPin outputPin = createObjectAction.output.get(0);
		ObjectFlow outgoingObjectFlow = (ObjectFlow) outputPin.outgoing.get(0);

		/**
		 * Look for ActivityExecutions that have CallOperationActionActivations
		 */
		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {

			if (extensionalValue instanceof ActivityExecution) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;

				for (ActivityNodeActivation activityNodeActivation : activityExecution.activationGroup.nodeActivations) {

					if (activityNodeActivation instanceof CallOperationActionActivation) {
						CallOperationAction callOperationAction = (CallOperationAction) activityNodeActivation.node;

						/**
						 * Next: Look for CallOperationAction InputPin's that
						 * have an incoming ObjectFlow that matches with the
						 * CreateObjectAction's ObjectFlow
						 */
						for (InputPin inputPin : callOperationAction.input) {

							if (inputPin.incoming.get(0) instanceof ObjectFlow) {
								ObjectFlow incomingObjectFlow = (ObjectFlow) inputPin.incoming.get(0);

								if (outgoingObjectFlow.hashCode() == incomingObjectFlow.hashCode()) {
									/**
									 * Arrived at the same ObjectFlow, next: set
									 * the CallOperationAction's target InputPin
									 */
									callOperationAction.target = inputPin;
									Debug.out(this, "Successfully set CallOperationAction's target InputPin.");

								}

							}

						}// InputPin loop

					}

				}// ActivityNodeActivation loop

			}

		}// ExtensionalValue loop

	}// assignTargetInputPinToCallOperationAction

	public IntegrationLayerImpl() {

		initialize();

	}// Constructor

	private void initialize() {

		// IL registers itself at the ExecutionContext instance
		executionContext.addEventListener(this);
		
		// Initialize Debugger
		Debug.clearDebugFile();

	}// initialize

	private void addObjects(Object_ fUmlObject, Object javaObject) {
		javaFUmlMap.put(javaObject, fUmlObject);
		fUmlJavaMap.put(fUmlObject, javaObject);
	}// addObjects

	/**
	 * Remove fUML {@link Object_} and corresponding Java {@link Object} from
	 * both HashMaps
	 * 
	 * @param fUmlObject
	 *            fUML {@link Object_} used for searching the fUML
	 *            {@link Object_} and Java {@link Object} to remove
	 */
	private void removeObjects(Object_ fUmlObject) {
		Object javaObject = getJavaObject(fUmlObject);
		fUmlJavaMap.remove(javaObject);
		javaFUmlMap.remove(fUmlObject);
	}// removeObjects(Object_)

	/**
	 * Remove Java {@link Object} and corresponding fUML {@link Object_} from
	 * both HashMaps
	 * 
	 * @param javaObject
	 *            Java {@link Object} used for searching the Java {@link Object}
	 *            and fUML {@link Object_} to remove
	 */
	private void removeObjects(Object javaObject) {
		Object_ fUmlObject = getFUmlObject(javaObject);
		fUmlJavaMap.remove(javaObject);
		javaFUmlMap.remove(fUmlObject);
	}// removeObjects(Object)

	/**
	 * Updates both HashMaps with the new fUML {@link Object_} based on the
	 * corresponding {@code javaObject}
	 * 
	 * @param javaObject
	 *            corresponding Java {@link Object} used to find the fUML
	 *            {@link Object_} to be replaced
	 * @param newFUmlObject
	 *            new fUML {@link Object_} used to update the HashMaps with
	 */
	private void updateFUmlObject(Object javaObject, Object_ newFUmlObject) {
		Object_ oldFUmlObject = getFUmlObject(javaObject);

		javaFUmlMap.remove(oldFUmlObject);
		fUmlJavaMap.remove(javaObject);

		javaFUmlMap.put(javaObject, newFUmlObject);
		fUmlJavaMap.put(newFUmlObject, javaObject);
	}// updateFUmlObject

	/**
	 * Updates both HashMaps with the new Java {@link Object} based on the
	 * corresponding {@code fUmlObject}
	 * 
	 * @param fUmlObject
	 *            corresponding fUML {@link Object_} used to find the Java
	 *            {@link Object} to be replaced
	 * @param newJavaObject
	 *            new Java {@link Object} used to update the HashMaps with
	 */
	private void updateJavaObject(Object_ fUmlObject, Object newJavaObject) {
		Object oldJavaObject = getJavaObject(fUmlObject);

		javaFUmlMap.remove(oldJavaObject);
		fUmlJavaMap.remove(fUmlObject);

		javaFUmlMap.put(newJavaObject, fUmlObject);
		fUmlJavaMap.put(fUmlObject, newJavaObject);
	}// updateJavaObject

	@Override
	public Object_ getFUmlObject(Object javaObject) {
		return javaFUmlMap.get(javaObject);
	}// getFUmlObject

	@Override
	public Object getJavaObject(Object_ fUmlObject) {
		return fUmlJavaMap.get(fUmlObject);
	}// getJavaObject

	@Override
	public ExecutionContext getExecutionContext() {
		return this.executionContext;
	}// getExecutionContext
	
	/**
	 * Retrieve the output parameter value of an {@link ActivityNodeEntryEvent} with
	 * Action named {@code actionName}
	 * @param eventKind kind of the event. Currently supported: 
	 * 		- ActivityEntryEvent
	 * 		- ActivityExitEvent
	 * 		- ActivityNodeEntryEvent
	 * 		- ActivityNodeExitEvent
	 * @param actionName {@link ActivityNodeEntryEvent}'s action name
	 * 
	 * @return output parameter value of the {@link ActivityNodeEntryEvent} action
	 */
	public ParameterValue getOutputParameterValue(String eventKind, String actionName) {
		for (Event event : eventList) {
			if (eventKind.equals("ActivityNodeEntryEvent") &&
					event.toString().contains("ActivityNodeEntryEvent node = " + actionName)) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				return (ParameterValue) executionContext
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
			} else if (eventKind.equals("ActivityNodeExitEvent") &&
					event.toString().contains("ActivityNodeExitEvent node = " + actionName)) {
				ActivityNodeExitEvent activityNodeEntryEvent = (ActivityNodeExitEvent) event;
				return (ParameterValue) executionContext
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
			} else if (eventKind.equals("ActivityEntryEvent") &&
					event.toString().contains("ActivityEntryEvent activity = " + actionName)) {
				ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
				return (ParameterValue) executionContext
						.getActivityOutput(activityEntryEvent.getActivityExecutionID()).get(0);
			} else if (eventKind.equals("ActivityExitEvent") &&
					event.toString().contains("ActivityExitEvent activity = " + actionName)) {
				ActivityExitEvent activityEntryEvent = (ActivityExitEvent) event;
				return (ParameterValue) executionContext
						.getActivityOutput(activityEntryEvent.getActivityExecutionID()).get(0);
			}
		}
		return null; // No ActivityNodeEntryEvent with the specified action name found
	}// getActivityNodeEntryEventOutputParameterValue

}// IntegrationLayerImpl
