
/*
 * Initial version copyright 2008 Lockheed Martin Corporation, except  
 * as stated in the file entitled Licensing-Information. 
 * 
 * All modifications copyright 2009 Data Access Technologies, Inc.
 *
 * Licensed under the Academic Free License version 3.0 
 * (http://www.opensource.org/licenses/afl-3.0.php), except as stated 
 * in the file entitled Licensing-Information. 
 *
 * Contributors:
 *   MDS - initial API and implementation
 *
 */

package fUML.Test;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Activities.CompleteStructuredActivities.*;
import fUML.Syntax.Actions.BasicActions.*;
import fUML.Syntax.Actions.IntermediateActions.*;
import fUML.Syntax.Actions.CompleteActions.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Test::TestSuite</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link TestSuite#TestSuite <em>TestSuite</em>}</li>
 * <li>{@link TestSuite#testSimpleActivites <em>testSimpleActivites</em>}</li>
 * <li>{@link TestSuite#testHelloWorld <em>testHelloWorld</em>}</li>
 * <li>{@link TestSuite#testPolymorphicOperationCall <em>
 * testPolymorphicOperationCall</em>}</li>
 * <li>{@link TestSuite#testSuperCall <em>testSuperCall</em>}</li>
 * <li>{@link TestSuite#testSignalSend <em>testSignalSend</em>}</li>
 * <li>{@link TestSuite#testStructuredNode <em>testStructuredNode</em>}</li>
 * <li>{@link TestSuite#testConditionalNode <em>testConditionalNode</em>}</li>
 * <li>{@link TestSuite#testLoopNode <em>testLoopNode</em>}</li>
 * <li>{@link TestSuite#testExpansionRegion <em>testExpansionRegion</em>}</li>
 * <li>{@link TestSuite#testLinkCreator <em>testLinkCreator</em>}</li>
 * <li>{@link TestSuite#testLinkDestroyer <em>testLinkDestroyer</em>}</li>
 * <li>{@link TestSuite#testLinkReader <em>testLinkReader</em>}</li>
 * <li>{@link TestSuite#testLinkWriter <em>testLinkWriter</em>}</li>
 * <li>{@link TestSuite#testLinkRemover <em>testLinkRemover</em>}</li>
 * <li>{@link TestSuite#testWriterReader <em>testWriterReader</em>}</li>
 * <li>{@link TestSuite#testIsClassified <em>testIsClassified</em>}</li>
 * <li>{@link TestSuite#testFireAgain <em>testFireAgain</em>}</li>
 * <li>{@link TestSuite#activityFactory <em>activityFactory</em>}</li>
 * <li>{@link TestSuite#classifierFactory <em>classifierFactory</em>}</li>
 * <li>{@link TestSuite#executorTest <em>executorTest</em>}</li>
 * <li>{@link TestSuite#variableUtility <em>variableUtility</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class TestSuite extends fUML.Test.Test {

	private fUML.Test.ActivityFactory activityFactory = null;
	private fUML.Test.ClassifierFactory classifierFactory = null;
	private fUML.Test.ExecutorTest executorTest = null;
	private fUML.Test.VariableUtility variableUtility = null;

	/**
	 * operation TestSuite <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TestSuite(fUML.Test.InitTestEnvironment init) {
		this.environment = init.environment;
		this.activityFactory = init.activityFactory;
		this.classifierFactory = init.classifierFactory;
		this.executorTest = init.executorTest;
		this.variableUtility = init.variableUtility;
	} // TestSuite

	/**
	 * operation testSimpleActivites <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void testSimpleActivites() {
		Debug.println("");
		Debug.println("[testSimpleActivites] Setting up...");

		this.activityFactory.createCopier();
		this.activityFactory.createCaller("Copier");
		this.activityFactory.createSimpleDecision(0);
		this.activityFactory.createSimpleDecision(1);
		this.activityFactory.createForkJoin();
		this.activityFactory.createDecisionJoin();
		this.activityFactory.createForkMerge();
		this.activityFactory.createForkMergeData();
		this.activityFactory.createSelfReader();

		Debug.println("[testSimpleActivities] Testing...");

		this.executorTest.testExecute("Copier");
		this.executorTest.testExecute("CopierCaller");
		this.executorTest.testExecute("SimpleDecision0");
		this.executorTest.testExecute("SimpleDecision1");
		this.executorTest.testExecute("ForkJoin");
		this.executorTest.testExecute("DecisionJoin");
		this.executorTest.testExecute("ForkMerge");
		this.executorTest.testExecute("ForkMergeData");
		this.executorTest.testExecute("SelfReader");

		Debug.println("[testSimpleActivities] Done!");
	} // testSimpleActivites

	/**
	 * operation testHelloWorld <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testHelloWorld() {
		Debug.println("");
		Debug.println("[testHelloWorld] Setting up...");
		this.activityFactory.createHelloWorld2();

		Debug.println("[testHelloWorld] Testing...");
		this.executorTest.testExecute("HelloWorld2");

		Debug.println("[testHelloWorld] Done!");

	} // testHelloWorld

	/**
	 * operation testPolymorphicOperationCall <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void testPolymorphicOperationCall(String superclassMethodName,
			String subclassMethodName) {
		Debug.println("");
		Debug.println("[testPolymorphicOperationCall] Setting up...");

		if (activityFactory.getActivity(superclassMethodName) == null)
			return;
		if (activityFactory.getActivity(subclassMethodName) == null)
			return;

		String superclassName = "Super_" + superclassMethodName;
		String subclassName = "Sub_" + subclassMethodName;

		if (this.environment.getElement(superclassName) != null) {
			Debug.println("[testPolymorphicOperationCall] Replacing class "
					+ superclassName + ".");
			this.environment.removeElement(superclassName);
		}

		if (this.environment.getElement(subclassName) != null) {
			Debug.println("[testPolymorphicOperationCall] Replacing class "
					+ subclassName + ".");
			this.environment.removeElement(subclassName);
		}

		this.classifierFactory.createClass(superclassName);
		this.classifierFactory.addOperation(superclassName, "", "test",
				superclassMethodName);

		this.classifierFactory.createClass(subclassName);
		this.classifierFactory.addOperation(subclassName, superclassName,
				"test", subclassMethodName);

		this.activityFactory.createPolymorphicOperationCaller(subclassName,
				superclassName, "test");

		Debug.println("[testPolymorphicOperationCall] Testing...");

		this.executorTest.testExecute(subclassName + superclassName
				+ "testCaller");

		Debug.println("[testPolymorphicOperationCall] Done!");

	} // testPolymorphicOperationCall

	/**
	 * operation testSuperCall <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testSuperCall(String superclassMethodName,
			String subclassMethodName) {
		Debug.println("");
		Debug.println("[testSuperCall] Setting up...");

		if (activityFactory.getActivity(superclassMethodName) == null)
			return;
		if (activityFactory.getActivity(subclassMethodName) == null)
			return;

		String superclassName = "Super_" + superclassMethodName;
		String subclassName = "Sub_" + subclassMethodName;

		if (this.environment.getElement(superclassName) != null) {
			Debug.println("[testSuperCall] Replacing class " + superclassName
					+ ".");
			this.environment.removeElement(superclassName);
		}

		if (this.environment.getElement(subclassName) != null) {
			Debug.println("[testSuperCall] Replacing class " + subclassName
					+ ".");
			this.environment.removeElement(subclassName);
		}

		// Debug.println("[testSuperCall] Creating class " + superclassName +
		// "...");

		this.classifierFactory.createClass(superclassName);
		this.classifierFactory.addOperation(superclassName, "", "test",
				superclassMethodName);
		this.activityFactory.createSelfCaller(superclassName, "test");
		// Activity callTestMethod =
		// (Activity)(this.environment.getElement(superclassName+"testSelfCaller"));
		this.classifierFactory.addOperation(superclassName, "", "callTest",
				superclassName + "testSelfCaller");
		// Debug.println("[testSuperCall] " + superclassName + "::" +
		// "callTest method = " + callTestMethod.name + ", context = " +
		// callTestMethod.context);

		// Debug.println("[testSuperCall] Creating class " + subclassName +
		// "...");

		this.classifierFactory.createClass(subclassName);
		this.classifierFactory.addOperation(subclassName, superclassName,
				"test", subclassMethodName);
		this.activityFactory.createMethodCaller(superclassName, "callTest");
		this.classifierFactory.addOperation(subclassName, superclassName,
				"callTest", superclassName + "callTestMethodCaller");

		// Debug.println("[testSuperCall] Adding generalization...");
		this.classifierFactory.addGeneralization(subclassName, superclassName);

		// Debug.println("[testSuperCall] Creating operation caller activity...");
		this.activityFactory.createOperationCaller(subclassName, "callTest");

		Debug.println("[testSuperCall] Testing...");

		this.executorTest.testExecute(subclassName + "callTestCaller");

		Debug.println("[testSuperCall] Done!");

	} // testSuperCall

	/**
	 * operation testSignalSend <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testSignalSend() {
		Debug.println("[testSignalSend] Setting up...");

		classifierFactory.createSignal("TestSignal");
		activityFactory.createSender("TestSignal");

		Debug.println("[testSignalSend] Testing...");

		executorTest.testExecute("TestSignalSender");

		Debug.println("[testSignalSend] Done!");
	} // testSignalSend

	/**
	 * operation testStructuredNode <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void testStructuredNode() {
		Debug.println("");
		Debug.println("[testStructuredNode] Setting up...");
		this.activityFactory.createStructuredNodeTester("ForkMergeInput");

		Debug.println("[testStructuredNode] Testing...");
		this.executorTest.testExecute("StructuredForkMergeInput");

		Debug.println("[testStructuredNode] Done!");
	} // testStructuredNode

	/**
	 * operation testConditionalNode <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void testConditionalNode() {
		Debug.println("");
		Debug.println("[testConditionalNode] Setting up...");
		this.activityFactory.createConditionalNodeTester(0);
		this.activityFactory.createConditionalNodeTester(1);
		this.activityFactory.createConditionalNodeTester(2);

		Debug.println("[testConditionalNode] Testing...");
		this.executorTest.testExecute("ConditionalNodeTester_0");
		this.executorTest.testExecute("ConditionalNodeTester_1");
		this.executorTest.testExecute("ConditionalNodeTester_2");

		Debug.println("[testConditionalNode] Done!");
	} // testConditionalNode

	/**
	 * operation testLoopNode <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testLoopNode() {
		Debug.println("");
		Debug.println("[testLoopNode] Setting up...");
		this.activityFactory.createLoopNodeTester(0);
		this.activityFactory.createLoopNodeTester(1);
		this.activityFactory.createLoopNodeTester(2);

		Debug.println("[testLoopNode] Testing...");
		this.executorTest.testExecute("LoopNodeTester_0");
		this.executorTest.testExecute("LoopNodeTester_1");
		this.executorTest.testExecute("LoopNodeTester_2");

		Debug.println("[testLoopNode] Done!");
	} // testLoopNode

	/**
	 * operation testExpansionRegion <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void testExpansionRegion() {
		Debug.println("");
		Debug.println("[testExpansionRegion] Setting up...");
		this.activityFactory.createExpansionRegionTester(0);
		this.activityFactory.createExpansionRegionTester(1);
		this.activityFactory.createExpansionRegionTester(2);

		Debug.println("[testExpansionRegion] Testing...");
		this.executorTest.testExecute("ExpansionRegionTester_0");
		this.executorTest.testExecute("ExpansionRegionTester_1");
		this.executorTest.testExecute("ExpansionRegionTester_2");

		Debug.println("[testExpansionRegion] Done!");
	} // testExpansionRegion

	/**
	 * operation testLinkCreator <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testLinkCreator() {
		Debug.println("[testLinkCreator] Setting up...");

		classifierFactory.createClass("A");
		classifierFactory.createClass("B");

		this.environment.removeElement("AB");

		classifierFactory.createAssociation("AB");
		classifierFactory.addEnd("AB", "a", "A", false);
		classifierFactory.addEnd("AB", "b", "B", false);

		activityFactory.createLinkCreator("AB");

		Debug.println("[testLinkCreator] Testing...");

		executorTest.testExecute("ABLinkCreator");
		this.environment.printExtent("AB");

		Debug.println("[testLinkCreator] Done!");
	} // testLinkCreator

	/**
	 * operation testLinkDestroyer <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testLinkDestroyer() {
		Debug.println("[testLinkDestroyer] Setting up...");

		classifierFactory.createClass("A");
		classifierFactory.createClass("B");

		this.environment.removeElement("AB");

		classifierFactory.createAssociation("AB");
		classifierFactory.addEnd("AB", "a", "A", false);
		classifierFactory.addEnd("AB", "b", "B", false);

		activityFactory.createLinkDestroyer("AB");

		Debug.println("[testLinkDestroyer] Testing...");

		executorTest.testExecute("ABLinkDestroyer");
		this.environment.printExtent("AB");

		Debug.println("[testLinkDestroyer] Done!");
	} // testLinkDestroyer

	/**
	 * operation testLinkReader <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testLinkReader() {
		Debug.println("[testLinkReader] Setting up...");

		classifierFactory.createClass("A");
		classifierFactory.createClass("B");

		this.environment.removeElement("AB");

		classifierFactory.createAssociation("AB");
		classifierFactory.addEnd("AB", "a", "A", false);
		classifierFactory.addEnd("AB", "b", "B", false);
		((Association) this.environment.getElement("AB")).memberEnd.getValue(1).multiplicityElement
				.setIsOrdered(true);

		activityFactory.createLinkReader("AB");

		Debug.println("[testLinkReader] Testing...");

		executorTest.testExecute("ABLinkReader");
		this.environment.printExtent("AB");

		Debug.println("[testLinkReader] Done!");
	} // testLinkReader

	/**
	 * operation testLinkWriter <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testLinkWriter() {
		Debug.println("[testLinkWriter] Setting up...");

		classifierFactory.createClass("A");
		classifierFactory.createClass("B");

		this.environment.removeElement("AB");

		classifierFactory.createAssociation("AB");
		classifierFactory.addEnd("AB", "a", "A", false);
		classifierFactory.addEnd("AB", "b", "B", false);
		((Association) this.environment.getElement("AB")).memberEnd.getValue(1).multiplicityElement
				.setIsOrdered(true);

		activityFactory.createLinkWriter("AB");

		Debug.println("[testLinkWriter] Testing...");

		executorTest.testExecute("ABLinkWriter");
		this.environment.printExtent("AB");

		Debug.println("[testLinkWriter] Done!");
	} // testLinkWriter

	/**
	 * operation testLinkRemover <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testLinkRemover() {
		Debug.println("[testLinkRemover] Setting up (unordered end)...");

		classifierFactory.createClass("A");
		classifierFactory.createClass("B");

		this.environment.removeElement("AB");

		classifierFactory.createAssociation("AB");
		classifierFactory.addEnd("AB", "a", "A", false);
		classifierFactory.addEnd("AB", "b", "B", false);

		activityFactory.createLinkRemover("AB");

		Debug.println("[testLinkRemover] Testing (unordered end)...");

		executorTest.testExecute("ABLinkRemover");
		this.environment.printExtent("AB");

		Debug.println("[testLinkRemover] Setting up (ordered end)...");

		this.environment.locus.extensionalValues.clear();
		((Association) this.environment.getElement("AB")).memberEnd.getValue(1).multiplicityElement
				.setIsOrdered(true);
		((Association) this.environment.getElement("AB")).memberEnd.getValue(1).multiplicityElement
				.setIsUnique(false);

		this.environment.removeElement("ABLinkWriter");
		this.environment.removeElement("ABLinkRemover");
		activityFactory.createLinkRemover("AB");

		Debug.println("[testLinkRemover] Testing (ordered end)...");

		executorTest.testExecute("ABLinkRemover");
		this.environment.printExtent("AB");

		Debug.println("[testLinkRemover] Done!");
	} // testLinkRemover

	/**
	 * operation testWriterReader <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testWriterReader() {
		Debug.println("[testWriterReader] Setting up...");

		this.environment.removeElement("TestClass");
		classifierFactory.createClass("TestClass");
		classifierFactory.addAttribute("TestClass", "x", "Integer", false);

		activityFactory.createWriterReader("TestClass", "x");

		Debug.println("[testWriterReader] Testing...");

		executorTest.testExecute("TestClass_x_WriterReader");
		this.environment.printExtent("TestClass");

		Debug.println("[testWriterReader] Done!");
	} // testWriterReader

	/**
	 * operation testIsClassified <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testIsClassified() {
		Debug.println("[testIsClassified] Setting up...");

		classifierFactory.createSignal("Signal");
		activityFactory.createIsClassifiedTester("Signal");
		// activityFactory.createAttributedCaller("TestIsSignal");

		// variableUtility.setDefaultValue("caller",
		// "AttributedTestIsSignalCaller");
		// variableUtility.setAttributeValue("caller", "input",
		// environment.makeValue(environment.getType("Signal")));

		Debug.println("[testIsClassified] Testing...");

		executorTest.testExecute("TestIsSignal");
		// variableUtility.run("caller");

		Debug.println("[testIsClassified] Done!");
	} // testIsClassified

	/**
	 * operation testFireAgain <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testFireAgain() {
		Debug.println("[testFireAgain] Setting up...");

		activityFactory.createFireAgainTester();

		Debug.println("[testFireAgain] Testing...");

		executorTest.testExecute("FireAgainTester");

		Debug.println("[testFireAgain] Done!");

	} // testFireAgain

} // TestSuite
