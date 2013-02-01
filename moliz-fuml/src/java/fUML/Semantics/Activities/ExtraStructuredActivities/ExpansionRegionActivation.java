package fUML.Semantics.Activities.ExtraStructuredActivities;


import fUML.Debug;
import UMLPrimitiveTypes.*;

import java.util.Iterator;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Activities.CompleteStructuredActivities.*;
import fUML.Syntax.Activities.ExtraStructuredActivities.*;
import fUML.Syntax.Actions.BasicActions.*;

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.CommonBehaviors.Communications.*;
import fUML.Semantics.Activities.IntermediateActivities.*;
import fUML.Semantics.Activities.CompleteStructuredActivities.*;
import fUML.Semantics.Actions.BasicActions.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Activities::ExtraStructuredActivities::ExpansionRegionActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ExpansionRegionActivation#takeOfferedTokens <em>takeOfferedTokens
 * </em>}</li>
 * <li>{@link ExpansionRegionActivation#doAction <em>doAction</em>}</li>
 * <li>{@link ExpansionRegionActivation#doStructuredActivity <em>
 * doStructuredActivity</em>}</li>
 * <li>{@link ExpansionRegionActivation#terminate <em>terminate</em>}</li>
 * <li>{@link ExpansionRegionActivation#isReady <em>isReady</em>}</li>
 * <li>{@link ExpansionRegionActivation#sendOffers <em>sendOffers</em>}</li>
 * <li>{@link ExpansionRegionActivation#runGroup <em>runGroup</em>}</li>
 * <li>{@link ExpansionRegionActivation#getExpansionNodeActivation <em>
 * getExpansionNodeActivation</em>}</li>
 * <li>{@link ExpansionRegionActivation#numberOfValues <em>numberOfValues</em>}</li>
 * <li>{@link ExpansionRegionActivation#activationGroups <em>activationGroups
 * </em>}</li>
 * <li>{@link ExpansionRegionActivation#inputTokens <em>inputTokens</em>}</li>
 * <li>{@link ExpansionRegionActivation#inputExpansionTokens <em>
 * inputExpansionTokens</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ExpansionRegionActivation extends
		fUML.Semantics.Actions.BasicActions.ActionActivation {

	public fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroupList activationGroups = new fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroupList();
	public fUML.Semantics.Activities.ExtraStructuredActivities.TokenSetList inputTokens = new fUML.Semantics.Activities.ExtraStructuredActivities.TokenSetList();
	public fUML.Semantics.Activities.ExtraStructuredActivities.TokenSetList inputExpansionTokens = new fUML.Semantics.Activities.ExtraStructuredActivities.TokenSetList();

	/**
	 * operation takeOfferedTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList takeOfferedTokens() {
		// Take the tokens from the input pin and input expansion node
		// activations and save them.
		
		super.takeOfferedTokens();

		ExpansionRegion region = (ExpansionRegion) (this.node);
		InputPinList inputPins = region.input;
		ExpansionNodeList inputElements = region.inputElement;

		this.inputTokens.clear();
		this.inputExpansionTokens.clear();

		for (int i = 0; i < inputPins.size(); i++) {
			InputPin inputPin = inputPins.getValue(i);
			TokenSet tokenSet = new TokenSet();
			tokenSet.tokens = this.getPinActivation(inputPin).takeTokens();
			this.inputTokens.addValue(tokenSet);
		}

		for (int i = 0; i < inputElements.size(); i++) {
			ExpansionNode inputElement = inputElements.getValue(i);
			ExpansionNodeActivation expansionNodeActivation = this
					.getExpansionNodeActivation(inputElement);
			expansionNodeActivation.fire(expansionNodeActivation
					.takeOfferedTokens());
			TokenSet tokenSet = new TokenSet();
			tokenSet.tokens = expansionNodeActivation.takeTokens();
			this.inputExpansionTokens.addValue(tokenSet);
		}

		return new TokenList();
	} // takeOfferedTokens

	/**
	 * operation doAction <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void doAction() {
		// If the expansion region has mustIsolate=true, then carry out its
		// behavior with isolation.
		// Otherwise just activate it normally.

		if (((StructuredActivityNode) (this.node)).mustIsolate) {
			_beginIsolation();
			this.doStructuredActivity();
			_endIsolation();
		} else {
			this.doStructuredActivity();
		}

	} // doAction

	/**
	 * operation doStructuredActivity <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void doStructuredActivity() {
		// Create a number of expansion region activation groups equal to the
		// number of values expanded in the region,
		// setting the region inputs and group inputs for each group.
		// Run the body of the region in each group, either iteratively or in
		// parallel.
		// Add the outputs of each activation group to the corresponding output
		// expansion node activations.

		ExpansionRegion region = (ExpansionRegion) this.node;
		InputPinList inputPins = region.input;
		ExpansionNodeList inputElements = region.inputElement;
		ExpansionNodeList outputElements = region.outputElement;

		int n = this.inputExpansionTokens.getValue(0).tokens.size();
		int k = 1;
		while (k <= n) {
			ExpansionActivationGroup activationGroup = new ExpansionActivationGroup();
			activationGroup.regionActivation = this;
			activationGroup.index = k;

			int j = 1;
			while (j <= inputPins.size()) {
				OutputPinActivation regionInput = new OutputPinActivation();
				regionInput.run();
				activationGroup.regionInputs.addValue(regionInput);
				j = j + 1;
			}

			j = 1;
			while (j <= inputElements.size()) {
				OutputPinActivation groupInput = new OutputPinActivation();
				groupInput.run();
				activationGroup.groupInputs.addValue(groupInput);
				j = j + 1;
			}

			j = 1;
			while (j <= outputElements.size()) {
				OutputPinActivation groupOutput = new OutputPinActivation();
				groupOutput.run();
				activationGroup.groupOutputs
						.addValue(new OutputPinActivation());
				j = j + 1;
			}

			activationGroup.createNodeActivations(region.node);
			activationGroup.createEdgeInstances(region.edge);
			this.activationGroups.addValue(activationGroup);

			k = k + 1;
		}

		ExpansionActivationGroupList activationGroups = this.activationGroups;

		if (region.mode == ExpansionKind.iterative) {
			Debug.println("[doStructuredActivity] Expansion mode = iterative");
			for (int i = 0; i < activationGroups.size(); i++) {
				ExpansionActivationGroup activationGroup = activationGroups
						.getValue(i);
				this.runGroup(activationGroup);
			}
		} else if (region.mode == ExpansionKind.parallel) {
			Debug.println("[doStructuredActivity] Expansion mode = parallel");
			// *** Activate all groups concurrently. ***
			for (Iterator i = activationGroups.iterator(); i.hasNext();) {
				ExpansionActivationGroup activationGroup = (ExpansionActivationGroup) i
						.next();
				this.runGroup(activationGroup);
			}
		}

		for (int i = 0; i < activationGroups.size(); i++) {
			ExpansionActivationGroup activationGroup = activationGroups
					.getValue(i);
			OutputPinActivationList groupOutputs = activationGroup.groupOutputs;
			for (int j = 0; j < groupOutputs.size(); j++) {
				OutputPinActivation groupOutput = groupOutputs.getValue(j);
				ExpansionNode outputElement = outputElements.getValue(j);
				this.getExpansionNodeActivation(outputElement).addTokens(
						groupOutput.takeTokens());
			}
		}

	} // doStructuredActivity

	/**
	 * operation terminate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void terminate() {
		// Terminate the execution of all contained node activations (which
		// completes the performance of the expansion region activation).

		ExpansionActivationGroupList activationGroups = this.activationGroups;
		for (int i = 0; i < activationGroups.size(); i++) {
			ExpansionActivationGroup activationGroup = this.activationGroups
					.getValue(i);
			activationGroup.terminateAll();
		}

		super.terminate();
	} // terminate

	/**
	 * operation isReady <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isReady() {
		// In addition to the usual ready checks for an action, check that all
		// expansion nodes have the same number of inputs (greater than zero).

		ExpansionRegion region = (ExpansionRegion) (this.node);

		boolean ready = super.isReady();
		if (ready) {
			int n = this.numberOfValues(); // This gets the number of values on
											// the first expansion node.

			if (n < 1) {
				return false;
			} else {
				int i = 1;
				while (ready & i <= region.inputElement.size()) {
					ready = (this.getExpansionNodeActivation(
							region.inputElement.getValue(i - 1))
							.countOfferedValues() == n);
					i = i + 1;
				}
			}
		}

		return ready;

	} // isReady

	/**
	 * operation sendOffers <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void sendOffers() {
		// Fire all output expansion nodes and send offers on all outgoing
		// control flows.

		ExpansionRegion region = (ExpansionRegion) (this.node);

		// *** Send offers from all output expansion nodes concurrently. ***
		ExpansionNodeList outputElements = region.outputElement;
		for (Iterator i = outputElements.iterator(); i.hasNext();) {
			ExpansionNode outputElement = (ExpansionNode) i.next();
			this.getExpansionNodeActivation(outputElement)
					.sendUnofferedTokens();
		}

		// Send offers on all outgoing control flows.
		super.sendOffers();

	} // sendOffers

	/**
	 * operation runGroup <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void runGroup(
			fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionActivationGroup activationGroup) {
		// Set up the inputs for the group with the given index, run the group
		// and then fire the group outputs.

		Debug.println("[runGroup] groupInput[0] = "
				+ this.inputExpansionTokens.getValue(0).tokens.getValue(
						activationGroup.index - 1).getValue());

		TokenSetList inputTokens = this.inputTokens;
		for (int j = 0; j < inputTokens.size(); j++) {
			TokenSet tokenSet = inputTokens.getValue(j);
			OutputPinActivation regionInput = activationGroup.regionInputs
					.getValue(j);
			regionInput.clearTokens();
			regionInput.addTokens(tokenSet.tokens);
			regionInput.sendUnofferedTokens();
		}

		TokenSetList inputExpansionTokens = this.inputExpansionTokens;
		for (int j = 0; j < inputExpansionTokens.size(); j++) {
			TokenSet tokenSet = inputExpansionTokens.getValue(j);
			OutputPinActivation groupInput = activationGroup.groupInputs
					.getValue(j);
			groupInput.clearTokens();
			groupInput.addToken(tokenSet.tokens
					.getValue(activationGroup.index - 1));
			groupInput.sendUnofferedTokens();
		}

		activationGroup.run(activationGroup.nodeActivations);

		OutputPinActivationList groupOutputs = activationGroup.groupOutputs;
		for (int i = 0; i < groupOutputs.size(); i++) {
			OutputPinActivation groupOutput = groupOutputs.getValue(i);
			groupOutput.fire(groupOutput.takeOfferedTokens());
		}

		activationGroup.terminateAll();
	} // runGroup

	/**
	 * operation getExpansionNodeActivation <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionNodeActivation getExpansionNodeActivation(
			fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode node) {
		// Return the expansion node activation corresponding to the given
		// expansion node, in the context of the activity node activation group
		// this expansion region activation is in.
		// [Note: Expansion regions do not own their expansion nodes. Instead,
		// they are own as object nodes by the enclosing activity or group.
		// Therefore, they will already be activated along with their expansion
		// region.]

		return (ExpansionNodeActivation) (this.group.getNodeActivation(node));

	} // getExpansionNodeActivation

	/**
	 * operation numberOfValues <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int numberOfValues() {
		// Return the number of values on the first input expansion node of the
		// expansion region of this activation.
		// (The region is required to have at least one input expansion node.)

		ExpansionRegion region = (ExpansionRegion) (this.node);

		return this.getExpansionNodeActivation(region.inputElement.getValue(0))
				.countOfferedValues();
	} // numberOfValues

} // ExpansionRegionActivation
