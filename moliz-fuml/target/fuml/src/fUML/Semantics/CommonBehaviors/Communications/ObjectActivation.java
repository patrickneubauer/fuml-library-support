
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

package fUML.Semantics.CommonBehaviors.Communications;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import java.util.Iterator;

import fUML.Syntax.*;
import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.*;
import fUML.Syntax.CommonBehaviors.Communications.*;

import fUML.Semantics.*;
import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.*;
import fUML.Semantics.Loci.LociL1.*;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Semantics::CommonBehaviors::Communications::ObjectActivation</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link ObjectActivation#startBehavior <em>startBehavior</em>}</li>
 * <li>{@link ObjectActivation#stop <em>stop</em>}</li>
 * <li>{@link ObjectActivation#register <em>register</em>}</li>
 * <li>{@link ObjectActivation#unregister <em>unregister</em>}</li>
 * <li>{@link ObjectActivation#send <em>send</em>}</li>
 * <li>{@link ObjectActivation#dispatchNextEvent <em>dispatchNextEvent</em>}</li>
 * <li>{@link ObjectActivation#getNextEvent <em>getNextEvent</em>}</li>
 * <li>{@link ObjectActivation#_startObjectBehavior <em>_startObjectBehavior
 * </em>}</li>
 * <li>{@link ObjectActivation#_send <em>_send</em>}</li>
 * <li>{@link ObjectActivation#classifierBehaviorExecutions <em>
 * classifierBehaviorExecutions</em>}</li>
 * <li>{@link ObjectActivation#waitingEventAccepters <em>waitingEventAccepters
 * </em>}</li>
 * <li>{@link ObjectActivation#eventPool <em>eventPool</em>}</li>
 * <li>{@link ObjectActivation#object <em>object</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class ObjectActivation extends org.modeldriven.fuml.FumlObject {

	public fUML.Semantics.CommonBehaviors.Communications.ClassifierBehaviorExecutionList classifierBehaviorExecutions = new fUML.Semantics.CommonBehaviors.Communications.ClassifierBehaviorExecutionList();
	public fUML.Semantics.CommonBehaviors.Communications.EventAccepterList waitingEventAccepters = new fUML.Semantics.CommonBehaviors.Communications.EventAccepterList();
	public fUML.Semantics.CommonBehaviors.Communications.SignalInstanceList eventPool = new fUML.Semantics.CommonBehaviors.Communications.SignalInstanceList();
	public fUML.Semantics.Classes.Kernel.Object_ object = null;

	/**
	 * operation startBehavior <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void startBehavior(
			fUML.Syntax.Classes.Kernel.Class_ classifier,
			fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList inputs) {
		// Start the event dispatch loop for this object activation (if it has
		// not already been started).
		// If a classifier is given that is a type of the object of this object
		// activation and there is not already a classifier behavior execution
		// for it,
		// then create a classifier behavior execution for it.
		// Otherwise, create a classifier behavior execution for each of the
		// types of the object of this object activation which has a classifier
		// behavior or which is a behavior itself
		// and for which there is not currently a classifier behavior execution.

		// Start EventDispatchLoop
		_startObjectBehavior();

		if (classifier == null) {
			Debug
					.println("[startBehavior] Starting behavior for all classifiers...");
			// *** Start all classifier behaviors concurrently. ***
			Class_List types = this.object.types;
			for (Iterator i = types.iterator(); i.hasNext();) {
				Class_ type = (Class_) i.next();
				if (type instanceof Behavior | type.classifierBehavior != null) {
					this.startBehavior(type, new ParameterValueList());
				}
			}
		} else {
			Debug.println("[startBehavior] Starting behavior for "
					+ classifier.name + "...");

			boolean notYetStarted = true;
			int i = 1;
			while (notYetStarted
					& i <= this.classifierBehaviorExecutions.size()) {
				notYetStarted = (this.classifierBehaviorExecutions
						.getValue(i - 1).classifier != classifier);
				i = i + 1;
			}

			if (notYetStarted) {
				ClassifierBehaviorExecution newExecution = new ClassifierBehaviorExecution();
				newExecution.objectActivation = this;
				this.classifierBehaviorExecutions.addValue(newExecution);
				newExecution.execute(classifier, inputs);
			}
		}
	} // startBehavior

	/**
	 * operation stop <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void stop() {
		// Stop this object activation by terminating all classifier behavior
		// executions.

		ClassifierBehaviorExecutionList classifierBehaviorExecutions = this.classifierBehaviorExecutions;
		for (int i = 0; i < classifierBehaviorExecutions.size(); i++) {
			ClassifierBehaviorExecution classifierBehaviorExecution = classifierBehaviorExecutions
					.getValue(i);
			classifierBehaviorExecution.terminate();
		}

	} // stop

	/**
	 * operation register <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void register(
			fUML.Semantics.CommonBehaviors.Communications.EventAccepter accepter) {
		// Register the given event accepter to wait for a dispatched signal
		// event.

		Debug.println("[register] object = " + this.object);
		Debug.println("[register] accepter = " + accepter);

		this.waitingEventAccepters.addValue(accepter);
	} // register

	/**
	 * operation unregister <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unregister(
			fUML.Semantics.CommonBehaviors.Communications.EventAccepter accepter) {
		// Remove the given event accepter for the list of waiting event
		// accepters.

		Debug.println("[unregister] object = " + this.object);
		Debug.println("[unregister] accepter = " + accepter);

		boolean notFound = true;
		int i = 1;
		while (notFound & i <= this.waitingEventAccepters.size()) {
			if (this.waitingEventAccepters.getValue(i - 1) == accepter) {
				this.waitingEventAccepters.remove(i - 1);
				notFound = false;
			}
			i = i + 1;
		}

	} // unregister

	/**
	 * operation send <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void send(
			fUML.Semantics.CommonBehaviors.Communications.SignalInstance signalInstance) {
		// Add the given signal instance to the event pool and signal that a new
		// signal instance has arrived.

		this.eventPool.addValue((SignalInstance) (signalInstance.copy()));
		_send(new ArrivalSignal());

	} // send

	/**
	 * operation dispatchNextEvent <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void dispatchNextEvent() {
		// Get the next signal instance out of the event pool.
		// If there is one or more waiting event accepters with triggers that
		// match the signal instance, then dispatch it to exactly one of those
		// waiting accepters.

		if (this.eventPool.size() > 0) {
			SignalInstance signalInstance = this.getNextEvent();

			Debug.println("[dispatchNextEvent] signalInstance = "
					+ signalInstance);

			intList matchingEventAccepterIndexes = new intList();
			EventAccepterList waitingEventAccepters = this.waitingEventAccepters;
			for (int i = 0; i < waitingEventAccepters.size(); i++) {
				EventAccepter eventAccepter = waitingEventAccepters.getValue(i);
				if (eventAccepter.match(signalInstance)) {
					matchingEventAccepterIndexes.addValue(i);
				}
			}

			if (matchingEventAccepterIndexes.size() > 0) {
				// *** Choose one matching event accepter non-deterministically.
				// ***
				int j = ((ChoiceStrategy) this.object.locus.factory
						.getStrategy("choice"))
						.choose(matchingEventAccepterIndexes.size());
				EventAccepter selectedEventAccepter = this.waitingEventAccepters
						.getValue(matchingEventAccepterIndexes.getValue(j - 1));
				this.waitingEventAccepters.removeValue(j - 1);
				selectedEventAccepter.accept(signalInstance);
			}
		}
	} // dispatchNextEvent

	/**
	 * operation getNextEvent <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.CommonBehaviors.Communications.SignalInstance getNextEvent() {
		// Get the next event from the event pool, using a get next event
		// strategy.

		return ((GetNextEventStrategy) this.object.locus.factory
				.getStrategy("getNextEvent")).getNextEvent(this);
	} // getNextEvent

	/**
	 * operation _startObjectBehavior <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void _startObjectBehavior() {
		// *** This should start the EventDispatchLoop ***

		return;
	} // _startObjectBehavior

	/**
	 * operation _send <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void _send(
			fUML.Semantics.CommonBehaviors.Communications.ArrivalSignal signal) {
		// Signal the arrival of a new signal instance in the event pool.

		// *** This should send an ArrivalSignal to the EventDispatchLoop to do
		// the following asynchronously. ***
		this.dispatchNextEvent();
	} // _send

} // ObjectActivation
