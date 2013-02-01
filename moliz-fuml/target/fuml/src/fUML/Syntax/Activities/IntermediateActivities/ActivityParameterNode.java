
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
 * <em><b>fUML::Syntax::Activities::IntermediateActivities::ActivityParameterNode</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ActivityParameterNode#setParameter <em>setParameter</em>}</li>
 * <li>{@link ActivityParameterNode#parameter <em>parameter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ActivityParameterNode extends
		fUML.Syntax.Activities.IntermediateActivities.ObjectNode {

	public fUML.Syntax.Classes.Kernel.Parameter parameter = null;

	/**
	 * operation setParameter <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setParameter(fUML.Syntax.Classes.Kernel.Parameter parameter) {
		this.parameter = parameter;
	} // setParameter

} // ActivityParameterNode
