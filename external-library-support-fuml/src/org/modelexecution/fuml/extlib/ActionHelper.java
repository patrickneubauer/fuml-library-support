/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.util.ArrayList;
import java.util.regex.Pattern;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Classes.Kernel.Comment;
import fUML.Syntax.Classes.Kernel.CommentList;
import fUML.Syntax.Classes.Kernel.Namespace;

/**
 * @author Patrick Neubauer
 * 
 */
public class ActionHelper {

	/*
	 * Obtains the referenced JAR path(s) from the {@code createObjectAction}
	 * 
	 * @param createObjectAction the {@link CreateObjectAction} to get the JAR
	 * path(s) from
	 * 
	 * @return {@link String[]} representing the file path(s) of the JAR(s) found or
	 * throws an {@link Exception} otherwise
	 * 
	 * @throws Exception Whenever the JAR path(s) could not be obtained
	 */
	public static String[] getClassJarPaths(CreateObjectAction createObjectAction) throws Exception {
		CommentList commentList = createObjectAction.classifier.ownedComment;
		Pattern pattern = Pattern.compile(Pattern.quote(UML2Preparer.JAR_FILES_DELIMITER));
		
		for (Comment comment : commentList) {
			if (comment.body.startsWith("@external")) {
				String jarPathsWithPotentialDelimiter = (String) comment.body.subSequence("@external=".length(), comment.body.length());
				return pattern.split(jarPathsWithPotentialDelimiter);
			}
		}

		throw new Exception("Error occured while trying to obtain the external JAR path from " + createObjectAction);
	}// obtainClassJarPath

	/**
	 * Obtains the Class name of the {@code createObjectAction}
	 * 
	 * @param createObjectAction
	 *            the {@link CreateObjectAction} to get the Class name from
	 * @return {@link String} representing the Class name or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	private static String obtainClassName(CreateObjectAction createObjectAction) throws Exception {
		if (createObjectAction.classifier != null) {
			if (createObjectAction.classifier.name != "") {
				return createObjectAction.classifier.name;
			}
		}

		throw new Exception("Error occured while trying to obtain the Class name from " + createObjectAction);
	}// obtainClassName(CreateObjectAction)
	
	/**
	 * Obtains the Class namespace and name separated by a dot ("."). If no
	 * namespace available, the Class name only is returned.
	 * 
	 * @param createObjectAction
	 *            the {@link CreateObjectAction} to get the operations Class
	 *            namespace and name from
	 * @return {@link String} representing the Class namespace and name or
	 *         throws an {@link Exception} if no Class name could be obtained
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	public static String getClassNamespaceAndName(CreateObjectAction createObjectAction) throws Exception {
		String namespace = obtainClassNamespace(createObjectAction);
		String className = obtainClassName(createObjectAction);

		if (namespace != null && namespace != "") {
			return namespace + "." + className;
		} else {
			return className;
		}
	}// obtainNamespaceAndClass(CallOperationAction)

	/**
	 * Obtains the Class namespace and name separated by a dot ("."). If no
	 * namespace available, the Class name only is returned.
	 * 
	 * @param callOperationAction
	 *            the {@link CallOperationAction} to get the operations Class
	 *            namespace and name from
	 * @return {@link String} representing the Class namespace and name or
	 *         throws an {@link Exception} if no Class name could be obtained
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	public static String getClassNamespaceAndName(CallOperationAction callOperationAction) throws Exception {
		String namespace = obtainClassNamespace(callOperationAction);
		String className = obtainClassName(callOperationAction);

		if (namespace != null && namespace != "") {
			return namespace + "." + className;
		} else {
			return className;
		}
	}// obtainNamespaceAndClass(CallOperationAction)

	/**
	 * Obtains the Class name of the {@code callOperationAction}'s operation
	 * 
	 * @param callOperationAction
	 *            the {@link CallOperationAction} to get the operations Class
	 *            name from
	 * @return {@link String} representing the Class name or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	private static String obtainClassName(CallOperationAction callOperationAction) throws Exception {
		if (callOperationAction.operation != null) {
			if (callOperationAction.operation.namespace != null) {
				if (callOperationAction.operation.namespace.name != "") {
					return callOperationAction.operation.namespace.name;
				}
			}
		}

		throw new Exception("Error occured while trying to obtain the Class name from " + callOperationAction);
	}// obtainClassName(CallOperationAction)

	/**
	 * Obtains the Class namespace of the {@code createObjectAction}
	 * 
	 * @param createObjectAction
	 *            the {@link CreateObjectAction} to get the Class namespace from
	 * @return {@link String} representing the Class namespace or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class namespace could not be obtained
	 */
	private static String obtainClassNamespace(CreateObjectAction createObjectAction) {
		if (createObjectAction.classifier != null) {
			if (createObjectAction.classifier.namespace != null && createObjectAction.classifier.namespace.owner != null) {
				// case: namespace has an owner namespace
				return obtainClassOwnerNamespace(createObjectAction.classifier.namespace);
			} else if (createObjectAction.classifier.namespace != null && createObjectAction.classifier.namespace.owner == null) {
				// case: namespace has NO owner namespace
				return createObjectAction.classifier.namespace.name;
			}
		}

		return null; // no namespace found
	}// obtainClassNamespace(CreateObjectAction)

	/**
	 * Obtains the Class owner's namespace recursively
	 * 
	 * @param namespace the {@link Namespace} to look for the owner namespace
	 * @return a String representing the owner namespace (recursively obtained) plus dot plus the namespace name itself
	 */
	private static String obtainClassOwnerNamespace(Namespace namespace) {
		if (namespace != null && namespace.owner != null && namespace.name != null) {
			return obtainClassOwnerNamespace((Namespace)namespace.owner) + "." + namespace.name;
		}
		return namespace.name;
	}// obtainClassOwnerNamespace
	
	/**
	 * Obtains the Class namespace of the {@code callOperationAction}
	 * 
	 * @param callOperationAction
	 *            the {@link CallOperationAction} to get the operations Class
	 *            namespace from
	 * @return {@link String} representing the Class namespace or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class namespace could not be obtained
	 */
	private static String obtainClassNamespace(CallOperationAction callOperationAction) throws Exception {
		if (callOperationAction.operation != null) {
			if (callOperationAction.operation.namespace != null) {
				if (callOperationAction.operation.namespace.namespace != null)
					return callOperationAction.operation.namespace.namespace.name;
			}
		}

		return null; // no namespace found
	}// obtainClassNamespace(CallOperationAction)
	
}
