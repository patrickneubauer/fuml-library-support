
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
 * <em><b>fUML::Semantics::Classes::Kernel::Value</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Value#specify <em>specify</em>}</li>
 * <li>{@link Value#equals <em>equals</em>}</li>
 * <li>{@link Value#copy <em>copy</em>}</li>
 * <li>{@link Value#new_ <em>new_</em>}</li>
 * <li>{@link Value#getTypes <em>getTypes</em>}</li>
 * <li>{@link Value#hasType <em>hasType</em>}</li>
 * <li>{@link Value#toString <em>toString</em>}</li>
 * <li>{@link Value#objectId <em>objectId</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Value extends fUML.Semantics.Loci.LociL1.SemanticVisitor {

	/**
	 * operation specify <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract fUML.Syntax.Classes.Kernel.ValueSpecification specify();

	/**
	 * operation equals <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean equals(fUML.Semantics.Classes.Kernel.Value otherValue) {
		// Test if this value is equal to otherValue. To be equal, this value
		// must have the same type as otherValue.
		// This operation must be overridden in Value subclasses to check for
		// equality of properties defined in those subclasses.

		ClassifierList myTypes = this.getTypes();
		ClassifierList otherTypes = otherValue.getTypes();

		boolean isEqual = true;

		// Debug.println("[equals] Value...");
		// Debug.println("[equals] this has " + myTypes.size() +
		// "types, other has " + otherTypes.size() + ".");
		if (myTypes.size() != otherTypes.size()) {
			isEqual = false;

		} else {
			// Debug.println("[equals] " + myTypes.size() + " type(s).");

			int i = 1;
			while (isEqual & i <= myTypes.size()) {

				// Debug.println("[equals] this type = " +
				// myTypes.getValue(i-1).name);

				boolean matched = false;
				int j = 1;
				while (!matched & j <= otherTypes.size()) {
					// Debug.println("[equals] other type = " +
					// otherTypes.getValue(j-1).name);
					matched = (otherTypes.getValue(j - 1) == myTypes
							.getValue(i - 1));
					j = j + 1;
				}

				isEqual = matched;
				i = i + 1;
			}
		}

		return isEqual;
	} // equals

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value copy() {
		// Create a new value that is equal to this value.
		// By default, this operation simply creates a new value with empty
		// properties.
		// It must be overridden in each Value subclass to do the superclass
		// copy and then appropriately set properties defined in the subclass.

		return this.new_();
	} // copy

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected abstract fUML.Semantics.Classes.Kernel.Value new_();

	/**
	 * operation getTypes <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract fUML.Syntax.Classes.Kernel.ClassifierList getTypes();

	/**
	 * operation hasType <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean hasType(fUML.Syntax.Classes.Kernel.Classifier type) {
		// Check if this object has the given classifier as a type.

		ClassifierList types = this.getTypes();

		boolean found = false;
		int i = 1;
		while (!found & i <= types.size()) {
			found = (types.getValue(i - 1) == type);
			i = i + 1;
		}

		return found;
	} // hasType

	/**
	 * operation toString <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract String toString();

	/**
	 * operation objectId <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String objectId() {
		// Return an identifier for this object.
		// [Non-normative.]

		return super.toString();
	} // objectId

} // Value
