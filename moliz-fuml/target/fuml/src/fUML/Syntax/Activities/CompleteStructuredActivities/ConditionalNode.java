
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
 * <em><b>fUML::Syntax::Activities::CompleteStructuredActivities::ConditionalNode</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ConditionalNode#setIsDeterminate <em>setIsDeterminate</em>}</li>
 * <li>{@link ConditionalNode#setIsAssured <em>setIsAssured</em>}</li>
 * <li>{@link ConditionalNode#addClause <em>addClause</em>}</li>
 * <li>{@link ConditionalNode#addResult <em>addResult</em>}</li>
 * <li>{@link ConditionalNode#isDeterminate <em>isDeterminate</em>}</li>
 * <li>{@link ConditionalNode#isAssured <em>isAssured</em>}</li>
 * <li>{@link ConditionalNode#clause <em>clause</em>}</li>
 * <li>{@link ConditionalNode#result <em>result</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ConditionalNode
		extends
		fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode {

	public boolean isDeterminate = false;
	public boolean isAssured = false;
	public fUML.Syntax.Activities.CompleteStructuredActivities.ClauseList clause = new fUML.Syntax.Activities.CompleteStructuredActivities.ClauseList();
	public fUML.Syntax.Actions.BasicActions.OutputPinList result = new fUML.Syntax.Actions.BasicActions.OutputPinList();

	/**
	 * operation setIsDeterminate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsDeterminate(boolean isDeterminate) {
		this.isDeterminate = isDeterminate;
	} // setIsDeterminate

	/**
	 * operation setIsAssured <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsAssured(boolean isAssured) {
		this.isAssured = isAssured;
	} // setIsAssured

	/**
	 * operation addClause <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addClause(
			fUML.Syntax.Activities.CompleteStructuredActivities.Clause clause) {
		super.addOwnedElement(clause);
		this.clause.addValue(clause);
	} // addClause

	/**
	 * operation addResult <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addResult(fUML.Syntax.Actions.BasicActions.OutputPin result) {
		super.addStructuredNodeOutput(result);
		this.result.addValue(result);
	} // addResult

} // ConditionalNode
