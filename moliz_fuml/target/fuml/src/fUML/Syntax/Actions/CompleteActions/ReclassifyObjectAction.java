
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
 * <em><b>fUML::Syntax::Actions::CompleteActions::ReclassifyObjectAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ReclassifyObjectAction#setIsReplaceAll <em>setIsReplaceAll</em>}</li>
 * <li>{@link ReclassifyObjectAction#addOldClassifier <em>addOldClassifier</em>}
 * </li>
 * <li>{@link ReclassifyObjectAction#addNewClassifier <em>addNewClassifier</em>}
 * </li>
 * <li>{@link ReclassifyObjectAction#setObject <em>setObject</em>}</li>
 * <li>{@link ReclassifyObjectAction#isReplaceAll <em>isReplaceAll</em>}</li>
 * <li>{@link ReclassifyObjectAction#oldClassifier <em>oldClassifier</em>}</li>
 * <li>{@link ReclassifyObjectAction#object <em>object</em>}</li>
 * <li>{@link ReclassifyObjectAction#newClassifier <em>newClassifier</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ReclassifyObjectAction extends
		fUML.Syntax.Actions.BasicActions.Action {

	public boolean isReplaceAll = false;
	public fUML.Syntax.Classes.Kernel.ClassifierList oldClassifier = new fUML.Syntax.Classes.Kernel.ClassifierList();
	public fUML.Syntax.Actions.BasicActions.InputPin object = null;
	public fUML.Syntax.Classes.Kernel.ClassifierList newClassifier = new fUML.Syntax.Classes.Kernel.ClassifierList();

	/**
	 * operation setIsReplaceAll <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsReplaceAll(boolean isReplaceAll) {
		this.isReplaceAll = isReplaceAll;
	} // setIsReplaceAll

	/**
	 * operation addOldClassifier <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOldClassifier(
			fUML.Syntax.Classes.Kernel.Classifier oldClassifier) {
		this.oldClassifier.addValue(oldClassifier);
	} // addOldClassifier

	/**
	 * operation addNewClassifier <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addNewClassifier(
			fUML.Syntax.Classes.Kernel.Classifier newClassifier) {
		this.newClassifier.addValue(newClassifier);
	} // addNewClassifier

	/**
	 * operation setObject <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setObject(fUML.Syntax.Actions.BasicActions.InputPin object) {
		super.addInput(object);
		this.object = object;

	} // setObject

} // ReclassifyObjectAction
