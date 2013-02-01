
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

package fUML.Syntax.Actions.IntermediateActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Actions::IntermediateActions::TestIdentityAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link TestIdentityAction#setFirst <em>setFirst</em>}</li>
 * <li>{@link TestIdentityAction#setSecond <em>setSecond</em>}</li>
 * <li>{@link TestIdentityAction#setResult <em>setResult</em>}</li>
 * <li>{@link TestIdentityAction#second <em>second</em>}</li>
 * <li>{@link TestIdentityAction#result <em>result</em>}</li>
 * <li>{@link TestIdentityAction#first <em>first</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class TestIdentityAction extends fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.Actions.BasicActions.InputPin second = null;
	public fUML.Syntax.Actions.BasicActions.OutputPin result = null;
	public fUML.Syntax.Actions.BasicActions.InputPin first = null;

	/**
	 * operation setFirst <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFirst(fUML.Syntax.Actions.BasicActions.InputPin first) {
		super.addInput(first);
		this.first = first;
	} // setFirst

	/**
	 * operation setSecond <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSecond(fUML.Syntax.Actions.BasicActions.InputPin second) {
		super.addInput(second);
		this.second = second;
	} // setSecond

	/**
	 * operation setResult <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResult(fUML.Syntax.Actions.BasicActions.OutputPin result) {
		super.addOutput(result);
		this.result = result;
	} // setResult

} // TestIdentityAction
