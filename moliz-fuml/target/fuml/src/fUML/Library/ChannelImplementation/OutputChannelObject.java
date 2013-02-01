
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

package fUML.Library.ChannelImplementation;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Semantics.Classes.Kernel.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Library::ChannelImplementation::OutputChannelObject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link OutputChannelObject#write <em>write</em>}</li>
 * <li>{@link OutputChannelObject#isFull <em>isFull</em>}</li>
 * <li>{@link OutputChannelObject#execute <em>execute</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class OutputChannelObject extends
		fUML.Library.ChannelImplementation.ChannelObject {

	/**
	 * operation write <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract void write(fUML.Semantics.Classes.Kernel.Value value);

	/**
	 * operation isFull <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract boolean isFull();

	/**
	 * operation execute <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void execute(
			fUML.Library.LibraryClassImplementation.OperationExecution execution) {
		String name = execution.getOperationName();

		if (name.equals("write")) {
			this.write(execution.getParameterValue("value").values.getValue(0));
		} else if (name.equals("isFull")) {
			BooleanValue isFullValue = new BooleanValue();
			isFullValue.value = this.isFull();
			execution.setParameterValue("result", isFullValue);
		} else {
			super.execute(execution);
		}
	} // execute

} // OutputChannelObject
