
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

package fUML.Syntax.Actions.IntermediateActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Actions::IntermediateActions::ValueSpecificationAction</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ValueSpecificationAction#setValue <em>setValue</em>}</li>
 * <li>{@link ValueSpecificationAction#setResult <em>setResult</em>}</li>
 * <li>{@link ValueSpecificationAction#value <em>value</em>}</li>
 * <li>{@link ValueSpecificationAction#result <em>result</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ValueSpecificationAction extends
		fUML.Syntax.Actions.BasicActions.Action {

	public fUML.Syntax.Classes.Kernel.ValueSpecification value = null;
	public fUML.Syntax.Actions.BasicActions.OutputPin result = null;

	/**
	 * operation setValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setValue(fUML.Syntax.Classes.Kernel.ValueSpecification value) {
		this.value = value;
	} // setValue

	/**
	 * operation setResult <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResult(fUML.Syntax.Actions.BasicActions.OutputPin result) {
		super.addOutput(result);
		this.result = result;
	} // setResult

} // ValueSpecificationAction
