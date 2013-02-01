
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

package fUML.Library.IntegerFunctionImplementation;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Semantics.Classes.Kernel.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Library::IntegerFunctionImplementation::IntegerFunctionBehaviorExecution</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link IntegerFunctionBehaviorExecution#doBody <em>doBody</em>}</li>
 * <li>{@link IntegerFunctionBehaviorExecution#doIntegerFunction <em>
 * doIntegerFunction</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class IntegerFunctionBehaviorExecution extends
		fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution {

	/**
	 * operation doBody <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void doBody(
			fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList inputParameters,
			fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList outputParameters) {
		// Extract integer arguments and perform an integer function on them.

		intList integerArguments = new intList();

		for (int i = 0; i < inputParameters.size(); i++) {
			int value = ((IntegerValue) (inputParameters.getValue(i)).values
					.getValue(0)).value;
			Debug.println("[doBody] argument = " + value);
			integerArguments.addValue(value);
		}

		IntegerValue result = new IntegerValue();
		result.value = this.doIntegerFunction(integerArguments);

		Debug.println("[doBody] result = " + result.value);

		ValueList values = new ValueList();
		values.addValue(result);
		outputParameters.getValue(0).values = values;

	} // doBody

	/**
	 * operation doIntegerFunction <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract int doIntegerFunction(UMLPrimitiveTypes.intList arguments);
} // IntegerFunctionBehaviorExecution
