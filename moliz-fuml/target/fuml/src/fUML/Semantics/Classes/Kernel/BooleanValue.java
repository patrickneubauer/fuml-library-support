
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
 * <em><b>fUML::Semantics::Classes::Kernel::BooleanValue</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link BooleanValue#specify <em>specify</em>}</li>
 * <li>{@link BooleanValue#equals <em>equals</em>}</li>
 * <li>{@link BooleanValue#copy <em>copy</em>}</li>
 * <li>{@link BooleanValue#new_ <em>new_</em>}</li>
 * <li>{@link BooleanValue#toString <em>toString</em>}</li>
 * <li>{@link BooleanValue#value <em>value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class BooleanValue extends fUML.Semantics.Classes.Kernel.PrimitiveValue {

	public boolean value = false;

	/**
	 * operation specify <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.ValueSpecification specify() {
		// Return a literal boolean with the value of this boolean value.

		LiteralBoolean literal = new LiteralBoolean();

		literal.type = this.type;
		literal.value = this.value;

		return literal;
	} // specify

	/**
	 * operation equals <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean equals(fUML.Semantics.Classes.Kernel.Value otherValue) {
		// Test if this boolean value is equal to the otherValue.
		// To be equal, the otherValue must have the same value as this boolean
		// value.

		boolean isEqual = false;
		if (otherValue instanceof BooleanValue) {
			isEqual = ((BooleanValue) otherValue).value == this.value;
		}

		return isEqual;
	} // equals

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value copy() {
		// Create a new boolean value with the same value as this boolean value.

		BooleanValue newValue = (BooleanValue) (super.copy());

		newValue.value = this.value;
		return newValue;
	} // copy

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Semantics.Classes.Kernel.Value new_() {
		// Return a new boolean value with no value.

		return new BooleanValue();
	} // new_

	/**
	 * operation toString <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String toString() {
		String stringValue = "false";

		if (this.value) {
			stringValue = "true";
		}

		return stringValue;

	} // toString

} // BooleanValue
