
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

package fUML.Semantics.Classes.Kernel;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;

import fUML.Semantics.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Classes::Kernel::Evaluation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Evaluation#evaluate <em>evaluate</em>}</li>
 * <li>{@link Evaluation#specification <em>specification</em>}</li>
 * <li>{@link Evaluation#locus <em>locus</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Evaluation extends
		fUML.Semantics.Loci.LociL1.SemanticVisitor {

	public fUML.Syntax.Classes.Kernel.ValueSpecification specification = null;
	public fUML.Semantics.Loci.LociL1.Locus locus = null;

	/**
	 * operation evaluate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public abstract fUML.Semantics.Classes.Kernel.Value evaluate();
} // Evaluation
