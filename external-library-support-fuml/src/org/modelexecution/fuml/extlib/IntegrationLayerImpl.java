/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Semantics.Actions.BasicActions.CallOperationActionActivation;
import fUML.Semantics.Actions.BasicActions.InputPinActivation;
import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Actions.BasicActions.PinActivation;
import fUML.Semantics.Actions.IntermediateActions.CreateObjectActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.ActivityEdge;
import fUML.Syntax.Activities.IntermediateActivities.ObjectFlow;

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
		System.out.println("EVENT = " + event);
		if (EventHelper.isExternalCreateObjectActionEntry(event)) {
			// store current ExtensionalValueList for later comparison
			previousExtensionalValueList = (ExtensionalValueList) executionContext.getExtensionalValues().clone();

		} else if (EventHelper.isExternalCreateObjectActionExit(event)) {
			Object_ fUmlPlaceholderObject = obtainFUmlPlaceholderObject();
			Object javaObject = obtainJavaObject(event);

			Object_Builder object_Builder = new Object_Builder(event, javaObject);
			Object_ fUmlObject = object_Builder.getObject_();

			System.out.println("[CreateObjectAction] Java Object = " + javaObject);
			System.out.println("[CreateObjectAction] fUML Placeholder Object = " + fUmlPlaceholderObject);
			System.out.println("[CreateObjectAction] fUML Build Object = " + fUmlObject);

			replaceLocusObject(fUmlPlaceholderObject, fUmlObject);
			assignObject_ToCreateObjectActionOutputPin(event, fUmlObject);
			assignObject_ToCallOperationActionInputPin(event, fUmlObject);

			addObjects(fUmlObject, javaObject);

		} else if (EventHelper.isExternalCallOperationActionEntry(event)) {
			Object returnValue = callOperation(event);
			
			/*
			 * TODO
			 * Assign the returnValue to the OutputPin of the CallOperationAction (?)
			 * 
			 */
			
			System.out.println("[CallOperationAction] END");

		} else if (EventHelper.isExternalCallOperationActionExit(event)) {
			System.out.println("isExternalCallOperationActionExit is TRUE");

		}
	}// notify

	private Object_ obtainFUmlObjectFromCallOperationActionInputPinActivation(CallOperationAction callOperationAction) {

		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {

			if (extensionalValue instanceof ActivityExecution) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;

				for (ActivityNodeActivation activityNodeActivation : activityExecution.activationGroup.nodeActivations) {

					if (activityNodeActivation instanceof CallOperationActionActivation) {
						CallOperationActionActivation callOperationActionActivation = (CallOperationActionActivation) activityNodeActivation;

						if (activityNodeActivation.node.equals(callOperationAction)) {
							/*
							 * Arrived at the correct
							 * CallOperationActionActivation, next: obtain InputPinActivation
							 */
							
							for (PinActivation pinActivation : callOperationActionActivation.pinActivations) {

								if (pinActivation instanceof InputPinActivation) {

									InputPinActivation inputPinActivation = (InputPinActivation) pinActivation;

									for (Token token : inputPinActivation.heldTokens) {
										if (token.holder.equals(inputPinActivation)) {
											/*
											 * Arrived at the correct
											 * ObjectToken, next: return
											 * referring fUML Object_
											 */
											ObjectToken objectToken = (ObjectToken) token;
											Reference reference = (Reference) objectToken.value;

											return reference.referent;

										}

									}// Token loop

								}

							}
							
						}
						
					}

				}// ActivityNodeActivation loop

			}

		}// ExtensionalValue loop

		return null;
	}

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
	 *            {@link Object_} to be removed from the {@link Locus}
	 * @param fUmlObject
	 *            {@link Object_} to be added to the {@link Locus}
	 */
	private void replaceLocusObject(Object_ fUmlPlaceholderObject, Object_ fUmlObject) {
		executionContext.getLocus().remove(fUmlPlaceholderObject);
		executionContext.getLocus().add(fUmlObject);
		System.out.println("Replaced Locus Object");
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
			
			String jarPath = ActionHelper.obtainClassJarPath(createObjectAction);
			String className = ActionHelper.obtainClassName(createObjectAction);
			String classNamespace = ActionHelper.obtainClassNamespace(createObjectAction);

			dynamicClassLoader = new DynamicClassLoader(jarPath);
			ClassLoader classLoader = dynamicClassLoader.getClassLoader();

			Class<?> clazz = classLoader.loadClass(classNamespace + "." + className);
			javaObject = clazz.newInstance();

		} catch (Exception e) {
			System.out.println("Error occured while trying to obtain the Java object. " + e);
		}

		return javaObject;
	}// obtainJavaObject
	
	private Object callOperation(Event event) {
		// Obtain the CallOperationAction for later comparison
		CallOperationAction callOperationAction = EventHelper.getExternalCallOperationAction(event);

		// Obtain fUML Object_ from InputPinActivation in CallOperationActionActivation
		Object_ fUmlObject = obtainFUmlObjectFromCallOperationActionInputPinActivation(callOperationAction);
		System.out.println("[CallOperationAction] fUmlObject = " + fUmlObject);
		
		// Obtain corresponding Java Object
		Object javaObject = fUmlJavaMap.get(fUmlObject);
		System.out.println("[CallOperationAction] javaObject = " + javaObject);
		
		String methodName = "";
		String className = "";
		String classNamespace = "";
		try {
			methodName = callOperationAction.operation.name;
			className = ActionHelper.obtainClassName(callOperationAction);
			classNamespace = ActionHelper.obtainClassNamespace(callOperationAction);
			
			/* TODO 
			 * - Call the method on the Java Object
			 * - Update the fUML Object in the Locus of the ExecutionContext (?)
			 * - Update both objects in the HashMap
			 * - Return the method return value to notify()
			 */
			
			
		} catch (Exception e) {
			System.out.println("Error occured while trying to call operation on Java object. " + e);
		}
		
		return null;
	}//callOperation

	/**
	 * Assigns a given {@link Object_} to the {@link ObjectToken} at the list of
	 * held tokens
	 * 
	 * @param event
	 *            must be an instance of {@link ActivityNodeExitEventImpl} and
	 *            it's node must be an instance of {@link CreateObjectAction}
	 * @param fUmlObject
	 *            fUML {@link Object_} to set at the {@link ObjectToken}
	 */
	private void assignObject_ToCreateObjectActionOutputPin(Event event, Object_ fUmlObject) {
		CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(event);
		/*
		 * Obtained the CreateObjectAction, next: navigate to the
		 * correct node that is equal to the obtained CreateObjectAction
		 */

		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {

			if (extensionalValue instanceof ActivityExecution) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;

				for (ActivityNodeActivation activityNodeActivation : activityExecution.activationGroup.nodeActivations) {

					if (activityNodeActivation instanceof CreateObjectActionActivation) {
						CreateObjectActionActivation createObjectActionActivation = (CreateObjectActionActivation) activityNodeActivation;

						if (createObjectActionActivation.node.equals(createObjectAction)) {
							/*
							 * Arrived at the correct node, next:
							 * navigate to OutputPinActivation
							 */

							for (PinActivation pinActivation : createObjectActionActivation.pinActivations) {

								if (pinActivation instanceof OutputPinActivation) {
									/*
									 * Arrived at the
									 * OutputPinActivation, next:
									 * navigate to ObjectToken
									 */
									OutputPinActivation outputPinActivation = (OutputPinActivation) pinActivation;

									for (Token token : outputPinActivation.heldTokens) {
										if (token.holder.equals(outputPinActivation)) {
											/*
											 * Arrived at the correct
											 * ObjectToken, next: set
											 * fUML Object_ as Reference
											 * of ObjectToken
											 */
											ObjectToken objectToken = (ObjectToken) token;

											Reference reference = new Reference();
											reference.referent = fUmlObject;

											objectToken.value = reference;
											return; // exit

										}

									}// Token loop

								}

							}// PinActivation loop

						}

					}

				}// ActivityNodeActivation loop

			}

		}// ExtensionalValue loop

	}// assignObject_ToCreateObjectActionOutputPin

	/**
	 * Assigns a given {@link Object_} to the {@link ObjectToken} at the list of
	 * held tokens
	 * 
	 * @param event
	 *            must be an instance of {@link ActivityNodeExitEventImpl} and
	 *            it's node must be an instance of {@link CallOperationAction}
	 * @param fUmlObject
	 *            fUML {@link Object_} to set at the {@link ObjectToken}
	 */
	private void assignObject_ToCallOperationActionInputPin(Event event, Object_ fUmlObject) {
		CreateObjectAction createObjectAction = EventHelper.getExternalCreateObjectAction(event);
		ObjectFlow createObjectActionOutgointObjectFlow = null;

		/*
		 * Obtain CreateObjectAction's OutputPin's ObjectFlow for later
		 * comparison
		 */
		for (OutputPin outputPin : createObjectAction.output) {

			for (ActivityEdge activityEdge : outputPin.outgoing) {

				if (activityEdge instanceof ObjectFlow) {
					createObjectActionOutgointObjectFlow = (ObjectFlow) activityEdge;
				}

			}

		}

		/*
		 * Obtained the CreateObjectAction, next: navigate to the
		 * correct node that is equal to the obtained
		 * CallOperationAction
		 */

		for (ExtensionalValue extensionalValue : executionContext.getLocus().extensionalValues) {

			if (extensionalValue instanceof ActivityExecution) {
				ActivityExecution activityExecution = (ActivityExecution) extensionalValue;

				for (ActivityNodeActivation activityNodeActivation : activityExecution.activationGroup.nodeActivations) {

					if (activityNodeActivation instanceof CallOperationActionActivation) {
						CallOperationActionActivation callOperationActionActivation = (CallOperationActionActivation) activityNodeActivation;

						// NEXT: obtain CallOperation's InputPinAction's
						// ActivityEdgeInstance's edge and compare it
						// with CreateObjectAction OutputPin ObjectFlow
						for (PinActivation pinActivation : callOperationActionActivation.pinActivations) {

							if (pinActivation instanceof InputPinActivation) {

								for (ActivityEdgeInstance activityEdgeInstance : pinActivation.incomingEdges) {

									if (activityEdgeInstance.edge.equals(createObjectActionOutgointObjectFlow)) {

										/*
										 * CreateObjectAction
										 * <OutputPin> equals
										 * CallOperationAction
										 * <InputPin>
										 */
										/*
										 * Next: create new ObjectToken
										 * at InputPinActivation
										 */
										InputPinActivation inputPinActivation = (InputPinActivation) pinActivation;

										for (Token token : inputPinActivation.heldTokens) {
											if (token.holder.equals(inputPinActivation)) {
												/*
												 * Arrived at the
												 * correct ObjectToken,
												 * next: set fUML
												 * Object_ as Reference
												 * of ObjectToken
												 */
												ObjectToken objectToken = (ObjectToken) token;

												Reference reference = new Reference();
												reference.referent = fUmlObject;

												objectToken.value = reference;
												
												// Add the InputPin to target of CallOperationAction
												CallOperationAction callOperationAction = (CallOperationAction) callOperationActionActivation.node;
												callOperationAction.target = (InputPin) inputPinActivation.node;
												
												return; // exit

											}

										}// Token loop

									}

								}

							}

						}

					}

				}// ActivityNodeActivation loop

			}

		}// ExtensionalValue loop

	}// assignObject_ToCallOperationActionInputPin

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
