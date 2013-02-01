
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
 * <em><b>fUML::Syntax::Actions::IntermediateActions::DestroyLinkAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link DestroyLinkAction#addEndData <em>addEndData</em>}</li>
 * <li>{@link DestroyLinkAction#endData <em>endData</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class DestroyLinkAction extends
		fUML.Syntax.Actions.IntermediateActions.WriteLinkAction {

	public fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionDataList endData = new fUML.Syntax.Actions.IntermediateActions.LinkEndDestructionDataList();

	/**
	 * operation addEndData <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addEndData(
			fUML.Syntax.Actions.IntermediateActions.LinkEndData endData) {
		super.addEndData(endData);
		this.endData.addValue((LinkEndDestructionData) endData);
	} // addEndData

} // DestroyLinkAction
