/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.UML2Preparer;
import org.modelexecution.fumldebug.core.ExecutionContext;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer}
 * 
 * @author Patrick Neubauer
 *
 */
public class IntegrationLayerIT {

	private ResourceSet resourceSet;

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
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
	 * Tests {@link CreateObjectAction} that invokes an Object from an external library
	 * and a {@link CallOperationAction} on the invoked Object
	 */
	@Test
	public void externalCreateObjectActionAndCallOperationActionTest() {
		String inputFilePath = "models/Vehicles.uml";
		String outputFilePath = "models/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/VehiclesActivityDiagram.uml";
		String activityName = "ShipActivity1";

		UML2Preparer converter = new UML2Preparer();
		converter.load(inputFilePath);
		converter.convert(jarFilePath);
		converter.save(outputFilePath);

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, outputFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		IntegrationLayer integrationLayer = new IntegrationLayerImpl();

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);
		
		System.out.println("");
		
		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);
		
		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);
		
		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
		
	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external library
	 * and a {@link CallOperationAction} on the invoked Object returning a {@link StringValue}
	 */
	@Test
	public void stringReturnValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/Vehicles.uml";
		String outputFilePath = "models/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "StringReturnValueActivity";

		UML2Preparer converter = new UML2Preparer();
		converter.load(inputFilePath);
		converter.convert(jarFilePath);
		converter.save(outputFilePath);

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, outputFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		IntegrationLayer integrationLayer = new IntegrationLayerImpl();

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);
		
		System.out.println("");
		
		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);
		
		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);
		
		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
		
		// TODO Add the return parameter somehow to the executionContext.locus (in IntegrationLayerImpl) and
		// then get it inside this unit test and compare the value
		
	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external library
	 * and a {@link CallOperationAction} on the invoked Object returning a {@link IntegerValue}
	 */
	@Test
	public void integerReturnValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/Vehicles.uml";
		String outputFilePath = "models/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "IntegerReturnValueActivity";

		UML2Preparer converter = new UML2Preparer();
		converter.load(inputFilePath);
		converter.convert(jarFilePath);
		converter.save(outputFilePath);

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, outputFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		IntegrationLayer integrationLayer = new IntegrationLayerImpl();

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);
		
		System.out.println("");
		
		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);
		
		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);
		
		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
		
		// TODO Add the return parameter somehow to the executionContext.locus (in IntegrationLayerImpl) and
		// then get it inside this unit test and compare the value
		
	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external library
	 * and a {@link CallOperationAction} on the invoked Object returning a {@link BooleanValue}
	 */
	@Test
	public void booleanReturnValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/Vehicles.uml";
		String outputFilePath = "models/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "BooleanReturnValueActivity";

		UML2Preparer converter = new UML2Preparer();
		converter.load(inputFilePath);
		converter.convert(jarFilePath);
		converter.save(outputFilePath);

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, outputFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		IntegrationLayer integrationLayer = new IntegrationLayerImpl();

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);
		
		System.out.println("");
		
		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);
		
		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);
		
		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
		
		// TODO Add the return parameter somehow to the executionContext.locus (in IntegrationLayerImpl) and
		// then get it inside this unit test and compare the value
		
	}
	
}
