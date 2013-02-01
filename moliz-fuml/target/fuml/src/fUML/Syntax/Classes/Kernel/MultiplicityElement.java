
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
 * <em><b>fUML::Syntax::Classes::Kernel::MultiplicityElement</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link MultiplicityElement#MultiplicityElement <em>MultiplicityElement
 * </em>}</li>
 * <li>{@link MultiplicityElement#setIsOrdered <em>setIsOrdered</em>}</li>
 * <li>{@link MultiplicityElement#setIsUnique <em>setIsUnique</em>}</li>
 * <li>{@link MultiplicityElement#setUpperValue <em>setUpperValue</em>}</li>
 * <li>{@link MultiplicityElement#setLowerValue <em>setLowerValue</em>}</li>
 * <li>{@link MultiplicityElement#setUpper <em>setUpper</em>}</li>
 * <li>{@link MultiplicityElement#setLower <em>setLower</em>}</li>
 * <li>{@link MultiplicityElement#isOrdered <em>isOrdered</em>}</li>
 * <li>{@link MultiplicityElement#isUnique <em>isUnique</em>}</li>
 * <li>{@link MultiplicityElement#upper <em>upper</em>}</li>
 * <li>{@link MultiplicityElement#lower <em>lower</em>}</li>
 * <li>{@link MultiplicityElement#upperValue <em>upperValue</em>}</li>
 * <li>{@link MultiplicityElement#lowerValue <em>lowerValue</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class MultiplicityElement extends fUML.Syntax.Classes.Kernel.Element {

	public boolean isOrdered = false;
	public boolean isUnique = true;
	public UnlimitedNatural upper = new UnlimitedNatural(1);
	public int lower = 1;
	public fUML.Syntax.Classes.Kernel.ValueSpecification upperValue = null;
	public fUML.Syntax.Classes.Kernel.ValueSpecification lowerValue = null;

	/**
	 * operation MultiplicityElement <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public MultiplicityElement() {
		// This constructor sets upperValue and lowerValue consistently with the
		// initializers for upper and lower.

		this.setUpper(1);
		this.setLower(1);
	} // MultiplicityElement

	/**
	 * operation setIsOrdered <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	} // setIsOrdered

	/**
	 * operation setIsUnique <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsUnique(boolean isUnique) {
		this.isUnique = isUnique;
	} // setIsUnique

	/**
	 * operation setUpperValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpperValue(
			fUML.Syntax.Classes.Kernel.ValueSpecification upperValue) {
		this.upperValue = upperValue;

		if (upperValue instanceof LiteralUnlimitedNatural) {
			this.upper = ((LiteralUnlimitedNatural) upperValue).value;
		}
	} // setUpperValue

	/**
	 * operation setLowerValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLowerValue(
			fUML.Syntax.Classes.Kernel.ValueSpecification lowerValue) {
		this.lowerValue = lowerValue;

		if (lowerValue instanceof LiteralInteger) {
			this.lower = ((LiteralInteger) lowerValue).value;
		}
	} // setLowerValue

	/**
	 * operation setUpper <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpper(int upper) {
		// Note: This is a convenience operation that may be used _instead_ of
		// setUpperValue, not in addition to it.

		this.upper = new UnlimitedNatural(upper);
		this.upperValue = new LiteralUnlimitedNatural();
		((LiteralUnlimitedNatural) this.upperValue).setValue(this.upper);
	} // setUpper

	/**
	 * operation setLower <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLower(int lower) {
		// Note: This is a convenience operation that may be used _instead_ of
		// setLowerValue, not in addition to it.

		this.lower = lower;
		this.lowerValue = new LiteralInteger();
		((LiteralInteger) this.lowerValue).setValue(this.lower);
	} // setLower

} // MultiplicityElement
