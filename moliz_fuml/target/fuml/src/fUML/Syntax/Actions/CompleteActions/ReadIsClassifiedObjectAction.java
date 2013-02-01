
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
 * <em><b>fUML::Syntax::Actions::CompleteActions::ReadIsClassifiedObjectAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ReadIsClassifiedObjectAction#setIsDirect <em>setIsDirect</em>}</li>
 * <li>{@link ReadIsClassifiedObjectAction#setClassifier <em>setClassifier</em>}
 * </li>
 * <li>{@link ReadIsClassifiedObjectAction#setResult <em>setResult</em>}</li>
 * <li>{@link ReadIsClassifiedObjectAction#setObject <em>setObject</em>}</li>
 * <li>{@link ReadIsClassifiedObjectAction#isDirect <em>isDirect</em>}</li>
 * <li>{@link ReadIsClassifiedObjectAction#classifier <em>classifier</em>}</li>
 * <li>{@link ReadIsClassifiedObjectAction#result <em>result</em>}</li>
 * <li>{@link ReadIsClassifiedObjectAction#object <em>object</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ReadIsClassifiedObjectAction extends
		fUML.Syntax.Actions.BasicActions.Action {

	public boolean isDirect = false;
	public fUML.Syntax.Classes.Kernel.Classifier classifier = null;
	public fUML.Syntax.Actions.BasicActions.OutputPin result = null;
	public fUML.Syntax.Actions.BasicActions.InputPin object = null;

	/**
	 * operation setIsDirect <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsDirect(boolean isDirect) {
		this.isDirect = isDirect;
	} // setIsDirect

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

	/**
	 * operation setObject <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setObject(fUML.Syntax.Actions.BasicActions.InputPin object) {
		super.addInput(object);
		this.object = object;
	} // setObject

} // ReadIsClassifiedObjectAction
