
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
 * <em><b>fUML::Syntax::Classes::Kernel::StructuralFeature</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link StructuralFeature#setIsReadOnly <em>setIsReadOnly</em>}</li>
 * <li>{@link StructuralFeature#setIsOrdered <em>setIsOrdered</em>}</li>
 * <li>{@link StructuralFeature#setIsUnique <em>setIsUnique</em>}</li>
 * <li>{@link StructuralFeature#setUpperValue <em>setUpperValue</em>}</li>
 * <li>{@link StructuralFeature#setLowerValue <em>setLowerValue</em>}</li>
 * <li>{@link StructuralFeature#setUpper <em>setUpper</em>}</li>
 * <li>{@link StructuralFeature#setLower <em>setLower</em>}</li>
 * <li>{@link StructuralFeature#setType <em>setType</em>}</li>
 * <li>{@link StructuralFeature#isReadOnly <em>isReadOnly</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class StructuralFeature extends
		fUML.Syntax.Classes.Kernel.Feature {

	public fUML.Syntax.Classes.Kernel.TypedElement typedElement = new fUML.Syntax.Classes.Kernel.TypedElement();
	public fUML.Syntax.Classes.Kernel.MultiplicityElement multiplicityElement = new fUML.Syntax.Classes.Kernel.MultiplicityElement();
	public boolean isReadOnly = false;

	/**
	 * operation setIsReadOnly <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	} // setIsReadOnly

	/**
	 * operation setIsOrdered <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsOrdered(boolean isOrdered) {
		this.multiplicityElement.setIsOrdered(isOrdered);
	} // setIsOrdered

	/**
	 * operation setIsUnique <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsUnique(boolean isUnique) {
		this.multiplicityElement.setIsUnique(isUnique);
	} // setIsUnique

	/**
	 * operation setUpperValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpperValue(
			fUML.Syntax.Classes.Kernel.ValueSpecification upperValue) {
		this.multiplicityElement.setUpperValue(upperValue);
	} // setUpperValue

	/**
	 * operation setLowerValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLowerValue(
			fUML.Syntax.Classes.Kernel.ValueSpecification lowerValue) {
		this.multiplicityElement.setLowerValue(lowerValue);
	} // setLowerValue

	/**
	 * operation setUpper <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpper(int upper) {
		// Note: This is a convenience operation that may be used _instead_ of
		// setUpperValue, not in addition to it.

		this.multiplicityElement.setUpper(upper);
	} // setUpper

	/**
	 * operation setLower <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLower(int lower) {
		// Note: This is a convenience operation that may be used _instead_ of
		// setLowerValue, not in addition to it.

		this.multiplicityElement.setLower(lower);

	} // setLower

	/**
	 * operation setType <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(fUML.Syntax.Classes.Kernel.Type type) {
		this.typedElement.setType(type);
	} // setType

} // StructuralFeature
