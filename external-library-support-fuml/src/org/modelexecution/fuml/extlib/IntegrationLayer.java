/**
 * 
 */
package org.modelexecution.fuml.extlib;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.ExecutionEventListener;

import fUML.Semantics.Classes.Kernel.Object_;

/**
 * {@link IntegrationLayer} that handles external libraries in fUML
 * 
 * @author Patrick Neubauer
 * 
 */
public interface IntegrationLayer extends ExecutionEventListener {

	/*
	 * TODO Define fields and methods required in the IL
	 */

	/**
	 * Get the {@link ExecutionContext} instance where the
	 * {@link IntegrationLayer} is registered
	 * 
	 * @return {@link ExecutionContext} of the {@link IntegrationLayer}
	 */
	public ExecutionContext getExecutionContext();

	/**
	 * Get the corresponding {@link Object_} from a {@link Object}
	 * 
	 * @param javaObject
	 *            Java object that corresponds to a fUML object
	 * @return fUML Object that corresponds to the given Java object
	 */
	public Object_ getFUmlObject(Object javaObject);

	/**
	 * Get the corresponding {@link Object} from a {@link Object_}
	 * 
	 * @param fUmlObject
	 *            fUML object that corresponds to a Java object
	 * @return Java object that corresponds to the given fUML object
	 */
	public Object getJavaObject(Object_ fUmlObject);

}
