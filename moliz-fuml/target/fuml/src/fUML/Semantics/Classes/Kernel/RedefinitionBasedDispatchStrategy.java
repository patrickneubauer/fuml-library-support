
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

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;

import fUML.Semantics.*;
import fUML.Semantics.CommonBehaviors.Communications.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Classes::Kernel::RedefinitionBasedDispatchStrategy</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link RedefinitionBasedDispatchStrategy#getMethod <em>getMethod</em>}</li>
 * <li>{@link RedefinitionBasedDispatchStrategy#operationsMatch <em>
 * operationsMatch</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class RedefinitionBasedDispatchStrategy extends
		fUML.Semantics.Classes.Kernel.DispatchStrategy {

	/**
	 * operation getMethod <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior getMethod(
			fUML.Semantics.Classes.Kernel.Object_ object,
			fUML.Syntax.Classes.Kernel.Operation operation) {
		// Get the method that corresponds to the given operation for the given
		// object.
		// [If there is more than one type with a method for the operation, then
		// the first one is arbitrarily chosen.]

		Behavior method = null;
		int i = 1;
		while (method == null & i <= object.types.size()) {
			Class_ type = object.types.getValue(i - 1);
			NamedElementList members = type.member;
			int j = 1;
			while (method == null & j <= members.size()) {
				NamedElement member = members.getValue(j - 1);
				if (member instanceof Operation) {
					Operation memberOperation = (Operation) member;
					if (this.operationsMatch(memberOperation, operation)) {
						method = memberOperation.method.getValue(0);
					}
				}
				j = j + 1;
			}
			i = i + 1;
		}

		return method;
	} // getMethod

	/**
	 * operation operationsMatch <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean operationsMatch(
			fUML.Syntax.Classes.Kernel.Operation ownedOperation,
			fUML.Syntax.Classes.Kernel.Operation baseOperation) {
		// Check if the owned operation is equal to or a redefinition (directly
		// or indirectly) of the base operation.

		boolean matches = false;
		if (ownedOperation == baseOperation) {
			matches = true;
		} else {
			int i = 1;
			while (!matches & i <= ownedOperation.redefinedOperation.size()) {
				matches = this.operationsMatch(
						ownedOperation.redefinedOperation.getValue(i - 1),
						baseOperation);
				i = i + 1;
			}
		}

		return matches;
	} // operationsMatch

} // RedefinitionBasedDispatchStrategy
