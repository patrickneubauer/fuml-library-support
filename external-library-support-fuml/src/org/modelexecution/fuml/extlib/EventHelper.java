/**
 * 
 */
package org.modelexecution.fuml.extlib;

import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Comment;
import fUML.Syntax.Classes.Kernel.CommentList;

/**
 * Helper to handle instances of {@link Event}
 * 
 * @author Patrick Neubauer
 * 
 */
public class EventHelper {

	/**
	 * Checks if the given {@code event} represents an
	 * {@link ActivityNodeEntryEvent}, is of type {@link CreateObjectAction} and
	 * references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} represents an
	 *         {@link ActivityNodeEntryEvent}, is of type
	 *         {@link CreateObjectAction} and references an external library,
	 *         false otherwise
	 */
	public static boolean isExternalCreateObjectActionEntry(Event event) {
		if (event instanceof ActivityNodeEntryEventImpl) {
			ActivityNode activityNode = ((ActivityNodeEntryEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				CommentList commentList = createObjectAction.classifier.ownedComment;
				for (Comment comment : commentList) {
					if (comment.body.startsWith("@external")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the given {@code event} represents an
	 * {@link ActivityNodeExitEvent}, is of type {@link CreateObjectAction} and
	 * references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return
	 * @return true if the {@link event} represents an
	 *         {@link ActivityNodeExitEvent}, is of type
	 *         {@link CreateObjectAction} and references an external library,
	 *         false otherwise
	 */
	public static boolean isExternalCreateObjectActionExit(Event event) {
		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				CommentList commentList = createObjectAction.classifier.ownedComment;
				for (Comment comment : commentList) {
					if (comment.body.startsWith("@external")) {
						return true;
					}
				}
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
	public static boolean isExternalCallOperationAction(Event event) {
		// TODO check if {@code event} is an external {@link
		// CallOperationAction}
		// Hint: Implementation might be quite similar to
		// isExternalCreateObjectAction(Event event)
		return false;
	}

	/*
	 * Obtains the referenced JAR path from the {@code event}
	 * 
	 * @param event the {@link Event} to get the JAR path from
	 * 
	 * @return {@link String} representing the file path of the JAR found in the
	 * given {@code event} or throws an {@link Exception} otherwise
	 * 
	 * @throws Exception Whenever the JAR path could not be obtained
	 */
	public static String obtainClassJarPath(Event event) throws Exception {

		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				CommentList commentList = createObjectAction.classifier.ownedComment;
				for (Comment comment : commentList) {
					if (comment.body.startsWith("@external")) {
						return (String) comment.body.subSequence("@external=".length(), comment.body.length());
					}
				}
			}
		}

		throw new Exception("Error occured while trying to obtain the external JAR path of event " + event);

	}

	/**
	 * Obtains the Class name of the {@code event} to be instantiated
	 * 
	 * @param event
	 *            the {@link Event} to get the Class name from
	 * @return {@link String} representing the Class name or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	public static String obtainClassName(Event event) throws Exception {

		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				return createObjectAction.classifier.name;
			}
		}

		throw new Exception("Error occured while trying to obtain the Class name of event " + event);

	}

	/**
	 * Obtains the Class namespace of the {@code event} to be instantiated
	 * 
	 * @param event
	 *            the {@link Event} to get the Class namespace from
	 * @return {@link String} representing the Class namespace or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class namespace could not be obtained
	 */
	public static String obtainClassNamespace(Event event) throws Exception {

		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				return createObjectAction.classifier.namespace.name;
			}
		}

		throw new Exception("Error occured while trying to obtain the Class namespace of event " + event);

	}

}
