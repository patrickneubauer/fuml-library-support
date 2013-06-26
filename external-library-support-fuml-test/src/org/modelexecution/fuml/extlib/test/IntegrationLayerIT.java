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
import org.junit.Ignore;
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
import fUML.Semantics.Classes.Kernel.Reference;
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
		
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/Vehicles.uml";
		String outputFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";

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
	public void externalCreateObjectActionAndCallOperationActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithExternalCreateObjectActionAndCallOperationAction/VehiclesActivityDiagram.uml";
		String activityName = "ShipActivity1";

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
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "StringReturnValueActivity";

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
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "IntegerReturnValueActivity";

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
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "BooleanReturnValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
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
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "BooleanInputValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
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
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "IntegerInputValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
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
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "StringInputValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
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
	public void multipleInputValueFromExternalCallOperationActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesMultiplePrimitiveInputValueActivityDiagram.uml";
		String activityName = "MultipleInputValueOperationCallActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
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
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting an input value field AND returning an output value
	 */
	@Test
	public void integerInputValueAndBooleanReturnValueFromExternalCallOperationActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputAndReturnValueActivityDiagram.uml";
		String activityName = "IntegerInputAndBooleanReturnValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);
		
		assertEquals("name", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);
		
		assertEquals("currentWarpSpeed", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof IntegerValue);
		assertEquals(2, ((IntegerValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);
		
		assertEquals("maxWarpSpeed", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof IntegerValue);
		assertEquals(3, ((IntegerValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
		
		assertEquals("shieldActivation", fUmlObject.getFeatureValues().get(3).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(3).values.get(0) instanceof BooleanValue);
		assertEquals(false, ((BooleanValue) fUmlObject.getFeatureValues().get(3).values.get(0)).value);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityNodeEntryEvent node = SetCurrentWarpSpeedCallOperationAction")) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
			}
		}

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.values.get(0) instanceof BooleanValue);
		// Check if operation's return value is correct (!)
		assertEquals(true, ((BooleanValue) outputParameterValue.values.get(0)).value);
	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting NEITHER an input value NOR returning an output value
	 * and checks if the Object has changed accordingly
	 */
	@Test
	public void noInputValueAndNoReturnValueFromExternalCallOperationActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesNoInputAndNoReturnValueActivityDiagram.uml";
		String activityName = "NoInputAndNoReturnValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);
		
		assertEquals("name", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).values.get(0) instanceof StringValue);
		assertEquals("NoName", ((StringValue) fUmlObject.getFeatureValues().get(0).values.get(0)).value);
		
		assertEquals("currentWarpSpeed", fUmlObject.getFeatureValues().get(1).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(1).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue) fUmlObject.getFeatureValues().get(1).values.get(0)).value);
		
		assertEquals("maxWarpSpeed", fUmlObject.getFeatureValues().get(2).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(2).values.get(0) instanceof IntegerValue);
		assertEquals(3, ((IntegerValue) fUmlObject.getFeatureValues().get(2).values.get(0)).value);
		
		assertEquals("shieldActivation", fUmlObject.getFeatureValues().get(3).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(3).values.get(0) instanceof BooleanValue);
		// shieldActivation should have been set to TRUE with activateShield() method call
		assertEquals(true, ((BooleanValue) fUmlObject.getFeatureValues().get(3).values.get(0)).value);

	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object returning
	 * a complex object (here: an instance of SimpleEngine)
	 */
	@Test
	public void simpleEngineReturnValueFromExternalCallOperationActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithComplexValues/VehiclesComplexReturnValueActivityDiagram.uml";
		String activityName = "SimpleEngineReturnValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute the constructor call of Car
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("engine", fUmlObject.getFeatureValues().get(0).feature.name);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityNodeEntryEvent node = GetEngineCallOperationAction")) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
				break;
			}
		}

		// Check if there is any output ParameterValue
		assertTrue(outputParameterValue != null);
		
		// Check if the return ParameterValue is a Reference
		assertTrue(outputParameterValue.values.get(0) instanceof Reference);
		Reference reference = (Reference) outputParameterValue.values.get(0);
				
		// Check if the Reference is an instance of an fUML Object_
		assertTrue(reference.referent instanceof Object_);
		Object_ referencedObject = (Object_) reference.referent;
		
		// Check if the fUML Object_ has a type (Class_) and if it is the correct one
		assertTrue(referencedObject.types != null);
		assertTrue(referencedObject.types.get(0) != null);		
		assertEquals("SimpleEngine", referencedObject.types.get(0).name);
		
		// Check if the fUML Object_ has the expected featureValues
		assertTrue(referencedObject.featureValues != null && referencedObject.featureValues.get(0) != null && referencedObject.featureValues.get(1) != null);
		
		assertEquals("horsePower", referencedObject.featureValues.get(0).feature.name);
		assertTrue(referencedObject.featureValues.get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue)referencedObject.featureValues.get(0).values.get(0)).value);
		
		assertEquals("vendor", referencedObject.featureValues.get(1).feature.name);
		assertTrue(referencedObject.featureValues.get(1).values.get(0) instanceof StringValue);
		assertEquals("NoVendor", ((StringValue)referencedObject.featureValues.get(1).values.get(0)).value);

	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object returning
	 * a complex object (here: an instance of SimpleEngine) by calling "return this()".
	 */
	@Test
	public void simpleEngineReturnThisValueFromExternalCallOperationActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithComplexValues/VehiclesComplexReturnValueActivityDiagram.uml";
		String activityName = "SimpleEngineReturnThisValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute the constructor call of Car
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("SimpleEngine", fUmlObject.types.get(0).name);
		assertEquals("horsePower", fUmlObject.getFeatureValues().get(0).feature.name);
		assertEquals("vendor", fUmlObject.getFeatureValues().get(1).feature.name);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityNodeEntryEvent node = GetEngineCallOperationAction")) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
				break;
			}
		}

		// Check if there is any output ParameterValue
		assertTrue(outputParameterValue != null);
		
		// Check if the return ParameterValue is a Reference
		assertTrue(outputParameterValue.values.get(0) instanceof Reference);
		Reference reference = (Reference) outputParameterValue.values.get(0);
				
		// Check if the Reference is an instance of an fUML Object_
		assertTrue(reference.referent instanceof Object_);
		Object_ referencedObject = (Object_) reference.referent;
		
		// Check if the fUML Object_ has a type (Class_) and if it is the correct one
		assertTrue(referencedObject.types != null);
		assertTrue(referencedObject.types.get(0) != null);		
		assertEquals("SimpleEngine", referencedObject.types.get(0).name);
		
		// Check if the fUML Object_ has the expected featureValues
		assertTrue(referencedObject.featureValues != null && referencedObject.featureValues.get(0) != null && referencedObject.featureValues.get(1) != null);
		
		assertEquals("horsePower", referencedObject.featureValues.get(0).feature.name);
		assertTrue(referencedObject.featureValues.get(0).values.get(0) instanceof IntegerValue);
		assertEquals(0, ((IntegerValue)referencedObject.featureValues.get(0).values.get(0)).value);
		
		assertEquals("vendor", referencedObject.featureValues.get(1).feature.name);
		assertTrue(referencedObject.featureValues.get(1).values.get(0) instanceof StringValue);
		assertEquals("NoVendor", ((StringValue)referencedObject.featureValues.get(1).values.get(0)).value);

	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object returning
	 * a complex object (here: an instance of SimpleEngine)
	 */
	@Test
	@Ignore
	public void simpleEngineInputValueFromExternalCallOperationActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithComplexValues/VehiclesComplexInputValueActivityDiagram.uml";
		String activityName = "SimpleEngineInputValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute the constructor call of Car
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		// TODO Check if the fUmlObject contains the SimpleEngine instance as set by setEngine Operation
		// First, the bug in fUML.Semantics.Classes.Kernel.StructuredValue.createFeatureValues() must be fixed
		// The bug causes that "this.getTypes()", if no containing types, still has size = 1 and then the following lines cause a NullPointerException

	}
	
	/**
	 * Tests {@link AddStructuralFeatureValueAction} that sets the value, specifically a {@link BooleanValue} of a 
	 * {@link StructuralFeature} of an Object from an external library. It equals the Java semantic of setting a variable value.
	 */
	@Test
	public void setBooleanVariableValueUsingAddStructuralFeatureValueActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithStructuralFeatureValueActions/VehiclesSetVariableValueActivityDiagram.uml";
		String activityName = "VehiclesSetBooleanVariableValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute the constructor call of Car
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		// Check if the fUmlObject is a Ship with name specified in the AddStructuralFeatureValueAction
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
	 * Tests {@link AddStructuralFeatureValueAction} that sets the value, specifically a {@link IntegerValue} of a 
	 * {@link StructuralFeature} of an Object from an external library. It equals the Java semantic of setting a variable value.
	 */
	@Test
	public void setIntegerVariableValueUsingAddStructuralFeatureValueActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithStructuralFeatureValueActions/VehiclesSetVariableValueActivityDiagram.uml";
		String activityName = "VehiclesSetIntegerVariableValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute the constructor call of Car
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		// Check if the fUmlObject is a Ship with name specified in the AddStructuralFeatureValueAction
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
	 * Tests {@link AddStructuralFeatureValueAction} that sets the value, specifically a {@link StringValue} of a 
	 * {@link StructuralFeature} of an Object from an external library. It equals the Java semantic of setting a variable value.
	 */
	@Test
	public void setStringVariableValueUsingAddStructuralFeatureValueActionTest() {
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String activityDiagramFilePath = "models/modelsAccessingAnExternalLibrary/activityWithStructuralFeatureValueActions/VehiclesSetVariableValueActivityDiagram.uml";
		String activityName = "VehiclesSetStringVariableValueActivity";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// Execute the constructor call of Car
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		// Check if the fUmlObject is a Ship with name specified in the AddStructuralFeatureValueAction
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
	
	
	

}
