
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

package fUML.Syntax.Activities.CompleteStructuredActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Activities::CompleteStructuredActivities::StructuredActivityNode</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link StructuredActivityNode#setMustIsolate <em>setMustIsolate</em>}</li>
 * <li>{@link StructuredActivityNode#addNode <em>addNode</em>}</li>
 * <li>{@link StructuredActivityNode#addEdge <em>addEdge</em>}</li>
 * <li>{@link StructuredActivityNode#addStructuredNodeOutput <em>
 * addStructuredNodeOutput</em>}</li>
 * <li>{@link StructuredActivityNode#addStructuredNodeInput <em>
 * addStructuredNodeInput</em>}</li>
 * <li>{@link StructuredActivityNode#node <em>node</em>}</li>
 * <li>{@link StructuredActivityNode#activity <em>activity</em>}</li>
 * <li>{@link StructuredActivityNode#mustIsolate <em>mustIsolate</em>}</li>
 * <li>{@link StructuredActivityNode#edge <em>edge</em>}</li>
 * <li>{@link StructuredActivityNode#structuredNodeOutput <em>
 * structuredNodeOutput</em>}</li>
 * <li>{@link StructuredActivityNode#structuredNodeInput <em>structuredNodeInput
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class StructuredActivityNode extends
		fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList node = new fUML.Syntax.Activities.IntermediateActivities.ActivityNodeList();
	public fUML.Syntax.Activities.IntermediateActivities.Activity activity = null;
	public boolean mustIsolate = false;
	public fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList edge = new fUML.Syntax.Activities.IntermediateActivities.ActivityEdgeList();
	public fUML.Syntax.Actions.BasicActions.OutputPinList structuredNodeOutput = new fUML.Syntax.Actions.BasicActions.OutputPinList();
	public fUML.Syntax.Actions.BasicActions.InputPinList structuredNodeInput = new fUML.Syntax.Actions.BasicActions.InputPinList();

	/**
	 * operation setMustIsolate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMustIsolate(boolean mustIsolate) {
		this.mustIsolate = mustIsolate;
	} // setMustIsolate

	/**
	 * operation addNode <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addNode(
			fUML.Syntax.Activities.IntermediateActivities.ActivityNode node) {
		this.node.addValue(node);
		node.inStructuredNode = this;
	} // addNode

	/**
	 * operation addEdge <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addEdge(
			fUML.Syntax.Activities.IntermediateActivities.ActivityEdge edge) {
		this.edge.addValue(edge);
		edge.inStructuredNode = this;
	} // addEdge

	/**
	 * operation addStructuredNodeOutput <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void addStructuredNodeOutput(
			fUML.Syntax.Actions.BasicActions.OutputPin structuredNodeOutput) {
		this.addOutput(structuredNodeOutput);
		this.structuredNodeOutput.addValue(structuredNodeOutput);
	} // addStructuredNodeOutput

	/**
	 * operation addStructuredNodeInput <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void addStructuredNodeInput(
			fUML.Syntax.Actions.BasicActions.InputPin structuredNodeInput) {
		this.addInput(structuredNodeInput);
		this.structuredNodeInput.addValue(structuredNodeInput);
	} // addStructuredNodeInput

} // StructuredActivityNode
