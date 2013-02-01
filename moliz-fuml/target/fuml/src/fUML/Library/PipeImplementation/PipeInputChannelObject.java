
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

package fUML.Library.PipeImplementation;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Library::PipeImplementation::PipeInputChannelObject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link PipeInputChannelObject#hasMore <em>hasMore</em>}</li>
 * <li>{@link PipeInputChannelObject#read <em>read</em>}</li>
 * <li>{@link PipeInputChannelObject#receive <em>receive</em>}</li>
 * <li>{@link PipeInputChannelObject#open <em>open</em>}</li>
 * <li>{@link PipeInputChannelObject#close <em>close</em>}</li>
 * <li>{@link PipeInputChannelObject#isOpen <em>isOpen</em>}</li>
 * <li>{@link PipeInputChannelObject#getName <em>getName</em>}</li>
 * <li>{@link PipeInputChannelObject#PipeInputChannelObject <em>
 * PipeInputChannelObject</em>}</li>
 * <li>{@link PipeInputChannelObject#values <em>values</em>}</li>
 * <li>{@link PipeInputChannelObject#opened <em>opened</em>}</li>
 * <li>{@link PipeInputChannelObject#name <em>name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class PipeInputChannelObject extends
		fUML.Library.ChannelImplementation.InputChannelObject {

	private fUML.Semantics.Classes.Kernel.ValueList values = new fUML.Semantics.Classes.Kernel.ValueList();
	private boolean opened = false;
	private String name = "";

	/**
	 * operation hasMore <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean hasMore() {
		return this.isOpen() && !(this.values.size() == 0);
	} // hasMore

	/**
	 * operation read <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value read() {
		if (this.hasMore()) {
			fUML.Semantics.Classes.Kernel.Value value = this.values.getValue(0);
			this.values.remove(0);
			return value;
		} else {
			return null;
		}
	} // read

	/**
	 * operation receive <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void receive(fUML.Semantics.Classes.Kernel.Value value) {
		this.values.addValue(value);

	} // receive

	/**
	 * operation open <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void open() {
		opened = true;
	} // open

	/**
	 * operation close <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void close() {
		opened = false;
	} // close

	/**
	 * operation isOpen <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isOpen() {
		return opened;
	} // isOpen

	/**
	 * operation getName <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		return name;
	} // getName

	/**
	 * operation PipeInputChannelObject <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PipeInputChannelObject(String name) {
		this.name = name;
		this.opened = true;
	} // PipeInputChannelObject

} // PipeInputChannelObject
