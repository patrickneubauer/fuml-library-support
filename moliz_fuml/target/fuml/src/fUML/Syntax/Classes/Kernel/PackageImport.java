
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
 * <em><b>fUML::Syntax::Classes::Kernel::PackageImport</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link PackageImport#setVisibility <em>setVisibility</em>}</li>
 * <li>{@link PackageImport#setImportedPackage <em>setImportedPackage</em>}</li>
 * <li>{@link PackageImport#visibility <em>visibility</em>}</li>
 * <li>{@link PackageImport#importingNamespace <em>importingNamespace</em>}</li>
 * <li>{@link PackageImport#importedPackage <em>importedPackage</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class PackageImport extends fUML.Syntax.Classes.Kernel.Element {

	public fUML.Syntax.Classes.Kernel.VisibilityKind visibility = null;
	public fUML.Syntax.Classes.Kernel.Namespace importingNamespace = null;
	public fUML.Syntax.Classes.Kernel.Package importedPackage = null;

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
	 * operation setImportedPackage <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setImportedPackage(
			fUML.Syntax.Classes.Kernel.Package importedPackage) {
		this.importedPackage = importedPackage;
	} // setImportedPackage

} // PackageImport
