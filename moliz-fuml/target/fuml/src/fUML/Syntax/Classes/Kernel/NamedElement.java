
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
 * <em><b>fUML::Syntax::Classes::Kernel::NamedElement</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link NamedElement#setName <em>setName</em>}</li>
 * <li>{@link NamedElement#setVisibility <em>setVisibility</em>}</li>
 * <li>{@link NamedElement#name <em>name</em>}</li>
 * <li>{@link NamedElement#visibility <em>visibility</em>}</li>
 * <li>{@link NamedElement#qualifiedName <em>qualifiedName</em>}</li>
 * <li>{@link NamedElement#namespace <em>namespace</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class NamedElement extends fUML.Syntax.Classes.Kernel.Element {

	public String name = "";
	public fUML.Syntax.Classes.Kernel.VisibilityKind visibility = null;
	public String qualifiedName = "";
	public fUML.Syntax.Classes.Kernel.Namespace namespace = null;

	/**
	 * operation setName <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String name) {
		this.name = name;

		if (name != null) {
			if (this.namespace == null) {
				this.qualifiedName = name;
			} else if (this.namespace.qualifiedName != null) {
				this.qualifiedName = this.namespace.qualifiedName + "::" + name;
			}
		}

	} // setName

	/**
	 * operation setVisibility <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVisibility(
			fUML.Syntax.Classes.Kernel.VisibilityKind visibility) {
		this.visibility = visibility;
	} // setVisibility

} // NamedElement
