package org.modelexecution.fuml.extlib;

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
 * Converter that is capable to load a given UML2 model and convert and save it s.t.
 * it contains a Placeholder Activity with Comments and references for each Operation
 * 
 * @author patrickneubauer
 *
 */
public class UML2UMLWithPlaceholderActivitiesConverter {

	private ResourceSet resourceSet;
	private Resource resource;
	private Copier copier;

	/**
	 * Initializes an instance of {@link UML2UMLWithPlaceholderActivitiesConverter}.
	 * Then, the converter can be loaded ("load" method) with an input UML and JAR file
	 * before converting ("convert" method) it to an output UML file. 
	 */
	public UML2UMLWithPlaceholderActivitiesConverter() {
		copier = new Copier();
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put("http://www.eclipse.org/uml2/3.0.0/UML", UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}
	
	/**
	 * Loads the converter with a UML a JAR file that provide the input for the converter
	 * necessary to do a conversation
	 * 
	 * @param inputFilePath {@link String} representing the relative file path to the input UML file
	 */
	public void load(String inputFilePath) {
		resource = resourceSet.getResource(URI.createFileURI(new File(inputFilePath).getAbsolutePath()), true);		
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
				classes.add(clazz);
			}
		}
		return classes;
	}
	
	/**
	 * Converts the previously loaded UML into a UML that contains:
	 * <ul>
	 *   <li>Placeholder Activity (represented as OwnedBehavior of Class) for every Operation</li>
	 *   <li>OwnedComment in every Class referencing the external JAR file in its body element</li>
	 *   <li>A reference of the Placeholder Activity in its corresponding Operation</li>
	 * </ul>
	 * 
	 * @param jarPath {@link String} representing the relative file path to the input JAR file
	 */
	public void convert(String jarFilePath) {		
		Collection<Class> resouceClasses = getAllClassesFromResource(resource);
		
		for(Class clazz : resouceClasses) {
			
			// add OwnedComment to Class that references the JAR file
			clazz.createOwnedComment().setBody("@external=" + jarFilePath);
			
			for(Operation operation : clazz.getOperations()) {
				Activity placeholderActivity = UMLFactory.eINSTANCE.createActivity();
				
				// add "name" attribute to Placeholder Activity equal the Operation's "name" attribute
				placeholderActivity.setName(operation.getName()); 	
								
				// add OwnedComment to Placeholder Activity identifying it as external
				placeholderActivity.createOwnedComment().setBody("@external");
			    
			    for(Parameter operationParameter : operation.getOwnedParameters()) {
			    	ActivityParameterNode activityParameterNode = UMLFactory.eINSTANCE.createActivityParameterNode();
			    	
			    	// reference the Operation Parameter in the ActivityParameterNode
			    	activityParameterNode.setParameter(operationParameter);
			    	
			    	// add the ActivityParameterNode to the Placeholder Activity
			    	placeholderActivity.getOwnedNodes().add(activityParameterNode);		 
			    }
			    
			    // add OwnedBehavior to Class referencing the Placeholder Activity
				clazz.getOwnedBehaviors().add(placeholderActivity);	
				
				// reference the Placeholder Activity in the Operation
				operation.getMethods().add(placeholderActivity);
			    
			}
		}
	}
	
	/**
	 * Saves the current {@link Resource} as a file to the given output file path
	 * 
	 * @param outputFilePath {@link String} representing the relative file path to the output UML file
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
