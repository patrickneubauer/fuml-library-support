
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
 * <em><b>fUML::Syntax::Classes::Kernel::Slot</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Slot#setDefiningFeature <em>setDefiningFeature</em>}</li>
 * <li>{@link Slot#addValue <em>addValue</em>}</li>
 * <li>{@link Slot#owningInstance <em>owningInstance</em>}</li>
 * <li>{@link Slot#definingFeature <em>definingFeature</em>}</li>
 * <li>{@link Slot#value <em>value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Slot extends fUML.Syntax.Classes.Kernel.Element {

	public fUML.Syntax.Classes.Kernel.InstanceSpecification owningInstance = null;
	public fUML.Syntax.Classes.Kernel.StructuralFeature definingFeature = null;
	public fUML.Syntax.Classes.Kernel.ValueSpecificationList value = new fUML.Syntax.Classes.Kernel.ValueSpecificationList();

	/**
	 * operation setDefiningFeature <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setDefiningFeature(
			fUML.Syntax.Classes.Kernel.StructuralFeature definingFeature) {
		this.definingFeature = definingFeature;
	} // setDefiningFeature

	/**
	 * operation addValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addValue(fUML.Syntax.Classes.Kernel.ValueSpecification value) {
		this.addOwnedElement(value);
		this.value.addValue(value);
	} // addValue

} // Slot
