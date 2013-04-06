/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.modelexecution.fuml.extlib.EventHelper;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityExitEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeEntryEventImpl;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Comment;
import fUML.Syntax.Classes.Kernel.CommentList;
import fUML.Syntax.Classes.Kernel.Operation;

/**
 * Class that tests the {@link EventHelper}
 * 
 * @author Patrick Neubauer
 *
 */
public class EventHelperTest {

	@Test
	public void isExternalCreateObjectActionEntryPositiveTest() {
		Classifier classifier = new Class_();
		CreateObjectAction createObjectAction = new CreateObjectAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		classifier.ownedComment = ownedComments;
		createObjectAction.classifier = classifier;
		
		ActivityNodeEntryEventImpl node = new ActivityNodeEntryEventImpl(0, createObjectAction, null);
		Event event = new ActivityNodeEntryEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCreateObjectActionEntry(event);		
		assertEquals(true, returnValue);
	}// isExternalCreateObjectActionEntryPositiveTest
	
	@Test
	public void isExternalCreateObjectActionEntryEmptyCommentListTest() {
		Classifier classifier = new Class_();
		CreateObjectAction createObjectAction = new CreateObjectAction();
		CommentList ownedComments = new CommentList();

		classifier.ownedComment = ownedComments;
		createObjectAction.classifier = classifier;
		
		ActivityNodeEntryEventImpl node = new ActivityNodeEntryEventImpl(0, createObjectAction, null);
		Event event = new ActivityNodeEntryEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCreateObjectActionEntry(event);		
		assertEquals(false, returnValue);
	}// isExternalCreateObjectActionEntryEmptyCommentListTest
	
	// --------------------------------------------------------------------------------------
	
	@Test
	public void isExternalCreateObjectActionExitPositiveTest() {
		Classifier classifier = new Class_();
		CreateObjectAction createObjectAction = new CreateObjectAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		classifier.ownedComment = ownedComments;
		createObjectAction.classifier = classifier;
		
		ActivityNodeExitEventImpl node = new ActivityNodeExitEventImpl(0, createObjectAction, null);
		Event event = new ActivityNodeExitEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCreateObjectActionExit(event);		
		assertEquals(true, returnValue);
	}// isExternalCreateObjectActionExitPositiveTest
	
	@Test
	public void isExternalCreateObjectActionExitEmptyCommentListTest() {
		Classifier classifier = new Class_();
		CreateObjectAction createObjectAction = new CreateObjectAction();
		CommentList ownedComments = new CommentList();
		
		classifier.ownedComment = ownedComments;
		createObjectAction.classifier = classifier;
		
		ActivityNodeExitEventImpl node = new ActivityNodeExitEventImpl(0, createObjectAction, null);
		Event event = new ActivityNodeExitEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCreateObjectActionExit(event);		
		assertEquals(false, returnValue);
	}// isExternalCreateObjectActionExitEmptyCommentListTest
	
	// --------------------------------------------------------------------------------------
	
	@Test
	public void getExternalCreateObjectActionWithEntryEventTest() {
		Classifier classifier = new Class_();
		CreateObjectAction createObjectAction = new CreateObjectAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		classifier.ownedComment = ownedComments;
		createObjectAction.classifier = classifier;
		
		ActivityNodeEntryEventImpl node = new ActivityNodeEntryEventImpl(0, createObjectAction, null);
		Event event = new ActivityNodeEntryEventImpl(0, node.getNode(), null);
		
		CreateObjectAction returnValue = EventHelper.getExternalCreateObjectAction(event);		
		assertTrue(returnValue instanceof CreateObjectAction);
	}// getExternalCreateObjectActionWithEntryEventTest
	
	@Test
	public void getExternalCreateObjectActionWithExitEventTest() {
		Classifier classifier = new Class_();
		CreateObjectAction createObjectAction = new CreateObjectAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		classifier.ownedComment = ownedComments;
		createObjectAction.classifier = classifier;
		
		ActivityNodeExitEventImpl node = new ActivityNodeExitEventImpl(0, createObjectAction, null);
		Event event = new ActivityNodeExitEventImpl(0, node.getNode(), null);
		
		CreateObjectAction returnValue = EventHelper.getExternalCreateObjectAction(event);		
		assertTrue(returnValue instanceof CreateObjectAction);
	}// getExternalCreateObjectActionWithExitEventTest
	
