/**
 * 
 */
package org.modelexecution.fuml.extlib;

import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.impl.ActivityNodeExitEventImpl;

import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

/**
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerImpl implements IntegrationLayer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.modelexecution.fumldebug.core.ExecutionEventListener#notify(org.
	 * modelexecution.fumldebug.core.event.Event)
	 */
	@Override
	public void notify(Event event) {
		/*
		 * TODO This method is called whenever an EntryEvent or ExitEvent is
		 * triggered Requires the IntegrationLayer to be registered at the
		 * ExecutionContext (done via ExecutionContext.addEventListener(instance
		 * of IntegrationLayerImpl).
		 * 
		 * Then, whenever this method is reached, the execution must be stop
		 * (how? already done by reaching this code?) Whenever the IL has done
		 * its job, the execution can be resumed (ExecutionContext.resume(int
		 * executionID)).
		 */
		System.out.println(event);
		isExternalCreateObjectAction(event);
	}

	public IntegrationLayerImpl() throws Exception {
		throw new Exception(
				"DEFAULT CONSTRUCTOR NOT ALLOWED, use the dedicated constructor.");
	}

	public IntegrationLayerImpl(String umlActivityDiagramFilePath,
			String... furtherPaths) {
		/*
		 * TODO Obtain a list UML Activities (org.eclipse.uml2.uml.Activity) and
		 * their corresponding fUML Activities
		 * (fUML.Syntax.Activities.IntermediateActivities.Activity).
		 * 
		 * In order to do this, every UML Activity has to be converted into a
		 * corresponding fUML Activity (using
		 * org.modelexecution.fuml.convert.uml2.UML2Converter.convert(...)).
		 */
	}

	private boolean isExternalCreateObjectAction(Event event) {
		if (event instanceof ActivityNodeExitEventImpl) {
			ActivityNode activityNode = ((ActivityNodeExitEventImpl) event)
					.getNode();
			if (activityNode instanceof CreateObjectAction) {
				CreateObjectAction createObjectAction = (CreateObjectAction) activityNode;
				// TODO add another check if it has a Comment body containing
				// "@external"
				return true;
			}
		}
		return false;
	}

}
