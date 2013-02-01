
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

package fUML.Semantics.Loci.LociL1;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Loci::LociL1::SemanticVisitor</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link SemanticVisitor#_endIsolation <em>_endIsolation</em>}</li>
 * <li>{@link SemanticVisitor#_beginIsolation <em>_beginIsolation</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class SemanticVisitor extends org.modeldriven.fuml.FumlObject {

	/**
	 * operation _endIsolation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void _endIsolation() {
		Debug.println("[_endIsolation] End isolation.");
	} // _endIsolation

	/**
	 * operation _beginIsolation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void _beginIsolation() {
		Debug.println("[_beginIsolation] Begin isolation.");
	} // _beginIsolation

} // SemanticVisitor
