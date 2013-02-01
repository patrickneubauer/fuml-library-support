
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

package fUML.Syntax.CommonBehaviors.Communications;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::CommonBehaviors::Communications::Trigger</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Trigger#setEvent <em>setEvent</em>}</li>
 * <li>{@link Trigger#event <em>event</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Trigger extends fUML.Syntax.Classes.Kernel.NamedElement {

	public fUML.Syntax.CommonBehaviors.Communications.Event event = null;

	/**
	 * operation setEvent <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEvent(fUML.Syntax.CommonBehaviors.Communications.Event event) {
		this.event = event;
	} // setEvent

} // Trigger
