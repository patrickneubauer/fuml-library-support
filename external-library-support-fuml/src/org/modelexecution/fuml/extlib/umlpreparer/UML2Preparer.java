package org.modelexecution.fuml.extlib.umlpreparer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * Preparer that is capable to load a given UML2 model and convert and save it
 * s.t. it contains a Placeholder Activity with Comments and references for each
 * Operation
 * 
 * UML2Preparer is typically called before {@link UML2Converter} in order to
 * provide a prepared input for {@link UML2Converter}
 * 
 * @author Patrick Neubauer
 * 
 */
public class UML2Preparer {

	public static final String JAR_FILES_DELIMITER = "||";
	private ResourceSet resourceSet;
	private Resource resource;
	private Copier copier;

	/**
	 * Initializes an instance of {@link UML2Preparer}. Then, the converter can
	 * be loaded ("load" method) with an input UML and JAR file before
	 * converting ("convert" method) it to an output UML file.
	 */
	public UML2Preparer() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put("http://www.eclipse.org/uml2/3.0.0/UML", UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		copier = new Copier();
	}

	/**
	 * Loads the converter with a UML a JAR file that provide the input for the
	 * converter necessary to do a conversation
	 * 
	 * @param inputFilePath
	 *            {@link String} representing the relative file path to the
	 *            input UML file
	 */
	public void load(String inputFilePath) {
		resource = resourceSet.getResource(URI.createFileURI(new File(inputFilePath).getAbsolutePath()), true);
	}

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
				classes.add(clazz);
			}
		}
		return classes;
	}
	
	/**
	 * Helper method that creates a String that holds all JAR file paths together separated by {@link JAR_FILES_DELIMITER} (in case their are multiple JAR file paths)
	 * 
	 * @param jarFilePaths jar file paths pointing to the jar files in the file system
	 * @return a String that holds all JAR file paths together separated by {@link JAR_FILES_DELIMITER} (in case their are multiple JAR file paths)
	 */
	String createJarFilesPathString(String... jarFilePaths) {
		String jarFilePathsString = "";
		int pathNo = 0;
		for (String jarPath : jarFilePaths) {
			if (pathNo == jarFilePaths.length-1) {
				// case: only one JAR path OR last JAR path
				jarFilePathsString = jarFilePathsString + jarPath;
			} else if (pathNo < jarFilePaths.length) {
				// case: one of multiple JAR paths (not the last one)
				jarFilePathsString = jarFilePathsString + jarPath + JAR_FILES_DELIMITER;				
			} 
			pathNo++;
		}
		return jarFilePathsString;
	}

	/**
	 * Converts the previously loaded UML into a UML that contains:
	 * <ul>
	 * <li>Placeholder Activity (represented as OwnedBehavior of Class) for
	 * every Operation</li>
	 * <li>OwnedComment in every Class referencing the external JAR file in its
	 * body element</li>
	 * <li>A reference of the Placeholder Activity in its corresponding
	 * Operation</li>
	 * </ul>
	 * 
	 * @param jarPath
	 *            {@link String} representing the relative file path to the
	 *            input JAR file
	 */
	public void convert(String... jarFilePaths) {
		Collection<Class> resouceClasses = getAllClassesFromResource(resource);

		for (Class clazz : resouceClasses) {

			// add OwnedComment to Class that references the JAR file
			clazz.createOwnedComment().setBody("@external=" + createJarFilesPathString(jarFilePaths));

			for (Operation operation : clazz.getOperations()) {
				Activity placeholderActivity = UMLFactory.eINSTANCE.createActivity();

				// add "name" attribute to Placeholder Activity equal the
				// Operation's "name" attribute
				placeholderActivity.setName(operation.getName());

				// add OwnedComment to Placeholder Activity identifying it as
				// external
				placeholderActivity.createOwnedComment().setBody("@external");

				for (Parameter operationParameter : operation.getOwnedParameters()) {
					// copy ownedParameters
					Parameter placeholderParameter = (Parameter) copier.copy(operationParameter);

					// add Placeholder Parameter to Placeholder Activity
					placeholderActivity.getOwnedParameters().add(placeholderParameter);

					ActivityParameterNode activityParameterNode = UMLFactory.eINSTANCE.createActivityParameterNode();

					// reference the Placeholder Parameter in the
					// ActivityParameterNode
					activityParameterNode.setParameter(placeholderParameter);

					// add the ActivityParameterNode to the Placeholder Activity
					placeholderActivity.getOwnedNodes().add(activityParameterNode);

				}// Parameter loop

				// add OwnedBehavior to Class referencing the Placeholder
				// Activity
				clazz.getOwnedBehaviors().add(placeholderActivity);

				// reference the Placeholder Activity in the Operation
				operation.getMethods().add(placeholderActivity);

			}// Operation loop
		}
	}

	/**
	 * Saves the current {@link Resource} as a file to the given output file
	 * path
	 * 
	 * @param outputFilePath
	 *            {@link String} representing the relative file path to the
	 *            output UML file
	 */
	public void save(String outputFilePath) {
		// save the modified Resource to the output file path
		try {
			resource.save(new FileOutputStream(new File(outputFilePath)), null);
		} catch (FileNotFoundException e) {
			System.out.println("Coudln't find output file " + outputFilePath + ". Details: " + e);
		} catch (IOException e) {
			System.out.println("Input/Output Exception occured. Details: " + e);
		}
	}

}
