
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

package fUML.Syntax.Activities.IntermediateActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Activities::IntermediateActivities::ObjectNode</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ObjectNode#setType <em>setType</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class ObjectNode extends
		fUML.Syntax.Activities.IntermediateActivities.ActivityNode {

	public fUML.Syntax.Classes.Kernel.TypedElement typedElement = new fUML.Syntax.Classes.Kernel.TypedElement();

	/**
	 * operation setType <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(fUML.Syntax.Classes.Kernel.Type type) {
		this.typedElement.type = type;
	} // setType

} // ObjectNode
