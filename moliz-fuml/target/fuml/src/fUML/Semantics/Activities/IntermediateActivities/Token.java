
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
 * <em><b>fUML::Semantics::Activities::IntermediateActivities::Token</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Token#transfer <em>transfer</em>}</li>
 * <li>{@link Token#withdraw <em>withdraw</em>}</li>
 * <li>{@link Token#equals <em>equals</em>}</li>
 * <li>{@link Token#copy <em>copy</em>}</li>
 * <li>{@link Token#isWithdrawn <em>isWithdrawn</em>}</li>
 * <li>{@link Token#isControl <em>isControl</em>}</li>
 * <li>{@link Token#getValue <em>getValue</em>}</li>
 * <li>{@link Token#holder <em>holder</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Token extends org.modeldriven.fuml.FumlObject {

	public fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation holder = null;

	/**
	 * operation transfer <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.Token transfer(
			fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation holder) {
		// if this token does not have any holder, make the given holder its
		// holder.
		// Otherwise, remove this token from its holder and return a copy of it
		// transfered to a new holder.

		Token token = this;
		if (this.holder != null) {
			this.withdraw();
			token = this.copy();
		}

		token.holder = holder;
		return token;
	} // transfer

	/**
	 * operation withdraw <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void withdraw() {
		// Remove this token from its holder, withdrawing any offers for it.

		if (!this.isWithdrawn()) {
			// Debug.println("[withdraw] Taking token with value = " +
			// this.getValue());
			this.holder.removeToken(this);
			this.holder = null;
		}
	} // withdraw

	/**
	 * operation equals <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract boolean equals(
			fUML.Semantics.Activities.IntermediateActivities.Token other);

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract fUML.Semantics.Activities.IntermediateActivities.Token copy();

	/**
	 * operation isWithdrawn <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isWithdrawn() {
		// Test if this token has been withdrawn.

		return this.holder == null;
	} // isWithdrawn

	/**
	 * operation isControl <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract boolean isControl();

	/**
	 * operation getValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract fUML.Semantics.Classes.Kernel.Value getValue();
} // Token
