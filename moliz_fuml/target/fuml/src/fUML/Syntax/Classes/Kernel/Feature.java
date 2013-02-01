
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
 * <em><b>fUML::Syntax::Classes::Kernel::Feature</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Feature#isStatic <em>isStatic</em>}</li>
 * <li>{@link Feature#featuringClassifier <em>featuringClassifier</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class Feature extends
		fUML.Syntax.Classes.Kernel.RedefinableElement {

	public boolean isStatic = false;
	public fUML.Syntax.Classes.Kernel.ClassifierList featuringClassifier = new fUML.Syntax.Classes.Kernel.ClassifierList();

} // Feature
