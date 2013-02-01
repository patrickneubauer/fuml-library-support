
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
 * <em><b>fUML::Syntax::Activities::IntermediateActivities::ActivityEdge</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ActivityEdge#setTarget <em>setTarget</em>}</li>
 * <li>{@link ActivityEdge#setSource <em>setSource</em>}</li>
 * <li>{@link ActivityEdge#setGuard <em>setGuard</em>}</li>
 * <li>{@link ActivityEdge#activity <em>activity</em>}</li>
 * <li>{@link ActivityEdge#source <em>source</em>}</li>
 * <li>{@link ActivityEdge#target <em>target</em>}</li>
 * <li>{@link ActivityEdge#guard <em>guard</em>}</li>
 * <li>{@link ActivityEdge#inStructuredNode <em>inStructuredNode</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class ActivityEdge extends
		fUML.Syntax.Classes.Kernel.RedefinableElement {

	public fUML.Syntax.Activities.IntermediateActivities.Activity activity = null;
	public fUML.Syntax.Activities.IntermediateActivities.ActivityNode source = null;
	public fUML.Syntax.Activities.IntermediateActivities.ActivityNode target = null;
	public fUML.Syntax.Classes.Kernel.ValueSpecification guard = null;
	public fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode inStructuredNode = null;

	/**
	 * operation setTarget <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTarget(
			fUML.Syntax.Activities.IntermediateActivities.ActivityNode target) {
		this.target = target;
		target.incoming.addValue(this);
	} // setTarget

	/**
	 * operation setSource <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSource(
			fUML.Syntax.Activities.IntermediateActivities.ActivityNode source) {
		this.source = source;
		source.outgoing.addValue(this);
	} // setSource

	/**
	 * operation setGuard <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGuard(fUML.Syntax.Classes.Kernel.ValueSpecification guard) {
		if (guard != null) {
			super.addOwnedElement(guard);
		}

		this.guard = guard;
	} // setGuard

} // ActivityEdge
