
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
 * <em><b>fUML::Syntax::Classes::Kernel::Generalization</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Generalization#setIsSubstitutable <em>setIsSubstitutable</em>}</li>
 * <li>{@link Generalization#setGeneral <em>setGeneral</em>}</li>
 * <li>{@link Generalization#isSubstitutable <em>isSubstitutable</em>}</li>
 * <li>{@link Generalization#specific <em>specific</em>}</li>
 * <li>{@link Generalization#general <em>general</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Generalization extends fUML.Syntax.Classes.Kernel.Element {

	public boolean isSubstitutable = false;
	public fUML.Syntax.Classes.Kernel.Classifier specific = null;
	public fUML.Syntax.Classes.Kernel.Classifier general = null;

	/**
	 * operation setIsSubstitutable <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setIsSubstitutable(boolean isSubstitutable) {
		this.isSubstitutable = isSubstitutable;
	} // setIsSubstitutable

	/**
	 * operation setGeneral <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGeneral(fUML.Syntax.Classes.Kernel.Classifier general) {
		this.general = general;
	} // setGeneral

} // Generalization
