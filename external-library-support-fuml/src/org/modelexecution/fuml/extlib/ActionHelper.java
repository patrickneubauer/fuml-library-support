/**
 * 
 */
package org.modelexecution.fuml.extlib;

import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Classes.Kernel.Comment;
import fUML.Syntax.Classes.Kernel.CommentList;

/**
 * @author Patrick Neubauer
 *
 */
public class ActionHelper {

	/*
	 * Obtains the referenced JAR path from the {@code createObjectAction}
	 * 
	 * @param createObjectAction the {@link CreateObjectAction} to get the JAR
	 * path from
	 * 
	 * @return {@link String} representing the file path of the JAR found or
	 * throws an {@link Exception} otherwise
	 * 
	 * @throws Exception Whenever the JAR path could not be obtained
	 */
	public static String obtainClassJarPath(CreateObjectAction createObjectAction) throws Exception {
		CommentList commentList = createObjectAction.classifier.ownedComment;
		for (Comment comment : commentList) {
			if (comment.body.startsWith("@external")) {
				return (String) comment.body.subSequence("@external=".length(), comment.body.length());
			}
		}
	
		throw new Exception("Error occured while trying to obtain the external JAR path from " + createObjectAction);
	}//obtainClassJarPath

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
	public static String obtainClassName(CreateObjectAction createObjectAction) throws Exception {
		if (createObjectAction.classifier != null) {
			return createObjectAction.classifier.name;			
		}
		
		throw new Exception("Error occured while trying to obtain the Class name from " + createObjectAction);
	}//obtainClassName(CreateObjectAction)
	
	/**
	 * Obtains the Class name of the {@code callOperationAction}'s operation
	 * 
	 * @param callOperationAction
	 *            the {@link CallOperationAction} to get the operations Class name from
	 * @return {@link String} representing the Class name or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class name could not be obtained
	 */
	public static String obtainClassName(CallOperationAction callOperationAction) throws Exception {
		if (callOperationAction.operation != null) {
			if (callOperationAction.operation.namespace != null) {
				return callOperationAction.operation.namespace.name;			
			}
		}
		
		throw new Exception("Error occured while trying to obtain the Class name from " + callOperationAction);
	}//obtainClassName(CallOperationAction)

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
	public static String obtainClassNamespace(CreateObjectAction createObjectAction) throws Exception {
		if (createObjectAction.classifier != null) {
			if (createObjectAction.classifier.namespace != null) {
				return createObjectAction.classifier.namespace.name;
			}
		}
		
		throw new Exception("Error occured while trying to obtain the Class namespace of " + createObjectAction);
	}//obtainClassNamespace(CreateObjectAction)
	
	/**
	 * Obtains the Class namespace of the {@code callOperationAction}
	 * 
	 * @param callOperationAction
	 *            the {@link CallOperationAction} to get the operations Class namespace from
	 * @return {@link String} representing the Class namespace or throws an
	 *         {@link Exception} otherwise
	 * @throws Exception
	 *             Whenever the Class namespace could not be obtained
	 */
	public static String obtainClassNamespace(CallOperationAction callOperationAction) throws Exception {
		if (callOperationAction.operation != null) {
			if (callOperationAction.operation.namespace != null) {
				if (callOperationAction.operation.namespace.namespace != null)
				return callOperationAction.operation.namespace.namespace.name;
			}
		}
		
		throw new Exception("Error occured while trying to obtain the Class namespace of " + callOperationAction);
	}//obtainClassNamespace(CallOperationAction)

}