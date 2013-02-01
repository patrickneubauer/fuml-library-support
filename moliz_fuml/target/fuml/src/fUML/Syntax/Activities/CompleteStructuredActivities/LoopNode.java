
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

package fUML.Syntax.Activities.CompleteStructuredActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Activities::CompleteStructuredActivities::LoopNode</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link LoopNode#setIsTestedFirst <em>setIsTestedFirst</em>}</li>
 * <li>{@link LoopNode#addTest <em>addTest</em>}</li>
 * <li>{@link LoopNode#addBodyPart <em>addBodyPart</em>}</li>
 * <li>{@link LoopNode#addLoopVariableInput <em>addLoopVariableInput</em>}</li>
 * <li>{@link LoopNode#addLoopVariable <em>addLoopVariable</em>}</li>
 * <li>{@link LoopNode#setDecider <em>setDecider</em>}</li>
 * <li>{@link LoopNode#addBodyOutput <em>addBodyOutput</em>}</li>
 * <li>{@link LoopNode#addResult <em>addResult</em>}</li>
 * <li>{@link LoopNode#isTestedFirst <em>isTestedFirst</em>}</li>
 * <li>{@link LoopNode#decider <em>decider</em>}</li>
 * <li>{@link LoopNode#test <em>test</em>}</li>
 * <li>{@link LoopNode#bodyOutput <em>bodyOutput</em>}</li>
 * <li>{@link LoopNode#loopVariableInput <em>loopVariableInput</em>}</li>
 * <li>{@link LoopNode#bodyPart <em>bodyPart</em>}</li>
 * <li>{@link LoopNode#result <em>result</em>}</li>
 * <li>{@link LoopNode#loopVariable <em>loopVariable</em>}</li>
 * <li>{@link LoopNode#setupPart <em>setupPart</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class LoopNode
		extends
		fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode {

	public boolean isTestedFirst = false;
	public fUML.Syntax.Actions.BasicActions.OutputPin decider = null;
	public fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList test = new fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList();
	public fUML.Syntax.Actions.BasicActions.OutputPinList bodyOutput = new fUML.Syntax.Actions.BasicActions.OutputPinList();
	public fUML.Syntax.Actions.BasicActions.InputPinList loopVariableInput = new fUML.Syntax.Actions.BasicActions.InputPinList();
	public fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList bodyPart = new fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList();
	public fUML.Syntax.Actions.BasicActions.OutputPinList result = new fUML.Syntax.Actions.BasicActions.OutputPinList();
	public fUML.Syntax.Actions.BasicActions.OutputPinList loopVariable = new fUML.Syntax.Actions.BasicActions.OutputPinList();
	public fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList setupPart = new fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList();

	/**
	 * operation setIsTestedFirst <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsTestedFirst(boolean isTestedFirst) {
		this.isTestedFirst = isTestedFirst;
	} // setIsTestedFirst

	/**
	 * operation addTest <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addTest(
			fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode test) {
		this.test.addValue(test);
	} // addTest

	/**
	 * operation addBodyPart <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addBodyPart(
			fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode bodyPart) {
		this.bodyPart.addValue(bodyPart);
	} // addBodyPart

	/**
	 * operation addLoopVariableInput <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void addLoopVariableInput(
			fUML.Syntax.Actions.BasicActions.InputPin loopVariableInput) {
		super.addStructuredNodeInput(loopVariableInput);
		this.loopVariableInput.addValue(loopVariableInput);
	} // addLoopVariableInput

	/**
	 * operation addLoopVariable <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addLoopVariable(
			fUML.Syntax.Actions.BasicActions.OutputPin loopVariable) {
		this.loopVariable.addValue(loopVariable);
	} // addLoopVariable

	/**
	 * operation setDecider <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDecider(fUML.Syntax.Actions.BasicActions.OutputPin decider) {
		this.decider = decider;
	} // setDecider

	/**
	 * operation addBodyOutput <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addBodyOutput(
			fUML.Syntax.Actions.BasicActions.OutputPin bodyOutput) {
		this.bodyOutput.addValue(bodyOutput);
	} // addBodyOutput

	/**
	 * operation addResult <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addResult(fUML.Syntax.Actions.BasicActions.OutputPin result) {
		super.addOutput(result);
		this.result.addValue(result);
	} // addResult

} // LoopNode
