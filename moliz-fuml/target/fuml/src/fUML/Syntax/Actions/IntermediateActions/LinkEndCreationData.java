
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
 * <em><b>fUML::Syntax::Actions::IntermediateActions::LinkEndCreationData</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link LinkEndCreationData#setIsReplaceAll <em>setIsReplaceAll</em>}</li>
 * <li>{@link LinkEndCreationData#setInsertAt <em>setInsertAt</em>}</li>
 * <li>{@link LinkEndCreationData#isReplaceAll <em>isReplaceAll</em>}</li>
 * <li>{@link LinkEndCreationData#insertAt <em>insertAt</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class LinkEndCreationData extends
		fUML.Syntax.Actions.IntermediateActions.LinkEndData {

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
		this.insertAt = insertAt;
	} // setInsertAt

} // LinkEndCreationData
