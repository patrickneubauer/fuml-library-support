
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
 * <em><b>fUML::Syntax::Actions::IntermediateActions::ClearAssociationAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ClearAssociationAction#setAssociation <em>setAssociation</em>}</li>
 * <li>{@link ClearAssociationAction#setObject <em>setObject</em>}</li>
 * <li>{@link ClearAssociationAction#association <em>association</em>}</li>
 * <li>{@link ClearAssociationAction#object <em>object</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ClearAssociationAction extends
		fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.Classes.Kernel.Association association = null;
	public fUML.Syntax.Actions.BasicActions.InputPin object = null;

	/**
	 * operation setAssociation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAssociation(
			fUML.Syntax.Classes.Kernel.Association association) {
		this.association = association;
	} // setAssociation

	/**
	 * operation setObject <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setObject(fUML.Syntax.Actions.BasicActions.InputPin object) {
		super.addInput(object);
		this.object = object;
	} // setObject

} // ClearAssociationAction
