
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

package fUML.Syntax.CommonBehaviors.BasicBehaviors;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::CommonBehaviors::BasicBehaviors::Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Behavior#Behavior <em>Behavior</em>}</li>
 * <li>{@link Behavior#addOwnedParameter <em>addOwnedParameter</em>}</li>
 * <li>{@link Behavior#_setContext <em>_setContext</em>}</li>
 * <li>{@link Behavior#isReentrant <em>isReentrant</em>}</li>
 * <li>{@link Behavior#specification <em>specification</em>}</li>
 * <li>{@link Behavior#ownedParameter <em>ownedParameter</em>}</li>
 * <li>{@link Behavior#context <em>context</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Behavior extends fUML.Syntax.Classes.Kernel.Class_ {

	public boolean isReentrant = true;
	public fUML.Syntax.Classes.Kernel.BehavioralFeature specification = null;
	public fUML.Syntax.Classes.Kernel.ParameterList ownedParameter = new fUML.Syntax.Classes.Kernel.ParameterList();
	public fUML.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier context = null;

	/**
	 * operation Behavior <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Behavior() {
		this.isReentrant = true;
	} // Behavior

	/**
	 * operation addOwnedParameter <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedParameter(
			fUML.Syntax.Classes.Kernel.Parameter ownedParameter) {
		super.addOwnedMember(ownedParameter);
		this.ownedParameter.addValue(ownedParameter);
	} // addOwnedParameter

	/**
	 * operation _setContext <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void _setContext(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier context) {
		// Note: This is a helper operation intended to be called by certain
		// operations outside the Behavior class in order to allow the setting
		// of the context to be overriden in subclasses.
		// It should _not_ be called otherwise in order to set the context
		// directly, rather than via these other operations.

		// Debug.println("[_setContext] behavior = " + this.name +
		// ", context = " + context.name);

		this.context = context;
	} // _setContext

} // Behavior
