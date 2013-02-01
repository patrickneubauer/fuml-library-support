
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

package fUML.Semantics.Classes.Kernel;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Classes::Kernel::DispatchStrategy</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link DispatchStrategy#getName <em>getName</em>}</li>
 * <li>{@link DispatchStrategy#dispatch <em>dispatch</em>}</li>
 * <li>{@link DispatchStrategy#getMethod <em>getMethod</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class DispatchStrategy extends
		fUML.Semantics.Loci.LociL1.SemanticStrategy {

	/**
	 * operation getName <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		// Dispatch strategies are always named "dispatch".

		return "dispatch";
	} // getName

	/**
	 * operation dispatch <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution dispatch(
			fUML.Semantics.Classes.Kernel.Object_ object,
			fUML.Syntax.Classes.Kernel.Operation operation) {
		// Get the behavior for the given operation as determined by the type(s)
		// of the given object, compile the behavior at the locus of the object,
		// and return the resulting execution object.

		return object.locus.factory.createExecution(this.getMethod(object,
				operation), object);
	} // dispatch

	/**
	 * operation getMethod <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior getMethod(
			fUML.Semantics.Classes.Kernel.Object_ object,
			fUML.Syntax.Classes.Kernel.Operation operation);
} // DispatchStrategy
