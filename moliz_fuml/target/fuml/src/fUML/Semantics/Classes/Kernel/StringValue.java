
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
 * <em><b>fUML::Semantics::Classes::Kernel::StringValue</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link StringValue#specify <em>specify</em>}</li>
 * <li>{@link StringValue#equals <em>equals</em>}</li>
 * <li>{@link StringValue#copy <em>copy</em>}</li>
 * <li>{@link StringValue#new_ <em>new_</em>}</li>
 * <li>{@link StringValue#toString <em>toString</em>}</li>
 * <li>{@link StringValue#value <em>value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class StringValue extends fUML.Semantics.Classes.Kernel.PrimitiveValue {

	public String value = "";

	/**
	 * operation specify <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.ValueSpecification specify() {
		// Return a literal string with the value of this string value.

		LiteralString literal = new LiteralString();

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
		// Test if this string value is equal to the otherValue.
		// To be equal, the otherValue must have the same value as this string
		// value.

		boolean isEqual = false;
		if (otherValue instanceof StringValue) {
			isEqual = ((StringValue) otherValue).value.equals(this.value);
		}

		return isEqual;
	} // equals

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value copy() {
		// Create a new string value with the same value as this string value.

		StringValue newValue = (StringValue) (super.copy());

		newValue.value = this.value;
		return newValue;
	} // copy

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Semantics.Classes.Kernel.Value new_() {
		// Create a new string value with no value.

		return new StringValue();
	} // new_

	/**
	 * operation toString <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String toString() {
		return value;
	} // toString

} // StringValue
