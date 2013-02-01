
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
 * <em><b>fUML::Syntax::Classes::Kernel::Property</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Property#setIsReadOnly <em>setIsReadOnly</em>}</li>
 * <li>{@link Property#setAggregation <em>setAggregation</em>}</li>
 * <li>{@link Property#isDerived <em>isDerived</em>}</li>
 * <li>{@link Property#isReadOnly <em>isReadOnly</em>}</li>
 * <li>{@link Property#isDerivedUnion <em>isDerivedUnion</em>}</li>
 * <li>{@link Property#aggregation <em>aggregation</em>}</li>
 * <li>{@link Property#isComposite <em>isComposite</em>}</li>
 * <li>{@link Property#owningAssociation <em>owningAssociation</em>}</li>
 * <li>{@link Property#datatype <em>datatype</em>}</li>
 * <li>{@link Property#association <em>association</em>}</li>
 * <li>{@link Property#class <em>class</em>}</li>
 * <li>{@link Property#opposite <em>opposite</em>}</li>
 * <li>{@link Property#defaultValue <em>defaultValue</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Property extends fUML.Syntax.Classes.Kernel.StructuralFeature {

	public boolean isDerived = false;
	public boolean isReadOnly = false;
	public boolean isDerivedUnion = false;
	public fUML.Syntax.Classes.Kernel.AggregationKind aggregation = fUML.Syntax.Classes.Kernel.AggregationKind.none;
	public boolean isComposite = false;
	public fUML.Syntax.Classes.Kernel.Association owningAssociation = null;
	public fUML.Syntax.Classes.Kernel.DataType datatype = null;
	public fUML.Syntax.Classes.Kernel.Association association = null;
	public fUML.Syntax.Classes.Kernel.Class_ class_ = null;
	public fUML.Syntax.Classes.Kernel.Property opposite = null;
	public fUML.Syntax.Classes.Kernel.ValueSpecification defaultValue = null;

	/**
	 * operation setIsReadOnly <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	} // setIsReadOnly

	/**
	 * operation setAggregation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAggregation(
			fUML.Syntax.Classes.Kernel.AggregationKind aggregation) {
		this.aggregation = aggregation;
		this.isComposite = (aggregation == AggregationKind.composite);
	} // setAggregation

} // Property
