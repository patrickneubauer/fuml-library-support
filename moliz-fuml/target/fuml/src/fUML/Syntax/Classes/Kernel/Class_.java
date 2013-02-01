
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
 * <em><b>fUML::Syntax::Classes::Kernel::Class</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Class#setIsAbstract <em>setIsAbstract</em>}</li>
 * <li>{@link Class#setIsActive <em>setIsActive</em>}</li>
 * <li>{@link Class#addGeneralization <em>addGeneralization</em>}</li>
 * <li>{@link Class#addOwnedAttribute <em>addOwnedAttribute</em>}</li>
 * <li>{@link Class#addOwnedOperation <em>addOwnedOperation</em>}</li>
 * <li>{@link Class#addOwnedReception <em>addOwnedReception</em>}</li>
 * <li>{@link Class#inherit <em>inherit</em>}</li>
 * <li>{@link Class#addNestedClassifier <em>addNestedClassifier</em>}</li>
 * <li>{@link Class#isAbstract <em>isAbstract</em>}</li>
 * <li>{@link Class#ownedOperation <em>ownedOperation</em>}</li>
 * <li>{@link Class#superClass <em>superClass</em>}</li>
 * <li>{@link Class#isActive <em>isActive</em>}</li>
 * <li>{@link Class#ownedReception <em>ownedReception</em>}</li>
 * <li>{@link Class#ownedAttribute <em>ownedAttribute</em>}</li>
 * <li>{@link Class#nestedClassifier <em>nestedClassifier</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Class_ extends
		fUML.Syntax.CommonBehaviors.BasicBehaviors.BehavioredClassifier {

	public boolean isAbstract = false;
	public fUML.Syntax.Classes.Kernel.OperationList ownedOperation = new fUML.Syntax.Classes.Kernel.OperationList();
	public fUML.Syntax.Classes.Kernel.Class_List superClass = new fUML.Syntax.Classes.Kernel.Class_List();
	public boolean isActive = false;
	public fUML.Syntax.CommonBehaviors.Communications.ReceptionList ownedReception = new fUML.Syntax.CommonBehaviors.Communications.ReceptionList();
	public fUML.Syntax.Classes.Kernel.PropertyList ownedAttribute = new fUML.Syntax.Classes.Kernel.PropertyList();
	public fUML.Syntax.Classes.Kernel.ClassifierList nestedClassifier = new fUML.Syntax.Classes.Kernel.ClassifierList();

	/**
	 * operation setIsAbstract <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	} // setIsAbstract

	/**
	 * operation setIsActive <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	} // setIsActive

	/**
	 * operation addGeneralization <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addGeneralization(
			fUML.Syntax.Classes.Kernel.Generalization generalization) {
		super.addGeneralization(generalization);

		if (generalization.general instanceof Class_) {
			this.superClass.addValue((Class_) generalization.general);
		}
	} // addGeneralization

	/**
	 * operation addOwnedAttribute <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedAttribute(
			fUML.Syntax.Classes.Kernel.Property ownedAttribute) {
		super.addAttribute(ownedAttribute);
		super.addOwnedMember(ownedAttribute);

		this.ownedAttribute.addValue(ownedAttribute);
		ownedAttribute.class_ = this;
	} // addOwnedAttribute

	/**
	 * operation addOwnedOperation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedOperation(
			fUML.Syntax.Classes.Kernel.Operation ownedOperation) {
		super.addFeature(ownedOperation);
		super.addOwnedMember(ownedOperation);

		this.ownedOperation.addValue(ownedOperation);
		ownedOperation.class_ = this;
	} // addOwnedOperation

	/**
	 * operation addOwnedReception <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOwnedReception(
			fUML.Syntax.CommonBehaviors.Communications.Reception ownedReception) {
		super.addOwnedMember(ownedReception);
		super.addFeature(ownedReception);

		this.ownedReception.addValue(ownedReception);
	} // addOwnedReception

	/**
	 * operation inherit <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.NamedElementList inherit(
			fUML.Syntax.Classes.Kernel.NamedElementList inhs) {
		// "The inherit operation is overridden to exclude redefined properties."

		RedefinableElementList redefinableMembers = new RedefinableElementList();

		for (int i = 0; i < this.ownedMember.size(); i++) {
			if (this.ownedMember.getValue(i) instanceof RedefinableElement) {
				redefinableMembers
						.addValue((RedefinableElement) this.ownedMember
								.getValue(i));
			}
		}

		NamedElementList inherited = new NamedElementList();

		for (int i = 0; i < inhs.size(); i++) {
			NamedElement inh = inhs.getValue(i);
			boolean exclude = false;
			for (int j = 0; j < redefinableMembers.size(); j++) {
				RedefinableElementList redefinedElements = redefinableMembers
						.getValue(j).redefinedElement;
				for (int k = 0; k < redefinedElements.size(); k++) {
					if (redefinedElements.getValue(k) == inh) {
						exclude = true;
						break;
					}
				}
				if (exclude)
					break;
			}

			if (!exclude) {
				inherited.addValue(inh);
			}
		}

		return inherited;
	} // inherit

	/**
	 * operation addNestedClassifier <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void addNestedClassifier(
			fUML.Syntax.Classes.Kernel.Classifier nestedClassifier) {
		this.addOwnedMember(nestedClassifier);
		this.nestedClassifier.addValue(nestedClassifier);
	} // addNestedClassifier

} // Class
