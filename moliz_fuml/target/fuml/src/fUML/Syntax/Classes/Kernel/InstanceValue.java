
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
 * <em><b>fUML::Syntax::Classes::Kernel::InstanceValue</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link InstanceValue#setInstance <em>setInstance</em>}</li>
 * <li>{@link InstanceValue#instance <em>instance</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class InstanceValue extends
		fUML.Syntax.Classes.Kernel.ValueSpecification {

	public fUML.Syntax.Classes.Kernel.InstanceSpecification instance = null;

	/**
	 * operation setInstance <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInstance(
			fUML.Syntax.Classes.Kernel.InstanceSpecification instance) {
		this.instance = instance;
	} // setInstance

} // InstanceValue
