/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.util.HashMap;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * Implementation of the {@link IntegrationLayer}
 * 
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerImpl implements IntegrationLayer {

	private final ExecutionContext executionContext = ExecutionContext
			.getInstance();

	private DynamicClassLoader dynamicClassLoader;

	private HashMap<Object_, Object> fUmlJavaMap = new HashMap<Object_, Object>();
	private HashMap<Object, Object_> javaFUmlMap = new HashMap<Object, Object_>();

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
		if (isExternalCreateObjectAction(event)) {
			Object javaObject = obtainJavaObject(event);
			Object_ fUmlObject = obtainFUmlObject(javaObject);
			
			replaceLocusObject(fUmlObject);
			addObjects(fUmlObject, javaObject);

		} else if (isExternalCallOperationAction(event)) {
			// TODO do something with this CallOperationAction
		}
	}

	private void replaceLocusObject(Object_ fUmlObject) {
		/*
		 * TODO 1) Remove corresponding Object_ from Locus 2) Add the newly
		 * created Object_ to the Locus
		 * 
		 * The Locus can be obtained from the ExecutionContext by calling
		 * getLocus()
		 */
		
	}

	private Object obtainJavaObject(Event event) {
		/*
		 * TODO Steps to consider: 1) Obtain the path to the external JAR file
		 * out of the Comment body of the Activity 2) Use the DynamicClassLoader
		 * to instantiate a Java object of the type found in the Activity's
		 * reference (a String called the same way as the Class found in the
		 * Jar) 3) return the Object instance
		 */

		return null;
	}

	private Object_ obtainFUmlObject(Object javaObject) {
		/*
		 * TODO use the given Java object to create a fUML object by setting the
		 * Object_'s type and featureValues
		 */

		return null;
	}

	public IntegrationLayerImpl() throws Exception {
		throw new Exception(
				"DEFAULT CONSTRUCTOR NOT ALLOWED, use the dedicated constructor.");
	}

	public IntegrationLayerImpl(String umlActivityDiagramFilePath,
			String... furtherPaths) {
		/*
		 * TODO Obtain a list UML Activities (org.eclipse.uml2.uml.Activity) and
		 * their corresponding fUML Activities
		 * (fUML.Syntax.Activities.IntermediateActivities.Activity).
		 * 
		 * In order to do this, every UML Activity has to be converted into a
		 * corresponding fUML Activity (using
		 * org.modelexecution.fuml.convert.uml2.UML2Converter.convert(...)).
		 */

		initialize();
		
	}
	
	private void initialize() {
		// IL registers itself at the ExecutionContext instance
		executionContext.addEventListener(this);
	}

	/**
	 * Checks if the given {@code event} is of type {@link CreateObjectAction}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link CreateObjectAction}
	 *         and references an external library, false otherwise
	 */
	private boolean isExternalCreateObjectAction(Event event) {
		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event)
					.getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				// TODO add another check if it has a Comment body containing
				// "@external"
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the given {@code event} is of type {@link CallOperationAction}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link CallOperationAction}
	 *         and references an external library, false otherwise
	 */
	private boolean isExternalCallOperationAction(Event event) {
		// TODO implementation might be quite similar to
		// isExternalCreateObjectAction(Event event)
		return false;
	}

	/*
	 * Obtains the referenced JAR path from the {@code event}
	 * 
	 * @param event the {@link Event} to get the JAR path from
	 * 
	 * @return {@link String} representing the file path of the JAR found in the
	 * given {@code event}
	 */
	private String obtainJarPath(Event event) {
		// TODO obtain JAR file path from Event
		return null;
	}

	private void addObjects(Object_ fUmlObject, Object javaObject) {
		javaFUmlMap.put(javaObject, fUmlObject);
		fUmlJavaMap.put(fUmlObject, javaObject);
	}

	@Override
	public Object_ getFUmlObject(Object javaObject) {
		return javaFUmlMap.get(javaObject);
	}

	@Override
	public Object getJavaObject(Object_ fUmlObject) {
		return fUmlJavaMap.get(fUmlObject);
	}

	@Override
	public ExecutionContext getExecutionContext() {
		return this.executionContext;
	}

}
