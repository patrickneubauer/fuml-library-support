
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

package fUML.Semantics.CommonBehaviors.Communications;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::CommonBehaviors::Communications::FIFOGetNextEventStrategy</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link FIFOGetNextEventStrategy#getNextEvent <em>getNextEvent</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class FIFOGetNextEventStrategy extends
		fUML.Semantics.CommonBehaviors.Communications.GetNextEventStrategy {

	/**
	 * operation getNextEvent <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.CommonBehaviors.Communications.SignalInstance getNextEvent(
			fUML.Semantics.CommonBehaviors.Communications.ObjectActivation objectActivation) {
		// Get the first event from the given event pool. The event is removed
		// from the pool.

		SignalInstance signalInstance = objectActivation.eventPool.getValue(0);
		objectActivation.eventPool.removeValue(0);
		return signalInstance;
	} // getNextEvent

} // FIFOGetNextEventStrategy
