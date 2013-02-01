
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

package fUML.Semantics.Actions.IntermediateActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Actions.BasicActions.*;
import fUML.Syntax.Actions.IntermediateActions.*;

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.Activities.IntermediateActivities.*;
import fUML.Semantics.Actions.BasicActions.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Actions::IntermediateActions::StructuralFeatureActionActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link StructuralFeatureActionActivation#getAssociation <em>
 * getAssociation</em>}</li>
 * <li>{@link StructuralFeatureActionActivation#getMatchingLinks <em>
 * getMatchingLinks</em>}</li>
 * <li>{@link StructuralFeatureActionActivation#getOppositeEnd <em>
 * getOppositeEnd</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public abstract class StructuralFeatureActionActivation extends
		fUML.Semantics.Actions.BasicActions.ActionActivation {

	/**
	 * operation getAssociation <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.Association getAssociation(
			fUML.Syntax.Classes.Kernel.StructuralFeature feature) {
		// If the structural feature for the action of this activation is an
		// association end,
		// then get the associated association.

		Association association = null;
		if (feature instanceof Property) {
			association = ((Property) feature).association;
		}

		return association;
	} // getAssociation

	/**
	 * operation getMatchingLinks <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.LinkList getMatchingLinks(
			fUML.Syntax.Classes.Kernel.Association association,
			fUML.Syntax.Classes.Kernel.StructuralFeature end,
			fUML.Semantics.Classes.Kernel.Value oppositeValue) {
		// Get the links of the given binary association whose end opposite
		// to the given end has the given value

		Property oppositeEnd = this.getOppositeEnd(association, end);

		ExtensionalValueList extent = this.getExecutionLocus().getExtent(
				association);

		LinkList links = new LinkList();
		for (int i = 0; i < extent.size(); i++) {
			ExtensionalValue link = extent.getValue(i);
			if (link.getFeatureValue(oppositeEnd).values.getValue(0).equals(
					oppositeValue)) {
				if (!end.multiplicityElement.isOrdered | links.size() == 0) {
					links.addValue((Link) link);
				} else {
					int n = link.getFeatureValue(end).position;
					boolean continueSearching = true;
					int j = 0;
					while (continueSearching & j < links.size()) {
						j = j + 1;
						continueSearching = links.getValue(j - 1)
								.getFeatureValue(end).position < n;
					}
					links.addValue(j - 1, (Link) link);
				}
			}
		}

		return links;
	} // getMatchingLinks

	/**
	 * operation getOppositeEnd <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.Property getOppositeEnd(
			fUML.Syntax.Classes.Kernel.Association association,
			fUML.Syntax.Classes.Kernel.StructuralFeature end) {
		// Get the end of a binary association opposite to the given end.

		Property oppositeEnd = association.memberEnd.getValue(0);
		if (oppositeEnd == end) {
			oppositeEnd = association.memberEnd.getValue(1);
		}

		return oppositeEnd;
	} // getOppositeEnd

} // StructuralFeatureActionActivation
