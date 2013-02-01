
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

package fUML.Library;

import fUML.Debug;
import UMLPrimitiveTypes.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Library::PrimitiveTypes</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link PrimitiveTypes#PrimitiveTypes <em>PrimitiveTypes</em>}</li>
 * <li>{@link PrimitiveTypes#createBuiltInType <em>createBuiltInType</em>}</li>
 * <li>{@link PrimitiveTypes#Boolean <em>Boolean</em>}</li>
 * <li>{@link PrimitiveTypes#String <em>String</em>}</li>
 * <li>{@link PrimitiveTypes#Integer <em>Integer</em>}</li>
 * <li>{@link PrimitiveTypes#UnlimitedNatural <em>UnlimitedNatural</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class PrimitiveTypes extends org.modeldriven.fuml.FumlObject {

	public fUML.Syntax.Classes.Kernel.PrimitiveType Boolean = null;
	public fUML.Syntax.Classes.Kernel.PrimitiveType String = null;
	public fUML.Syntax.Classes.Kernel.PrimitiveType Integer = null;
	public fUML.Syntax.Classes.Kernel.PrimitiveType UnlimitedNatural = null;

	/**
	 * operation PrimitiveTypes <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PrimitiveTypes(fUML.Semantics.Loci.LociL1.ExecutionFactory factory) {
		this.Boolean = this.createBuiltInType("Boolean", factory);
		this.String = this.createBuiltInType("String", factory);
		this.Integer = this.createBuiltInType("Integer", factory);
		this.UnlimitedNatural = this.createBuiltInType("UnlimitedNatural",
				factory);
	} // PrimitiveTypes

	/**
	 * operation createBuiltInType <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.PrimitiveType createBuiltInType(
			String name, fUML.Semantics.Loci.LociL1.ExecutionFactory factory) {
		fUML.Syntax.Classes.Kernel.PrimitiveType type = new fUML.Syntax.Classes.Kernel.PrimitiveType();
		type.name = name;
		factory.addBuiltInType(type);

		return type;
	} // createBuiltInType

} // PrimitiveTypes
