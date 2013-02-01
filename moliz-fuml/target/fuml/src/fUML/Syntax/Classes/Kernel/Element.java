
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
 * <em><b>fUML::Syntax::Classes::Kernel::Element</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Element#addOwnedElement <em>addOwnedElement</em>}</li>
 * <li>{@link Element#ownedElement <em>ownedElement</em>}</li>
 * <li>{@link Element#owner <em>owner</em>}</li>
 * <li>{@link Element#ownedComment <em>ownedComment</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Element extends org.modeldriven.fuml.FumlObject {

	public fUML.Syntax.Classes.Kernel.ElementList ownedElement = new fUML.Syntax.Classes.Kernel.ElementList();
	public fUML.Syntax.Classes.Kernel.Element owner = null;
	public fUML.Syntax.Classes.Kernel.CommentList ownedComment = new fUML.Syntax.Classes.Kernel.CommentList();

	/**
	 * operation addOwnedElement <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addOwnedElement(
			fUML.Syntax.Classes.Kernel.Element ownedElement) {
		this.ownedElement.add(ownedElement);
		ownedElement.owner = this;

	} // addOwnedElement

} // Element
