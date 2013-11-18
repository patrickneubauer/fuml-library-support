/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

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
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.umlpreparer.UML2Preparer;

import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;

/**
 * Airbus Example Test Case
 * 
 * IMPORTANT: Must be run as a JUnit Plug-in Test Run Configuration: Test runner
 * = "JUnit 4" and run vm arguments include "-javaagent:lib/aspectjweaver.jar"
 * 
 * @author Patrick Neubauer
 * 
 */
public class AirbusExampleTest {

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
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesWithAirplane.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesWithAirplaneConverted.uml";
		String jarFilePath = "extlibs/VehiclesWithAirplane.jar";

		UML2Preparer converter = new UML2Preparer();
		converter.load(inputFilePath);
		converter.convert(jarFilePath);
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
	 * library and a {@link CallOperationAction} on the invoked Object
	 */
	@Test
	public void airbusExampleTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesWithAirplaneConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/airplaneActivity/airbusExample.uml";
		String activityName = "AirbusExampleActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);
		
		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityExitEvent", "AirbusExampleActivity");

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.values.get(0) instanceof Reference);
		
		Reference airplaneReference = (Reference) outputParameterValue.values.get(0);
		Object_ airplane = airplaneReference.referent;
		
		// Check if operation's return value is correct (!)
		assertTrue(((StringValue) airplane.featureValues.get(0).values.get(0)).value.contains("Airbus"));
		assertTrue(((StringValue) airplane.featureValues.get(1).values.get(0)).value.contains("A320-214"));
		assertTrue(((StringValue) airplane.featureValues.get(2).values.get(0)).value.contains("multi-engine"));
		
		assertTrue(((IntegerValue) airplane.featureValues.get(5).values.get(0)).value == 8);
		assertTrue(((IntegerValue) airplane.featureValues.get(6).values.get(0)).value == 150);
		assertTrue(((IntegerValue) airplane.featureValues.get(7).values.get(0)).value == 4);

	}

}
