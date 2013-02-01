
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
 * <em><b>fUML::Semantics::Activities::IntermediateActivities::ForkNodeActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ForkNodeActivation#fire <em>fire</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ForkNodeActivation extends
		fUML.Semantics.Activities.IntermediateActivities.ControlNodeActivation {

	/**
	 * operation fire <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void fire(
			fUML.Semantics.Activities.IntermediateActivities.TokenList incomingTokens) {
		// Create forked tokens for all incoming tokens and offer them on all
		// outgoing edges.

		if (this.node == null) {
			Debug.println("[fire] Anonymous fork node.");
		} else {
			Debug.println("[fire] Fork node " + this.node.name + "...");
		}

		ActivityEdgeInstanceList outgoingEdges = this.outgoingEdges;
		int outgoingEdgeCount = outgoingEdges.size();

		TokenList forkedTokens = new TokenList();
		for (int i = 0; i < incomingTokens.size(); i++) {
			Token token = incomingTokens.getValue(i);
			ForkedToken forkedToken = new ForkedToken();
			forkedToken.baseToken = token;
			forkedToken.remainingOffersCount = outgoingEdgeCount;
			forkedTokens.addValue(forkedToken);
		}

		this.addTokens(forkedTokens);

		this.sendOffers(forkedTokens);
	} // fire

} // ForkNodeActivation
