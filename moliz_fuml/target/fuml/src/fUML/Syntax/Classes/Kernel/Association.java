
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
 * <em><b>fUML::Syntax::Classes::Kernel::Association</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Association#addOwnedEnd <em>addOwnedEnd</em>}</li>
 * <li>{@link Association#addNavigableOwnedEnd <em>addNavigableOwnedEnd</em>}</li>
 * <li>{@link Association#isDerived <em>isDerived</em>}</li>
 * <li>{@link Association#ownedEnd <em>ownedEnd</em>}</li>
 * <li>{@link Association#endType <em>endType</em>}</li>
 * <li>{@link Association#memberEnd <em>memberEnd</em>}</li>
 * <li>{@link Association#navigableOwnedEnd <em>navigableOwnedEnd</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Association extends fUML.Syntax.Classes.Kernel.Classifier {

	public boolean isDerived = false;
	public fUML.Syntax.Classes.Kernel.PropertyList ownedEnd = new fUML.Syntax.Classes.Kernel.PropertyList();
	public fUML.Syntax.Classes.Kernel.TypeList endType = new fUML.Syntax.Classes.Kernel.TypeList();
	public fUML.Syntax.Classes.Kernel.PropertyList memberEnd = new fUML.Syntax.Classes.Kernel.PropertyList();
	public fUML.Syntax.Classes.Kernel.PropertyList navigableOwnedEnd = new fUML.Syntax.Classes.Kernel.PropertyList();

	/**
	 * operation addOwnedEnd <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedEnd(fUML.Syntax.Classes.Kernel.Property ownedEnd) {
		super.addFeature(ownedEnd);
		super.addOwnedMember(ownedEnd);

		this.ownedEnd.addValue(ownedEnd);
		ownedEnd.owningAssociation = this;

		this.memberEnd.addValue(ownedEnd);
		ownedEnd.association = this;

		this.endType.addValue(ownedEnd.typedElement.type);
	} // addOwnedEnd

	/**
	 * operation addNavigableOwnedEnd <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void addNavigableOwnedEnd(
			fUML.Syntax.Classes.Kernel.Property navigableOwnedEnd) {
		// Note: A navigable end must also be set as an owned end using
		// setOwnedEnd.

		this.navigableOwnedEnd.addValue(navigableOwnedEnd);
	} // addNavigableOwnedEnd

} // Association
