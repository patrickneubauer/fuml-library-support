/**
 * 
 */
package org.modelexecution.fuml.extlib;

import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
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
	}//isExternalCreateObjectActionExit

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
	}//isExternalCreateObjectActionExit

	/**
	 * Get the {@link CreateObjectAction} out of the {@code event}
	 * 
	 * @param event
	 *            the {@link Event} where to get the {@link CreateObjectAction}
	 *            from
	 * @return instance of the {@link CreateObjectAction} if it exists in the
	 *         {@code event}, null otherwise
	 */
	public static CreateObjectAction getExternalCreateObjectAction(Event event) {

		if (event instanceof ActivityNodeEntryEventImpl) {
			ActivityNode activityNode = ((ActivityNodeEntryEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				CommentList commentList = createObjectAction.classifier.ownedComment;
				for (Comment comment : commentList) {
					if (comment.body.startsWith("@external")) {
						return createObjectAction;
					}
				}
			}// activityNode instanceof CreateObjectAction

		} else if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				CommentList commentList = createObjectAction.classifier.ownedComment;
				for (Comment comment : commentList) {
					if (comment.body.startsWith("@external")) {
						return createObjectAction;
					}
				}
			}// activityNode instanceof CreateObjectAction
		}

		return null; // no CreateObjectAction found in event
	}//getExternalCreateObjectAction

	/**
	 * Checks if the given {@code event} is of type {@link CallOperationAction}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link CallOperationAction}
	 *         and references an external library, false otherwise
	 */
	public static boolean isExternalCallOperationActionEntry(Event event) {
		if (event instanceof ActivityNodeEntryEventImpl) {
			ActivityNode activityNode = ((ActivityNodeEntryEventImpl) event).getNode();
			if (activityNode instanceof CallOperationAction) {
				CallOperationAction callOperationAction = (CallOperationAction) activityNode;

				if (callOperationAction.operation != null) {
					if (callOperationAction.operation.owner != null) {
						for (Comment comment : callOperationAction.operation.owner.ownedComment) {
							if (comment.body.startsWith("@external")) {
								return true;
							}
						}
					}
				}

			}// activityNode instanceof CallOperationAction
		}

		return false;
	}//isExternalCallOperationActionEntry

	/**
	 * Get the external {@link CallOperationAction} out of the {@code event}
	 * 
	 * @param event
	 *            the {@link Event} where to get the external {@link CallOperationAction}
	 *            from
	 * @return instance of the external {@link CallOperationAction} if it exists in the
	 *         {@code event}, null otherwise
	 */
	public static CallOperationAction getExternalCallOperationAction(Event event) {

		if (event instanceof ActivityNodeEntryEventImpl) {
			ActivityNode activityNode = ((ActivityNodeEntryEventImpl) event).getNode();
			if (activityNode instanceof CallOperationAction) {
				CallOperationAction callOperationAction = (CallOperationAction) activityNode;

				if (callOperationAction.operation != null) {
					if (callOperationAction.operation.owner != null) {
						for (Comment comment : callOperationAction.operation.owner.ownedComment) {
							if (comment.body.startsWith("@external")) {
								return callOperationAction;
							}
						}
					}
				}

			}// activityNode instanceof CallOperationAction

		} else if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CallOperationAction) {
				CallOperationAction callOperationAction = (CallOperationAction) activityNode;

				if (callOperationAction.operation != null) {
					if (callOperationAction.operation.owner != null) {
						for (Comment comment : callOperationAction.operation.owner.ownedComment) {
							if (comment.body.startsWith("@external")) {
								return callOperationAction;
							}
						}
					}
				}

			}// activityNode instanceof CallOperationAction
		}

		return null; // no CallOperationAction found in event
	}//getExternalCallOperationAction

	/**
	 * Checks if the given {@code event} is of type {@link CallOperationAction}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link CallOperationAction}
	 *         and references an external library, false otherwise
	 */
	public static boolean isExternalCallOperationActionExit(Event event) {
		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event).getNode();
			if (activityNode instanceof CallOperationAction) {
				CallOperationAction callOperationAction = (CallOperationAction) activityNode;

				if (callOperationAction.operation != null) {
					if (callOperationAction.operation.owner != null) {
						for (Comment comment : callOperationAction.operation.owner.ownedComment) {
							if (comment.body.startsWith("@external")) {
								return true;
							}
						}
					}
				}

			}// activityNode instanceof CallOperationAction
		}

		return false;
	}//isExternalCallOperationActionExit
	
	/**
	 * Checks if the given {@code event} is of type {@link ActivityEntryEvent}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link ActivityEntryEvent}
	 *         and references an external library, false otherwise
	 */
	public static boolean isExternalActivityEntryEvent(Event event) {
		if (event instanceof ActivityEntryEventImpl) {
			Activity activity = ((ActivityEntryEventImpl) event).getActivity();
			
			for (Comment ownedComment : activity.ownedComment) {
				if (ownedComment.body.equals("@external")) {
					return true;
				}
			}
			
		}// event instanceof ActivityEntryEventImpl

		return false;
	}//isExternalActivityEntryEvent
	
	/**
	 * Checks if the given {@code event} is of type {@link ActivityExitEvent}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link ActivityExitEvent}
	 *         and references an external library, false otherwise
	 */
	public static boolean isExternalActivityExitEvent(Event event) {
		if (event instanceof ActivityExitEventImpl) {
			Activity activity = ((ActivityExitEventImpl) event).getActivity();
			
			for (Comment ownedComment : activity.ownedComment) {
				if (ownedComment.body.equals("@external")) {
					return true;
				}
			}
			
		}// event instanceof ActivityExitEventImpl

		return false;
	}//isExternalActivityExitEvent
	
	/**
	 * Get the external {@link Activity} out of the {@code event}
	 * 
	 * @param event
	 *            the {@link Event} where to get the external {@link Activity}
	 *            from
	 * @return instance of the external {@link Activity} if it exists in the
	 *         {@code event}, null otherwise
	 */
	public static Activity getExternalActivity(Event event) {
		
		if (event instanceof ActivityEntryEventImpl) {
			Activity activity = ((ActivityEntryEventImpl) event).getActivity();
			
			for (Comment ownedComment : activity.ownedComment) {
				if (ownedComment.body.equals("@external")) {
					return activity;
				}
			}
			
		} else if (event instanceof ActivityExitEventImpl) {
			Activity activity = ((ActivityExitEventImpl) event).getActivity();
			
			for (Comment ownedComment : activity.ownedComment) {
				if (ownedComment.body.equals("@external")) {
					return activity;
				}
			}
			
		}

		return null;
	}//getExternalActivity
	
	
	
}
