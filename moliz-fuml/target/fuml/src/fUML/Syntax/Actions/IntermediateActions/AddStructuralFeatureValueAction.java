
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
 * <em><b>fUML::Syntax::Actions::IntermediateActions::AddStructuralFeatureValueAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link AddStructuralFeatureValueAction#setIsReplaceAll <em>
 * setIsReplaceAll</em>}</li>
 * <li>{@link AddStructuralFeatureValueAction#setInsertAt <em>setInsertAt</em>}</li>
 * <li>{@link AddStructuralFeatureValueAction#isReplaceAll <em>isReplaceAll
 * </em>}</li>
 * <li>{@link AddStructuralFeatureValueAction#insertAt <em>insertAt</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class AddStructuralFeatureValueAction extends
		fUML.Syntax.Actions.IntermediateActions.WriteStructuralFeatureAction {

	public boolean isReplaceAll = false;
	public fUML.Syntax.Actions.BasicActions.InputPin insertAt = null;

	/**
	 * operation setIsReplaceAll <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsReplaceAll(boolean isReplaceAll) {
		this.isReplaceAll = isReplaceAll;
	} // setIsReplaceAll

	/**
	 * operation setInsertAt <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInsertAt(fUML.Syntax.Actions.BasicActions.InputPin insertAt) {
		if (insertAt != null) {
			super.addInput(insertAt);
		}

		this.insertAt = insertAt;
	} // setInsertAt

} // AddStructuralFeatureValueAction
