
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
 * <em><b>fUML::Syntax::Classes::Kernel::InstanceSpecification</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link InstanceSpecification#addClassifier <em>addClassifier</em>}</li>
 * <li>{@link InstanceSpecification#addSlot <em>addSlot</em>}</li>
 * <li>{@link InstanceSpecification#classifier <em>classifier</em>}</li>
 * <li>{@link InstanceSpecification#slot <em>slot</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class InstanceSpecification extends
		fUML.Syntax.Classes.Kernel.NamedElement {

	public fUML.Syntax.Classes.Kernel.ClassifierList classifier = new fUML.Syntax.Classes.Kernel.ClassifierList();
	public fUML.Syntax.Classes.Kernel.SlotList slot = new fUML.Syntax.Classes.Kernel.SlotList();

	/**
	 * operation addClassifier <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addClassifier(fUML.Syntax.Classes.Kernel.Classifier classifier) {
		this.classifier.addValue(classifier);
	} // addClassifier

	/**
	 * operation addSlot <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addSlot(fUML.Syntax.Classes.Kernel.Slot slot) {
		this.addOwnedElement(slot);
		this.slot.addValue(slot);
		slot.owningInstance = this;
	} // addSlot

} // InstanceSpecification
