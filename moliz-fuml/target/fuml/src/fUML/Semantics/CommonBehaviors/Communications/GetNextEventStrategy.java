
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
 * <em><b>fUML::Semantics::CommonBehaviors::Communications::GetNextEventStrategy</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link GetNextEventStrategy#getName <em>getName</em>}</li>
 * <li>{@link GetNextEventStrategy#getNextEvent <em>getNextEvent</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class GetNextEventStrategy extends
		fUML.Semantics.Loci.LociL1.SemanticStrategy {

	/**
	 * operation getName <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		// Get next event strategies are always named "getNextEvent".

		return "getNextEvent";
	} // getName

	/**
	 * operation getNextEvent <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract fUML.Semantics.CommonBehaviors.Communications.SignalInstance getNextEvent(
			fUML.Semantics.CommonBehaviors.Communications.ObjectActivation objectActivation);
} // GetNextEventStrategy
