/**
 * 
 */
package org.modelexecution.fuml.extlib.casestudy;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.umlpreparer.UML2Preparer;
import org.modelexecution.fumldebug.libraryregistry.LibraryRegistry;
import org.modelexecution.fumldebug.libraryregistry.OpaqueBehaviorCallReplacer;

import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer} on the Petstore Case Study
 * 
 * IMPORTANT: Must be run as a JUnit Plug-in Test Run Configuration: Test runner
 * = "JUnit 4" and run vm arguments include "-javaagent:lib/aspectjweaver.jar"
 * 
 * RESULT: This Case Study will succeed and send an e-mail to the customer
 * that purchased items from the PetStore in scenario7 of activity model.
 * In detail: the scenario7Customer activity is called which itself calls the
 * scenario7 to get the customer Object_ from it. Then the e-mail address of the
 * customer is retrieved and passed to the external mail library operation
 * in order to finally send the e-mail.
 * 
 * @author Patrick Neubauer
 * 
 */
public class PetstoreCaseStudyTest {

	private ResourceSet resourceSet;
	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();
	private IConversionResult conversionResult;
	
	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	@Before
	public void setUp() {
		String inputFilePath = "models/PetstoreCaseStudy/commons-email-1.3.1.uml";
		String outputFilePath = "models/PetstoreCaseStudy/commons-email-1.3.1Converted.uml";
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

	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting an input value field AND returning an output value
	 */
	@Test
	public void petstoreCaseStudyTest() {		
		String externalUmlFilePath = "models/petstoreCaseStudy/commons-email-1.3.1Converted.uml";
		String activityDiagramFilePath = "models/petstoreCaseStudy/petstore.uml";
		String activityName = "SendEmail";

		// Load and execute Activity
		integrationLayer.loadActivity(externalUmlFilePath, activityName, activityDiagramFilePath);
		integrationLayer.executeActivity(null, new ParameterValueList());
		
		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = integrationLayer.getOutputParameterValue("ActivityEntryEvent", "SendEmail");

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.values.get(0) instanceof StringValue);
		// Check if operation's return value is correct (!)
		assertTrue(((StringValue) outputParameterValue.values.get(0)).value.contains("JavaMail")); // otherwise it would throw an exception
	}

}
