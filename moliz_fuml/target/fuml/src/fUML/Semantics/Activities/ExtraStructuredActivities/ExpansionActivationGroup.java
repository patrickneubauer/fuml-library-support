
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

package fUML.Semantics.Activities.ExtraStructuredActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

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
 * <em><b>fUML::Semantics::Activities::ExtraStructuredActivities::ExpansionActivationGroup</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ExpansionActivationGroup#getNodeActivation <em>getNodeActivation
 * </em>}</li>
 * <li>{@link ExpansionActivationGroup#getActivityExecution <em>
 * getActivityExecution</em>}</li>
 * <li>{@link ExpansionActivationGroup#regionInputs <em>regionInputs</em>}</li>
 * <li>{@link ExpansionActivationGroup#groupInputs <em>groupInputs</em>}</li>
 * <li>{@link ExpansionActivationGroup#regionActivation <em>regionActivation
 * </em>}</li>
 * <li>{@link ExpansionActivationGroup#groupOutputs <em>groupOutputs</em>}</li>
 * <li>{@link ExpansionActivationGroup#index <em>index</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ExpansionActivationGroup
		extends
		fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivationGroup {

	public fUML.Semantics.Actions.BasicActions.OutputPinActivationList regionInputs = new fUML.Semantics.Actions.BasicActions.OutputPinActivationList();
	public fUML.Semantics.Actions.BasicActions.OutputPinActivationList groupInputs = new fUML.Semantics.Actions.BasicActions.OutputPinActivationList();
	public fUML.Semantics.Activities.ExtraStructuredActivities.ExpansionRegionActivation regionActivation = null;
	public fUML.Semantics.Actions.BasicActions.OutputPinActivationList groupOutputs = new fUML.Semantics.Actions.BasicActions.OutputPinActivationList();
	public int index = 0;

	/**
	 * operation getNodeActivation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.ActivityNodeActivation getNodeActivation(
			fUML.Syntax.Activities.IntermediateActivities.ActivityNode node) {
		// If the given node is an input pin of the expansion region, then
		// return the corresponding region-input output-pin activation.
		// If the given node is an input expansion node of the expansion region,
		// then return the corresponding group-input output-pin activation.
		// If the given node is an output expansion node of the expansion
		// region, then return the corresponding group-output output-pin
		// activation.
		// Otherwise return the node activation from the activation group, as
		// usual.

		ExpansionRegion region = (ExpansionRegion) (this.regionActivation.node);

		InputPinList inputs = region.input;
		ActivityNodeActivation activation = null;

		int i = 1;
		while (activation == null & i <= region.input.size()) {
			if (node == region.input.getValue(i - 1)) {
				activation = this.regionInputs.getValue(i - 1);
			}
			i = i + 1;
		}

		int j = 1;
		while (activation == null & j <= region.inputElement.size()) {
			if (node == region.inputElement.getValue(j - 1)) {
				activation = this.groupInputs.getValue(j - 1);
			}
			j = j + 1;
		}

		int k = 1;
		while (activation == null & k <= region.outputElement.size()) {
			if (node == region.outputElement.getValue(k - 1)) {
				activation = this.groupOutputs.getValue(k - 1);
			}
			k = k + 1;
		}

		if (activation == null) {
			activation = super.getNodeActivation(node);
		}

		return activation;
	} // getNodeActivation

	/**
	 * operation getActivityExecution <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.ActivityExecution getActivityExecution() {
		// Get the activity execution that contains the expansion region
		// activation for this activation group.

		return this.regionActivation.getActivityExecution();
	} // getActivityExecution

} // ExpansionActivationGroup
