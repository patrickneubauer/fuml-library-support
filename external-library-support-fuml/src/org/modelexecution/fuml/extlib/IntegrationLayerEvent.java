/**
 * 
 */
package org.modelexecution.fuml.extlib;


/**
 * @author Patrick Neubauer
 *
 */
public class IntegrationLayerEvent {
	
	public enum Type {
		START, STOP;
	}

	private Type type;
	private IntegrationLayer il;

	protected IntegrationLayerEvent(Type type,
			IntegrationLayer integrationLayer) {
		super();
		this.type = type;
		this.il = integrationLayer;
	}

	public Type getType() {
		return type;
	}

	public IntegrationLayer getIntegrationLayer() {
		return il;
	}

}
