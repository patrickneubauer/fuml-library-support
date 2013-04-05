/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.modelexecution.fuml.extlib.ActionHelper;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Comment;
import fUML.Syntax.Classes.Kernel.Namespace;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Package;

/**
 * Class that tests the {@link ActionHelper}
 * 
 * @author Patrick Neubauer
 * 
 */
public class ActionHelperTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void getClassJarPathPositiveTest() throws Exception {
		CreateObjectAction createObjectAction = new CreateObjectAction();
		Classifier classifier = new Class_();
		Comment comment = new Comment();
		comment.body = "@external=extlibs/Vehicles.jar";
		classifier.ownedComment.add(comment);
		createObjectAction.classifier = classifier;

		String returnValue = ActionHelper.getClassJarPath(createObjectAction);

		assertEquals("extlibs/Vehicles.jar", returnValue);
	}

	@Test(expected = Exception.class)
	public void getClassJarPathNoCommentTest() throws Exception {
		CreateObjectAction createObjectAction = new CreateObjectAction();

		String returnValue = ActionHelper.getClassJarPath(createObjectAction);
		// supposed to throw an Exception
	}

	@Test(expected = Exception.class)
	public void getClassJarPathEmptyCommentTest() throws Exception {
		CreateObjectAction createObjectAction = new CreateObjectAction();
		Classifier classifier = new Class_();
		Comment comment = new Comment();
		comment.body = "";
		classifier.ownedComment.add(comment);
		createObjectAction.classifier = classifier;

		String returnValue = ActionHelper.getClassJarPath(createObjectAction);
		// supposed to throw an Exception
	}

	@Test
	public void getClassJarPathNoPathTest() throws Exception {
		CreateObjectAction createObjectAction = new CreateObjectAction();
		Classifier classifier = new Class_();
		Comment comment = new Comment();
		comment.body = "@external=";
		classifier.ownedComment.add(comment);
		createObjectAction.classifier = classifier;

		String returnValue = ActionHelper.getClassJarPath(createObjectAction);

		assertEquals("", returnValue);
	}

	// --------------------------------------------------------------------------------------

	@Test
	public void getClassNamespaceAndNameOfCreateObjectActionPositiveTest() throws Exception {
		CreateObjectAction createObjectAction = new CreateObjectAction();
		Classifier classifier = new Class_();
		Namespace namespace = new Package();
		namespace.name = "vehiclelibrary";
		classifier.namespace = namespace;
		classifier.name = "Ship";
		createObjectAction.classifier = classifier;

		String returnValue = ActionHelper.getClassNamespaceAndName(createObjectAction);

		assertEquals("vehiclelibrary.Ship", returnValue);
	}
	
	@Test
	public void getClassNamespaceAndNameOfCreateObjectActionNoClassTest() {
		boolean thrown = false;

		CreateObjectAction createObjectAction = new CreateObjectAction();

		try {
			String returnValue = ActionHelper.getClassNamespaceAndName(createObjectAction);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(
					"Error occured while trying to obtain the Class name from fUML.Syntax.Actions.IntermediateActions.CreateObjectAction"));
			thrown = true;
		}

		assertEquals(true, thrown);
	}

	@Test
	public void getClassNamespaceAndNameOfCreateObjectActionNoClassNameTest() {
		boolean thrown = false;

		CreateObjectAction createObjectAction = new CreateObjectAction();
		Classifier classifier = new Class_();
		Namespace namespace = new Package();

		namespace.name = "vehiclelibrary";

		classifier.namespace = namespace;
		createObjectAction.classifier = classifier;

		try {
			String returnValue = ActionHelper.getClassNamespaceAndName(createObjectAction);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(
					"Error occured while trying to obtain the Class name from fUML.Syntax.Actions.IntermediateActions.CreateObjectAction"));
			thrown = true;
		}

		assertEquals(true, thrown);
	}

	@Test
	public void getClassNamespaceAndNameOfCreateObjectActionNoNamespaceTest() throws Exception {
		CreateObjectAction createObjectAction = new CreateObjectAction();
		Classifier classifier = new Class_();

		classifier.name = "Ship";
		createObjectAction.classifier = classifier;

		String returnValue = ActionHelper.getClassNamespaceAndName(createObjectAction);

		assertEquals("Ship", returnValue);
	}

	@Test
	public void getClassNamespaceAndNameOfCreateObjectActionNoNamespaceNameTest() throws Exception {
		CreateObjectAction createObjectAction = new CreateObjectAction();
		Classifier classifier = new Class_();
		Namespace namespace = new Package();

		classifier.namespace = namespace;
		classifier.name = "Ship";
		createObjectAction.classifier = classifier;

		String returnValue = ActionHelper.getClassNamespaceAndName(createObjectAction);

		assertEquals("Ship", returnValue);
	}

	// --------------------------------------------------------------------------------------

	@Test
	public void getClassNamespaceAndNameOfCallOperationActionPositiveTest() throws Exception {
		CallOperationAction callOperationAction = new CallOperationAction();
		Operation operation = new Operation();
		Namespace operationNamespace = new Package();
		Namespace operationNamespaceNamespace = new Package();

		operationNamespaceNamespace.name = "vehiclelibrary";
		operationNamespace.name = "Ship";
		operationNamespace.namespace = operationNamespaceNamespace;

		operation.namespace = operationNamespace;
		operation.name = "toString"; // not necessary
		callOperationAction.operation = operation;

		String returnValue = ActionHelper.getClassNamespaceAndName(callOperationAction);

		assertEquals("vehiclelibrary.Ship", returnValue);
	}
	
	@Test
	public void getClassNamespaceAndNameOfCallOperationActionNoOperationTest() {
		boolean thrown = false;

		CallOperationAction callOperationAction = new CallOperationAction();

		try {
			String returnValue = ActionHelper.getClassNamespaceAndName(callOperationAction);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(
					"Error occured while trying to obtain the Class name from fUML.Syntax.Actions.BasicActions.CallOperationAction"));
			thrown = true;
		}

		assertEquals(true, thrown);
	}
	
	/**
	 * NOTE that name space is the Operation's Class here
	 */
	@Test
	public void getClassNamespaceAndNameOfCallOperationActionNoNamespaceTest() {
		boolean thrown = false;

		CallOperationAction callOperationAction = new CallOperationAction();
		Operation operation = new Operation();

		operation.name = "toString"; // not necessary
		callOperationAction.operation = operation;

		try {
			String returnValue = ActionHelper.getClassNamespaceAndName(callOperationAction);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(
					"Error occured while trying to obtain the Class name from fUML.Syntax.Actions.BasicActions.CallOperationAction"));
			thrown = true;
		}

		assertEquals(true, thrown);
	}

	/**
	 * NOTE that name space name is the Operation's Class name here
	 */
	@Test
	public void getClassNamespaceAndNameOfCallOperationActionNoNamespaceNameTest() {
		boolean thrown = false;

		CallOperationAction callOperationAction = new CallOperationAction();
		Operation operation = new Operation();
		Namespace operationNamespace = new Package();
		Namespace operationNamespaceNamespace = new Package();

		operationNamespaceNamespace.name = "vehiclelibrary";

		operationNamespace.namespace = operationNamespaceNamespace;

		operation.namespace = operationNamespace;
		operation.name = "toString"; // not necessary
		callOperationAction.operation = operation;

		try {
			String returnValue = ActionHelper.getClassNamespaceAndName(callOperationAction);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(
					"Error occured while trying to obtain the Class name from fUML.Syntax.Actions.BasicActions.CallOperationAction"));
			thrown = true;
		}

		assertEquals(true, thrown);
	}

	@Test
	public void getClassNamespaceAndNameOfCallOperationActionNoNamespaceNamespaceTest() throws Exception {
		CallOperationAction callOperationAction = new CallOperationAction();
		Operation operation = new Operation();
		Namespace operationNamespace = new Package();

		operationNamespace.name = "Ship";

		operation.namespace = operationNamespace;
		operation.name = "toString"; // not necessary
		callOperationAction.operation = operation;

		String returnValue = ActionHelper.getClassNamespaceAndName(callOperationAction);

		assertEquals("Ship", returnValue);
	}

	@Test
	public void getClassNamespaceAndNameOfCallOperationActionNoNamespaceNamespaceNameTest() throws Exception {
		CallOperationAction callOperationAction = new CallOperationAction();
		Operation operation = new Operation();
		Namespace operationNamespace = new Package();
		Namespace operationNamespaceNamespace = new Package();

		operationNamespace.name = "Ship";
		operationNamespace.namespace = operationNamespaceNamespace;

		operation.namespace = operationNamespace;
		operation.name = "toString"; // not necessary
		callOperationAction.operation = operation;

		String returnValue = ActionHelper.getClassNamespaceAndName(callOperationAction);

		assertEquals("Ship", returnValue);
	}
}
