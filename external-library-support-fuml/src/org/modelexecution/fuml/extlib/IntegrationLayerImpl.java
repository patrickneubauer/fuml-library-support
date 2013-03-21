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

import fUML.Semantics.Actions.BasicActions.OutputPinActivation;
import fUML.Semantics.Actions.BasicActions.PinActivation;
import fUML.Semantics.Actions.IntermediateActions.CreateObjectActionActivation;
import fUML.Semantics.Activities.IntermediateActivities.ActivityExecution;
import fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation;
import fUML.Semantics.Activities.IntermediateActivities.ObjectToken;
import fUML.Semantics.Activities.IntermediateActivities.Token;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValueList;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

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
		System.out.println(event);
		if (EventHelper.isExternalCreateObjectActionEntry(event)) {
			// store current ExtensionalValueList to compare later on
			previousExtensionalValueList = (ExtensionalValueList) executionContext.getExtensionalValues().clone();

		} else if (EventHelper.isExternalCreateObjectActionExit(event)) {
			Object_ fUmlPlaceholderObject = obtainFUmlPlaceholderObject();
			Object javaObject = obtainJavaObject(event);

			Object_Builder object_Builder = new Object_Builder(event, javaObject);
			Object_ fUmlObject = object_Builder.getObject_();

			System.out.println("fUML Placeholder Object = " + fUmlPlaceholderObject);

			replaceLocusObject(fUmlPlaceholderObject, fUmlObject);
			assignFUmlObjectToToken(event, fUmlObject);
			addObjects(fUmlObject, javaObject);

		} else if (EventHelper.isExternalCallOperationAction(event)) {
			// TODO do something with this CallOperationAction
		}
	}// notify

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
			String jarPath = EventHelper.obtainClassJarPath(event);
			String className = EventHelper.obtainClassName(event);
			String classNamespace = EventHelper.obtainClassNamespace(event);

			dynamicClassLoader = new DynamicClassLoader(jarPath);
			ClassLoader classLoader = dynamicClassLoader.getClassLoader();

			Class<?> clazz = classLoader.loadClass(classNamespace + "." + className);
			javaObject = clazz.newInstance();

		} catch (Exception e) {
			System.out.println("Error occured while trying to obtain the Java object. " + e);
		}

		return javaObject;
	}// obtainJavaObject

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
	private void assignFUmlObjectToToken(Event event, Object_ fUmlObject) {
		// Obtain the CreateObjectAction
		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
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
													 * fUML Object_ as value of
													 * ObjectToken
													 */
													ObjectToken objectToken = (ObjectToken) token;

													objectToken.value = fUmlObject;
													return; // exit

												}

											}//Token loop

										}

									}//PinActivation loop

								}

							}

						}//ActivityNodeActivation loop

					}

				}//ExtensionalValue loop

			}//activityNode instanceof CreateObjectAction
			
		}//event instanceof ActivityNodeExitEventImpl

	}//assignFUmlObjectToToken

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
