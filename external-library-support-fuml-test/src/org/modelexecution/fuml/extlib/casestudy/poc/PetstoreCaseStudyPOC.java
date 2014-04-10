/**
 * 
 */
package org.modelexecution.fuml.extlib.casestudy.poc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.core.sashwindows.di.util.DiResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.papyrus.PapyrusModelILExecutor;
import org.modelexecution.fuml.extlib.umlpreparer.UML2Preparer;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.libraryregistry.LibraryRegistry;
import org.modelexecution.fumldebug.libraryregistry.OpaqueBehaviorCallReplacer;

import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer} on the Petstore Case Study Proof-Of-Concept
 * It contains tests that use the IntegrationLayer's ExecutionContext directly and
 * tests that use the IntegrationLayer's ExecutionContext via the PapyrusModelILExecutor
 * 
 * @author Patrick Neubauer
 * 
 */
public class PetstoreCaseStudyPOC implements ExecutionEventListener {

	private ResourceSet resourceSet;
	private List<Event> eventlist = new ArrayList<Event>();
	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();
	private IConversionResult conversionResult;
	
	public PetstoreCaseStudyPOC() {
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
//		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("di", new DiResourceFactoryImpl()); //$NON-NLS-1$
	}

	@Before
	public void setUp() {
		eventlist = new ArrayList<Event>();
		
		String inputFilePath = "models/petstoreCaseStudyPOC/commons-email-1.3.1.uml";
		String outputFilePath = "models/petstoreCaseStudyPOC/commons-email-1.3.1Converted.uml";
		String[] jarFilePaths = {"extlibs/commons-email-1.3.1.jar", "extlibs/mail.jar"};

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

	private Resource getResource(String activitypath, String... paths) {
		for (String path : paths) {
			resourceSet.getResource(URI.createFileURI(new File(path).getAbsolutePath()), true);
		}
		return resourceSet.getResource(URI.createFileURI(new File(activitypath).getAbsolutePath()), true);
	}

	private void clearLocus(PapyrusModelILExecutor executor) {
		executor.getExecutionContext().removeEventListener(this);
		executor.getExecutionContext().addEventListener(this);
		executor.getExecutionContext().getLocus().extensionalValues.clear();
	}
	
	// -------------------

	/**
	 * Tests the Petstore Scenario7 using the IntegrationLayer's ExecutionContext directly
	 */
	@Test
	public void petstoreScenario7Test() {		
		String externalUmlFilePath = "models/petstoreCaseStudyPOC/commons-email-1.3.1Converted.uml";
		String activityDiagramFilePath = "models/petstoreCaseStudyPOC/petstore.uml";
		String activityName = "scenario7";
		
		// Convert
		Resource resource = getResource(activityDiagramFilePath, externalUmlFilePath);
		EcoreUtil.resolveAll(resourceSet);
		UML2Converter uml2Converter = new UML2Converter();
		conversionResult = uml2Converter.convert(resource);
		registerOpaqueBehaviors();
		
		// Get Activity
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = conversionResult.getActivity(activityName);
		
		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityEntryEvent", "scenario7");
		
		assertTrue(outputParameterValue != null);
		
		assertTrue(outputParameterValue.values.get(0) instanceof Reference);
		Reference orderReference = (Reference) outputParameterValue.values.get(0);
		
		assertTrue(orderReference.referent.getTypes().get(0).name.equals("Order"));
		
		assertEquals("orderDate", orderReference.getFeatureValues().get(0).feature.name);
		assertEquals("orderLines", orderReference.getFeatureValues().get(1).feature.name);
		assertEquals("customer", orderReference.getFeatureValues().get(2).feature.name);
	}
	
	/**
	 * Tests the Petstore Scenario7Customer using the IntegrationLayer's ExecutionContext directly
	 */
	@Test
	public void petstoreScenario7CustomerTest() {		
		String externalUmlFilePath = "models/petstoreCaseStudyPOC/commons-email-1.3.1Converted.uml";
		String activityDiagramFilePath = "models/petstoreCaseStudyPOC/petstore.uml";
		String activityName = "scenario7Customer";
		
		// Convert
		Resource resource = getResource(activityDiagramFilePath, externalUmlFilePath);
		EcoreUtil.resolveAll(resourceSet);
		UML2Converter uml2Converter = new UML2Converter();
		conversionResult = uml2Converter.convert(resource);
		registerOpaqueBehaviors();
		
		
		// Get Activity
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = conversionResult.getActivity(activityName);
		
		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityEntryEvent", "scenario7Customer");

		assertTrue(outputParameterValue != null);
		
		assertTrue(outputParameterValue.values.get(0) instanceof Reference);
		Reference customerReference = (Reference) outputParameterValue.values.get(0);
		
		assertTrue(customerReference.referent.getTypes().get(0).name.equals("Customer"));
		
		assertEquals("login", customerReference.getFeatureValues().get(0).feature.name);
		assertEquals("liz", customerReference.getFeatureValues().get(0).values.get(0).toString());
		
		assertEquals("password", customerReference.getFeatureValues().get(1).feature.name);
		assertEquals("liz", customerReference.getFeatureValues().get(1).values.get(0).toString());
		
		assertEquals("email", customerReference.getFeatureValues().get(4).feature.name);
		assertEquals("franzmair1980@gmail.com", customerReference.getFeatureValues().get(4).values.get(0).toString());		
	}
	
	/**
	 * Tests the Petstore Scenario7 using the IntegrationLayer's ExecutionContext via the PapyrusModelILExecutor
	 */
	@Test
	public void petstoreScenario7CustomerPapyrusTest() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudyPOC/petstore.di");
		clearLocus(executor);

		Trace trace = executor.executeActivity("scenario7Customer", null, null);
		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());

