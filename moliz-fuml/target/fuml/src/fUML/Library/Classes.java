
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

package fUML.Library;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.Classes.Kernel.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Library::Classes</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Classes#createClass <em>createClass</em>}</li>
 * <li>{@link Classes#addAttribute <em>addAttribute</em>}</li>
 * <li>{@link Classes#addOperation <em>addOperation</em>}</li>
 * <li>{@link Classes#addParameter <em>addParameter</em>}</li>
 * <li>{@link Classes#setMultiplicity <em>setMultiplicity</em>}</li>
 * <li>{@link Classes#addGeneralization <em>addGeneralization</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Classes extends org.modeldriven.fuml.FumlObject {

	/**
	 * operation createClass <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Syntax.Classes.Kernel.Class_ createClass(String name,
			boolean isAbstract) {
		Class_ newClass = new Class_();
		newClass.name = name;
		newClass.isAbstract = isAbstract;

		return newClass;
	} // createClass

	/**
	 * operation addAttribute <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addAttribute(fUML.Syntax.Classes.Kernel.Class_ owner,
			String name, fUML.Syntax.Classes.Kernel.Type type,
			fUML.Syntax.Classes.Kernel.VisibilityKind visibility,
			boolean isComposite) {

		Property attribute = new Property();
		attribute.name = name;
		attribute.typedElement = new TypedElement();
		attribute.typedElement.type = type;
		attribute.multiplicityElement = new MultiplicityElement();
		this.setMultiplicity(attribute.multiplicityElement, 1, 1);

		if (visibility == null) {
			attribute.visibility = VisibilityKind.public_;
		} else {
			attribute.visibility = visibility;
		}

		attribute.isComposite = isComposite;
		if (isComposite) {
			attribute.aggregation = AggregationKind.composite;
		} else {
			attribute.aggregation = AggregationKind.none;
		}

		attribute.featuringClassifier.addValue(owner);
		attribute.namespace = owner;

		owner.ownedAttribute.addValue(attribute);
		owner.attribute.addValue(attribute);
		owner.feature.addValue(attribute);
		owner.ownedMember.addValue(attribute);
		owner.member.addValue(attribute);

	} // addAttribute

	/**
	 * operation addOperation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Syntax.Classes.Kernel.Operation addOperation(
			fUML.Syntax.Classes.Kernel.Class_ owner, String name,
			fUML.Syntax.Classes.Kernel.VisibilityKind visibility) {
		Operation operation = new Operation();
		operation.name = name;
		operation.featuringClassifier.addValue(owner);
		operation.namespace = owner;

		if (visibility == null) {
			operation.visibility = VisibilityKind.public_;
		} else {
			operation.visibility = visibility;
		}

		owner.ownedOperation.addValue(operation);
		owner.feature.addValue(operation);
		owner.ownedMember.addValue(operation);
		owner.member.addValue(operation);

		return operation;
	} // addOperation

	/**
	 * operation addParameter <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addParameter(fUML.Syntax.Classes.Kernel.Operation operation,
			String name,
			fUML.Syntax.Classes.Kernel.ParameterDirectionKind direction,
			fUML.Syntax.Classes.Kernel.Type type) {
		Parameter parameter = new Parameter();
		parameter.name = name;
		parameter.type = type;
		parameter.direction = direction;
		parameter.multiplicityElement = new MultiplicityElement();
		this.setMultiplicity(parameter.multiplicityElement, 1, 1);

		operation.ownedParameter.addValue(parameter);

		if (direction.equals(ParameterDirectionKind.return_)) {
			operation.type = type;
		}
	} // addParameter

	/**
	 * operation setMultiplicity <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setMultiplicity(
			fUML.Syntax.Classes.Kernel.MultiplicityElement element, int lower,
			int upper) {
		UnlimitedNatural unlimitedNatural = new UnlimitedNatural();
		unlimitedNatural.naturalValue = upper;

		element.lower = lower;
		element.upper = unlimitedNatural;
		element.isOrdered = false;
		element.isUnique = true;
	} // setMultiplicity

	/**
	 * operation addGeneralization <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addGeneralization(
			fUML.Syntax.Classes.Kernel.Class_ specific,
			fUML.Syntax.Classes.Kernel.Class_ general) {
		Generalization generalization = new Generalization();
		generalization.specific = specific;
		generalization.general = general;

		specific.generalization.addValue(generalization);
		specific.general.addValue(general);
		specific.superClass.addValue(general);

		for (int i = 0; i < general.member.size(); i++) {
			NamedElement member = general.member.getValue(i);
			if (member.visibility != VisibilityKind.private_) {
				specific.member.addValue(member);
				specific.inheritedMember.addValue(member);
				if (member instanceof Feature) {
					specific.feature.addValue((Feature) member);
					((Feature) member).featuringClassifier.addValue(specific);
				}
			}
		}
	} // addGeneralization

} // Classes
