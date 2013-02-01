
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

package fUML.Semantics.Actions.CompleteActions;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;
import fUML.Syntax.Activities.IntermediateActivities.*;
import fUML.Syntax.Actions.BasicActions.*;
import fUML.Syntax.Actions.IntermediateActions.*;
import fUML.Syntax.Actions.CompleteActions.*;

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.Activities.IntermediateActivities.*;
import fUML.Semantics.Actions.BasicActions.*;
import fUML.Semantics.Actions.IntermediateActions.*;
import fUML.Semantics.Loci.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::Actions::CompleteActions::ReduceActionActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ReduceActionActivation#doAction <em>doAction</em>}</li>
 * <li>{@link ReduceActionActivation#terminate <em>terminate</em>}</li>
 * <li>{@link ReduceActionActivation#currentExecution <em>currentExecution</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ReduceActionActivation extends
		fUML.Semantics.Actions.BasicActions.ActionActivation {

	public fUML.Semantics.CommonBehaviors.BasicBehaviors.Execution currentExecution = null;

	/**
	 * operation doAction <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void doAction() {
		// Get the values of the collection input pin.
		// If the input pin has no values, then do nothing. Otherwise, do the
		// following.
		// Repeatedly invoke the reducer behavior on successive pairs to reduce
		// the collection to a single value, and place that value on the result
		// pin.
		// To invoke the reducer behavior, compile it to create an execution,
		// make the execution the current execution, place the appropriate
		// values on its input parameters, and execute it.

		ReduceAction action = (ReduceAction) (this.node);

		ValueList values = this.takeTokens(action.collection);

		if (values.size() > 0) {
			ParameterList parameters = action.reducer.ownedParameter;
			Parameter input1 = null;
			Parameter input2 = null;
			Parameter output = null;

			int i = 1;
			while (i <= parameters.size()) {
				Parameter parameter = parameters.getValue(i - 1);
				if (parameter.direction == ParameterDirectionKind.in) {
					if (input1 != null) {
						input1 = parameter;
					} else {
						input2 = parameter;
					}
				} else if (parameter.direction == ParameterDirectionKind.out) {
					output = parameter;
				}
				i = i + 1;
			}

			ParameterValue parameterValue1 = new ParameterValue();
			parameterValue1.parameter = input1;
			parameterValue1.values = new ValueList();
			parameterValue1.values.addValue(values.getValue(0));

			int j = 2;
			while (j <= values.size()) {
				this.currentExecution = this.getExecutionLocus().factory
						.createExecution(action.reducer, this
								.getExecutionContext());

				this.currentExecution.setParameterValue(parameterValue1);

				ParameterValue parameterValue2 = new ParameterValue();
				parameterValue2.parameter = input2;
				parameterValue2.values = new ValueList();
				parameterValue2.values.addValue(values.getValue(j - 1));
				this.currentExecution.setParameterValue(parameterValue2);

				this.currentExecution.execute();

				parameterValue1 = this.currentExecution
						.getParameterValue(output);

				j = j + 1;
			}

			this.putTokens(action.result, parameterValue1.values);
		}
	} // doAction

	/**
	 * operation terminate <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void terminate() {
		// If there is a current execution, terminate it. Then terminate self.

		if (this.currentExecution != null) {
			this.currentExecution.terminate();
		}

		super.terminate();
	} // terminate

} // ReduceActionActivation
