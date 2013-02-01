
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

import java.util.*;

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
 * <em><b>fUML::Semantics::Actions::BasicActions::ActionActivation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ActionActivation#run <em>run</em>}</li>
 * <li>{@link ActionActivation#takeOfferedTokens <em>takeOfferedTokens</em>}</li>
 * <li>{@link ActionActivation#fire <em>fire</em>}</li>
 * <li>{@link ActionActivation#terminate <em>terminate</em>}</li>
 * <li>{@link ActionActivation#isReady <em>isReady</em>}</li>
 * <li>{@link ActionActivation#isFiring <em>isFiring</em>}</li>
 * <li>{@link ActionActivation#doAction <em>doAction</em>}</li>
 * <li>{@link ActionActivation#sendOffers <em>sendOffers</em>}</li>
 * <li>{@link ActionActivation#createNodeActivations <em>createNodeActivations
 * </em>}</li>
 * <li>{@link ActionActivation#addOutgoingEdge <em>addOutgoingEdge</em>}</li>
 * <li>{@link ActionActivation#addPinActivation <em>addPinActivation</em>}</li>
 * <li>{@link ActionActivation#getPinActivation <em>getPinActivation</em>}</li>
 * <li>{@link ActionActivation#putToken <em>putToken</em>}</li>
 * <li>{@link ActionActivation#putTokens <em>putTokens</em>}</li>
 * <li>{@link ActionActivation#getTokens <em>getTokens</em>}</li>
 * <li>{@link ActionActivation#takeTokens <em>takeTokens</em>}</li>
 * <li>{@link ActionActivation#isSourceFor <em>isSourceFor</em>}</li>
 * <li>{@link ActionActivation#valueParticipatesInLink <em>
 * valueParticipatesInLink</em>}</li>
 * <li>{@link ActionActivation#makeBooleanValue <em>makeBooleanValue</em>}</li>
 * <li>{@link ActionActivation#pinActivations <em>pinActivations</em>}</li>
 * <li>{@link ActionActivation#firing <em>firing</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class ActionActivation extends
		fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation {

	public fUML.Semantics.Actions.BasicActions.PinActivationList pinActivations = new fUML.Semantics.Actions.BasicActions.PinActivationList();
	public boolean firing = false;

	/**
	 * operation run <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void run() {
		// Run this action activation and any outoging fork node attached to it.

		super.run();

		if (this.outgoingEdges.size() > 0) {
			this.outgoingEdges.getValue(0).target.run();
		}

		this.firing = false;
	} // run

	/**
	 * operation takeOfferedTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList takeOfferedTokens() {
		// If the action is not locally reentrant, then mark this activation as
		// firing.
		// Take any incoming offers of control tokens, then concurrently fire
		// all input pin activations.

		// Note: This is included here to happen in the same isolation scope as
		// the isReady test.
		this.firing = !((Action) this.node).isLocallyReentrant;

		TokenList offeredTokens = new TokenList();

		ActivityEdgeInstanceList incomingEdges = this.incomingEdges;
		for (int i = 0; i < incomingEdges.size(); i++) {
			ActivityEdgeInstance incomingEdge = incomingEdges.getValue(i);
			TokenList tokens = incomingEdge.takeOfferedTokens();
			for (int j = 0; j < tokens.size(); j++) {
				Token token = tokens.getValue(j);
				token.withdraw();
				offeredTokens.addValue(token);
			}
		}

		Action action = (Action) (this.node);

		// *** Fire all input pins concurrently. ***
		InputPinList inputPins = action.input;
		for (Iterator i = inputPins.iterator(); i.hasNext();) {
			InputPin pin = (InputPin) (i.next());
			PinActivation pinActivation = this.getPinActivation(pin);
			TokenList tokens = pinActivation.takeOfferedTokens();
			pinActivation.fire(tokens);
			for (int j = 0; j < tokens.size(); j++) {
				Token token = tokens.getValue(j);
				offeredTokens.addValue(token);
			}
		}

		return offeredTokens;
	} // takeOfferedTokens

	/**
	 * operation fire <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void fire(
			fUML.Semantics.Activities.IntermediateActivities.TokenList incomingTokens) {
		// Do the main action behavior then concurrently fire all output pin
		// activations
		// and offer a single control token. Then activate the action again,
		// if it is still ready to fire and has at least one token actually
		// being
		// offered to it.

		Action action = (Action) this.node;
		InputPinList inputPins = action.input;

		boolean fireAgain = false;
		do {

			Debug.println("[fire] Action " + this.node.name + "...");
			Debug.println("[event] Fire activity="
					+ this.getActivityExecution().getBehavior().name
					+ " action=" + this.node.name);

			this.doAction();
			this.sendOffers();

			Debug.println("[fire] Checking if " + this.node.name
					+ " should fire again...");

			_beginIsolation();
			fireAgain = false;
			this.firing = false;
			if (this.isReady()) {
				incomingTokens = this.takeOfferedTokens();
				fireAgain = incomingTokens.size() > 0;
				this.firing = this.isFiring() & fireAgain;
			}
			_endIsolation();

		} while (fireAgain);
	} // fire

	/**
	 * operation terminate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void terminate() {
		// Terminate this action activation and any outgoing fork node attached
		// to it.

		super.terminate();

		if (this.outgoingEdges.size() > 0) {
			this.outgoingEdges.getValue(0).target.terminate();
		}
	} // terminate

	/**
	 * operation isReady <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isReady() {
		// In addition to the default condition, check that, if the action has
		// isLocallyReentrant=false, then the activation is not currently
		// firing,
		// and that the sources of all incoming edges (control flows) have
		// offers and all input pin activations are ready.
		// [This assumes that all edges directly incoming to the action are
		// control flows.]

		boolean ready = super.isReady()
				& (((Action) this.node).isLocallyReentrant | !this.isFiring());

		int i = 1;
		while (ready & i <= this.incomingEdges.size()) {
			ready = this.incomingEdges.getValue(i - 1).hasOffer();
			i = i + 1;
		}

		InputPinList inputPins = ((Action) (this.node)).input;
		int j = 1;
		while (ready & j <= inputPins.size()) {
			ready = this.getPinActivation(inputPins.getValue(j - 1)).isReady();
			j = j + 1;
		}

		return ready;
	} // isReady

	/**
	 * operation isFiring <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isFiring() {
		// Indicate whether this action activation is currently firing or not.

		return firing;
	} // isFiring

	/**
	 * operation doAction <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract void doAction();

	/**
	 * operation sendOffers <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void sendOffers() {
		// Fire all output pins and send offers on all outgoing control flows.

		Action action = (Action) (this.node);

		// *** Send offers from all output pins concurrently. ***
		OutputPinList outputPins = action.output;
		for (Iterator i = outputPins.iterator(); i.hasNext();) {
			OutputPin outputPin = (OutputPin) i.next();
			PinActivation pinActivation = this.getPinActivation(outputPin);
			pinActivation.sendUnofferedTokens();
		}

		// Send offers on all outgoing control flows.
		if (this.outgoingEdges.size() > 0) {
			TokenList tokens = new TokenList();
			tokens.addValue(new ControlToken());
			this.addTokens(tokens);
			this.outgoingEdges.getValue(0).sendOffer(tokens);
		}
	} // sendOffers

	/**
	 * operation createNodeActivations <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void createNodeActivations() {
		// Create node activations for the input and output pins of the action
		// for this activation.
		// [Note: Pins are owned by their actions, not by the enclosing activity
		// (or group), so they must be activated through the action activation.]

		Action action = (Action) (this.node);

		ActivityNodeList inputPinNodes = new ActivityNodeList();
		InputPinList inputPins = action.input;
		for (int i = 0; i < inputPins.size(); i++) {
			InputPin inputPin = inputPins.getValue(i);
			inputPinNodes.addValue(inputPin);
		}

		this.group.createNodeActivations(inputPinNodes);

		for (int i = 0; i < inputPinNodes.size(); i++) {
			ActivityNode node = inputPinNodes.getValue(i);
			this.addPinActivation((PinActivation) (this.group
					.getNodeActivation(node)));
		}

		ActivityNodeList outputPinNodes = new ActivityNodeList();
		OutputPinList outputPins = action.output;
		for (int i = 0; i < outputPins.size(); i++) {
			OutputPin outputPin = outputPins.getValue(i);
			outputPinNodes.addValue(outputPin);
		}

		this.group.createNodeActivations(outputPinNodes);

		for (int i = 0; i < outputPinNodes.size(); i++) {
			ActivityNode node = outputPinNodes.getValue(i);
			this.addPinActivation((PinActivation) (this.group
					.getNodeActivation(node)));
		}
	} // createNodeActivations

	/**
	 * operation addOutgoingEdge <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOutgoingEdge(
			fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance edge) {
		// If there are no outgoing activity edge instances, create a single
		// activity edge instance with a fork node execution at the other end.
		// Add the give edge to the fork node execution that is the target of
		// the activity edge instance out of this action execution.
		// [This assumes that all edges directly outgoing from the action are
		// control flows, with an implicit fork for offers out of the action.]

		ActivityNodeActivation forkNodeActivation;

		if (this.outgoingEdges.size() == 0) {
			forkNodeActivation = new ForkNodeActivation();
			ActivityEdgeInstance newEdge = new ActivityEdgeInstance();
			super.addOutgoingEdge(newEdge);
			forkNodeActivation.addIncomingEdge(newEdge);
		} else {
			forkNodeActivation = this.outgoingEdges.getValue(0).target;
		}

		forkNodeActivation.addOutgoingEdge(edge);
	} // addOutgoingEdge

	/**
	 * operation addPinActivation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addPinActivation(
			fUML.Semantics.Actions.BasicActions.PinActivation pinActivation) {
		// Add a pin activation to this action activation.

		this.pinActivations.addValue(pinActivation);
		pinActivation.actionActivation = this;
	} // addPinActivation

	/**
	 * operation getPinActivation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Actions.BasicActions.PinActivation getPinActivation(
			fUML.Syntax.Actions.BasicActions.Pin pin) {
		// Precondition: The given pin is owned by the action of the action
		// activation.
		// Return the pin activation corresponding to the given pin.

		PinActivation pinActivation = null;
		int i = 1;
		while (pinActivation == null & i <= this.pinActivations.size()) {
			PinActivation thisPinActivation = this.pinActivations
					.getValue(i - 1);
			if (thisPinActivation.node == pin) {
				pinActivation = thisPinActivation;
			}
			i = i + 1;
		}

		return pinActivation;

	} // getPinActivation

	/**
	 * operation putToken <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void putToken(fUML.Syntax.Actions.BasicActions.OutputPin pin,
			fUML.Semantics.Classes.Kernel.Value value) {
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Place a token for the given value on the pin activation corresponding
		// to the given output pin.

		Debug.println("[putToken] node = " + this.node.name);

		ObjectToken token = new ObjectToken();
		token.value = value;
		PinActivation pinActivation = this.getPinActivation(pin);
		pinActivation.addToken(token);

	} // putToken

	/**
	 * operation putTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void putTokens(fUML.Syntax.Actions.BasicActions.OutputPin pin,
			fUML.Semantics.Classes.Kernel.ValueList values) {
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Place tokens for the given values on the pin activation corresponding
		// to the given output pin.

		// Debug.println("[putTokens] node = " + this.node.name);

		for (int i = 0; i < values.size(); i++) {
			Value value = values.getValue(i);
			this.putToken(pin, value);
		}

	} // putTokens

	/**
	 * operation getTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.ValueList getTokens(
			fUML.Syntax.Actions.BasicActions.InputPin pin) {
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Get any tokens held by the pin activation corresponding to the given
		// input pin and return them
		// (but leave the tokens on the pin).

		Debug.println("[getTokens] node = " + this.node.name + ", pin = "
				+ pin.name);

		PinActivation pinActivation = this.getPinActivation(pin);
		TokenList tokens = pinActivation.getUnofferedTokens();

		ValueList values = new ValueList();
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.getValue(i);
			Value value = ((ObjectToken) token).value;
			if (value != null) {
				values.addValue(value);
			}
		}

		return values;
	} // getTokens

	/**
	 * operation takeTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.ValueList takeTokens(
			fUML.Syntax.Actions.BasicActions.InputPin pin) {
		// Precondition: The action execution has fired and the given pin is
		// owned by the action of the action execution.
		// Take any tokens held by the pin activation corresponding to the given
		// input pin and return them.

		Debug.println("[takeTokens] node = " + this.node.name + ", pin = "
				+ pin.name);

		PinActivation pinActivation = this.getPinActivation(pin);
		TokenList tokens = pinActivation.takeUnofferedTokens();

		ValueList values = new ValueList();
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.getValue(i);
			Value value = ((ObjectToken) token).value;
			if (value != null) {
				values.addValue(value);
			}
		}

		return values;
	} // takeTokens

	/**
	 * operation isSourceFor <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSourceFor(
			fUML.Semantics.Activities.IntermediateActivities.ActivityEdgeInstance edgeInstance) {
		// If this action has an outgoing fork node, check that the fork node is
		// the source of the given edge instance.

		boolean isSource = false;
		if (this.outgoingEdges.size() > 0) {
			isSource = this.outgoingEdges.getValue(0).target
					.isSourceFor(edgeInstance);
		}

		return isSource;
	} // isSourceFor

	/**
	 * operation valueParticipatesInLink <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean valueParticipatesInLink(
			fUML.Semantics.Classes.Kernel.Value value,
			fUML.Semantics.Classes.Kernel.Link link) {
		// Test if the given value participates in the given link.

		FeatureValueList linkFeatureValues = link.getFeatureValues();

		boolean participates = false;
		int i = 1;
		while (!participates & i <= linkFeatureValues.size()) {
			participates = linkFeatureValues.getValue(i - 1).values.getValue(0)
					.equals(value);
			i = i + 1;
		}

		return participates;
	} // valueParticipatesInLink

	/**
	 * operation makeBooleanValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.BooleanValue makeBooleanValue(
			boolean value) {
		// Make a Boolean value using the built-in Boolean primitive type.
		// [This ensures that Boolean values created internally are the same as
		// the default used for evaluating Boolean literals.]

		LiteralBoolean booleanLiteral = new LiteralBoolean();
		booleanLiteral.value = value;
		return (BooleanValue) (this.getExecutionLocus().executor
				.evaluate(booleanLiteral));
	} // makeBooleanValue

} // ActionActivation
