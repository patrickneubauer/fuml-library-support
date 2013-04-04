/**
 * 
 */
package org.modelexecution.fuml.extlib;

import org.modelexecution.fumldebug.core.event.Event;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;

/**
 * Helper to handle instances of {@link Activity}
 * 
 * @author Patrick Neubauer
 * 
 */
public class ActivityHelper {

	public static Parameter getReturnParameter(Activity activity) throws Exception {
		for (Parameter parameter : activity.ownedParameter) {
			if (parameter.direction == ParameterDirectionKind.return_) {
				return parameter;
			}
		}
		
		throw new Exception("Could not obtain Activity return Parameter.");
	}
	
	public static String getOperationName(Activity activity) throws Exception {
		if (activity.specification instanceof Operation) {
			return activity.specification.name;
		}
		
		throw new Exception("Could not obtain Activity Operation name.");
	}
	
	/**
	 * Obtains the Class name space and name separated by a dot ("."). If no
	 * name space available, the Class name only is returned.
	 * 
	 * @param activity
	 *            the {@link Activity} to get the operations Class
	 *            name space and name from
	 * @return {@link String} representing the Class name space and name or
	 *         throws an {@link Exception} if no Class name could be obtained
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	public static String getOperationNamespaceAndName(Activity activity) throws Exception {
		String namespace = getClassNamespace(activity);
		String className = getClassName(activity);

		if (namespace != null) {
			return namespace + "." + className;
		} else {
			return className;
		}
	}// getOperationNamespaceAndName(Activity)

	
	/**
	 * Obtains the Class name space of the {@code activity}'s Operation
	 * 
	 * @param activity
	 *            the {@link Activity} to get the operations Class
	 *            name space from
	 * @return {@link String} representing the Class name space or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class name space could not be obtained
	 */
	private static String getClassNamespace(Activity activity) throws Exception {
		if (activity.specification != null) {
			if (activity.specification.namespace != null) {
				if (activity.specification.namespace.namespace != null)
					return activity.specification.namespace.namespace.name;
			}
		}

		throw new Exception("Error occured while trying to obtain the Class name space of " + activity);
	}// getClassNamespace(Activity)
	
	/**
	 * Obtains the Class name of the {@code activity}'s Operation
	 * 
	 * @param activity
	 *            the {@link Activity} to get the operations Class
	 *            name from
	 * @return {@link String} representing the Class name or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	private static String getClassName(Activity activity) throws Exception {
		if (activity.specification != null) {
			if (activity.specification.namespace != null) {
				return activity.specification.namespace.name;
			}
		}

		throw new Exception("Error occured while trying to obtain the Class name from " + activity);
	}// getClassName(Activity)
}
