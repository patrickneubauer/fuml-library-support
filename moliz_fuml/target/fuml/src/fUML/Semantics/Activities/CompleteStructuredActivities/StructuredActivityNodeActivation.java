
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

package fUML.Semantics.Activities.CompleteStructuredActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import java.util.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Activities.CompleteStructuredActivities.*;
import fUML.Syntax.Actions.BasicActions.*;

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.Activities.IntermediateActivities.*;
import fUML.Semantics.Actions.BasicActions.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * 
 * <em><b>fUML::Semantics::Activities::CompleteStructuredActivities::StructuredActivityNodeActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link StructuredActivityNodeActivation#doAction <em>doAction</em>}</li>
 * <li>{@link StructuredActivityNodeActivation#doStructuredActivity <em>
 * doStructuredActivity</em>}</li>
 * <li>{@link StructuredActivityNodeActivation#terminate <em>terminate</em>}</li>
 * <li>{@link StructuredActivityNodeActivation#getNodeActivation <em>
 * getNodeActivation</em>}</li>
 * <li>{@link StructuredActivityNodeActivation#makeActivityNodeList <em>
 * makeActivityNodeList</em>}</li>
 * <li>{@link StructuredActivityNodeActivation#getPinValues <em>getPinValues
 * </em>}</li>
 * <li>{@link StructuredActivityNodeActivation#putPinValues <em>putPinValues
 * </em>}</li>
 * <li>{@link StructuredActivityNodeActivation#createNodeActivations <em>
 * createNodeActivations</em>}</li>
 * <li>{@link StructuredActivityNodeActivation#createEdgeInstances <em>
 * createEdgeInstances</em>}</li>
 * <li>{@link StructuredActivityNodeActivation#activationGroup <em>
 * activationGroup</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class StructuredActivityNodeActivation extends
		fUML.Semantics.Actions.BasicActions.ActionActivation {

	public fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationGroup activationGroup = null;

	/**
	 * operation doAction <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void doAction() {
		// If the structured activity node has mustIsolate=true, then carry out
		// its behavior with isolation.
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
		// Run all activations of contained nodes. When this is complete,
		// return.
		// (This is the default behavior for a structured activity node used
		// simply as a group. It is overridden for the execution of conditional
		// and loop nodes.)

		Action action = (Action) (this.node);

		// *** Concurrently send offers from all input pins. ***
		InputPinList inputPins = action.input;
		for (Iterator i = inputPins.iterator(); i.hasNext();) {
			InputPin inputPin = (InputPin) i.next();
			PinActivation pinActivation = this.getPinActivation(inputPin);
			pinActivation.sendUnofferedTokens();
		}

		this.activationGroup.run(this.activationGroup.nodeActivations);
	} // doStructuredActivity

	/**
	 * operation terminate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void terminate() {
		// Terminate the execution of all contained node activations (which
		// completes the performance of the structured activity node
		// activation).

		this.activationGroup.terminateAll();
		super.terminate();
	} // terminate

	/**
	 * operation getNodeActivation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation getNodeActivation(
			fUML.Syntax.Activities.IntermediateActivities.ActivityNode node) {
		// If this structured activity node activation is not for the given
		// node, then check if there is an activation for the node in the
		// activation group.

		ActivityNodeActivation thisActivation = super.getNodeActivation(node);

		ActivityNodeActivation activation = null;
		if (thisActivation != null) {
			activation = thisActivation;
		} else if (this.activationGroup != null) {
			activation = this.activationGroup.getNodeActivation(node);
		}

		return activation;
	} // getNodeActivation

	/**
	 * operation makeActivityNodeList <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList makeActivityNodeList(
			fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList nodes) {
		// Return an activity node list containing the given list of executable
		// nodes
		// and any pins that they own.

		ActivityNodeList activityNodes = new ActivityNodeList();

		for (int i = 0; i < nodes.size(); i++) {
			ActivityNode node = nodes.getValue(i);
			activityNodes.addValue(node);

			if (node instanceof Action) {
				Action action = (Action) node;

				InputPinList inputPins = action.input;
				for (int j = 0; j < inputPins.size(); j++) {
					InputPin inputPin = inputPins.getValue(j);
					activityNodes.addValue(inputPin);
				}

				OutputPinList outputPins = action.output;
				for (int j = 0; j < outputPins.size(); j++) {
					OutputPin outputPin = outputPins.getValue(j);
					activityNodes.addValue(outputPin);
				}
			}
		}

		return activityNodes;
	} // makeActivityNodeList

	/**
	 * operation getPinValues <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.ValueList getPinValues(
			fUML.Syntax.Actions.BasicActions.OutputPin pin) {
		// Return the values of the tokens on the pin activation corresponding
		// to the given pin in the internal activation group for this node
		// activation.

		PinActivation pinActivation = (PinActivation) (this.activationGroup
				.getNodeActivation(pin));
		TokenList tokens = pinActivation.getTokens();

		ValueList values = new ValueList();
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.getValue(i);
			Value value = ((ObjectToken) token).value;
			if (value != null) {
				values.addValue(value);
			}
		}

		return values;
	} // getPinValues

	/**
	 * operation putPinValues <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void putPinValues(fUML.Syntax.Actions.BasicActions.OutputPin pin,
			fUML.Semantics.Classes.Kernel.ValueList values) {
		// Place tokens for the given values on the pin activation corresponding
		// to the given output pin on the internal activation group for this
		// node activation.

		PinActivation pinActivation = (PinActivation) (this.activationGroup
				.getNodeActivation(pin));

		for (int i = 0; i < values.size(); i++) {
			Value value = values.getValue(i);
			ObjectToken token = new ObjectToken();
			token.value = value;
			pinActivation.addToken(token);
		}

	} // putPinValues

	/**
	 * operation createNodeActivations <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void createNodeActivations() {
		// Create an activation group and create node activations for all the
		// nodes within the structured activity node.

		super.createNodeActivations();

		this.activationGroup = new ActivityNodeActivationGroup();
		this.activationGroup.containingNodeActivation = this;
		this.activationGroup
				.createNodeActivations(((StructuredActivityNode) (this.node)).node);

	} // createNodeActivations

	/**
	 * operation createEdgeInstances <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void createEdgeInstances() {
		// Create instances for all edges owned by this node.

		this.activationGroup
				.createEdgeInstances(((StructuredActivityNode) (this.node)).edge);
	} // createEdgeInstances

} // StructuredActivityNodeActivation
