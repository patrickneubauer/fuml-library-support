
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

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * 
 * <em><b>fUML::Library::IntegerFunctionImplementation::IntegerMinusFunctionBehaviorExecution</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link IntegerMinusFunctionBehaviorExecution#doIntegerFunction <em>
 * doIntegerFunction</em>}</li>
 * <li>{@link IntegerMinusFunctionBehaviorExecution#new_ <em>new_</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class IntegerMinusFunctionBehaviorExecution
		extends
		fUML.Library.IntegerFunctionImplementation.IntegerFunctionBehaviorExecution {

	/**
	 * operation doIntegerFunction <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int doIntegerFunction(UMLPrimitiveTypes.intList arguments) {
		// Compute the integer minus function.

		return arguments.getValue(0) - arguments.getValue(1);
	} // doIntegerFunction

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value new_() {
		// Create a new instance of this kind of function behavior execution.

		return new IntegerMinusFunctionBehaviorExecution();
	} // new_

} // IntegerMinusFunctionBehaviorExecution