		assertTrue(output != null);
		
		assertTrue(output.get(0).values.get(0) instanceof Reference);
		Reference customerReference = (Reference) output.get(0).values.get(0);
		
		assertTrue(customerReference.referent.getTypes().get(0).name.equals("Customer"));
		
		assertEquals("login", customerReference.getFeatureValues().get(0).feature.name);
		assertEquals("liz", customerReference.getFeatureValues().get(0).values.get(0).toString());
		
		assertEquals("password", customerReference.getFeatureValues().get(1).feature.name);
		assertEquals("liz", customerReference.getFeatureValues().get(1).values.get(0).toString());
		
		assertEquals("email", customerReference.getFeatureValues().get(4).feature.name);
		assertEquals("franzmair1980@gmail.com", customerReference.getFeatureValues().get(4).values.get(0).toString());
	}
	
	/**
	 * Tests the Petstore Scenario7 using the IntegrationLayer's ExecutionContext via the PapyrusModelILExecutor
	 */
	@Test
	public void petstoreScenario7PapyrusTest() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudyPOC/petstore.di");
		clearLocus(executor);

		Trace trace = executor.executeActivity("scenario7", null, null);
		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		
		assertTrue(output != null);
		
		assertTrue(output.get(0).values.get(0) instanceof Reference);
		Reference orderReference = (Reference) output.get(0).values.get(0);
		
		assertTrue(orderReference.referent.getTypes().get(0).name.equals("Order"));
		
		assertEquals("orderDate", orderReference.getFeatureValues().get(0).feature.name);
		assertEquals("orderLines", orderReference.getFeatureValues().get(1).feature.name);
		assertEquals("customer", orderReference.getFeatureValues().get(2).feature.name);
	}
	
	private void registerOpaqueBehaviors() {
		LibraryRegistry libraryRegistry = new LibraryRegistry(integrationLayer.getExecutionContext());
		Map<String, OpaqueBehavior> registeredOpaqueBehaviors = libraryRegistry.loadRegisteredLibraries();
		OpaqueBehaviorCallReplacer.instance.replaceOpaqueBehaviorCalls(conversionResult
				.getAllActivities(), registeredOpaqueBehaviors);				
	}

}