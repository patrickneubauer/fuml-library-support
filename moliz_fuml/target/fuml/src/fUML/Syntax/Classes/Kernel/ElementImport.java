
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
 * <em><b>fUML::Syntax::Classes::Kernel::ElementImport</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ElementImport#setAlias <em>setAlias</em>}</li>
 * <li>{@link ElementImport#setVisibility <em>setVisibility</em>}</li>
 * <li>{@link ElementImport#setImportedElement <em>setImportedElement</em>}</li>
 * <li>{@link ElementImport#visibility <em>visibility</em>}</li>
 * <li>{@link ElementImport#alias <em>alias</em>}</li>
 * <li>{@link ElementImport#importedElement <em>importedElement</em>}</li>
 * <li>{@link ElementImport#importingNamespace <em>importingNamespace</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ElementImport extends fUML.Syntax.Classes.Kernel.Element {

	public fUML.Syntax.Classes.Kernel.VisibilityKind visibility = null;
	public String alias = "";
	public fUML.Syntax.Classes.Kernel.PackageableElement importedElement = null;
	public fUML.Syntax.Classes.Kernel.Namespace importingNamespace = null;

	/**
	 * operation setAlias <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	} // setAlias

	/**
	 * operation setVisibility <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVisibility(
			fUML.Syntax.Classes.Kernel.VisibilityKind visibility) {
		this.visibility = visibility;
	} // setVisibility

	/**
	 * operation setImportedElement <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setImportedElement(
			fUML.Syntax.Classes.Kernel.PackageableElement importedElement) {
		this.importedElement = importedElement;
	} // setImportedElement

} // ElementImport
