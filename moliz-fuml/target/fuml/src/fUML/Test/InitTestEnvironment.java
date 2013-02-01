
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

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Test::InitTestEnvironment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link InitTestEnvironment#InitTestEnvironment <em>InitTestEnvironment
 * </em>}</li>
 * <li>{@link InitTestEnvironment#environment <em>environment</em>}</li>
 * <li>{@link InitTestEnvironment#executorTest <em>executorTest</em>}</li>
 * <li>{@link InitTestEnvironment#activityFactory <em>activityFactory</em>}</li>
 * <li>{@link InitTestEnvironment#variableUtility <em>variableUtility</em>}</li>
 * <li>{@link InitTestEnvironment#classifierFactory <em>classifierFactory</em>}</li>
 * <li>{@link InitTestEnvironment#testSuite <em>testSuite</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class InitTestEnvironment extends org.modeldriven.fuml.FumlObject {

	public fUML.Test.TestEnvironment environment = null;
	public fUML.Test.ExecutorTest executorTest = null;
	public fUML.Test.ActivityFactory activityFactory = null;
	public fUML.Test.VariableUtility variableUtility = null;
	public fUML.Test.ClassifierFactory classifierFactory = null;
	public fUML.Test.TestSuite testSuite = null;

	/**
	 * operation InitTestEnvironment <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public InitTestEnvironment() {
		// Debug.println("[InitTestEnvironment] Start...");

		environment = new TestEnvironment();
		executorTest = new ExecutorTest(environment);
		activityFactory = new ActivityFactory(environment);
		variableUtility = new VariableUtility(environment);
		classifierFactory = new ClassifierFactory(environment);

		testSuite = new TestSuite(this);

		// Debug.println("[InitTestEnvironment] Done...");

	} // InitTestEnvironment

} // InitTestEnvironment