	@Test
	public void getExternalCreateObjectActionEmptyCommentListTest() {
		Classifier classifier = new Class_();
		CreateObjectAction createObjectAction = new CreateObjectAction();
		CommentList ownedComments = new CommentList();
		
		classifier.ownedComment = ownedComments;
		createObjectAction.classifier = classifier;
		
		ActivityNodeExitEventImpl node = new ActivityNodeExitEventImpl(0, createObjectAction, null);
		Event event = new ActivityNodeExitEventImpl(0, node.getNode(), null);
		
		CreateObjectAction returnValue = EventHelper.getExternalCreateObjectAction(event);		
		assertEquals(null, returnValue);
	}// getExternalCreateObjectActionEmptyCommentListTest
	
	// --------------------------------------------------------------------------------------
	
	@Test
	public void isExternalCallOperationActionEntryPositiveTest() {
		Classifier owner = new Class_();		
		Operation operation = new Operation();
		CallOperationAction callOperationAction = new CallOperationAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		owner.ownedComment = ownedComments;
		operation.owner = owner;
		callOperationAction.operation = operation;
		
		ActivityNodeEntryEventImpl node = new ActivityNodeEntryEventImpl(0, callOperationAction, null);
		Event event = new ActivityNodeEntryEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCallOperationActionEntry(event);		
		assertEquals(true, returnValue);
	}// isExternalCallOperationActionEntryPositiveTest
	
	@Test
	public void isExternalCallOperationActionEntryEmptyCommentListTest() {
		Classifier owner = new Class_();		
		Operation operation = new Operation();
		CallOperationAction callOperationAction = new CallOperationAction();
		CommentList ownedComments = new CommentList();
		
		owner.ownedComment = ownedComments;
		operation.owner = owner;
		callOperationAction.operation = operation;
		
		ActivityNodeEntryEventImpl node = new ActivityNodeEntryEventImpl(0, callOperationAction, null);
		Event event = new ActivityNodeEntryEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCallOperationActionEntry(event);		
		assertEquals(false, returnValue);
	}// isExternalCallOperationActionEntryEmptyCommentListTest
	
	// --------------------------------------------------------------------------------------

	@Test
	public void getExternalCallOperationActionWithEntryEventTest() {
		Classifier owner = new Class_();		
		Operation operation = new Operation();
		CallOperationAction callOperationAction = new CallOperationAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		owner.ownedComment = ownedComments;
		operation.owner = owner;
		callOperationAction.operation = operation;
		
		ActivityNodeEntryEventImpl node = new ActivityNodeEntryEventImpl(0, callOperationAction, null);
		Event event = new ActivityNodeEntryEventImpl(0, node.getNode(), null);
		
		CallOperationAction returnValue = EventHelper.getExternalCallOperationAction(event);		
		assertTrue(returnValue instanceof CallOperationAction);
	}// getExternalCallOperationActionWithEntryEventTest
	
	@Test
	public void getExternalCallOperationActionWithExitEventTest() {
		Classifier owner = new Class_();		
		Operation operation = new Operation();
		CallOperationAction callOperationAction = new CallOperationAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		owner.ownedComment = ownedComments;
		operation.owner = owner;
		callOperationAction.operation = operation;
		
		ActivityNodeExitEventImpl node = new ActivityNodeExitEventImpl(0, callOperationAction, null);
		Event event = new ActivityNodeExitEventImpl(0, node.getNode(), null);
		
		CallOperationAction returnValue = EventHelper.getExternalCallOperationAction(event);		
		assertTrue(returnValue instanceof CallOperationAction);
	}// getExternalCallOperationActionWithExitEventTest
	
	@Test
	public void getExternalCallOperationActionEmptyCommentListTest() {
		Classifier owner = new Class_();		
		Operation operation = new Operation();
		CallOperationAction callOperationAction = new CallOperationAction();
		CommentList ownedComments = new CommentList();
		
		owner.ownedComment = ownedComments;
		operation.owner = owner;
		callOperationAction.operation = operation;
		
		ActivityNodeEntryEventImpl node = new ActivityNodeEntryEventImpl(0, callOperationAction, null);
		Event event = new ActivityNodeEntryEventImpl(0, node.getNode(), null);
		
		CallOperationAction returnValue = EventHelper.getExternalCallOperationAction(event);		
		assertEquals(null, returnValue);
	}// getExternalCallOperationActionEmptyCommentListTest
	
	// --------------------------------------------------------------------------------------
	
	@Test
	public void isExternalCallOperationActionExitPositiveTest() {
		Classifier owner = new Class_();		
		Operation operation = new Operation();
		CallOperationAction callOperationAction = new CallOperationAction();
		CommentList ownedComments = new CommentList();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		ownedComments.add(comment);
		
		owner.ownedComment = ownedComments;
		operation.owner = owner;
		callOperationAction.operation = operation;
		
		ActivityNodeExitEventImpl node = new ActivityNodeExitEventImpl(0, callOperationAction, null);
		Event event = new ActivityNodeExitEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCallOperationActionExit(event);		
		assertEquals(true, returnValue);
	}// isExternalCallOperationActionExitPositiveTest
	
