
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

package fUML.Semantics.Activities.IntermediateActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Actions.BasicActions.*;

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.Actions.BasicActions.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Activities::IntermediateActivities::ObjectToken</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ObjectToken#equals <em>equals</em>}</li>
 * <li>{@link ObjectToken#copy <em>copy</em>}</li>
 * <li>{@link ObjectToken#isControl <em>isControl</em>}</li>
 * <li>{@link ObjectToken#getValue <em>getValue</em>}</li>
 * <li>{@link ObjectToken#value <em>value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ObjectToken extends
		fUML.Semantics.Activities.IntermediateActivities.Token {

	public fUML.Semantics.Classes.Kernel.Value value = null;

	/**
	 * operation equals <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean equals(
			fUML.Semantics.Activities.IntermediateActivities.Token other) {
		// Test if this object token is the same as the other token.

		return this == other;
	} // equals

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.Token copy() {
		// Return a new object token with the same value as this token.
		// [Note: the holder of the copy is not set.]

		ObjectToken copy = new ObjectToken();
		copy.value = this.value;

		return copy;
	} // copy

	/**
	 * operation isControl <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isControl() {
		// Return false for an object token.

		return false;
	} // isControl

	/**
	 * operation getValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value getValue() {
		// Return the value of this object token.

		return this.value;
	} // getValue

} // ObjectToken
