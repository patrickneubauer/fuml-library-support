
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

package fUML.Syntax.Activities.IntermediateActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Activities::IntermediateActivities::DecisionNode</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link DecisionNode#setDecisionInput <em>setDecisionInput</em>}</li>
 * <li>{@link DecisionNode#setDecisionInputFlow <em>setDecisionInputFlow</em>}</li>
 * <li>{@link DecisionNode#decisionInput <em>decisionInput</em>}</li>
 * <li>{@link DecisionNode#decisionInputFlow <em>decisionInputFlow</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class DecisionNode extends
		fUML.Syntax.Activities.IntermediateActivities.ControlNode {

	public fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior decisionInput = null;
	public fUML.Syntax.Activities.IntermediateActivities.ObjectFlow decisionInputFlow = null;

	/**
	 * operation setDecisionInput <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDecisionInput(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior decisionInput) {
		this.decisionInput = decisionInput;
	} // setDecisionInput

	/**
	 * operation setDecisionInputFlow <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setDecisionInputFlow(
			fUML.Syntax.Activities.IntermediateActivities.ObjectFlow decisionInputFlow) {
		this.decisionInputFlow = decisionInputFlow;
	} // setDecisionInputFlow

} // DecisionNode
