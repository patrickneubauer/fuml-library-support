/**
 * 
 */
package org.modelexecution.fuml.extlib.casestudy;

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

import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer} on the Mail Case Study
 * 
 * IMPORTANT: Must be run as a JUnit Plug-in Test Run Configuration: Test runner
 * = "JUnit 4" and run vm arguments include "-javaagent:lib/aspectjweaver.jar"
 * 
 * RESULT: This Case Study will succeed and send an e-mail to the associated
 * e-mail address referenced in the activity model
 * 
 * @author Patrick Neubauer
 * 
 */
public class MailCaseStudyTest implements ExecutionEventListener {

	private ResourceSet resourceSet;
	private List<Event> eventlist = new ArrayList<Event>();
	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();

	public MailCaseStudyTest() {
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
		
		String inputFilePath = "models/MailCaseStudy/commons-email-1.3.1.uml";
		String outputFilePath = "models/MailCaseStudy/commons-email-1.3.1Converted.uml";
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
	public void mailCaseStudyTest() {		
		String externalUmlFilePath = "models/MailCaseStudy/commons-email-1.3.1Converted.uml";
		String activityDiagramFilePath = "models/MailCaseStudy/MailActivityModel.uml";
		String activityName = "MailActivity";

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
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityNodeEntryEvent node = SendCallOperationAction")) {
				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
			}
		}

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.values.get(0) instanceof StringValue);
		// Check if operation's return value is correct (!)
		assertTrue(((StringValue) outputParameterValue.values.get(0)).value.contains("JavaMail")); // otherwise it would throw an exception
	}

}
