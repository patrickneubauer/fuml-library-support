/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.modelexecution.fuml.extlib.UML2Preparer;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer}
 * 
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerIT implements ExecutionEventListener {

	private ResourceSet resourceSet;
	private List<Event> eventlist = new ArrayList<Event>();
	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();

	public IntegrationLayerIT() {
		integrationLayer.getExecutionContext().addEventListener(this);
	}

	@Override
	public void notify(Event event) {
		if (!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
		}
	}

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	@Before
	public void setUp() {
		eventlist = new ArrayList<Event>();
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
	public void externalCreateObjectActionAndCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithExternalCreateObjectActionAndCallOperationAction/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithExternalCreateObjectActionAndCallOperationAction/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithExternalCreateObjectActionAndCallOperationAction/VehiclesActivityDiagram.uml";
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

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

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
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object returning
	 * a {@link StringValue}
	 */
	@Test
	public void stringReturnValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
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

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);

		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);

		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityNodeEntryEvent node = ToStringCallOperationAction")) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
			}
		}

		assertTrue(outputParameterValue != null);
 		assertEquals("This ocean liner ship, named NoName, with a length of 0 ft and has 4 seats, 4 doors.",
				((StringValue) outputParameterValue.values.get(0)).value);

	}

	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object returning
	 * a {@link IntegerValue}
	 */
	@Test
	public void integerReturnValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
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

		// Execute the constructor call of Ship()
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);

		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);

		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityNodeEntryEvent node = GetLengthCallOperationAction")) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
			}
		}

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) outputParameterValue.values.get(0)).value);

	}

	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object returning
	 * a {@link BooleanValue}
	 */
	@Test
	public void booleanReturnValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveReturnValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
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

		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);

		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);

		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityNodeEntryEvent node = IsOceanLinerCallOperationAction")) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
			}
		}

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) outputParameterValue.values.get(0)).value);

	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting a {@link BooleanValue} field
	 */
	@Test
	public void booleanInputValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "BooleanInputValueActivity";

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

		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);

		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);

		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(false, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);

	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting a {@link IntegerValue} field
	 */
	@Test
	public void integerInputValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "IntegerInputValueActivity";

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

		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(3500, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);

		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);

		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);

	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting a {@link StringValue} field
	 */
	@Test
	public void stringInputValueFromExternalCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveInputValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "StringInputValueActivity";

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

		
		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);

		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("Titanic", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);

		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting multiple input value fields
	 */
	@Test
	public void multipleInputValueOperationCallFromExternalCallOperationActionTest() {
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithMultiplePrimitiveInputValues/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/activityWithMultiplePrimitiveInputValues/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithMultiplePrimitiveInputValues/VehiclesMultiplePrimitiveInputValueActivityDiagram.uml";
		String activityName = "MultipleInputValueOperationCallActivity";

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

		
		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("length", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof IntegerValue);
		assertEquals(3500, ((IntegerValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);

		assertEquals("name", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof StringValue);
		assertEquals("Titanic", ((StringValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);

		assertEquals("oceanLiner", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof BooleanValue);
		assertEquals(false, ((BooleanValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
	}

}
