
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
 * <em><b>fUML::Semantics::Loci::LociL1::FirstChoiceStrategy</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link FirstChoiceStrategy#choose <em>choose</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class FirstChoiceStrategy extends
		fUML.Semantics.Loci.LociL1.ChoiceStrategy {

	/**
	 * operation choose <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int choose(int size) {
		// Always choose one.

		return 1;
	} // choose

} // FirstChoiceStrategy
