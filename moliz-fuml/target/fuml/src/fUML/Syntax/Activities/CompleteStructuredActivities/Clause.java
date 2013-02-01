
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
 * <em><b>fUML::Syntax::Activities::CompleteStructuredActivities::Clause</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Clause#addPredecessorClause <em>addPredecessorClause</em>}</li>
 * <li>{@link Clause#addTest <em>addTest</em>}</li>
 * <li>{@link Clause#addBody <em>addBody</em>}</li>
 * <li>{@link Clause#setDecider <em>setDecider</em>}</li>
 * <li>{@link Clause#addBodyOutput <em>addBodyOutput</em>}</li>
 * <li>{@link Clause#test <em>test</em>}</li>
 * <li>{@link Clause#body <em>body</em>}</li>
 * <li>{@link Clause#predecessorClause <em>predecessorClause</em>}</li>
 * <li>{@link Clause#successorClause <em>successorClause</em>}</li>
 * <li>{@link Clause#decider <em>decider</em>}</li>
 * <li>{@link Clause#bodyOutput <em>bodyOutput</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Clause extends fUML.Syntax.Classes.Kernel.Element {

	public fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList test = new fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList();
	public fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList body = new fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNodeList();
	public fUML.Syntax.Activities.CompleteStructuredActivities.ClauseList predecessorClause = new fUML.Syntax.Activities.CompleteStructuredActivities.ClauseList();
	public fUML.Syntax.Activities.CompleteStructuredActivities.ClauseList successorClause = new fUML.Syntax.Activities.CompleteStructuredActivities.ClauseList();
	public fUML.Syntax.Actions.BasicActions.OutputPin decider = null;
	public fUML.Syntax.Actions.BasicActions.OutputPinList bodyOutput = new fUML.Syntax.Actions.BasicActions.OutputPinList();

	/**
	 * operation addPredecessorClause <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void addPredecessorClause(
			fUML.Syntax.Activities.CompleteStructuredActivities.Clause predecessorClause) {
		this.predecessorClause.addValue(predecessorClause);
		predecessorClause.successorClause.addValue(this);
	} // addPredecessorClause

	/**
	 * operation addTest <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addTest(
			fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode test) {
		this.test.addValue(test);
	} // addTest

	/**
	 * operation addBody <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addBody(
			fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode body) {
		this.body.addValue(body);
	} // addBody

	/**
	 * operation setDecider <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDecider(fUML.Syntax.Actions.BasicActions.OutputPin decider) {
		this.decider = decider;
	} // setDecider

	/**
	 * operation addBodyOutput <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addBodyOutput(
			fUML.Syntax.Actions.BasicActions.OutputPin bodyOutput) {
		this.bodyOutput.addValue(bodyOutput);
	} // addBodyOutput

} // Clause
