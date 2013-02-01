
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

package fUML.Syntax.Actions.BasicActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Actions::BasicActions::Action</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Action#addInput <em>addInput</em>}</li>
 * <li>{@link Action#addOutput <em>addOutput</em>}</li>
 * <li>{@link Action#setIsLocallyReentrant <em>setIsLocallyReentrant</em>}</li>
 * <li>{@link Action#output <em>output</em>}</li>
 * <li>{@link Action#context <em>context</em>}</li>
 * <li>{@link Action#input <em>input</em>}</li>
 * <li>{@link Action#isLocallyReentrant <em>isLocallyReentrant</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Action extends
		fUML.Syntax.Activities.CompleteStructuredActivities.ExecutableNode {

	public fUML.Syntax.Actions.BasicActions.OutputPinList output = new fUML.Syntax.Actions.BasicActions.OutputPinList();
	public fUML.Syntax.Classes.Kernel.Classifier context = null;
	public fUML.Syntax.Actions.BasicActions.InputPinList input = new fUML.Syntax.Actions.BasicActions.InputPinList();
	public boolean isLocallyReentrant = false;

	/**
	 * operation addInput <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addInput(fUML.Syntax.Actions.BasicActions.InputPin input) {
		super.addOwnedElement(input);
		this.input.addValue(input);
	} // addInput

	/**
	 * operation addOutput <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addOutput(fUML.Syntax.Actions.BasicActions.OutputPin output) {
		super.addOwnedElement(output);
		this.output.addValue(output);
	} // addOutput

	/**
	 * operation setIsLocallyReentrant <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void setIsLocallyReentrant(boolean isLocallyReentrant) {
		this.isLocallyReentrant = isLocallyReentrant;
	} // setIsLocallyReentrant

} // Action
