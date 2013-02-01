
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
 * <em><b>fUML::Syntax::Classes::Kernel::BehavioralFeature</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link BehavioralFeature#setIsAbstract <em>setIsAbstract</em>}</li>
 * <li>{@link BehavioralFeature#addOwnedParameter <em>addOwnedParameter</em>}</li>
 * <li>{@link BehavioralFeature#addMethod <em>addMethod</em>}</li>
 * <li>{@link BehavioralFeature#ownedParameter <em>ownedParameter</em>}</li>
 * <li>{@link BehavioralFeature#isAbstract <em>isAbstract</em>}</li>
 * <li>{@link BehavioralFeature#method <em>method</em>}</li>
 * <li>{@link BehavioralFeature#concurrency <em>concurrency</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class BehavioralFeature extends
		fUML.Syntax.Classes.Kernel.Feature {

	public fUML.Syntax.Classes.Kernel.ParameterList ownedParameter = new fUML.Syntax.Classes.Kernel.ParameterList();
	public boolean isAbstract = false;
	public fUML.Syntax.CommonBehaviors.BasicBehaviors.BehaviorList method = new fUML.Syntax.CommonBehaviors.BasicBehaviors.BehaviorList();
	public fUML.Syntax.CommonBehaviors.BasicBehaviors.CallConcurrencyKind concurrency = fUML.Syntax.CommonBehaviors.BasicBehaviors.CallConcurrencyKind.sequential;

	/**
	 * operation setIsAbstract <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	} // setIsAbstract

	/**
	 * operation addOwnedParameter <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedParameter(
			fUML.Syntax.Classes.Kernel.Parameter ownedParameter) {
		// this.addOwnedMember(ownedParameter); [Note: BehavioralFeature is not
		// a Namespace in fUML, to avoid multiple inheritance.]

		this.ownedParameter.addValue(ownedParameter);
	} // addOwnedParameter

	/**
	 * operation addMethod <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addMethod(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior method) {
		method.specification = this;
		this.method.addValue(method);
	} // addMethod

} // BehavioralFeature
