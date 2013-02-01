
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
 * <em><b>fUML::Semantics::Classes::Kernel::EnumerationValue</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link EnumerationValue#specify <em>specify</em>}</li>
 * <li>{@link EnumerationValue#equals <em>equals</em>}</li>
 * <li>{@link EnumerationValue#copy <em>copy</em>}</li>
 * <li>{@link EnumerationValue#new_ <em>new_</em>}</li>
 * <li>{@link EnumerationValue#getTypes <em>getTypes</em>}</li>
 * <li>{@link EnumerationValue#toString <em>toString</em>}</li>
 * <li>{@link EnumerationValue#literal <em>literal</em>}</li>
 * <li>{@link EnumerationValue#type <em>type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class EnumerationValue extends fUML.Semantics.Classes.Kernel.Value {

	public fUML.Syntax.Classes.Kernel.EnumerationLiteral literal = null;
	public fUML.Syntax.Classes.Kernel.Enumeration type = null;

	/**
	 * operation specify <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.ValueSpecification specify() {
		// Return an instance value with literal as the instance.

		InstanceValue instanceValue = new InstanceValue();
		InstanceSpecification instance = new InstanceSpecification();

		instanceValue.type = this.type;
		instanceValue.instance = this.literal;

		return instanceValue;
	} // specify

	/**
	 * operation equals <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean equals(fUML.Semantics.Classes.Kernel.Value otherValue) {
		// Test if this enumeration value is equal to the otherValue.
		// To be equal, the otherValue must also be an enumeration value with
		// the same literal as this enumeration value.

		boolean isEqual = false;
		if (otherValue instanceof EnumerationValue) {
			isEqual = ((EnumerationValue) otherValue).literal == this.literal;
		}

		return isEqual;
	} // equals

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value copy() {
		// Create a new enumeration value with the same literal as this
		// enumeration value.

		EnumerationValue newValue = (EnumerationValue) (super.copy());

		newValue.type = this.type;
		newValue.literal = this.literal;

		return newValue;
	} // copy

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Semantics.Classes.Kernel.Value new_() {
		// Create a new enumeration value with no literal.

		return new EnumerationValue();
	} // new_

	/**
	 * operation getTypes <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.ClassifierList getTypes() {
		// Return the single type of this enumeration value.

		ClassifierList types = new ClassifierList();
		types.addValue(this.type);

		return types;
	} // getTypes

	/**
	 * operation toString <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String toString() {
		return literal.name;
	} // toString

} // EnumerationValue
