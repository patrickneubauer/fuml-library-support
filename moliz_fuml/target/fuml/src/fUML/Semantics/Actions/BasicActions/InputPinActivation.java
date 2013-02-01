
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

package fUML.Semantics.Actions.BasicActions;

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
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Actions::BasicActions::InputPinActivation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link InputPinActivation#receiveOffer <em>receiveOffer</em>}</li>
 * <li>{@link InputPinActivation#isReady <em>isReady</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class InputPinActivation extends
		fUML.Semantics.Actions.BasicActions.PinActivation {

	/**
	 * operation receiveOffer <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void receiveOffer() {
		// Forward the offer to the action activation. [When all input pins are
		// ready, the action will fire them.]

		this.actionActivation.receiveOffer();
	} // receiveOffer

	/**
	 * operation isReady <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isReady() {
		// Return true if the total number of values already being offered by
		// this pin plus those being offered
		// by the sources of incoming edges is at least equal to the minimum
		// multiplicity of the pin.

		boolean ready = super.isReady();
		if (ready) {
			int totalValueCount = this.countUnofferedTokens()
					+ this.countOfferedValues();
			int minimum = ((Pin) this.node).multiplicityElement.lower;
			ready = totalValueCount >= minimum;
		}

		return ready;
	} // isReady

} // InputPinActivation
