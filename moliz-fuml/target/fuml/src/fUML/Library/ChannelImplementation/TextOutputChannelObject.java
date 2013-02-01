
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
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Library::ChannelImplementation::TextOutputChannelObject</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link TextOutputChannelObject#writeString <em>writeString</em>}</li>
 * <li>{@link TextOutputChannelObject#writeNewLine <em>writeNewLine</em>}</li>
 * <li>{@link TextOutputChannelObject#writeLine <em>writeLine</em>}</li>
 * <li>{@link TextOutputChannelObject#writeInteger <em>writeInteger</em>}</li>
 * <li>{@link TextOutputChannelObject#writeBoolean <em>writeBoolean</em>}</li>
 * <li>{@link TextOutputChannelObject#writeUnlimitedNatural <em>
 * writeUnlimitedNatural</em>}</li>
 * <li>{@link TextOutputChannelObject#execute <em>execute</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class TextOutputChannelObject extends
		fUML.Library.ChannelImplementation.OutputChannelObject {

	/**
	 * operation writeString <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract void writeString(String value);

	/**
	 * operation writeNewLine <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract void writeNewLine();

	/**
	 * operation writeLine <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void writeLine(String value) {
		this.writeString(value);
		this.writeNewLine();
	} // writeLine

	/**
	 * operation writeInteger <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void writeInteger(int value) {
		this.writeString(Integer.toString(value));
	} // writeInteger

	/**
	 * operation writeBoolean <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void writeBoolean(boolean value) {
		this.writeString(Boolean.toString(value));
	} // writeBoolean

	/**
	 * operation writeUnlimitedNatural <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void writeUnlimitedNatural(UnlimitedNatural value) {
		int naturalValue = value.naturalValue;

		if (naturalValue < 0) {
			this.writeString("*");
		} else {
			this.writeString(Integer.toString(naturalValue));
		}
	} // writeUnlimitedNatural

	/**
	 * operation execute <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void execute(
			fUML.Library.LibraryClassImplementation.OperationExecution execution) {
		String name = execution.getOperationName();
		// Debug.println("[execute] operation = " + name);

		ParameterValue parameterValue = execution.getParameterValue("value");
		// if ((parameterValue != null) && (parameterValue.values.size() > 0)) {
		// Debug.println("[execute] argument = " +
		// parameterValue.values.getValue(0));
		// }

		if (name.equals("writeNewLine")) {
			this.writeNewLine();
		} else if (name.equals("writeString")) {
			this
					.writeString(((StringValue) (parameterValue.values
							.getValue(0))).value);
		} else if (name.equals("writeLine")) {
			this
					.writeLine(((StringValue) (parameterValue.values
							.getValue(0))).value);
		} else if (name.equals("writeInteger")) {
			this.writeInteger(((IntegerValue) (parameterValue.values
					.getValue(0))).value);
		} else if (name.equals("writeBoolean")) {
			this.writeBoolean(((BooleanValue) (parameterValue.values
					.getValue(0))).value);
		} else if (name.equals("writeUnlimitedNatural")) {
			this
					.writeUnlimitedNatural(((UnlimitedNaturalValue) (parameterValue.values
							.getValue(0))).value);
		} else {
			super.execute(execution);
		}
	} // execute

} // TextOutputChannelObject
