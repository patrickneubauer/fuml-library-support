
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

package fUML.Syntax.Actions.CompleteActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Actions::CompleteActions::ReduceAction</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ReduceAction#setIsOrdered <em>setIsOrdered</em>}</li>
 * <li>{@link ReduceAction#setReducer <em>setReducer</em>}</li>
 * <li>{@link ReduceAction#setCollection <em>setCollection</em>}</li>
 * <li>{@link ReduceAction#setResult <em>setResult</em>}</li>
 * <li>{@link ReduceAction#reducer <em>reducer</em>}</li>
 * <li>{@link ReduceAction#result <em>result</em>}</li>
 * <li>{@link ReduceAction#collection <em>collection</em>}</li>
 * <li>{@link ReduceAction#isOrdered <em>isOrdered</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ReduceAction extends fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior reducer = null;
	public fUML.Syntax.Actions.BasicActions.OutputPin result = null;
	public fUML.Syntax.Actions.BasicActions.InputPin collection = null;
	public boolean isOrdered = false;

	/**
	 * operation setIsOrdered <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	} // setIsOrdered

	/**
	 * operation setReducer <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReducer(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior reducer) {
		this.reducer = reducer;
	} // setReducer

	/**
	 * operation setCollection <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCollection(
			fUML.Syntax.Actions.BasicActions.InputPin collection) {
		super.addInput(collection);
		this.collection = collection;
	} // setCollection

	/**
	 * operation setResult <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResult(fUML.Syntax.Actions.BasicActions.OutputPin result) {
		super.addOutput(result);
		this.result = result;
	} // setResult

} // ReduceAction
