
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
 * <em><b>fUML::Syntax::Activities::IntermediateActivities::Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Activity#setIsReadOnly <em>setIsReadOnly</em>}</li>
 * <li>{@link Activity#addNode <em>addNode</em>}</li>
 * <li>{@link Activity#addEdge <em>addEdge</em>}</li>
 * <li>{@link Activity#_setContext <em>_setContext</em>}</li>
 * <li>{@link Activity#structuredNode <em>structuredNode</em>}</li>
 * <li>{@link Activity#node <em>node</em>}</li>
 * <li>{@link Activity#isReadOnly <em>isReadOnly</em>}</li>
 * <li>{@link Activity#edge <em>edge</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Activity extends
		fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior {

	public fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNodeList structuredNode = new fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNodeList();
	public fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList node = new fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList();
	public boolean isReadOnly = false;
	public fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList edge = new fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList();

	/**
	 * operation setIsReadOnly <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	} // setIsReadOnly

	/**
	 * operation addNode <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addNode(
			fUML.Syntax.Activities.IntermediateActivities.ActivityNode node) {
		super.addOwnedElement(node);

		this.node.addValue(node);
		node.activity = this;

	} // addNode

	/**
	 * operation addEdge <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addEdge(
			fUML.Syntax.Activities.IntermediateActivities.ActivityEdge edge) {
		super.addOwnedElement(edge);

		this.edge.addValue(edge);
		edge.activity = this;
	} // addEdge

	/**
	 * operation _setContext <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void _setContext(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier context) {
		// Note: The context of an activity should be set only _after_ all nodes
		// have been added to the activity.

		super._setContext(context);

		for (int i = 0; i < this.node.size(); i++) {
			ActivityNode node = this.node.getValue(i);
			if (node instanceof fUML.Syntax.Actions.BasicActions.Action) {
				((fUML.Syntax.Actions.BasicActions.Action) node).context = context;
			}
		}
	} // _setContext

} // Activity
