
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
import fUML.Semantics.Loci.LociL1.Locus;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Classes::Kernel::Link</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Link#destroy <em>destroy</em>}</li>
 * <li>{@link Link#copy <em>copy</em>}</li>
 * <li>{@link Link#new_ <em>new_</em>}</li>
 * <li>{@link Link#getTypes <em>getTypes</em>}</li>
 * <li>{@link Link#setFeatureValue <em>setFeatureValue</em>}</li>
 * <li>{@link Link#type <em>type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Link extends fUML.Semantics.Classes.Kernel.ExtensionalValue {

	public fUML.Syntax.Classes.Kernel.Association type = null;

	public boolean isMatchingLink(ExtensionalValue link, Property end) {
		// Test whether the given link matches the values of this link on all
		// ends other than the given end.
		
		PropertyList ends = this.type.memberEnd;
		
		boolean matches = true;
		int i = 1;
		while (matches & i <= ends.size()) {
			Property otherEnd = ends.getValue(i - 1);
			if (otherEnd != end &
					!this.getFeatureValue(otherEnd).values.getValue(0).equals(
					link.getFeatureValue(otherEnd).values.getValue(0))) {
				matches = false;
			}
			i = i + 1;
		}
		
		return matches;
	}
	
	public FeatureValueList getOtherFeatureValues(ExtensionalValueList extent, Property end) {
		// Return all feature values for the given end of links in the given
		// extent whose other ends match this link.
		
		FeatureValueList featureValues = new FeatureValueList();
		for (int i = 0; i < extent.size(); i++) {
			ExtensionalValue link = extent.getValue(i);
			if (link != this) {
				if (isMatchingLink(link, end)) {
					featureValues.addValue(link.getFeatureValue(end));
				}
			}
		}
		return featureValues;
	}
	
	public void addTo(Locus locus) {
		// Add this link to the extent of its association at the given locus.
		// Shift the positions of ends of other links, as appropriate, for ends
		// that are ordered.
		
		Debug.println("[addTo] link = " + this.objectId());

		PropertyList ends = this.type.memberEnd;
		ExtensionalValueList extent = locus.getExtent(this.type);
		
		for (int i = 0; i < ends.size(); i++) {
			Property end = ends.getValue(i);
			if (end.multiplicityElement.isOrdered) {
				FeatureValue featureValue = this.getFeatureValue(end);
				FeatureValueList otherFeatureValues = 
					this.getOtherFeatureValues(extent, end);
				int n = otherFeatureValues.size();
				if (featureValue.position < 0 | featureValue.position > n) {
					featureValue.position = n + 1;
				} else {
					if (featureValue.position == 0) {
						featureValue.position = 1;
					}
					for (int j = 0; j < otherFeatureValues.size(); j++) {
						FeatureValue otherFeatureValue = featureValues.getValue(j);
						if (featureValue.position <= otherFeatureValue.position) {
							otherFeatureValue.position = otherFeatureValue.position + 1;
						}
					}
				}
			}
		}
		
		locus.add(this);
	}

	/**
	 * operation destroy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void destroy() {
		// Remove the type of this link and destroy it.
		// Shift the positions of the feature values of any remaining links in
		// the extent of the same association, for ends that are ordered.

		Debug.println("[destroy] link = " + this.objectId());

		PropertyList ends = this.type.memberEnd;
		ExtensionalValueList extent = this.locus.getExtent(this.type);
		
		for (int i = 0; i < ends.size(); i++) {
			Property end = ends.getValue(i);
			FeatureValue featureValue = this.getFeatureValue(end);
			FeatureValueList otherFeatureValues = 
				this.getOtherFeatureValues(extent, end);
			for (int j = 0; j < otherFeatureValues.size(); j++) {
				FeatureValue otherFeatureValue = featureValues.getValue(j);
				if (featureValue.position < otherFeatureValue.position) {
					otherFeatureValue.position = otherFeatureValue.position - 1;
				}
			}
		}

		this.type = null;
		super.destroy();
	} // destroy

	/**
	 * operation copy <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value copy() {
		// Create a new link with the same type, locus and feature values as
		// this link.

		Link newValue = (Link) (super.copy());

		newValue.type = this.type;

		return newValue;
	} // copy

	/**
	 * operation new_ <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected fUML.Semantics.Classes.Kernel.Value new_() {
		// Create a new link with no type or properies.

		return new Link();
	} // new_

	/**
	 * operation getTypes <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.ClassifierList getTypes() {
		// Return the single type of this link (if any).

		ClassifierList types = null;

		if (this.type == null) {
			types = new ClassifierList();
		} else {
			types = new ClassifierList();
			types.addValue(this.type);
		}

		return types;

	} // getTypes
	
	/**
	 * operation setFeatureValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	/*
	public void setFeatureValue(
			fUML.Syntax.Classes.Kernel.StructuralFeature feature,
			fUML.Semantics.Classes.Kernel.ValueList values, int position) {
		// If a position is given, before setting the given feature value,
		// adjust the position values of the ends of other links for this same
		// feature.

		if (position > 0) {
			ExtensionalValueList extent = this.locus.getExtent(this.type);
			for (int i = 0; i < extent.size(); i++) {
				ExtensionalValue value = extent.getValue(i);
				FeatureValue featureValue = value.getFeatureValue(feature);
				if (featureValue.position >= position) {
					featureValue.position = featureValue.position + 1;
				}
			}
		}

		super.setFeatureValue(feature, values, position);
	} // setFeatureValue
	*/

} // Link
