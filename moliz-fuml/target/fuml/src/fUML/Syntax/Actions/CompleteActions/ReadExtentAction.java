
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
 * <em><b>fUML::Syntax::Actions::CompleteActions::ReadExtentAction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ReadExtentAction#setClassifier <em>setClassifier</em>}</li>
 * <li>{@link ReadExtentAction#setResult <em>setResult</em>}</li>
 * <li>{@link ReadExtentAction#result <em>result</em>}</li>
 * <li>{@link ReadExtentAction#classifier <em>classifier</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ReadExtentAction extends fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.Actions.BasicActions.OutputPin result = null;
	public fUML.Syntax.Classes.Kernel.Classifier classifier = null;

	/**
	 * operation setClassifier <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setClassifier(fUML.Syntax.Classes.Kernel.Classifier classifier) {
		this.classifier = classifier;
	} // setClassifier

	/**
	 * operation setResult <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResult(fUML.Syntax.Actions.BasicActions.OutputPin result) {
		super.addOutput(result);
		this.result = result;
	} // setResult

} // ReadExtentAction
