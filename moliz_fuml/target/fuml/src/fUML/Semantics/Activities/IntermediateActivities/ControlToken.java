
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
 * <em><b>fUML::Semantics::Activities::IntermediateActivities::ControlToken</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ControlToken#equals <em>equals</em>}</li>
 * <li>{@link ControlToken#copy <em>copy</em>}</li>
 * <li>{@link ControlToken#isControl <em>isControl</em>}</li>
 * <li>{@link ControlToken#getValue <em>getValue</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ControlToken extends
		fUML.Semantics.Activities.IntermediateActivities.Token {

	/**
	 * operation equals <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean equals(
			fUML.Semantics.Activities.IntermediateActivities.Token other) {
		// Return true if the other token is a control token, because control
		// tokens are interchangable.

		return other instanceof ControlToken;

	} // equals

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.Token copy() {
		// Return a new control token.

		return new ControlToken();
	} // copy

	/**
	 * operation isControl <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isControl() {
		// Return true for a control token.

		return true;
	} // isControl

	/**
	 * operation getValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value getValue() {
		// Control tokens do not have values.

		return null;
	} // getValue

} // ControlToken
