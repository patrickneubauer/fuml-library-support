
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

package fUML.Syntax.Classes.Kernel;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Classes::Kernel::Operation</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Operation#setIsQuery <em>setIsQuery</em>}</li>
 * <li>{@link Operation#addOwnedParameter <em>addOwnedParameter</em>}</li>
 * <li>{@link Operation#addRedefinedOperation <em>addRedefinedOperation</em>}</li>
 * <li>{@link Operation#addMethod <em>addMethod</em>}</li>
 * <li>{@link Operation#isQuery <em>isQuery</em>}</li>
 * <li>{@link Operation#isOrdered <em>isOrdered</em>}</li>
 * <li>{@link Operation#isUnique <em>isUnique</em>}</li>
 * <li>{@link Operation#lower <em>lower</em>}</li>
 * <li>{@link Operation#upper <em>upper</em>}</li>
 * <li>{@link Operation#class <em>class</em>}</li>
 * <li>{@link Operation#redefinedOperation <em>redefinedOperation</em>}</li>
 * <li>{@link Operation#type <em>type</em>}</li>
 * <li>{@link Operation#ownedParameter <em>ownedParameter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Operation extends fUML.Syntax.Classes.Kernel.BehavioralFeature {

	public boolean isQuery = false;
	public boolean isOrdered = false;
	public boolean isUnique = true;
	public int lower = 0;
	public UnlimitedNatural upper = null;
	public fUML.Syntax.Classes.Kernel.Class_ class_ = null;
	public fUML.Syntax.Classes.Kernel.OperationList redefinedOperation = new fUML.Syntax.Classes.Kernel.OperationList();
	public fUML.Syntax.Classes.Kernel.Type type = null;
	public fUML.Syntax.Classes.Kernel.ParameterList ownedParameter = new fUML.Syntax.Classes.Kernel.ParameterList();

	/**
	 * operation setIsQuery <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsQuery(boolean isQuery) {
		this.isQuery = isQuery;
	} // setIsQuery

	/**
	 * operation addOwnedParameter <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedParameter(
			fUML.Syntax.Classes.Kernel.Parameter ownedParameter) {
		super.addOwnedParameter(ownedParameter);
		this.ownedParameter.addValue(ownedParameter);
		ownedParameter.operation = this;

		if (ownedParameter.direction == ParameterDirectionKind.return_) {
			this.isOrdered = ownedParameter.multiplicityElement.isOrdered;
			this.isUnique = ownedParameter.multiplicityElement.isUnique;
			this.lower = ownedParameter.multiplicityElement.lower;
			this.upper = ownedParameter.multiplicityElement.upper;
			this.type = ownedParameter.type;
		}
	} // addOwnedParameter

	/**
	 * operation addRedefinedOperation <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void addRedefinedOperation(
			fUML.Syntax.Classes.Kernel.Operation redefinedOperation) {
		super.addRedefinedElement(redefinedOperation);
		this.redefinedOperation.addValue(redefinedOperation);
	} // addRedefinedOperation

	/**
	 * operation addMethod <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addMethod(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior method) {
		// Note: To have a method, an operation must be owned by a class. The
		// method must be an owned behavior of the class.

		this.method.addValue(method);
		method.specification = this;

	} // addMethod

} // Operation
