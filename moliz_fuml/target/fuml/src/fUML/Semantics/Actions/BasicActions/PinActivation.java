
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
import fUML.Semantics.Activities.IntermediateActivities.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Actions::BasicActions::PinActivation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link PinActivation#fire <em>fire</em>}</li>
 * <li>{@link PinActivation#takeOfferedTokens <em>takeOfferedTokens</em>}</li>
 * <li>{@link PinActivation#actionActivation <em>actionActivation</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class PinActivation extends
		fUML.Semantics.Activities.IntermediateActivities.ObjectNodeActivation {

	public fUML.Semantics.Actions.BasicActions.ActionActivation actionActivation = null;

	/**
	 * operation fire <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void fire(
			fUML.Semantics.Activities.IntermediateActivities.TokenList incomingTokens) {
		// Add all incoming tokens to the pin.
		// [Note that a pin will consume all tokens offered to it, even if this
		// is more than the multiplicity upper bound, but will only offer tokens
		// up to that upper bound.]

		Debug.println("[fire] Pin "
				+ (this.node == null ? "" : this.node.name + "..."));

		this.addTokens(incomingTokens);

	} // fire

	/**
	 * operation takeOfferedTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList takeOfferedTokens() {
		// Take only a number of tokens only up to the limit allowed by
		// the multiplicity upper bound of the pin for this activation.

		int count = this.countUnofferedTokens();
		int upper = -1;

		// Note: A pin activation used in an expansion activation group
		// will have this.node == null.
		if (this.node != null) {
			upper = ((Pin) (this.node)).multiplicityElement.upper.naturalValue;
		}

		TokenList tokens = new TokenList();

		// Note: upper < 0 indicates an unbounded upper multiplicity.
		if (upper < 0 | count < upper) {
			ActivityEdgeInstanceList incomingEdges = this.incomingEdges;
			for (int i = 0; i < incomingEdges.size(); i++) {
				ActivityEdgeInstance edge = incomingEdges.getValue(i);
				int incomingCount = edge.countOfferedValues();
				TokenList incomingTokens = new TokenList();
				if (upper < 0 | incomingCount < upper - count) {
					incomingTokens = edge.takeOfferedTokens();
					count = count + incomingCount;
				} else if (count < upper) {
					incomingTokens = edge.takeOfferedTokens(upper - count);
					count = upper;
				}
				for (int j = 0; j < incomingTokens.size(); j++) {
					Token token = incomingTokens.getValue(j);
					tokens.addValue(token);
				}
			}
		}

		return tokens;
	} // takeOfferedTokens

} // PinActivation
