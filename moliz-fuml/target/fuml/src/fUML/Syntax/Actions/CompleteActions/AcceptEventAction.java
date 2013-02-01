
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

package fUML.Syntax.Actions.CompleteActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Actions::CompleteActions::AcceptEventAction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link AcceptEventAction#setIsUnmarshall <em>setIsUnmarshall</em>}</li>
 * <li>{@link AcceptEventAction#addTrigger <em>addTrigger</em>}</li>
 * <li>{@link AcceptEventAction#addResult <em>addResult</em>}</li>
 * <li>{@link AcceptEventAction#isUnmarshall <em>isUnmarshall</em>}</li>
 * <li>{@link AcceptEventAction#result <em>result</em>}</li>
 * <li>{@link AcceptEventAction#trigger <em>trigger</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class AcceptEventAction extends fUML.Syntax.Actions.BasicActions.Action {

	public boolean isUnmarshall = false;
	public fUML.Syntax.Actions.BasicActions.OutputPinList result = new fUML.Syntax.Actions.BasicActions.OutputPinList();
	public fUML.Syntax.CommonBehaviors.Communications.TriggerList trigger = new fUML.Syntax.CommonBehaviors.Communications.TriggerList();

	/**
	 * operation setIsUnmarshall <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsUnmarshall(boolean isUnmarshall) {
		this.isUnmarshall = isUnmarshall;
	} // setIsUnmarshall

	/**
	 * operation addTrigger <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addTrigger(
			fUML.Syntax.CommonBehaviors.Communications.Trigger trigger) {
		this.trigger.addValue(trigger);
	} // addTrigger

	/**
	 * operation addResult <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addResult(fUML.Syntax.Actions.BasicActions.OutputPin result) {
		super.addOutput(result);
		this.result.addValue(result);
	} // addResult

} // AcceptEventAction
