
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
 * <em><b>fUML::Syntax::Actions::IntermediateActions::LinkAction</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link LinkAction#addEndData <em>addEndData</em>}</li>
 * <li>{@link LinkAction#addInputValue <em>addInputValue</em>}</li>
 * <li>{@link LinkAction#endData <em>endData</em>}</li>
 * <li>{@link LinkAction#inputValue <em>inputValue</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class LinkAction extends
		fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.Actions.IntermediateActions.LinkEndDataList endData = new fUML.Syntax.Actions.IntermediateActions.LinkEndDataList();
	public fUML.Syntax.Actions.BasicActions.InputPinList inputValue = new fUML.Syntax.Actions.BasicActions.InputPinList();

	/**
	 * operation addEndData <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addEndData(
			fUML.Syntax.Actions.IntermediateActions.LinkEndData endData) {
		this.endData.addValue(endData);
	} // addEndData

	/**
	 * operation addInputValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addInputValue(
			fUML.Syntax.Actions.BasicActions.InputPin inputValue) {
		super.addInput(inputValue);
		this.inputValue.addValue(inputValue);
	} // addInputValue

} // LinkAction
