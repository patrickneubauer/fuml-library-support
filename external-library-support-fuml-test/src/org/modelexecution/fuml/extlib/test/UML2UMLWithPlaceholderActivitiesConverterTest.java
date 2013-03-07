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
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.extlib.UML2UMLWithPlaceholderActivitiesConverter;

public class UML2UMLWithPlaceholderActivitiesConverterTest {

	private ResourceSet resourceSet;
	
	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}
	
	@Test
	public void addPlaceholderActivitiesToVehiclesUML() {
		String inputFilePath = "models/Vehicles.uml";
		String outputFilePath = "models/VehiclesConverted.uml";
		String jarFilePath = "libraries/Vehicles.jar";
		
		UML2UMLWithPlaceholderActivitiesConverter converter = new UML2UMLWithPlaceholderActivitiesConverter();
		converter.load(inputFilePath);
		converter.convert(outputFilePath, jarFilePath);
		
		// check if every Class contains a OwnedComment referring to the JAR file
		containsComments(outputFilePath, jarFilePath);
		
		// check if every OwnedOperation in every Class also has a corresponding OwnedBehavior
		containsPlaceholderActivities(outputFilePath);
	}
	
	/**
	 * Returns all classes of a specific {@link Resource}
	 * 
	 * @param resource the {@link Resource} from which the classes should be obtained
	 * @return a {@link Collection} of {@link Class} from a specific {@link Resource}
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
	}
	
	/**
	 * Searches for a specified {@link Comment} body element in a {@link Class} based on its content (i.e. whatever is inside <body>...</body)
	 * 
	 * @param clazz {@link Class} in which the comment body content should be searched
	 * @param commentBody the body content to be search
	 * @return true if found, false otherwise
	 */
	private boolean containsComment(Class clazz, String commentBody) {
		for(Comment comment : clazz.getOwnedComments()) {
			if (comment.getBody().equals(commentBody)) {
				return true; // found
			}
		}
		return false; // not found
	}
	
	/**
	 * Searches for a specified {@link Comment} body element in an {@link Activity} based on its content (i.e. whatever is inside <body>...</body)
	 * 
	 * @param placeholderActivity {@link Activity} in which the comment body content should be searched
	 * @param commentBody the body content to be search
	 * @return true if found, false otherwise
	 */
	private boolean containsComment(Activity placeholderActivity, String commentBody) {
		for(Comment comment : placeholderActivity.getOwnedComments()) {
			if (comment.getBody().equals(commentBody)) {
				return true; // found
			}
		}
		return false; // not found
	}
	
	/**
	 * Checks if every Class contains a corresponding OwnedComment having a "@external" reference in its body
	 */
	private void containsComments(String outputFilePath, String jarFilePath) {
		Resource resource = resourceSet.getResource(URI.createFileURI(new File(outputFilePath).getAbsolutePath()), true);

		Collection<Class> resouceClasses = getAllClassesFromResource(resource);
		
		for(Class clazz : resouceClasses) {
			assertTrue(containsComment(clazz, "@external="+jarFilePath));
		}
	}
	
	/**
	 * Checks if the output file created with the {@link UML2UMLWithPlaceholderActivitiesConverter} contains
	 * the necessary new OwnedBehavior (with all the parameters) elements and OwnedComment element for every Class
	 */
	private void containsPlaceholderActivities(String outputFilePath) {
		Resource resource = resourceSet.getResource(URI.createFileURI(new File(outputFilePath).getAbsolutePath()), true);

		Collection<Class> resouceClasses = getAllClassesFromResource(resource);
		
		for(Class clazz : resouceClasses) {
			for(Operation operation : clazz.getOperations()) {
				Activity placeholderActivity = (Activity) clazz.getOwnedBehavior(operation.getName());
				
				// check if for every Operation there is a corresponding OwnedBehavior
				assertEquals(operation.getName(), placeholderActivity.getName());

				// check if the Placeholder Activity has the expected OwnedComment
				containsComment(placeholderActivity, "@external");

				//TODO whenever placeholderActivity's ownedParameters are created with a "specification" (or similar) attribute
				//that references the xmi:id of the actual parameter then check for it
			}
		}
		
	}
	
}
