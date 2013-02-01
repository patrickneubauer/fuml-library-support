
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
 * <em><b>fUML::Semantics::Activities::IntermediateActivities::ObjectNodeActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ObjectNodeActivation#run <em>run</em>}</li>
 * <li>{@link ObjectNodeActivation#sendOffers <em>sendOffers</em>}</li>
 * <li>{@link ObjectNodeActivation#terminate <em>terminate</em>}</li>
 * <li>{@link ObjectNodeActivation#addToken <em>addToken</em>}</li>
 * <li>{@link ObjectNodeActivation#removeToken <em>removeToken</em>}</li>
 * <li>{@link ObjectNodeActivation#clearTokens <em>clearTokens</em>}</li>
 * <li>{@link ObjectNodeActivation#countOfferedValues <em>countOfferedValues
 * </em>}</li>
 * <li>{@link ObjectNodeActivation#sendUnofferedTokens <em>sendUnofferedTokens
 * </em>}</li>
 * <li>{@link ObjectNodeActivation#countUnofferedTokens <em>countUnofferedTokens
 * </em>}</li>
 * <li>{@link ObjectNodeActivation#getUnofferedTokens <em>getUnofferedTokens
 * </em>}</li>
 * <li>{@link ObjectNodeActivation#takeUnofferedTokens <em>takeUnofferedTokens
 * </em>}</li>
 * <li>{@link ObjectNodeActivation#offeredTokenCount <em>offeredTokenCount</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class ObjectNodeActivation extends
		fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation {

	public int offeredTokenCount = 0;

	/**
	 * operation run <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void run() {
		// Initialize the offered token count to zero.

		super.run();
		this.offeredTokenCount = 0;
	} // run

	/**
	 * operation sendOffers <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void sendOffers(
			fUML.Semantics.Activities.IntermediateActivities.TokenList tokens) {
		// If the set of tokens to be sent is empty, then offer a null token
		// instead.
		// Otherwise, offer the given tokens as usual.

		if (tokens.size() == 0) {
			tokens.addValue(new ObjectToken());
		}

		super.sendOffers(tokens);
	} // sendOffers

	/**
	 * operation terminate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void terminate() {
		// Remove any offered tokens and terminate.

		this.clearTokens();
		super.terminate();
	} // terminate

	/**
	 * operation addToken <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addToken(
			fUML.Semantics.Activities.IntermediateActivities.Token token) {
		// Transfer the given token to be held by this node only if it is a
		// non-null object token.
		// If it is a control token or a null token, consume it without holding
		// it.

		if (token.getValue() == null) {
			token.withdraw();
		} else {
			super.addToken(token);
		}
	} // addToken

	/**
	 * operation removeToken <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int removeToken(
			fUML.Semantics.Activities.IntermediateActivities.Token token) {
		// Remove the given token, if it is held by this node activation.

		int i = super.removeToken(token);
		if (i > 0 & i <= this.offeredTokenCount) {
			this.offeredTokenCount = this.offeredTokenCount - 1;
		}

		return i;
	} // removeToken

	/**
	 * operation clearTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void clearTokens() {
		// Remove all held tokens.

		super.clearTokens();
		this.offeredTokenCount = 0;
	} // clearTokens

	/**
	 * operation countOfferedValues <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public int countOfferedValues() {
		// Count the total number of non-null object tokens being offered to
		// this node activation.

		int totalValueCount = 0;
		int i = 1;
		while (i <= this.incomingEdges.size()) {
			totalValueCount = totalValueCount
					+ this.incomingEdges.getValue(i - 1).countOfferedValues();
			i = i + 1;
		}

		return totalValueCount;
	} // countOfferedValues

	/**
	 * operation sendUnofferedTokens <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void sendUnofferedTokens() {
		// Send offers over all outgoing edges, if there are any tokens to be
		// offered.

		TokenList tokens = this.getUnofferedTokens();
		this.offeredTokenCount = this.offeredTokenCount + tokens.size();

		this.sendOffers(tokens);
	} // sendUnofferedTokens

	/**
	 * operation countUnofferedTokens <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public int countUnofferedTokens() {
		// Return the number of unoffered tokens that are to be offered next.
		// (By default, this is all unoffered tokens.)

		if (this.heldTokens.size() == 0) {
			this.offeredTokenCount = 0;
		}

		return this.heldTokens.size() - this.offeredTokenCount;
	} // countUnofferedTokens

	/**
	 * operation getUnofferedTokens <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList getUnofferedTokens() {
		// Get the next set of unoffered tokens to be offered and return it.
		// [Note: This effectively treats all object flows as if they have
		// weight=*, rather than the weight=1 default in the current
		// superstructure semantics.]

		TokenList tokens = new TokenList();

		int i = 1;
		while (i <= this.countUnofferedTokens()) {
			tokens.addValue(this.heldTokens.getValue(this.offeredTokenCount + i
					- 1));
			i = i + 1;
		}

		return tokens;
	} // getUnofferedTokens

	/**
	 * operation takeUnofferedTokens <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList takeUnofferedTokens() {
		// Take the next set of unoffered tokens to be offered from this node
		// activation and return them.

		TokenList tokens = this.getUnofferedTokens();
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.getValue(i);
			token.withdraw();
		}
		return tokens;
	} // takeUnofferedTokens

} // ObjectNodeActivation
