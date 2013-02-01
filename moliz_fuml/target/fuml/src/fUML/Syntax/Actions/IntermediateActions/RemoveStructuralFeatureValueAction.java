
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
 * <em><b>fUML::Syntax::Actions::IntermediateActions::RemoveStructuralFeatureValueAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link RemoveStructuralFeatureValueAction#setIsRemoveDuplicates <em>
 * setIsRemoveDuplicates</em>}</li>
 * <li>{@link RemoveStructuralFeatureValueAction#setRemoveAt <em>setRemoveAt
 * </em>}</li>
 * <li>{@link RemoveStructuralFeatureValueAction#isRemoveDuplicates <em>
 * isRemoveDuplicates</em>}</li>
 * <li>{@link RemoveStructuralFeatureValueAction#removeAt <em>removeAt</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class RemoveStructuralFeatureValueAction extends
		fUML.Syntax.Actions.IntermediateActions.WriteStructuralFeatureAction {

	public boolean isRemoveDuplicates = false;
	public fUML.Syntax.Actions.BasicActions.InputPin removeAt = null;

	/**
	 * operation setIsRemoveDuplicates <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setIsRemoveDuplicates(boolean isRemoveDuplicates) {
		this.isRemoveDuplicates = isRemoveDuplicates;
	} // setIsRemoveDuplicates

	/**
	 * operation setRemoveAt <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRemoveAt(fUML.Syntax.Actions.BasicActions.InputPin removeAt) {
		if (removeAt != null) {
			super.addInput(removeAt);
		}

		this.removeAt = removeAt;
	} // setRemoveAt

} // RemoveStructuralFeatureValueAction
