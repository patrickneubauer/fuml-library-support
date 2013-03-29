/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.collections.CollectionUtils;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Semantics.Actions.BasicActions.CallOperationActionActivation;
import fUML.Semantics.Actions.BasicActions.InputPinActivation;
import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Actions.IntermediateActions.CreateObjectActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.BasicActions.InputPin;
import fUML.Syntax.Actions.BasicActions.OutputPin;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
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
		if (EventHelper.isExternalCreateObjectActionEntry(event)) {
			System.out.println("[External CreateObjectAction ActivityNodeEntryEvent]");
			// store current ExtensionalValueList for later comparison
			previousExtensionalValueList = (ExtensionalValueList) executionContext.getExtensionalValues().clone();

		} else if (EventHelper.isExternalCreateObjectActionExit(event)) {
			System.out.println("[External CreateObjectAction ActivityNodeExitEvent]");

			Object_ fUmlPlaceholderObject = obtainFUmlPlaceholderObject();
			Object javaObject = obtainJavaObject(event);

			Object_Transformer object_Transformer = new Object_Transformer(event, fUmlPlaceholderObject, javaObject);
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

			Object returnValue = callOperation(event);

			/*
			 * TODO Assign the returnValue to the OutputPin of the
			 * CallOperationAction (?)
			 */

		} else if (EventHelper.isExternalCallOperationActionExit(event)) {
			System.out.println("[External CallOperationAction ActivityNodeExitEvent]");

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
			if (extensionalValues.get(i).equals(fUmlPlaceholderObject)) {
				extensionalValues.set(i, fUmlObject);
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

			String jarPath = ActionHelper.obtainClassJarPath(createObjectAction);
			String classNamespaceAndName = ActionHelper.obtainClassNamespaceAndName(createObjectAction);

			dynamicClassLoader = new DynamicClassLoader(jarPath);
			ClassLoader classLoader = dynamicClassLoader.getClassLoader();

			Class<?> clazz = classLoader.loadClass(classNamespaceAndName);
			javaObject = clazz.newInstance();

		} catch (Exception e) {
			System.out.println("Error occured while trying to obtain the Java object. " + e);
		}

		return javaObject;
	}// obtainJavaObject

	private Object callOperation(Event event) {
		// Obtain the CallOperationAction for later comparison
		CallOperationAction callOperationAction = EventHelper.getExternalCallOperationAction(event);

		try {

			// Obtain fUML Object_ from InputPinActivation in
			// CallOperationActionActivation
			Object_ fUmlObject = obtainFUmlObjectFromCallOperationActionEntryEvent(event, callOperationAction);
			System.out.println("[CallOperationAction] fUmlObject = " + fUmlObject);

			// Obtain corresponding Java Object
			Object javaObject = fUmlJavaMap.get(fUmlObject);
			System.out.println("[CallOperationAction] javaObject = " + javaObject);

			String methodName = "";
			String classNamespaceAndName = "";

			methodName = callOperationAction.operation.name;
			classNamespaceAndName = ActionHelper.obtainClassNamespaceAndName(callOperationAction);
			
			

			/*
			 * TODO - Call the method on the Java Object - Update the fUML
			 * Object in the Locus of the ExecutionContext (?) - Update both
			 * objects in the HashMap - Return the method return value to
			 * notify()
			 */

		} catch (Exception e) {
			System.out.println("Error occured while trying to call operation on Java object. " + e);
		}

		return null;
	}// callOperation

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
					System.out.println("Successfully assigned Object_ Reference to CreateObjectActionAction's OutputPin.");
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
						 * Next: Look for CallOperationAction InputPin's that have an
						 * incoming ObjectFlow that matches with the
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