	@Test
	public void isExternalCallOperationActionExitEmptyCommentListTest() {
		Classifier owner = new Class_();		
		Operation operation = new Operation();
		CallOperationAction callOperationAction = new CallOperationAction();
		CommentList ownedComments = new CommentList();
		
		owner.ownedComment = ownedComments;
		operation.owner = owner;
		callOperationAction.operation = operation;
		
		ActivityNodeExitEventImpl node = new ActivityNodeExitEventImpl(0, callOperationAction, null);
		Event event = new ActivityNodeExitEventImpl(0, node.getNode(), null);
		
		boolean returnValue = EventHelper.isExternalCallOperationActionExit(event);		
		assertEquals(false, returnValue);
	}// isExternalCallOperationActionExitEmptyCommentListTest
	
	// --------------------------------------------------------------------------------------

	@Test
	public void isExternalActivityEntryEventPositiveTest() {
		Activity activity = new Activity();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		
		activity.ownedComment.add(comment);
		
		ActivityEntryEventImpl node = new ActivityEntryEventImpl(0, activity, null);
		Event event = new ActivityEntryEventImpl(0, node.getActivity(), null);
		
		boolean returnValue = EventHelper.isExternalActivityEntryEvent(event);		
		assertEquals(true, returnValue);
	}// isExternalActivityEntryEventPositiveTest
	
	@Test
	public void isExternalActivityEntryEventNoCommentTest() {
		Activity activity = new Activity();
		
		ActivityEntryEventImpl node = new ActivityEntryEventImpl(0, activity, null);
		Event event = new ActivityEntryEventImpl(0, node.getActivity(), null);
		
		boolean returnValue = EventHelper.isExternalActivityEntryEvent(event);		
		assertEquals(false, returnValue);
	}// isExternalActivityEntryEventNoCommentTest
	
	// --------------------------------------------------------------------------------------

	@Test
	public void isExternalActivityExitEventPositiveTest() {
		Activity activity = new Activity();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		
		activity.ownedComment.add(comment);
		
		ActivityExitEventImpl node = new ActivityExitEventImpl(0, activity, null);
		Event event = new ActivityExitEventImpl(0, node.getActivity(), null);
		
		boolean returnValue = EventHelper.isExternalActivityExitEvent(event);		
		assertEquals(true, returnValue);
	}// isExternalActivityExitEventPositiveTest
	
	@Test
	public void isExternalActivityExitEventNoCommentTest() {
		Activity activity = new Activity();
		
		ActivityExitEventImpl node = new ActivityExitEventImpl(0, activity, null);
		Event event = new ActivityExitEventImpl(0, node.getActivity(), null);
		
		boolean returnValue = EventHelper.isExternalActivityExitEvent(event);		
		assertEquals(false, returnValue);
	}// isExternalActivityExitEventNoCommentTest
	
	// --------------------------------------------------------------------------------------

	@Test
	public void getExternalActivityWithEntryEventTest() {
		Activity activity = new Activity();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		
		activity.ownedComment.add(comment);
		
		ActivityEntryEventImpl node = new ActivityEntryEventImpl(0, activity, null);
		Event event = new ActivityEntryEventImpl(0, node.getActivity(), null);
		
		Activity returnValue = EventHelper.getExternalActivity(event);		
		assertTrue(returnValue instanceof Activity);
	}// getExternalActivityWithEntryEventTest
	
	@Test
	public void getExternalActivityWithExitEventTest() {
		Activity activity = new Activity();
		
		Comment comment = new Comment();
		comment.body = "@external";		
		
		activity.ownedComment.add(comment);
		
		ActivityExitEventImpl node = new ActivityExitEventImpl(0, activity, null);
		Event event = new ActivityExitEventImpl(0, node.getActivity(), null);
		
		Activity returnValue = EventHelper.getExternalActivity(event);		
		assertTrue(returnValue instanceof Activity);
	}// getExternalActivityWithExitEventTest
	
	@Test
	public void getExternalActivityNoCommentTest() {
		Activity activity = new Activity();
		
		ActivityExitEventImpl node = new ActivityExitEventImpl(0, activity, null);
		Event event = new ActivityExitEventImpl(0, node.getActivity(), null);
		
		Activity returnValue = EventHelper.getExternalActivity(event);		
		assertEquals(null, returnValue);
	}// getExternalActivityNoCommentTest
	
}// EventHelperTest
