/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Semantics.Actions.BasicActions.CallOperationActionActivation;
import fUML.Semantics.Actions.BasicActions.InputPinActivation;
import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Actions.IntermediateActions.CreateObjectActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.DataValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.StructuredValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterList;

/**
 * Implementation of the {@link IntegrationLayer}
 * 
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerImpl implements IntegrationLayer {

	private final ExecutionContext executionContext = ExecutionContext.getInstance();

	private DynamicClassLoader dynamicClassLoader;

	private HashMap<Object_, Object> fUmlJavaMap = new HashMap<Object_, Object>();
	private HashMap<Object, Object_> javaFUmlMap = new HashMap<Object, Object_>();

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
		if (EventHelper.isExternalCreateObjectActionEntry(event)) {
			System.out.println("[External CreateObjectAction ActivityNodeEntryEvent]");
			// store current ExtensionalValueList for later comparison
			previousExtensionalValueList = (ExtensionalValueList) executionContext.getExtensionalValues().clone();

		} else if (EventHelper.isExternalCreateObjectActionExit(event)) {
			System.out.println("[External CreateObjectAction ActivityNodeExitEvent]");

			Object_ fUmlPlaceholderObject = obtainFUmlPlaceholderObject();
			Object javaObject = obtainJavaObject(event);

			Object_Transformer object_Transformer = new Object_Transformer(fUmlPlaceholderObject, javaObject, event, executionContext);
			Object_ fUmlObject = object_Transformer.getObject_();

			try {
				replaceLocusObject(fUmlPlaceholderObject, fUmlObject);
				assignObject_ToCreateObjectActionOutputPin(event, fUmlObject);
				assignTargetInputPinToCallOperationAction(event);
			} catch (Exception e) {
				System.out.println("Operations during CreateObjectAction on ActivityNodeExitEvent failed: " + e);
			}

			addObjects(fUmlObject, javaObject);

		} else if (EventHelper.isExternalCallOperationActionEntry(event)) {
			System.out.println("[External CallOperationAction ActivityNodeEntryEvent]");
			// do nothing

		} else if (EventHelper.isExternalCallOperationActionExit(event)) {
			System.out.println("[External CallOperationAction ActivityNodeExitEvent]");
			// do nothing

		} else if (EventHelper.isExternalActivityEntryEvent(event)) {
			System.out.println("[External ActivityEntryEvent]");

			callOperation(event);

		} else if (EventHelper.isExternalActivityExitEvent(event)) {
			System.out.println("[External ActivityExitEvent]");

		}
	}// notify

	/**
	 * Obtains the fUML Object_ out of a {@link CallOperationAction} and the
	 * current {@link Event} (which must be an {@link ActivityNodeEntryEvent}
	 * 
	 * @param callOperationActionEntryEvent
	 *            the current {@Event}, must be of type
	 *            {@link ActivityNodeEntryEvent}
	 * @param callOperationAction
	 *            the {@link CallOperationAction} from which to obtain the fUML
	 *            Object_
	 * @return fUML {@link Object_} or null if not found
	 * @throws Exception
	 *             When {@code callOperationActionEntryEvent} is not of type
	 *             {@link ActivityNodeEntryEvent}
	 */
	private Object_ obtainFUmlObjectFromCallOperationActionEntryEvent(Event callOperationActionEntryEvent, CallOperationAction callOperationAction)
			throws Exception {

		if (callOperationActionEntryEvent instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent event = (ActivityNodeEntryEvent) callOperationActionEntryEvent;

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

		} else {
			throw new Exception("Error occured while trying to obtain the fUML object.");
		}

		return null;
	}// obtainFUmlObjectFromCallOperationActionEntryEvent

	/**
	 * Obtains the previously created fUML {@link Object_}
	 * 
	 * @return the previously created fUML {@link Object_}
	 */
	private Object_ obtainFUmlPlaceholderObject() {
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
				System.out.println("Successfully replaced Locus Object.");
				return; // exit
			}
		}

		throw new Exception("Failed to replace Locus Object.");

	}// replaceLocusObject

	/**
	 * Obtains a Java {@link Object} from a given {@code event} using the
	 * {@link DynamicClassLoader}
	 * 
	 * @param event
	 *            from which the {@link Object} is obtained
	 * @return Java {@link Object} instance if found, null otherwise
	 */
	private Object obtainJavaObject(Event event) {
		Object javaObject = null;

		try {
			CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(event);

			String[] jarPaths = ActionHelper.getClassJarPaths(createObjectAction);
			String classNamespaceAndName = ActionHelper.getClassNamespaceAndName(createObjectAction);

			dynamicClassLoader = new DynamicClassLoader(jarPaths);
			ClassLoader classLoader = dynamicClassLoader.getClassLoader();

			Class<?> clazz = classLoader.loadClass(classNamespaceAndName);
			javaObject = clazz.newInstance();

		} catch (Exception e) {
			System.out.println("Error occured while trying to obtain the Java object. " + e);
		}

		return javaObject;
	}// obtainJavaObject

	private void callOperation(Event event) {
		// Obtain the Activity for modifications
		Activity activity = EventHelper.getExternalActivity(event);

		try {

			// Obtain fUML Object_ from InputPinActivation in
			// ActivityExecution
			Object_ fUmlObject = obtainFUmlObjectFromActivityExecution(event);
			System.out.println("[CallOperationAction] fUmlObject = " + fUmlObject);

			// Obtain corresponding Java Object
			Object javaObject = fUmlJavaMap.get(fUmlObject);
			System.out.println("[CallOperationAction] javaObject = " + javaObject);

			String methodName = ActivityHelper.getOperationName(activity);
			String classNamespaceAndName = ActivityHelper.getOperationNamespaceAndName(activity);

			// ------------------------------------------------
			
			LinkedHashMap<Parameter, ParameterValue> fUmlParameterWithValueMap = obtainfUmlInputParameters(activity.specification.ownedParameter, executionContext.getLocus().extensionalValues);

			// ------------------------------------------------
			
			LinkedHashMap<Object, Class> javaParameterWithValueMap = obtainJavaInputParameters(fUmlParameterWithValueMap);
			
			// ------------------------------------------------

			// Step 1: Call method on Java Object and store the return value
			Class<?> javaClass = javaObject.getClass();
			Method javaMethod = null;
			Object javaMethodReturnValue = null;
			
			if (javaParameterWithValueMap.size() > 0) {
				// Case: With Input Parameter(s)
				javaMethod = javaClass.getMethod(methodName, javaParameterWithValueMap.values().toArray(new Class[0]));

				// Warning: the following invocation alters javaObject (!)
				javaMethodReturnValue = javaMethod.invoke(javaObject, javaParameterWithValueMap.keySet().toArray());
			
			} else {
				// Case: Without Input Parameter(s)
				javaMethod = javaClass.getMethod(methodName, null);

				// Warning: the following invocation alters javaObject (!)
				javaMethodReturnValue = javaMethod.invoke(javaObject, null);
			}
			
			// ------------------------------------------------

			// Step 2: Update the corresponding fUML Object_ using the
			// Object_Transfomer
			Object_Transformer object_Transformer = new Object_Transformer(fUmlObject, javaObject, event, executionContext);
			Object_ newFUmlObject = object_Transformer.getObject_();

			// Step 3: Update the HashMaps
			// TODO: Check if this really is necessary.
			// Reason: It seems that the HashMap data structure just keeps references to the objects and not the objects itself.
			Object oldJavaObject = getJavaObject(fUmlObject);
			removeObjects(oldJavaObject);
			addObjects(newFUmlObject, javaObject);

			// Step 4: Translate return value into fUML Parameter
			Parameter outputParameter = ActivityHelper.getReturnParameter(activity);

			ParameterValue outputParameterValue = new ParameterValue();
			outputParameterValue.parameter = outputParameter;

			if (javaMethod.getReturnType().getName().equals("boolean")) {

				BooleanValue booleanValue = new BooleanValue();
				booleanValue.value = (boolean) javaMethodReturnValue;
				outputParameterValue.values.add(booleanValue);

			} else if (javaMethod.getReturnType().getName().equals("int")) {

				IntegerValue integerValue = new IntegerValue();
				integerValue.value = (int) javaMethodReturnValue;
				outputParameterValue.values.add(integerValue);

			} else if (javaMethod.getReturnType().getName().equals("java.lang.String")) {

				StringValue stringValue = new StringValue();
				stringValue.value = (String) javaMethodReturnValue;
				outputParameterValue.values.add(stringValue);

			} else {
				
				// NOTE the following lines are not tested with anything different than Object_ (e.g. Enum)
				
				if (activity.specification != null && activity.specification instanceof Operation && ((Operation) activity.specification).type != null) {
					Operation operation = (Operation) activity.specification;
					Class_ returnType = (Class_) operation.type;
					Object_ newfUmlObject = new Object_();
					newfUmlObject.types.add(returnType);
					
					Object_Creator object_Creator = new Object_Creator(newfUmlObject, javaMethodReturnValue, executionContext);
					
					Reference reference = new Reference();
					reference.referent = object_Creator.getfUmlObject();
					outputParameterValue.values.add(reference);
				}
				
			}

			System.out.println("Java method return value = " + javaMethodReturnValue);
			System.out.println("new Java Object  = " + getJavaObject(newFUmlObject));
			System.out.println("new fUML Object_ = " + getFUmlObject(javaObject));

			// Step 5.1: Plugging output parameter to Placeholder Activity (if not null)
			if (outputParameterValue.parameter != null) {
				for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {
					if (extensionalValue.hashCode() == ((ActivityEntryEventImpl) event).getActivityExecutionID()) {
						ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
						activityExecution.setParameterValue(outputParameterValue);
						return; // exit
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error occured while trying to call operation on Java object. " + e);
		}

	}// callOperation

	/**
	 * Obtains an ordered map of {@link Class} (i.e. Java parameter type) and its corresponding {@link Object} (i.e. Java parameter value)
	 * 
	 * @param fUmlParameterWithValueMap a {@link LinkedHashMap} (ordered) on the {@link Parameter} and its corresponding {@link ParameterValue}
	 * @return a {@link LinkedHashMap} (ordered) on the {@link Object} (i.e. Java parameter value) and its {@link Class} (i.e. Java parameter type) found in {@code fUmlParameterWithValueMap}
	 */
	private LinkedHashMap<Object, Class> obtainJavaInputParameters(LinkedHashMap<Parameter, ParameterValue> fUmlParameterWithValueMap) {
		LinkedHashMap<Object, Class> javaParameterWithValueMap = new LinkedHashMap<Object, Class>();
		
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
				}
			}// for loop (fUmlParameterWithValueMap)
		}// if (fUmlParameterWithValueMap not empty)
		
		return javaParameterWithValueMap;
	}
	
	
	/**
	 * Obtains an ordered map of {@link Parameter} and its corresponding {@link ParameterValue}
	 * 
	 * @param parameterList {@link ParameterList} from which to to obtain {@link Parameter}s
	 * @param extensionalValueList {@link ExtensionalValueList} from the Locus from which to obtain the {@link ParameterValue} for a corresponding {@link Parameter} in {@code parameterList}
	 * @return a {@link LinkedHashMap} (ordered) on the {@link Parameter} and its corresponding {@link ParameterValue} found in the {@code parameterList}
	 */
	private LinkedHashMap<Parameter, ParameterValue> obtainfUmlInputParameters(ParameterList parameterList, ExtensionalValueList extensionalValueList) {
		
		LinkedHashMap<Parameter, ParameterValue> parameterWithParameterValueMap = new LinkedHashMap<Parameter, ParameterValue>();
		
		// Go through the list of Parameters
		for (Parameter parameter : parameterList) {
			if (parameter.name != null && parameter.type != null) {
				if (parameter.type.name.toString().equals("boolean") || parameter.type.name.toString().equals("int")
						|| parameter.type.name.toString().equals("String")) {
					
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
			}
		}// for loop (parameterList)
		
		return parameterWithParameterValueMap;
	}
	

	private Object_ obtainFUmlObjectFromActivityExecution(Event event) throws Exception {
		if (EventHelper.isExternalActivityEntryEvent(event)) {

			for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {
				if (extensionalValue.hashCode() == ((ActivityEntryEventImpl) event).getActivityExecutionID()) {
					ActivityExecution activityExecution = (ActivityExecution) extensionalValue;
					Object_ fUmlObject = activityExecution.context;
					return fUmlObject;
				}
			}

		}// event instance of ActivityEntryEventImpl

		throw new Exception("Failed to obtain the fUML Object_ from ActivityExecution.");
	}// obtainFUmlObjectFromActivityExecution

	/**
	 * Assigns a given {@link Object_} as an {@link ObjectToken} to the
	 * {@link CreateObjectAction}'s result {@link OutputPin}
	 * 
	 * @param createObjectActionExitEvent
	 *            must be an instance of {@link ActivityNodeExitEventImpl} and
	 *            it's node must be an instance of {@link CreateObjectAction}
	 * @param fUmlObject
	 *            fUML {@link Object_} to set at the {@link ObjectToken}
	 */
	private void assignObject_ToCreateObjectActionOutputPin(Event createObjectActionExitEvent, Object_ fUmlObject) throws Exception {
		CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(createObjectActionExitEvent);
		/*
		 * Obtained the CreateObjectAction, next: navigate to the correct node
		 * that is equal to the obtained CreateObjectAction
		 */

		if (createObjectActionExitEvent instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent event = (ActivityNodeExitEvent) createObjectActionExitEvent;

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
					System.out.println("Successfully assigned Object_ Reference to CreateObjectAction's OutputPin.");
					return; // exit

				}

			}// ExtensionalValue loop

		} else {
			throw new Exception("Error occured while trying to assign Object_ to CreateObjectAction's result OutputPin.");
		}

	}// assignObject_ToCreateObjectActionOutputPin

	/**
	 * Assigns the CallOperationAction's InputPin based on the
	 * CreateObjectAction's OutputPin ObjectFlow
	 * 
	 * @param createObjectActionExitEvent
	 *            must be an instance of {@link ActivityNodeExitEventImpl} and
	 *            it's node must be an instance of {@link CreateObjectAction}
	 */
	private void assignTargetInputPinToCallOperationAction(Event createObjectActionExitEvent) throws Exception {
		CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(createObjectActionExitEvent);

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
									System.out.println("Successfully set CallOperationAction's target InputPin.");

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

}// IntegrationLayerImpl
