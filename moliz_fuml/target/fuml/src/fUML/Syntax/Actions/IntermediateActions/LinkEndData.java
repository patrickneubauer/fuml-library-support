
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

package fUML.Syntax.Actions.IntermediateActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Syntax::Actions::IntermediateActions::LinkEndData</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link LinkEndData#setEnd <em>setEnd</em>}</li>
 * <li>{@link LinkEndData#setValue <em>setValue</em>}</li>
 * <li>{@link LinkEndData#value <em>value</em>}</li>
 * <li>{@link LinkEndData#end <em>end</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class LinkEndData extends fUML.Syntax.Classes.Kernel.Element {

	public fUML.Syntax.Actions.BasicActions.InputPin value = null;
	public fUML.Syntax.Classes.Kernel.Property end = null;

	/**
	 * operation setEnd <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEnd(fUML.Syntax.Classes.Kernel.Property end) {
		this.end = end;
	} // setEnd

	/**
	 * operation setValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setValue(fUML.Syntax.Actions.BasicActions.InputPin value) {
		this.value = value;
	} // setValue

} // LinkEndData
