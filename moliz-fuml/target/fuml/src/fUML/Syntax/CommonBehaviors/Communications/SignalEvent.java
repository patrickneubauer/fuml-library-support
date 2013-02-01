
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
 * <em><b>fUML::Syntax::CommonBehaviors::Communications::SignalEvent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link SignalEvent#setSignal <em>setSignal</em>}</li>
 * <li>{@link SignalEvent#signal <em>signal</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class SignalEvent extends
		fUML.Syntax.CommonBehaviors.Communications.MessageEvent {

	public fUML.Syntax.CommonBehaviors.Communications.Signal signal = null;

	/**
	 * operation setSignal <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSignal(
			fUML.Syntax.CommonBehaviors.Communications.Signal signal) {
		this.signal = signal;
	} // setSignal

} // SignalEvent
