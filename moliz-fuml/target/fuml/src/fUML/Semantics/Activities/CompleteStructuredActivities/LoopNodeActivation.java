
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
 * <em><b>fUML::Semantics::Activities::CompleteStructuredActivities::LoopNodeActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link LoopNodeActivation#doStructuredActivity <em>doStructuredActivity
 * </em>}</li>
 * <li>{@link LoopNodeActivation#runTest <em>runTest</em>}</li>
 * <li>{@link LoopNodeActivation#runBody <em>runBody</em>}</li>
 * <li>{@link LoopNodeActivation#runLoopVariables <em>runLoopVariables</em>}</li>
 * <li>{@link LoopNodeActivation#createNodeActivations <em>createNodeActivations
 * </em>}</li>
 * <li>{@link LoopNodeActivation#makeLoopVariableList <em>makeLoopVariableList
 * </em>}</li>
 * <li>{@link LoopNodeActivation#bodyOutputLists <em>bodyOutputLists</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class LoopNodeActivation
		extends
		fUML.Semantics.Activities.CompleteStructuredActivities.StructuredActivityNodeActivation {

	public fUML.Semantics.Activities.CompleteStructuredActivities.ValuesList bodyOutputLists = new fUML.Semantics.Activities.CompleteStructuredActivities.ValuesList();

	/**
	 * operation doStructuredActivity <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void doStructuredActivity() {
		// Set the initial values for the body outputs to the values of the loop
		// variable input pins.
		// If isTestedFirst is true, then repeatedly run the test part and the
		// body part of the loop, copying values from the body outputs to the
		// loop variables.
		// If isTestedFirst is false, then repeatedly run the body part and the
		// test part of the loop, copying values from the body outputs to the
		// loop variables.
		// When the test fails, copy the values of the body outputs to the loop
		// outputs.
		// [Note: The body outputs are used for the loop outputs, rather than
		// the loop variables, since values on the loop variables may be
		// consumed when running the test for the last time.]

		LoopNode loopNode = (LoopNode) (this.node);
		InputPinList loopVariableInputs = loopNode.loopVariableInput;
		OutputPinList loopVariables = loopNode.loopVariable;
		OutputPinList resultPins = loopNode.result;

		ValuesList bodyOutputLists = this.bodyOutputLists;
		for (int i = 0; i < loopVariableInputs.size(); i++) {
			InputPin loopVariableInput = loopVariableInputs.getValue(i);
			Values bodyOutputList = new Values();
			bodyOutputList.values = this.takeTokens(loopVariableInput);
			this.bodyOutputLists.addValue(bodyOutputList);
		}

		boolean continuing = true;
		do {

			// Set loop variable values
			this.runLoopVariables();
			for (int i = 0; i < loopVariables.size(); i++) {
				OutputPin loopVariable = loopVariables.getValue(i);
				Values bodyOutputList = bodyOutputLists.getValue(i);
				ValueList values = bodyOutputList.values;
				this.putPinValues(loopVariable, values);
				((OutputPinActivation) this.activationGroup
						.getNodeActivation(loopVariable)).sendUnofferedTokens();
			}

			// Run all the non-executable, non-pin nodes in the conditional
			// node.
			ActivityNodeActivationList nodeActivations = this.activationGroup.nodeActivations;
			ActivityNodeActivationList nonExecutableNodeActivations = new ActivityNodeActivationList();
			for (int i = 0; i < nodeActivations.size(); i++) {
				ActivityNodeActivation nodeActivation = nodeActivations
						.getValue(i);
				if (!(nodeActivation.node instanceof ExecutableNode | nodeActivation.node instanceof Pin)) {
					nonExecutableNodeActivations.addValue(nodeActivation);
				}
			}
			this.activationGroup.run(nonExecutableNodeActivations);

			// Run the loop
			if (loopNode.isTestedFirst) {
				continuing = this.runTest();
				if (continuing) {
					this.runBody();
				}
			} else {
				this.runBody();
				continuing = this.runTest();
			}

			this.activationGroup.terminateAll();

			Debug.println("[doStructuredActivity] "
					+ (continuing ? "Continuing." : "Done."));

		} while (continuing);

		for (int i = 0; i < bodyOutputLists.size(); i++) {
			Values bodyOutputList = bodyOutputLists.getValue(i);
			OutputPin resultPin = resultPins.getValue(i);
			this.putTokens(resultPin, bodyOutputList.values);
		}

	} // doStructuredActivity

	/**
	 * operation runTest <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean runTest() {
		// Run the test part of the loop node for this node activation.
		// Return the value on the decider pin.

		Debug.println("[runTest] Running test...");

		LoopNode loopNode = (LoopNode) (this.node);

		this.activationGroup.runNodes(this.makeActivityNodeList(loopNode.test));

		ValueList values = this.getPinValues(loopNode.decider);

		// If there is no decider value, treat it as false.
		boolean decision = false;
		if (values.size() > 0) {
			decision = ((BooleanValue) (values.getValue(0))).value;
		}

		Debug.println("[runTest] "
				+ (decision ? "Test succeeded." : "Test failed."));

		return decision;
	} // runTest

	/**
	 * operation runBody <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void runBody() {
		// Run the body part of the loop node for this node activation and save
		// the body outputs.

		Debug.println("[runBody] Running body...");

		LoopNode loopNode = (LoopNode) this.node;

		this.activationGroup.runNodes(this
				.makeActivityNodeList(loopNode.bodyPart));

		OutputPinList bodyOutputs = loopNode.bodyOutput;
		ValuesList bodyOutputLists = this.bodyOutputLists;
		for (int i = 0; i < bodyOutputs.size(); i++) {
			OutputPin bodyOutput = bodyOutputs.getValue(i);
			Values bodyOutputList = bodyOutputLists.getValue(i);
			bodyOutputList.values = this.getPinValues(bodyOutput);
		}
	} // runBody

	/**
	 * operation runLoopVariables <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void runLoopVariables() {
		// Run the loop variable pins of the loop node for this node activation.

		this.activationGroup.runNodes(this.makeLoopVariableList());
	} // runLoopVariables

	/**
	 * operation createNodeActivations <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void createNodeActivations() {
		// In addition to creating activations for contained nodes, create
		// activations for any loop variables.

		super.createNodeActivations();
		this.activationGroup.createNodeActivations(this.makeLoopVariableList());
	} // createNodeActivations

	/**
	 * operation makeLoopVariableList <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList makeLoopVariableList() {
		// Return an activity node list containing the loop variable pins for
		// the loop node of this activation.

		LoopNode loopNode = (LoopNode) (this.node);
		ActivityNodeList nodes = new ActivityNodeList();

		OutputPinList loopVariables = loopNode.loopVariable;
		for (int i = 0; i < loopVariables.size(); i++) {
			OutputPin loopVariable = loopVariables.getValue(i);
			nodes.addValue(loopVariable);
		}

		return nodes;

	} // makeLoopVariableList

} // LoopNodeActivation
