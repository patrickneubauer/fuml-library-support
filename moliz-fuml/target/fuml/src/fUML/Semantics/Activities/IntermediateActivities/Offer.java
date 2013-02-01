
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

package fUML.Semantics.Activities.IntermediateActivities;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Actions.BasicActions.*;

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.Actions.BasicActions.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Activities::IntermediateActivities::Offer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Offer#countOfferedValues <em>countOfferedValues</em>}</li>
 * <li>{@link Offer#getOfferedTokens <em>getOfferedTokens</em>}</li>
 * <li>{@link Offer#removeOfferedValues <em>removeOfferedValues</em>}</li>
 * <li>{@link Offer#removeWithdrawnTokens <em>removeWithdrawnTokens</em>}</li>
 * <li>{@link Offer#hasTokens <em>hasTokens</em>}</li>
 * <li>{@link Offer#offeredTokens <em>offeredTokens</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class Offer extends org.modeldriven.fuml.FumlObject {

	public fUML.Semantics.Activities.IntermediateActivities.TokenList offeredTokens = new fUML.Semantics.Activities.IntermediateActivities.TokenList();

	/**
	 * operation countOfferedValues <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public int countOfferedValues() {
		// Return the number of values being offered on object tokens.
		// Remove any tokens that have already been withdrawn and don't include
		// them in the count.

		this.removeWithdrawnTokens();

		int count = 0;
		for (int i = 0; i < this.offeredTokens.size(); i++) {
			if (this.offeredTokens.getValue(i).getValue() != null) {
				count = count + 1;
			}
		}

		return count;
	} // countOfferedValues

	/**
	 * operation getOfferedTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Activities.IntermediateActivities.TokenList getOfferedTokens() {
		// Get the offered tokens, removing any that have been withdrawn.

		this.removeWithdrawnTokens();

		TokenList tokens = new TokenList();
		TokenList offeredTokens = this.offeredTokens;
		for (int i = 0; i < this.offeredTokens.size(); i++) {
			Token offeredToken = offeredTokens.getValue(i);
			// Debug.println("[getOfferedTokens] token value = " +
			// offeredToken.getValue());
			tokens.addValue(offeredToken);
		}

		return tokens;

	} // getOfferedTokens

	/**
	 * operation removeOfferedValues <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void removeOfferedValues(int count) {
		// Remove the given number of non-null object tokens from those in this
		// offer.

		int n = count;
		int i = 1;
		while (n > 0) {
			if (this.offeredTokens.getValue(i - 1).getValue() != null) {
				this.offeredTokens.removeValue(i - 1);
			} else {
				i = i + 1;
			}
			n = n - 1;
		}
	} // removeOfferedValues

	/**
	 * operation removeWithdrawnTokens <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void removeWithdrawnTokens() {
		// Remove any tokens that have already been consumed.

		TokenList offeredTokens = this.offeredTokens;
		int i = 1;
		while (i <= this.offeredTokens.size()) {
			if (this.offeredTokens.getValue(i - 1).isWithdrawn()) {
				this.offeredTokens.remove(i - 1);
				i = i - 1;
			}
			i = i + 1;
		}
	} // removeWithdrawnTokens

	/**
	 * operation hasTokens <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean hasTokens() {
		// Check whether this offer has any tokens that have not been withdrawn.

		this.removeWithdrawnTokens();
		return this.offeredTokens.size() > 0;
	} // hasTokens

} // Offer
