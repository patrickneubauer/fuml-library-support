/**
 * 
 */
package org.modelexecution.fuml.extlib.helper;

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Classes.Kernel.Namespace;
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

	public static Parameter getReturnParameter(Activity activity) {
		for (Parameter parameter : activity.ownedParameter) {
			if (parameter.direction == ParameterDirectionKind.return_) {
				return parameter;
			}
		}
		// No return parameter has been found (method return is "void"), therefore return null 
		return null;
	}
	
	public static String getOperationName(Activity activity) throws Exception {
		if (activity.specification instanceof Operation) {
			if (activity.specification.name != "") {
				return activity.specification.name;
			}
		}
		
		throw new Exception("Could not obtain Operation name of Activity " + activity);
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

		if (namespace != null && namespace != "") {
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
				if (activity.specification.namespace.namespace != null && activity.specification.namespace.owner != null) {
					// case: namespace has an owner namespace
					return obtainClassOwnerNamespace(activity.specification.namespace.namespace);
				} else if (activity.specification.namespace.namespace != null) {
					// case: namespace has NO owner namespace
					return activity.specification.namespace.namespace.name;
				}
			}
		}

		return null; // no name space found
	}// getClassNamespace(Activity)
	
	/**
	 * Obtains the Class owner's namespace recursively
	 * 
	 * @param namespace the {@link Namespace} to look for the owner namespace
	 * @return a String representing the owner namespace (recursively obtained) plus dot plus the namespace name itself
	 */
	private static String obtainClassOwnerNamespace(Namespace namespace) {
		if (namespace != null && namespace.owner != null) {
			return obtainClassOwnerNamespace((Namespace)namespace.owner) + "." + namespace.name;
		}
		return namespace.name;
	}// obtainClassOwnerNamespace(Namespace)
	
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
				if (activity.specification.namespace.name != "") {
					return activity.specification.namespace.name;
				}
			}
		}

		throw new Exception("Error occured while trying to obtain the Class name from " + activity);
	}// getClassName(Activity)
}
