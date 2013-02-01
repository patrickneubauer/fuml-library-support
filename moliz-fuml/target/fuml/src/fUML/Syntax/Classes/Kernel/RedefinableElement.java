
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
 * <em><b>fUML::Syntax::Classes::Kernel::RedefinableElement</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link RedefinableElement#setIsLeaf <em>setIsLeaf</em>}</li>
 * <li>{@link RedefinableElement#addRedefinedElement <em>addRedefinedElement
 * </em>}</li>
 * <li>{@link RedefinableElement#addRedefinitionContext <em>
 * addRedefinitionContext</em>}</li>
 * <li>{@link RedefinableElement#isLeaf <em>isLeaf</em>}</li>
 * <li>{@link RedefinableElement#redefinedElement <em>redefinedElement</em>}</li>
 * <li>{@link RedefinableElement#redefinitionContext <em>redefinitionContext
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class RedefinableElement extends
		fUML.Syntax.Classes.Kernel.NamedElement {

	public boolean isLeaf = false;
	public fUML.Syntax.Classes.Kernel.RedefinableElementList redefinedElement = new fUML.Syntax.Classes.Kernel.RedefinableElementList();
	public fUML.Syntax.Classes.Kernel.ClassifierList redefinitionContext = new fUML.Syntax.Classes.Kernel.ClassifierList();

	/**
	 * operation setIsLeaf <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	} // setIsLeaf

	/**
	 * operation addRedefinedElement <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected void addRedefinedElement(
			fUML.Syntax.Classes.Kernel.RedefinableElement redefinedElement) {
		this.redefinedElement.addValue(redefinedElement);
	} // addRedefinedElement

	/**
	 * operation addRedefinitionContext <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRedefinitionContext(
			fUML.Syntax.Classes.Kernel.Classifier redefinitionContext) {
		this.redefinitionContext.addValue(redefinitionContext);
	} // addRedefinitionContext

} // RedefinableElement
