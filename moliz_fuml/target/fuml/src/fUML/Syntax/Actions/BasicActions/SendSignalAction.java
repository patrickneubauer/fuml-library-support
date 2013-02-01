
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

package fUML.Syntax.Actions.BasicActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Actions::BasicActions::SendSignalAction</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link SendSignalAction#setTarget <em>setTarget</em>}</li>
 * <li>{@link SendSignalAction#setSignal <em>setSignal</em>}</li>
 * <li>{@link SendSignalAction#target <em>target</em>}</li>
 * <li>{@link SendSignalAction#signal <em>signal</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class SendSignalAction extends
		fUML.Syntax.Actions.BasicActions.InvocationAction {

	public fUML.Syntax.Actions.BasicActions.InputPin target = null;
	public fUML.Syntax.CommonBehaviors.Communications.Signal signal = null;

	/**
	 * operation setTarget <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTarget(fUML.Syntax.Actions.BasicActions.InputPin target) {
		super.addInput(target);
		this.target = target;
	} // setTarget

	/**
	 * operation setSignal <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSignal(
			fUML.Syntax.CommonBehaviors.Communications.Signal signal) {
		this.signal = signal;
	} // setSignal

} // SendSignalAction
