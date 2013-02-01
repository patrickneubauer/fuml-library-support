
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
 * <em><b>fUML::Syntax::Classes::Kernel::Classifier</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Classifier#addFeature <em>addFeature</em>}</li>
 * <li>{@link Classifier#addAttribute <em>addAttribute</em>}</li>
 * <li>{@link Classifier#addGeneralization <em>addGeneralization</em>}</li>
 * <li>{@link Classifier#setIsAbstract <em>setIsAbstract</em>}</li>
 * <li>{@link Classifier#inherit <em>inherit</em>}</li>
 * <li>{@link Classifier#inheritableMembers <em>inheritableMembers</em>}</li>
 * <li>{@link Classifier#hasVisibilityOf <em>hasVisibilityOf</em>}</li>
 * <li>{@link Classifier#setIsFinalSpecialization <em>setIsFinalSpecialization
 * </em>}</li>
 * <li>{@link Classifier#isAbstract <em>isAbstract</em>}</li>
 * <li>{@link Classifier#generalization <em>generalization</em>}</li>
 * <li>{@link Classifier#feature <em>feature</em>}</li>
 * <li>{@link Classifier#inheritedMember <em>inheritedMember</em>}</li>
 * <li>{@link Classifier#attribute <em>attribute</em>}</li>
 * <li>{@link Classifier#general <em>general</em>}</li>
 * <li>{@link Classifier#isFinalSpecialization <em>isFinalSpecialization</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Classifier extends fUML.Syntax.Classes.Kernel.Type {

	public boolean isAbstract = false;
	public fUML.Syntax.Classes.Kernel.GeneralizationList generalization = new fUML.Syntax.Classes.Kernel.GeneralizationList();
	public fUML.Syntax.Classes.Kernel.FeatureList feature = new fUML.Syntax.Classes.Kernel.FeatureList();
	public fUML.Syntax.Classes.Kernel.NamedElementList inheritedMember = new fUML.Syntax.Classes.Kernel.NamedElementList();
	public fUML.Syntax.Classes.Kernel.PropertyList attribute = new fUML.Syntax.Classes.Kernel.PropertyList();
	public fUML.Syntax.Classes.Kernel.ClassifierList general = new fUML.Syntax.Classes.Kernel.ClassifierList();
	public boolean isFinalSpecialization = false;

	/**
	 * operation addFeature <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFeature(fUML.Syntax.Classes.Kernel.Feature feature) {
		// Note: This operation should not be used directly to add Properties.
		// The addAttribute operation should be used instead.

		this.feature.addValue(feature);
		feature.featuringClassifier.add(this);
	} // addFeature

	/**
	 * operation addAttribute <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAttribute(fUML.Syntax.Classes.Kernel.Property attribute) {
		this.addFeature(attribute);
		this.attribute.addValue(attribute);
	} // addAttribute

	/**
	 * operation addGeneralization <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addGeneralization(
			fUML.Syntax.Classes.Kernel.Generalization generalization) {
		this.addOwnedElement(generalization);
		this.generalization.addValue(generalization);
		generalization.specific = this;
		this.general.addValue(generalization.general);

		NamedElementList inheritedMembers = this.inherit(generalization.general
				.inheritableMembers(this));

		for (int i = 0; i < inheritedMembers.size(); i++) {
			NamedElement inheritedMember = inheritedMembers.getValue(i);
			this.addMember(inheritedMember);
			this.inheritedMember.addValue(inheritedMember);
		}
	} // addGeneralization

	/**
	 * operation setIsAbstract <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	} // setIsAbstract

	/**
	 * operation inherit <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.NamedElementList inherit(
			fUML.Syntax.Classes.Kernel.NamedElementList inhs) {
		NamedElementList inheritedElements = new NamedElementList();

		for (int i = 0; i < inhs.size(); i++) {
			inheritedElements.addValue(inhs.getValue(i));
		}

		return inheritedElements;

	} // inherit

	/**
	 * operation inheritableMembers <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.NamedElementList inheritableMembers(
			fUML.Syntax.Classes.Kernel.Classifier c) {
		NamedElementList inheritable = new NamedElementList();

		for (int i = 0; i < this.member.size(); i++) {
			NamedElement m = this.member.getValue(i);
			if (c.hasVisibilityOf(m)) {
				inheritable.addValue(m);
			}
		}

		return inheritable;
	} // inheritableMembers

	/**
	 * operation hasVisibilityOf <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean hasVisibilityOf(fUML.Syntax.Classes.Kernel.NamedElement n) {
		for (int i = 0; i < this.inheritedMember.size(); i++) {
			if (this.inheritedMember.getValue(i) == n) {
				return n.visibility != VisibilityKind.private_;
			}
		}

		return true;
	} // hasVisibilityOf

	/**
	 * operation setIsFinalSpecialization <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsFinalSpecialization(boolean isFinalSpecialization) {
		this.isFinalSpecialization = isFinalSpecialization;
	} // setIsFinalSpecialization

} // Classifier
