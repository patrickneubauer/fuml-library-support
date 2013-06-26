/**
 * 
 */
package org.modelexecution.fuml.extlib;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.internal.impl.AddStructuralFeatureValueActionImpl;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEventType;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Comment;
import fUML.Syntax.Classes.Kernel.CommentList;
import fUML.Syntax.Classes.Kernel.Property;

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
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNode activityNode = ((ActivityNodeEntryEvent) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				
				if (createObjectAction.classifier != null) {
					CommentList commentList = createObjectAction.classifier.ownedComment;
					for (Comment comment : commentList) {
						if (comment.body.startsWith("@external")) {
							return true;
						}
					}
				} else {
					Debug.out(EventHelper.class, "[WARNING] No Classifier specified in " + createObjectAction);
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
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNode activityNode = ((ActivityNodeExitEvent) event).getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				
				if (createObjectAction.classifier != null) {
					CommentList commentList = createObjectAction.classifier.ownedComment;
					for (Comment comment : commentList) {
						if (comment.body.startsWith("@external")) {
							return true;
						}
					}
				} else {
					Debug.out(EventHelper.class, "[WARNING] No Classifier specified in " + createObjectAction);
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
		
		if (isExternalCreateObjectActionEntry(event)) {
			
			CreateObjectAction createObjectAction = (CreateObjectAction) ((ActivityNodeEntryEvent) event).getNode();
			return createObjectAction;
			
		} else if (isExternalCreateObjectActionExit(event)) {
			
			CreateObjectAction createObjectAction = (CreateObjectAction) ((ActivityNodeExitEvent) event).getNode();
			return createObjectAction;
			
		}

		return null; // no external CreateObjectAction found in event
	}//getExternalCreateObjectAction
	
	/**
	 * Get the {@link AddStructuralFeatureValueAction} out of the {@code event}
	 * 
	 * @param event
	 *            the {@link Event} where to get the {@link AddStructuralFeatureValueAction}
	 *            from
	 * @return instance of the {@link AddStructuralFeatureValueAction} if it exists in the
	 *         {@code event}, null otherwise
	 */
	public static AddStructuralFeatureValueAction getExternalAddStructuralFeatureValueAction(Event event) {
		
		if (isExternalAddStructuralFeatureValueActionEntryEvent(event)) {
			
			AddStructuralFeatureValueAction addStructuralFeatureValueAction = (AddStructuralFeatureValueAction) ((ActivityNodeEntryEvent) event).getNode();
			return addStructuralFeatureValueAction;
			
		} else if (isExternalAddStructuralFeatureValueActionExitEvent(event)) {
			
			AddStructuralFeatureValueAction addStructuralFeatureValueAction = (AddStructuralFeatureValueAction) ((ActivityNodeExitEvent) event).getNode();
			return addStructuralFeatureValueAction;
			
		}
		
		return null; // no Extern		
	}//getExternalAddStructuralFeatureValueAction

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
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNode activityNode = ((ActivityNodeEntryEvent) event).getNode();
			if (activityNode instanceof CallOperationAction) {
				CallOperationAction callOperationAction = (CallOperationAction) activityNode;

				if (callOperationAction.operation != null) {
					if (callOperationAction.operation.owner != null) {
						
						if (callOperationAction.operation.owner != null) {
							for (Comment comment : callOperationAction.operation.owner.ownedComment) {
								if (comment.body.startsWith("@external")) {
									return true;
								}
							}
						} else {
							Debug.out(EventHelper.class, "[WARNING] No Operation Owner specified in " + callOperationAction);
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
		
		if (isExternalCallOperationActionEntry(event)) {
			
			CallOperationAction callOperationAction = (CallOperationAction) ((ActivityNodeEntryEvent) event).getNode();
			return callOperationAction;
			
		} else if (isExternalCallOperationActionExit(event)) {
			
			CallOperationAction callOperationAction = (CallOperationAction) ((ActivityNodeExitEvent) event).getNode();
			return callOperationAction;
			
		}

		return null; // no external CallOperationAction found in event
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
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNode activityNode = ((ActivityNodeExitEvent) event).getNode();
			if (activityNode instanceof CallOperationAction) {
				CallOperationAction callOperationAction = (CallOperationAction) activityNode;

				if (callOperationAction.operation != null) {
					if (callOperationAction.operation.owner != null) {
						for (Comment comment : callOperationAction.operation.owner.ownedComment) {
							if (comment.body.startsWith("@external")) {
								return true;
							}
						}
					} else {
						Debug.out(EventHelper.class, "[WARNING] No Operation Owner specified in " + callOperationAction);
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
		if (event instanceof ActivityEntryEvent) {
			Activity activity = ((ActivityEntryEvent) event).getActivity();
			
			for (Comment ownedComment : activity.ownedComment) {
				if (ownedComment.body.startsWith("@external")) {
					return true;
				}
			}
			
		}// event instanceof ActivityEntryEvent

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
		if (event instanceof ActivityExitEvent) {
			Activity activity = ((ActivityExitEvent) event).getActivity();
			
			for (Comment ownedComment : activity.ownedComment) {
				if (ownedComment.body.startsWith("@external")) {
					return true;
				}
			}
			
		}// event instanceof ActivityExitEvent

		return false;
	}//isExternalActivityExitEvent
	
	/**
	 * Checks if the given {@code event} is of type {@link ActivityNodeEntryEvent}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link ActivityNodeEntryEvent}
	 *         and references an external library, false otherwise
	 */
	public static boolean isExternalActivityNodeEntryEvent(Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNode activityNode = ((ActivityNodeEntryEvent) event).getNode();
			
			for (Comment ownedComment : activityNode.ownedComment) {
				if (ownedComment.body.startsWith("@external")) {
					return true;
				}
			}
			
		}// event instanceof ActivityNodeEntryEvent

		return false;
	}//isExternalActivityNodeEntryEvent
	
	/**
	 * Checks if the given {@code event} is of type {@link ActivityNodeExitEvent}
	 * and references an external library
	 * 
	 * @param event
	 *            the {@link Event} to check
	 * @return true if the {@link event} is of type {@link ActivityNodeExitEvent}
	 *         and references an external library, false otherwise
	 */
	public static boolean isExternalActivityNodeExitEvent(Event event) {
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNode activityNode = ((ActivityNodeExitEvent) event).getNode();
			
			for (Comment ownedComment : activityNode.ownedComment) {
				if (ownedComment.body.startsWith("@external")) {
					return true;
				}
			}
			
		}// event instanceof ActivityNodeExitEvent

		return false;
	}//isExternalActivityNodeExitEvent
	
	/**
	 * Checks if the given {@code event} is of type {@link ActivityNodeEntryEvent} with a {@link StructuralFeatureValueAction} node
	 * that has a {@link StructuralFeature} of instance of {@link Property} referencing an external library
	 * 
	 * @param event the {@link Event} to check
	 * @return true if the {@link event} is of type {@link AddStructuralFeatureValueAction}
	 * 		   and has a {@link StructuralFeature} of instance of {@link Property} referencing an external library
	 */
	public static boolean isExternalAddStructuralFeatureValueActionEntryEvent(Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
			
			if (activityNodeEntryEvent.getNode() instanceof AddStructuralFeatureValueAction) {
				AddStructuralFeatureValueAction addStructuralFeatureValueAction = (AddStructuralFeatureValueAction) activityNodeEntryEvent.getNode();
			
				if (addStructuralFeatureValueAction.structuralFeature instanceof Property) {
					Property property = (Property) addStructuralFeatureValueAction.structuralFeature;
					Class_ propertyClass = property.class_;
					
					for (Comment ownedComment : propertyClass.ownedComment) {
						if (ownedComment.body.startsWith("@external")) {
							return true;
						}
					}
					
				}// addStructuralFeatureValueAction.structuralFeature instanceof Property
				
			}// activityNodeEntryEvent.node instanceof AddStructuralFeatureValueAction
			
		}// event instanceof ActivityNodeEntryEvent
		
		return false;
	}//isExternalAddStructuralFeatureValueActionEntryEvent
	
	/**
	 * Checks if the given {@code event} is of type {@link ActivityNodeExitEvent} with a {@link StructuralFeatureValueAction} node
	 * that has a {@link StructuralFeature} of instance of {@link Property} referencing an external library
	 * 
	 * @param event the {@link Event} to check
	 * @return true if the {@link event} is of type {@link AddStructuralFeatureValueAction}
	 * 		   and has a {@link StructuralFeature} of instance of {@link Property} referencing an external library
	 */
	public static boolean isExternalAddStructuralFeatureValueActionExitEvent(Event event) {
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent activityNodeEntryEvent = (ActivityNodeExitEvent) event;
			
			if (activityNodeEntryEvent.getNode() instanceof AddStructuralFeatureValueAction) {
				AddStructuralFeatureValueAction addStructuralFeatureValueAction = (AddStructuralFeatureValueAction) activityNodeEntryEvent.getNode();
			
				if (addStructuralFeatureValueAction.structuralFeature instanceof Property) {
					Property property = (Property) addStructuralFeatureValueAction.structuralFeature;
					Class_ propertyClass = property.class_;
					
					for (Comment ownedComment : propertyClass.ownedComment) {
						if (ownedComment.body.startsWith("@external")) {
							return true;
						}
					}
					
				}// addStructuralFeatureValueAction.structuralFeature instanceof Property
				
			}// activityNodeEntryEvent.node instanceof AddStructuralFeatureValueAction
			
		}// event instanceof ActivityNodeExitEvent
		
		return false;
	}//isExternalAddStructuralFeatureValueActionEntryEvent
	
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
		
		if (isExternalActivityEntryEvent(event)) {
			
			Activity activity = ((ActivityEntryEvent) event).getActivity();
			return activity;
			
		} else if (isExternalActivityExitEvent(event)) {
			
			Activity activity = ((ActivityExitEvent) event).getActivity();
			return activity;
			
		}

		return null;
	}//getExternalActivity

	
	
	
}
