
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

import java.util.Iterator;

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
 * <em><b>fUML::Semantics::Activities::IntermediateActivities::ActivityNodeActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ActivityNodeActivation#run <em>run</em>}</li>
 * <li>{@link ActivityNodeActivation#receiveOffer <em>receiveOffer</em>}</li>
 * <li>{@link ActivityNodeActivation#takeOfferedTokens <em>takeOfferedTokens
 * </em>}</li>
 * <li>{@link ActivityNodeActivation#fire <em>fire</em>}</li>
 * <li>{@link ActivityNodeActivation#sendOffers <em>sendOffers</em>}</li>
 * <li>{@link ActivityNodeActivation#terminate <em>terminate</em>}</li>
 * <li>{@link ActivityNodeActivation#isReady <em>isReady</em>}</li>
 * <li>{@link ActivityNodeActivation#isRunning <em>isRunning</em>}</li>
 * <li>{@link ActivityNodeActivation#addOutgoingEdge <em>addOutgoingEdge</em>}</li>
 * <li>{@link ActivityNodeActivation#addIncomingEdge <em>addIncomingEdge</em>}</li>
 * <li>{@link ActivityNodeActivation#createNodeActivations <em>
 * createNodeActivations</em>}</li>
 * <li>{@link ActivityNodeActivation#createEdgeInstances <em>createEdgeInstances
 * </em>}</li>
 * <li>{@link ActivityNodeActivation#isSourceFor <em>isSourceFor</em>}</li>
 * <li>{@link ActivityNodeActivation#getActivityExecution <em>
 * getActivityExecution</em>}</li>
 * <li>{@link ActivityNodeActivation#getExecutionContext <em>getExecutionContext
 * </em>}</li>
 * <li>{@link ActivityNodeActivation#getExecutionLocus <em>getExecutionLocus
 * </em>}</li>
 * <li>{@link ActivityNodeActivation#getNodeActivation <em>getNodeActivation
 * </em>}</li>
 * <li>{@link ActivityNodeActivation#addToken <em>addToken</em>}</li>
 * <li>{@link ActivityNodeActivation#removeToken <em>removeToken</em>}</li>
 * <li>{@link ActivityNodeActivation#addTokens <em>addTokens</em>}</li>
 * <li>{@link ActivityNodeActivation#takeTokens <em>takeTokens</em>}</li>
 * <li>{@link ActivityNodeActivation#clearTokens <em>clearTokens</em>}</li>
 * <li>{@link ActivityNodeActivation#getTokens <em>getTokens</em>}</li>
 * <li>{@link ActivityNodeActivation#group <em>group</em>}</li>
 * <li>{@link ActivityNodeActivation#node <em>node</em>}</li>
 * <li>{@link ActivityNodeActivation#incomingEdges <em>incomingEdges</em>}</li>
 * <li>{@link ActivityNodeActivation#outgoingEdges <em>outgoingEdges</em>}</li>
 * <li>{@link ActivityNodeActivation#running <em>running</em>}</li>
 * <li>{@link ActivityNodeActivation#heldTokens <em>heldTokens</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class ActivityNodeActivation extends
		fUML.Semantics.Loci.LociL1.SemanticVisitor {

	public fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationGroup group = null;
	public fUML.Syntax.Activities.IntermediateActivities.ActivityNode node = null;
	public fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstanceList incomingEdges = new fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstanceList();
	public fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstanceList outgoingEdges = new fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstanceList();
	public boolean running = false;
	public fUML.Semantics.Activities.IntermediateActivities.TokenList heldTokens = new fUML.Semantics.Activities.IntermediateActivities.TokenList();

	/**
	 * operation run <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void run() {
		// Run the activation of this node.

		if (this.node != null) {
			Debug.println("[run] node = " + this.node.name);
		} else {
			Debug.println("[run] Anonymous activation of type "
					+ this.getClass().getName());
		}

		this.running = true;
	} // run

	/**
	 * operation receiveOffer <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void receiveOffer() {
		// Receive an offer from an incoming edge.
		// Check if all prerequisites have been satisfied. If so, fire.

		if (this.node != null) {
			Debug.println("[receiveOffer] node = " + this.node.name);
		}

		_beginIsolation();

		boolean ready = this.isReady();

		TokenList tokens = new TokenList();
		if (ready) {
			Debug.println("[receiveOffer] Firing.");
			tokens = this.takeOfferedTokens();
		}

		_endIsolation();

		if (ready) {
			this.fire(tokens);
		}

	} // receiveOffer

	/**
	 * operation takeOfferedTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList takeOfferedTokens() {
		// Get tokens from all incoming edges.

		TokenList allTokens = new TokenList();
		ActivityEdgeInstanceList incomingEdges = this.incomingEdges;
		for (int i = 0; i < incomingEdges.size(); i++) {
			ActivityEdgeInstance incomingEdge = incomingEdges.getValue(i);
			TokenList tokens = incomingEdge.takeOfferedTokens();
			for (int j = 0; j < tokens.size(); j++) {
				Token token = tokens.getValue(j);
				allTokens.addValue(token);
			}
		}

		return allTokens;
	} // takeOfferedTokens

	/**
	 * operation fire <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract void fire(
			fUML.Semantics.Activities.IntermediateActivities.TokenList incomingTokens);

	/**
	 * operation sendOffers <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void sendOffers(
			fUML.Semantics.Activities.IntermediateActivities.TokenList tokens) {
		// Send offers for the given set of tokens over all outgoing edges (if
		// there are any tokens actually being offered).

		if (tokens.size() > 0) {

			// *** Send all outgoing offers concurrently. ***
			ActivityEdgeInstanceList outgoingEdges = this.outgoingEdges;
			for (Iterator i = outgoingEdges.iterator(); i.hasNext();) {
				ActivityEdgeInstance outgoingEdge = (ActivityEdgeInstance) i
						.next();
				// Debug.println("[sendOffers] Sending offer to " +
				// outgoingEdge.target.node.name + ".");
				outgoingEdge.sendOffer(tokens);
			}

		}

	} // sendOffers

	/**
	 * operation terminate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void terminate() {
		// Terminate the activation of this node.

		if (this.running) {
			if (this.node != null) {
				Debug.println("[terminate] node = " + this.node.name);
			} else {
				Debug.println("[terminate] Anonymous activation of type "
						+ this.getClass().getName());
			}
		}

		this.running = false;
	} // terminate

	/**
	 * operation isReady <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isReady() {
		// Check if all the prerequisites for this node have been satisfied.
		// By default, check that this node is running.

		return this.isRunning();

	} // isReady

	/**
	 * operation isRunning <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isRunning() {
		// Test whether this node activation is running.

		return this.running;
	} // isRunning

	/**
	 * operation addOutgoingEdge <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOutgoingEdge(
			fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance edge) {
		// Add an activity edge instance as an outgoing edge of this activity
		// node activation.

		edge.source = this;
		this.outgoingEdges.addValue(edge);
	} // addOutgoingEdge

	/**
	 * operation addIncomingEdge <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addIncomingEdge(
			fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance edge) {
		// Add an activity edge instance as an incoming edge of this activity
		// node activation.

		edge.target = this;
		this.incomingEdges.addValue(edge);
	} // addIncomingEdge

	/**
	 * operation createNodeActivations <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void createNodeActivations() {
		// Create node activations for any subnodes of the node for this
		// activation.
		// For most kinds of nodes, this does nothing.

		return;

	} // createNodeActivations

	/**
	 * operation createEdgeInstances <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void createEdgeInstances() {
		// Create edge instances for any edge instances owned by the node for
		// this activation.
		// For most kinds of nodes, this does nothing.

		return;

	} // createEdgeInstances

	/**
	 * operation isSourceFor <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSourceFor(
			fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance edgeInstance) {
		// Check if this node activation is the effective source for the given
		// edge instance.

		return edgeInstance.source == this;
	} // isSourceFor

	/**
	 * operation getActivityExecution <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.ActivityExecution getActivityExecution() {
		// Return the activity execution that contains this activity node
		// activation, directly or indirectly.

		return this.group.getActivityExecution();
	} // getActivityExecution

	/**
	 * operation getExecutionContext <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Object_ getExecutionContext() {
		// Get the context object for the containing activity execution.

		return this.getActivityExecution().context;
	} // getExecutionContext

	/**
	 * operation getExecutionLocus <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Loci.LociL1.Locus getExecutionLocus() {
		// Get the locus of the containing activity execution.

		return this.getActivityExecution().locus;
	} // getExecutionLocus

	/**
	 * operation getNodeActivation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation getNodeActivation(
			fUML.Syntax.Activities.IntermediateActivities.ActivityNode node) {
		// Get the activity node activation corresponding to the given activity
		// node, in the context of this activity node activation.
		// By default, return this activity node activation, if it is for the
		// given node, otherwise return nothing.

		ActivityNodeActivation activation = null;
		if (node == this.node) {
			activation = this;
		}

		return activation;
	} // getNodeActivation

	/**
	 * operation addToken <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addToken(
			fUML.Semantics.Activities.IntermediateActivities.Token token) {
		// Transfer the given token to be held by this node.

		if (this.node == null) {
			Debug.println("[addToken] ...");
		} else {
			Debug.println("[addToken] node = " + this.node.name);
		}

		Token transferredToken = token.transfer(this);
		// Debug.println("[addToken] Adding token with value = " +
		// transferredToken.getValue());
		this.heldTokens.addValue(transferredToken);
	} // addToken

	/**
	 * operation removeToken <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int removeToken(
			fUML.Semantics.Activities.IntermediateActivities.Token token) {
		// Remove the given token, if it is held by this node activation.
		// Return the position (counting from 1) of the removed token (0 if
		// there is none removed).

		boolean notFound = true;
		int i = 1;
		while (notFound & i <= this.heldTokens.size()) {
			if (this.heldTokens.getValue(i - 1) == token) {
				if (this.node == null) {
					Debug.println("[removeToken] ...");
				} else {
					Debug.println("[removeToken] node = " + this.node.name);
				}
				this.heldTokens.remove(i - 1);
				notFound = false;
			}
			i = i + 1;
		}

		if (notFound) {
			i = 0;
		} else {
			i = i - 1;
		}

		return i;
	} // removeToken

	/**
	 * operation addTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addTokens(
			fUML.Semantics.Activities.IntermediateActivities.TokenList tokens) {
		// Transfer the given tokens to be the held tokens for this node.

		// if (this.node == null) {
		// Debug.println("[addTokens] ...");
		// } else {
		// Debug.println("[addTokens] node = " + this.node.name);
		// }

		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.getValue(i);
			this.addToken(token);
		}
	} // addTokens

	/**
	 * operation takeTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList takeTokens() {
		// Take the tokens held by this node activation.

		TokenList tokens = this.getTokens();
		this.clearTokens();

		return tokens;
	} // takeTokens

	/**
	 * operation clearTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void clearTokens() {
		// Remove all held tokens.

		while (this.heldTokens.size() > 0) {
			this.heldTokens.getValue(0).withdraw();
		}

	} // clearTokens

	/**
	 * operation getTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList getTokens() {
		// Get the tokens held by this node activation.

		// Debug.println("[getTokens] node = " + this.node.name);

		TokenList tokens = new TokenList();
		TokenList heldTokens = this.heldTokens;
		for (int i = 0; i < heldTokens.size(); i++) {
			Token heldToken = heldTokens.getValue(i);
			// Debug.println("[getTokens] token value = " +
			// heldTokens.getValue());
			tokens.addValue(heldToken);
		}

		return tokens;
	} // getTokens

} // ActivityNodeActivation
