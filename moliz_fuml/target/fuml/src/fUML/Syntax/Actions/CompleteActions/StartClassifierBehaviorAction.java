
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
 * <em><b>fUML::Syntax::Actions::CompleteActions::StartClassifierBehaviorAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link StartClassifierBehaviorAction#setObject <em>setObject</em>}</li>
 * <li>{@link StartClassifierBehaviorAction#object <em>object</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class StartClassifierBehaviorAction extends
		fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.Actions.BasicActions.InputPin object = null;

	/**
	 * operation setObject <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setObject(fUML.Syntax.Actions.BasicActions.InputPin object) {
		super.addInput(object);
		this.object = object;

	} // setObject

} // StartClassifierBehaviorAction
