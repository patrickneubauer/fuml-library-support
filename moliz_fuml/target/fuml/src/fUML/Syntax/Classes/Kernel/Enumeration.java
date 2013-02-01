
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

package fUML.Syntax.Classes.Kernel;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Classes::Kernel::Enumeration</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Enumeration#addOwnedLiteral <em>addOwnedLiteral</em>}</li>
 * <li>{@link Enumeration#ownedLiteral <em>ownedLiteral</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Enumeration extends fUML.Syntax.Classes.Kernel.DataType {

	public fUML.Syntax.Classes.Kernel.EnumerationLiteralList ownedLiteral = new fUML.Syntax.Classes.Kernel.EnumerationLiteralList();

	/**
	 * operation addOwnedLiteral <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedLiteral(
			fUML.Syntax.Classes.Kernel.EnumerationLiteral ownedLiteral) {
		super.addOwnedMember(ownedLiteral);

		this.ownedLiteral.addValue(ownedLiteral);
		ownedLiteral.enumeration = this;
	} // addOwnedLiteral

} // Enumeration
