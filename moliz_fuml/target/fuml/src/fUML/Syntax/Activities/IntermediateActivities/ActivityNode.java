
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
 * <em><b>fUML::Syntax::Activities::IntermediateActivities::ActivityNode</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ActivityNode#inStructuredNode <em>inStructuredNode</em>}</li>
 * <li>{@link ActivityNode#activity <em>activity</em>}</li>
 * <li>{@link ActivityNode#outgoing <em>outgoing</em>}</li>
 * <li>{@link ActivityNode#incoming <em>incoming</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class ActivityNode extends
		fUML.Syntax.Classes.Kernel.RedefinableElement {

	public fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode inStructuredNode = null;
	public fUML.Syntax.Activities.IntermediateActivities.Activity activity = null;
	public fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList outgoing = new fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList();
	public fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList incoming = new fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList();

} // ActivityNode
