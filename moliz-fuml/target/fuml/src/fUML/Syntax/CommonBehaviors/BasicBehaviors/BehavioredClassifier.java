
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
 * <em><b>fUML::Syntax::CommonBehaviors::BasicBehaviors::BehavioredClassifier</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link BehavioredClassifier#addOwnedBehavior <em>addOwnedBehavior</em>}</li>
 * <li>{@link BehavioredClassifier#setClassifierBehavior <em>
 * setClassifierBehavior</em>}</li>
 * <li>{@link BehavioredClassifier#ownedBehavior <em>ownedBehavior</em>}</li>
 * <li>{@link BehavioredClassifier#classifierBehavior <em>classifierBehavior
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class BehavioredClassifier extends
		fUML.Syntax.Classes.Kernel.Classifier {

	public fUML.Syntax.CommonBehaviors.BasicBehaviors.BehaviorList ownedBehavior = new fUML.Syntax.CommonBehaviors.BasicBehaviors.BehaviorList();
	public fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior classifierBehavior = null;

	/**
	 * operation addOwnedBehavior <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedBehavior(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior ownedBehavior) {
		super.addOwnedMember(ownedBehavior);

		this.ownedBehavior.addValue(ownedBehavior);
		ownedBehavior._setContext(this);

	} // addOwnedBehavior

	/**
	 * operation setClassifierBehavior <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setClassifierBehavior(
			fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior classifierBehavior) {
		// Note: The classifier behavior must also be added as an owned behavior
		// using addOwnedBehavior.

		this.classifierBehavior = classifierBehavior;
	} // setClassifierBehavior

} // BehavioredClassifier
