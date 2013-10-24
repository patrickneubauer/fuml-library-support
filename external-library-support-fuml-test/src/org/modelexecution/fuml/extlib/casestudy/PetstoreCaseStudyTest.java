/**
 * 
 */
package org.modelexecution.fuml.extlib.casestudy;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.UML2Preparer;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;

import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Actions.BasicActions.CallBehaviorAction;
import fUML.Syntax.Activities.CompleteStructuredActivities.StructuredActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.DecisionNode;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import fUML.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;

/**
 * Integration Test (IT) Class for {@link IntegrationLayer} on the Petstore Case Study
 * 
 * @author Patrick Neubauer
 * 
 */
public class PetstoreCaseStudyTest implements ExecutionEventListener {

	private ResourceSet resourceSet;
	private List<Event> eventlist = new ArrayList<Event>();
	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();

	public PetstoreCaseStudyTest() {
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
	
	// -------------------
	
	private void replaceOpaqueBehaviors(fUML.Syntax.Activities.IntermediateActivities.Activity activity) {
		List<ActivityNode> nodesWithBehavior = new ArrayList<ActivityNode>();
		nodesWithBehavior.addAll(getBehaviorNodes(activity.node));

		for (ActivityNode node : nodesWithBehavior) {
			if (node instanceof CallBehaviorAction) {
				CallBehaviorAction callBehaviorAction = (CallBehaviorAction) node;
				Behavior behavior = callBehaviorAction.behavior;
				OpaqueBehavior behaviorReplacement = integrationLayer.getExecutionContext()
						.getOpaqueBehavior(behavior.name);
				if (behaviorReplacement != null) {
					callBehaviorAction.behavior = behaviorReplacement;
				}
			} else if (node instanceof DecisionNode) {
				DecisionNode decision = (DecisionNode) node;
				Behavior behavior = decision.decisionInput;
				OpaqueBehavior behaviorReplacement = integrationLayer.getExecutionContext()
						.getOpaqueBehavior(behavior.name);
				if (behaviorReplacement != null) {
					decision.decisionInput = behaviorReplacement;
				}
			}
		}
	}
	
	private List<ActivityNode> getBehaviorNodes(List<ActivityNode> nodes) {
		List<ActivityNode> nodesWithBehavior = new ArrayList<ActivityNode>();
		for (ActivityNode node : nodes) {
			if (node instanceof CallBehaviorAction) {
				CallBehaviorAction action = (CallBehaviorAction) node;
				nodesWithBehavior.add(action);
			} else if (node instanceof DecisionNode) {
				DecisionNode decision = (DecisionNode) node;
				if (decision.decisionInput != null) {
					nodesWithBehavior.add(decision);
				}
			}
			if (node instanceof StructuredActivityNode) {
				StructuredActivityNode structurednode = (StructuredActivityNode) node;
				nodesWithBehavior.addAll(getBehaviorNodes(structurednode.node));
			}
		}
		return nodesWithBehavior;
	}
	
	// -------------------

	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting an input value field AND returning an output value
	 */
	@Test
	public void petstoreCaseStudyTest() {		
		String externalUmlFilePath = "models/PetstoreCaseStudy/commons-email-1.3.1Converted.uml";
		String activityDiagramFilePath = "models/PetstoreCaseStudy/petstore.uml";
		String activityName = "SendEmail";

		// Convert
		Resource resource = getResource(activityDiagramFilePath, externalUmlFilePath);
		UML2Converter uml2Converter = new UML2Converter();
		IConversionResult conversionResult2 = uml2Converter.convert(resource.getContents().get(0));
		
		// Replace Opaque Behaviors
		Iterator<fUML.Syntax.Activities.IntermediateActivities.Activity> iterator = conversionResult2.getActivities().iterator();
		while(iterator.hasNext()) {
			fUML.Syntax.Activities.IntermediateActivities.Activity activity = iterator.next();
			replaceOpaqueBehaviors(activity);
		}

		// Get Activity
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = conversionResult2.getActivity(activityName);
		
		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

		// Check if correct output ParameterValue exists in the
		// IntegrationLayer's ExecutionContext.activityExecutionOutput
		ParameterValue outputParameterValue = null;

		for (Event event : eventlist) {
			if (event.toString().contains("ActivityEntryEvent activity = SendEmail")) {
				ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
						.getActivityOutput(activityEntryEvent.getActivityExecutionID()).get(0);
			}
		}

		assertTrue(outputParameterValue != null);
		assertTrue(outputParameterValue.values.get(0) instanceof StringValue);
		// Check if operation's return value is correct (!)
		assertTrue(((StringValue) outputParameterValue.values.get(0)).value.contains("JavaMail")); // otherwise it would throw an exception
	}

}
