/**
 * 
 */
package org.modelexecution.fuml.extlib.casestudy;

import static org.junit.Assert.assertTrue;

import java.io.File;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.umlpreparer.UML2Preparer;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer} on the Database Case Study
 * 
 * IMPORTANT: Must be run as a JUnit Plug-in Test Run Configuration: Test runner
 * = "JUnit 4" and run vm arguments include "-javaagent:lib/aspectjweaver.jar"
 * 
 * INFORMATION: This Case Study will only run if the MongoDB Server on localhost is
 * installed and running. More can be found here: http://docs.mongodb.org/manual/tutorial/
 * 
 * RESULT: This Case Study will not "succeed" as the IntegrationLayer does not support 
 * MULTIPLE COMPLEX INPUT parameters for CallOperationAction as required by:
 * DBCollection.insert(DBObject ... arr)
 * 
 * @author Patrick Neubauer
 * 
 */
public class DatabaseCaseStudyTest {

	private ResourceSet resourceSet;
	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	@Before
	public void setUp() {
		String inputFilePath = "models/DatabaseCaseStudy/mongo-java-driver-2.10.1.uml";
		String outputFilePath = "models/DatabaseCaseStudy/mongo-java-driver-2.10.1Converted.uml";
		String[] jarFilePaths = {"extlibs/mongo-java-driver-2.10.1.jar"};

		UML2Preparer converter = new UML2Preparer();
		converter.load(inputFilePath);
		converter.convert(jarFilePaths);
		converter.save(outputFilePath);		
	}
	
	@After
	public void shutdown() {
		// reset ExecutionContext (preparation for next test)
		integrationLayer.getExecutionContext().reset();
	}

	private Activity loadActivity(String path, String activityName, String... furtherPaths) {
		return obtainActivity(getResource(path, furtherPaths), activityName);
	}

	private Resource getResource(String activitypath, String... paths) {
		for (String path : paths) {
			resourceSet.getResource(URI.createFileURI(new File(path).getAbsolutePath()), true);
		}
		return resourceSet.getResource(URI.createFileURI(new File(activitypath).getAbsolutePath()), true);
	}

	private Activity obtainActivity(Resource resource, String activityName) {
		for (TreeIterator<EObject> iterator = resource.getAllContents(); iterator.hasNext();) {
			EObject next = iterator.next();
			if (next instanceof Activity) {
				Activity activity = (Activity) next;
				if (activityName.equals(activity.getName())) {
					return activity;
				}
			}
		}
		return null;
	}

	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting an input value field AND returning an output value
	 */
	@Test
	public void databaseCaseStudyTest() {		
		String externalUmlFilePath = "models/DatabaseCaseStudy/mongo-java-driver-2.10.1Converted.uml";
		String activityDiagramFilePath = "models/DatabaseCaseStudy/DatabaseActivityModel.uml";
		String activityName = "DatabaseActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityEntryEvent", "DatabaseActivity");

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.parameter.name.equals("outputParameter"));
		
		// Escalated MongoDB Case Study
		// Check output parameter value is empty
		assertTrue(outputParameterValue.values.size()==0);
		
		
	}

}
