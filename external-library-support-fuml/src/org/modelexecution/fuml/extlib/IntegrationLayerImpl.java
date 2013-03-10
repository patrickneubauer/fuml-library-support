/**
 * 
 */
package org.modelexecution.fuml.extlib;

import org.modelexecution.fumldebug.core.event.Event;

/**
 * @author Patrick Neubauer
 *
 */
public class IntegrationLayerImpl implements IntegrationLayer {

	/* (non-Javadoc)
	 * @see org.modelexecution.fumldebug.core.ExecutionEventListener#notify(org.modelexecution.fumldebug.core.event.Event)
	 */
	@Override
	public void notify(Event event) {
		// TODO Auto-generated method stub
		System.out.println(event.toString());
	}

}
