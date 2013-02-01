
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
 * 
 * <em><b>fUML::Library::IntegerFunctionImplementation::IntegerGreaterFunctionBehaviorExecution</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link IntegerGreaterFunctionBehaviorExecution#doBody <em>doBody</em>}</li>
 * <li>{@link IntegerGreaterFunctionBehaviorExecution#new_ <em>new_</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class IntegerGreaterFunctionBehaviorExecution extends
		fUML.Semantics.CommonBehaviors.BasicBehaviors.OpaqueBehaviorExecution {

	/**
	 * operation doBody <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void doBody(
			fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList inputParameters,
			fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList outputParameters) {
		int value0 = ((IntegerValue) inputParameters.getValue(0).values
				.getValue(0)).value;
		int value1 = ((IntegerValue) inputParameters.getValue(1).values
				.getValue(0)).value;

		BooleanValue result = new BooleanValue();
		result.value = value0 > value1;

		Debug.println("[doBody] result = " + result.value);

		ValueList values = new ValueList();
		values.addValue(result);
		outputParameters.getValue(0).values = values;
	} // doBody

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value new_() {
		// Create a new instance of this kind of function behavior execution.

		return new IntegerGreaterFunctionBehaviorExecution();
	} // new_

} // IntegerGreaterFunctionBehaviorExecution
