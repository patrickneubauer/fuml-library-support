package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.extlib.UML2Preparer;

/**
 * Class to test the {@link UML2Preparer} Can be run as a JUnit test (not a
 * JUnit Plug-in Test)
 * 
 * @author Patrick Neubauer
 * 
 */
public class UML2PreparerTest {

	private ResourceSet resourceSet;

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}// prepareResourceSet

	@Test
	public void addPlaceholderActivitiesToVehiclesUML() {
		String inputFilePath = "models/Vehicles.uml";
		String outputFilePath = "models/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		UML2Preparer converter = new UML2Preparer();
		converter.load(inputFilePath);
		converter.convert(jarFilePath);
		converter.save(outputFilePath);

		// check if every Class contains a OwnedComment referring to the JAR
		// file
		containsComments(outputFilePath, jarFilePath);

		// check if every OwnedOperation in every Class also has a corresponding
		// OwnedBehavior
		containsPlaceholderActivities(outputFilePath);
	}// addPlaceholderActivitiesToVehiclesUML

	/**
	 * Returns all classes of a specific {@link Resource}
	 * 
	 * @param resource
	 *            the {@link Resource} from which the classes should be obtained
	 * @return a {@link Collection} of {@link Class} from a specific
	 *         {@link Resource}
	 */
	private Collection<Class> getAllClassesFromResource(Resource resource) {
		Collection<Class> classes = new HashSet<Class>();

		for (TreeIterator<EObject> iterator = resource.getAllContents(); iterator.hasNext();) {
			EObject next = iterator.next();
			if (next instanceof Class) {
				Class clazz = (Class) next;
				if (clazz.getPackage() != null) {
					classes.add(clazz);
					System.out.println("adding class " + clazz.getName() + " from package " + clazz.getPackage().getName());
				}
			}
		}
		return classes;
	}// getAllClassesFromResource

	/**
	 * Searches for a specified {@link Comment} body element in a {@link Class}
	 * based on its content (i.e. whatever is inside <body>...</body)
	 * 
	 * @param clazz
	 *            {@link Class} in which the comment body content should be
	 *            searched
	 * @param commentBody
	 *            the body content to be search
	 * @return true if found, false otherwise
	 */
	private boolean containsComment(Class clazz, String commentBody) {
		for (Comment comment : clazz.getOwnedComments()) {
			if (comment.getBody().equals(commentBody)) {
				return true; // found
			}
		}
		return false; // not found
	}// containsComment

	/**
	 * Searches for a specified {@link Comment} body element in an
	 * {@link Activity} based on its content (i.e. whatever is inside
	 * <body>...</body)
	 * 
	 * @param placeholderActivity
	 *            {@link Activity} in which the comment body content should be
	 *            searched
	 * @param commentBody
	 *            the body content to be search
	 * @return true if found, false otherwise
	 */
	private boolean containsComment(Activity placeholderActivity, String commentBody) {
		for (Comment comment : placeholderActivity.getOwnedComments()) {
			if (comment.getBody().equals(commentBody)) {
				return true; // found
			}
		}
		return false; // not found
	}// containsComment

	/**
	 * Checks if every Class contains a corresponding OwnedComment having a
	 * "@external" reference in its body
	 */
	private void containsComments(String outputFilePath, String jarFilePath) {
		Resource resource = resourceSet.getResource(URI.createFileURI(new File(outputFilePath).getAbsolutePath()), true);

		Collection<Class> resouceClasses = getAllClassesFromResource(resource);

		for (Class clazz : resouceClasses) {
			assertTrue(containsComment(clazz, "@external=" + jarFilePath));
		}
	}// containsComments

	/**
	 * Evaluates if a given Placeholder Activity contains a corresponding ActivityParameterNode for each owned Parameter
	 * 
	 * @param placeholderActivity
	 *            {@link Activity} Parameters to be searched for in the
	 *            {@link Operation}
	 * @return true if found, false otherwise
	 */
	private boolean containsParameters(Activity placeholderActivity) {
		for (Parameter placeholderParameter : placeholderActivity.getOwnedParameters()) {
			boolean found = false;
			
			for (ActivityNode activityNode : placeholderActivity.getOwnedNodes()) {
				if (activityNode instanceof ActivityParameterNode) {
					ActivityParameterNode activityParameterNode = (ActivityParameterNode) activityNode;
					
					if (placeholderParameter.equals(activityParameterNode.getParameter())) {
						found = true;
					}
					
				}
			}
			
			if (found==false) {
				System.out.println("Could NOT find corresponding ActivityParameterNode for Placeholder Parameter.");
				return false;
			}
			
		}
		
		return true; // found
	}// containsParameters

	/**
	 * Checks if the output file created with the {@link UML2Preparer} contains
	 * the necessary new OwnedBehavior (with all the parameters) elements and
	 * OwnedComment element for every Class
	 * 
	 * @param outputFilePath
	 *            File output path where to search for the Placeholder
	 *            Activities
	 */
	private void containsPlaceholderActivities(String outputFilePath) {
		Resource resource = resourceSet.getResource(URI.createFileURI(new File(outputFilePath).getAbsolutePath()), true);

		Collection<Class> resouceClasses = getAllClassesFromResource(resource);
		int classCounter = 0;
		int operationCounter = 0;

		for (Class clazz : resouceClasses) {
			classCounter++;

			for (Operation classOperation : clazz.getOperations()) {

				// check if the current Operation has a corresponding
				// Placeholder Activity
				for (Behavior classBehavior : clazz.getOwnedBehaviors()) {
					if (classBehavior instanceof Activity) {
						Activity placeholderActivity = (Activity) classBehavior;

						if (placeholderActivity.equals(classOperation.getMethods().get(0))) {
							operationCounter++;

							// check if their name is equal
							assertEquals(placeholderActivity.getName(), classOperation.getName());

							// check if the Placeholder Activity has the
							// expected OwnedComment
							assertTrue(containsComment(placeholderActivity, "@external"));

							// check if all Parameters have been copied
							// TODO
							
							// check if each Parameters has its ActivityParameterNode reference
							assertTrue(containsParameters(placeholderActivity));
						}
					}
				}
			}
		}

		assertEquals(19, classCounter);
		assertEquals(50, operationCounter);
	}// containsPlaceholderActivities

}// UML2PreparerTest
