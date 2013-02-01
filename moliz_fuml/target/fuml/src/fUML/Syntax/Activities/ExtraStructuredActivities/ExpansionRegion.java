
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

package fUML.Syntax.Activities.ExtraStructuredActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Activities::ExtraStructuredActivities::ExpansionRegion</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ExpansionRegion#setMode <em>setMode</em>}</li>
 * <li>{@link ExpansionRegion#addInputElement <em>addInputElement</em>}</li>
 * <li>{@link ExpansionRegion#addOutputElement <em>addOutputElement</em>}</li>
 * <li>{@link ExpansionRegion#mode <em>mode</em>}</li>
 * <li>{@link ExpansionRegion#outputElement <em>outputElement</em>}</li>
 * <li>{@link ExpansionRegion#inputElement <em>inputElement</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ExpansionRegion
		extends
		fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode {

	public fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind mode = fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind.iterative;
	public fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNodeList outputElement = new fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNodeList();
	public fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNodeList inputElement = new fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNodeList();

	/**
	 * operation setMode <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMode(
			fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind mode) {
		this.mode = mode;
	} // setMode

	/**
	 * operation addInputElement <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addInputElement(
			fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode inputElement) {
		this.inputElement.addValue(inputElement);
		inputElement.regionAsInput = this;
	} // addInputElement

	/**
	 * operation addOutputElement <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addOutputElement(
			fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode outputElement) {
		this.outputElement.addValue(outputElement);
		outputElement.regionAsOutput = this;
	} // addOutputElement

} // ExpansionRegion
