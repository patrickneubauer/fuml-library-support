
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

package fUML.Semantics.Classes.Kernel;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;

import fUML.Semantics.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Classes::Kernel::DataValue</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link DataValue#getTypes <em>getTypes</em>}</li>
 * <li>{@link DataValue#copy <em>copy</em>}</li>
 * <li>{@link DataValue#new_ <em>new_</em>}</li>
 * <li>{@link DataValue#type <em>type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class DataValue extends fUML.Semantics.Classes.Kernel.CompoundValue {

	public fUML.Syntax.Classes.Kernel.DataType type = null;

	/**
	 * operation getTypes <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.ClassifierList getTypes() {
		// Return the single type of this data value.

		ClassifierList types = new ClassifierList();
		types.addValue(this.type);

		return types;
	} // getTypes

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value copy() {
		// Create a new data value with the same type and feature values as this
		// data value.

		DataValue newValue = (DataValue) (super.copy());

		newValue.type = this.type;

		return newValue;
	} // copy

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Semantics.Classes.Kernel.Value new_() {
		// Create a new data value with no type or feature values.

		return new DataValue();
	} // new_

} // DataValue
