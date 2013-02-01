
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

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Test::ExecutorTest</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ExecutorTest#ExecutorTest <em>ExecutorTest</em>}</li>
 * <li>{@link ExecutorTest#createDefaultInputValues <em>createDefaultInputValues
 * </em>}</li>
 * <li>{@link ExecutorTest#testEvaluate <em>testEvaluate</em>}</li>
 * <li>{@link ExecutorTest#testExecute <em>testExecute</em>}</li>
 * <li>{@link ExecutorTest#testStart <em>testStart</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ExecutorTest extends fUML.Test.Test {

	/**
	 * operation ExecutorTest <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ExecutorTest(fUML.Test.TestEnvironment environment) {
		this.environment = environment;
	} // ExecutorTest

	/**
	 * operation createDefaultInputValues <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList createDefaultInputValues(
			fUML.Syntax.Classes.Kernel.ParameterList parameters) {
		// Debug.println("[createDefaultParameterValues] " + parameters.size() +
		// " parameter(s):");

		ParameterValueList parameterValues = new ParameterValueList();

		for (int i = 0; i < parameters.size(); i++) {
			// Debug.println("[createDefaultParameterValues] Checking parameter "
			// + parameters.getValue(i).name + "...");
			ParameterDirectionKind direction = parameters.getValue(i).direction;
			if (direction.equals(ParameterDirectionKind.in)
					|| direction.equals(ParameterDirectionKind.inout)) {
				Debug
						.println("[createDefaultParameterValues] Creating parameter value for parameter "
								+ parameters.getValue(i).name + "...");
				ParameterValue parameterValue = new ParameterValue();
				parameterValue.parameter = parameters.getValue(i);

				Value value = this.environment
						.makeValue((Classifier) (parameters.getValue(i).type));

				if (value == null) {
					Debug
							.println("[createDefaultParameterValues] Error creating parameter value.");
					return null;
				}

				Debug
						.println("[createDefaultParameterValues] value = "
								+ value);
				parameterValue.values.addValue(value);
				parameterValues.addValue(parameterValue);
			}
		}

		return parameterValues;
	} // createDefaultInputValues

	/**
	 * operation testEvaluate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testEvaluate(String typeName) {
		Debug.println("");

		Classifier type = environment.getType(typeName);

		if (type == null) {
			Debug.println("[testEvaluate] " + typeName
					+ " not found or not a classifier.");
			return;
		}

		Value originalValue = environment.makeValue(type);
		Debug.println("[testEvaluate] Original value = " + originalValue);

		ValueSpecification specification = originalValue.specify();
		Debug.println("[testEvaluate] Value specification is "
				+ specification.getClass().getName());

		// try{
		Value evaluatedValue = this.environment.locus.executor
				.evaluate(specification);
		Debug.println("[testEvaluate] Evaluated value = " + evaluatedValue);

		Debug.println("[testEvaluate] Equality test = "
				+ evaluatedValue.equals(originalValue));
		// }
		// catch (Throwable e) {
		// Debug.println("[testEvaluate] Evaluation terminated due to " +
		// e.getClass().getName() + "...");
		// StackTraceElement [] stackTrace = e.getStackTrace();
		// if (stackTrace.length > 0) Debug.println(stackTrace[0] + ".");
		// }

	} // testEvaluate

	/**
	 * operation testExecute <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testExecute(String behaviorName) {
		Debug.println("");

		NamedElement element = this.environment.getElement(behaviorName);

		if ((element == null) || !(element instanceof Behavior)) {
			Debug.println("[testExecute] " + behaviorName
					+ " not found or is not a behavior.");
			return;
		}

		Behavior behavior = (Behavior) element;
		ParameterList parameters = behavior.ownedParameter;
		ParameterValueList parameterValues = this
				.createDefaultInputValues(parameters);

		if (parameterValues == null)
			return;

		Debug.println("[testExecute] Executing the behavior...");

		// try {
		ParameterValueList outputParameterValues = this.environment.locus.executor
				.execute(behavior, null, parameterValues);

		for (int i = 0; i < outputParameterValues.size(); i++) {
			ParameterValue outputParameterValue = outputParameterValues
					.getValue(i);
			Debug.println("[testExecute] Output parameter "
					+ outputParameterValue.parameter.name + " has "
					+ outputParameterValue.values.size() + " value(s):");
			for (int j = 0; j < outputParameterValue.values.size(); j++) {
				Debug.println("[testExecute] value [" + j + "] = "
						+ outputParameterValue.values.getValue(j));
			}
		}
		// }
		// catch (Throwable e) {
		// Debug.println("[testExecute] Execution terminated due to " +
		// e.getClass().getName() + "...");
		// StackTraceElement [] stackTrace = e.getStackTrace();
		// if (stackTrace.length > 0) Debug.println(stackTrace[0] + ".");
		// throw e;
		// }

		Debug.println("");
	} // testExecute

	/**
	 * operation testStart <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void testStart(String typeName) {
		Debug.println("");

		NamedElement element = this.environment.getElement(typeName);

		if ((element == null) || !(element instanceof Class_)) {
			Debug.println("[testStart] " + typeName
					+ " not found or is not a class.");
			return;
		}

		Class_ type = (Class_) element;
		Behavior behavior;

		if (type instanceof Behavior) {
			behavior = (Behavior) type;
		} else if (type.classifierBehavior != null) {
			behavior = type.classifierBehavior;
		} else {
			Debug.println("[testStart] " + typeName
					+ " does not have behavior.");
			return;
		}

		ParameterList parameters = behavior.ownedParameter;
		ParameterValueList parameterValues = this
				.createDefaultInputValues(parameters);

		if (parameterValues == null)
			return;

		Debug.println("[testStart] Starting the behavior...");

		Object_ object;
		// try {
		object = this.environment.locus.executor.start(type, parameterValues).referent;
		// }
		// catch (Exception e) {
		// Debug.println("[testStart] Execution terminated due to " +
		// e.getClass().getName() + "...");
		// StackTraceElement [] stackTrace = e.getStackTrace();
		// //if (stackTrace.length > 0) Debug.println(stackTrace[0] + ".");
		// for (int i = 0; i < stackTrace.length; i++) {
		// Debug.println(stackTrace[i]);
		// }
		// return;
		// }

		Debug.println("[testStart] Instantiated object:" + object);

		ParameterValueList outputParameterValues;
		if (object instanceof Execution) {
			outputParameterValues = ((Execution) object)
					.getOutputParameterValues();
		} else {
			outputParameterValues = object.objectActivation.classifierBehaviorExecutions
					.getValue(0).execution.getOutputParameterValues();
		}

		for (int i = 0; i < outputParameterValues.size(); i++) {
			ParameterValue outputParameterValue = outputParameterValues
					.getValue(i);
			Debug.println("[testStart] Output parameter "
					+ outputParameterValue.parameter.name + " has "
					+ outputParameterValue.values.size() + " value(s):");
			for (int j = 0; j < outputParameterValue.values.size(); j++) {
				Debug.println("[testStart] value [" + j + "] = "
						+ outputParameterValue.values.getValue(j));
			}
		}

	} // testStart

} // ExecutorTest
