/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.umlpreparer.UML2Preparer;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Actions.BasicActions.CallOperationAction;
import fUML.Syntax.Actions.IntermediateActions.AddStructuralFeatureValueAction;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.StructuralFeature;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer}
 * 
 * IMPORTANT: Must be run as a JUnit Plug-in Test Run Configuration: Test runner
 * = "JUnit 4" and run vm arguments include "-javaagent:lib/aspectjweaver.jar"
 * 
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerIT {

	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();

	@Before
	public void setUp() {
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
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object
	 */
	@Test
	public void externalCreateObjectActionAndCallOperationActionNewTest() {
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithExternalCreateObjectActionAndCallOperationAction/VehiclesActivityDiagram.uml";
		String activityName = "ShipActivity1";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
	 * library and a {@link CallOperationAction} on the invoked Object
	 */
	@Test
	public void externalCreateObjectActionAndCallOperationActionTest() {
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithExternalCreateObjectActionAndCallOperationAction/VehiclesActivityDiagram.uml";
		String activityName = "ShipActivity1";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "StringReturnValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityNodeEntryEvent", "ToStringCallOperationAction");

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "IntegerReturnValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityNodeEntryEvent", "GetLengthCallOperationAction");

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveReturnValueActivityDiagram.uml";
		String activityName = "BooleanReturnValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityNodeEntryEvent", "IsOceanLinerCallOperationAction");

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "BooleanInputValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "IntegerInputValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputValueActivityDiagram.uml";
		String activityName = "StringInputValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesMultiplePrimitiveInputValueActivityDiagram.uml";
		String activityName = "MultipleInputValueOperationCallActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
	 * library and an inherited {@link CallOperationAction} on the invoked Object setting an input value field
	 */
	@Test
	public void inputValueFromInheritedExternalCallOperationActionTest() {
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputValueToInheritedOperationActivityDiagram.uml";
		String activityName = "InputValueInheritedOperationCallActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
	
		assertEquals("seats", fUmlObject.getFeatureValues().get(4).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(4).values.get(0) instanceof IntegerValue);
		assertEquals(50, ((IntegerValue) fUmlObject.getFeatureValues().get(4).values.get(0)).value);
	}
	
	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting an input value field AND returning an output value
	 */
	@Test
	public void integerInputValueAndBooleanReturnValueFromExternalCallOperationActionTest() {
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesPrimitiveInputAndReturnValueActivityDiagram.uml";
		String activityName = "IntegerInputAndBooleanReturnValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityNodeEntryEvent", "SetCurrentWarpSpeedCallOperationAction");

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesNoInputAndNoReturnValueActivityDiagram.uml";
		String activityName = "NoInputAndNoReturnValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithComplexValues/VehiclesComplexReturnValueActivityDiagram.uml";
		String activityName = "SimpleEngineReturnValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("engine", fUmlObject.getFeatureValues().get(0).feature.name);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityNodeEntryEvent", "GetEngineCallOperationAction");

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithComplexValues/VehiclesComplexReturnValueActivityDiagram.uml";
		String activityName = "SimpleEngineReturnThisValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		assertEquals("SimpleEngine", fUmlObject.types.get(0).name);
		assertEquals("horsePower", fUmlObject.getFeatureValues().get(0).feature.name);
		assertEquals("vendor", fUmlObject.getFeatureValues().get(1).feature.name);

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityNodeEntryEvent", "GetEngineCallOperationAction");

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
	public void simpleEngineInputValueFromExternalCallOperationActionTest() {
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithComplexValues/VehiclesComplexInputValueActivityDiagram.uml";
		String activityName = "SimpleEngineInputValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

		Locus locus = integrationLayer.getExecutionContext().getLocus();
		Object_ fUmlObject = (Object_) locus.extensionalValues.get(0);

		// Check if the fUmlObject is a Car with a SimpleEngine having horsePower equal to 100
		assertEquals("Car", fUmlObject.types.get(0).name);
		assertEquals("engine", fUmlObject.getFeatureValues().get(0).feature.name);
		assertTrue(fUmlObject.getFeatureValues().get(0).feature instanceof Property);
		
		Property simpleEngineProperty = (Property) fUmlObject.getFeatureValues().get(0).feature;
		assertEquals("SimpleEngine", simpleEngineProperty.class_.name);
		
		Object_ simpleEngineObject_ = (Object_)fUmlObject.getFeatureValues().get(0).values.get(0);
		assertEquals(100, ((IntegerValue) simpleEngineObject_.featureValues.get(0).values.get(0)).value);
		assertEquals("NoVendor", ((StringValue) simpleEngineObject_.featureValues.get(1).values.get(0)).value);

	}
	
	/**
	 * Tests {@link AddStructuralFeatureValueAction} that sets the value, specifically a {@link BooleanValue} of a 
	 * {@link StructuralFeature} of an Object from an external library. It equals the Java semantic of setting a variable value.
	 */
	@Test
	public void setBooleanVariableValueUsingAddStructuralFeatureValueActionTest() {
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithStructuralFeatureValueActions/VehiclesSetVariableValueActivityDiagram.uml";
		String activityName = "VehiclesSetBooleanVariableValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithStructuralFeatureValueActions/VehiclesSetVariableValueActivityDiagram.uml";
		String activityName = "VehiclesSetIntegerVariableValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
		String libraryModel = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String umlModel = "models/modelsAccessingAnExternalLibrary/activityWithStructuralFeatureValueActions/VehiclesSetVariableValueActivityDiagram.uml";
		String activityName = "VehiclesSetStringVariableValueActivity";

		integrationLayer.loadActivity(libraryModel, activityName, umlModel);
		integrationLayer.executeActivity(null, new ParameterValueList());

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
