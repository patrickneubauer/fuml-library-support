/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageList;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.core.sashwindows.di.util.DiResourceFactoryImpl;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.ConverterRegistry;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.IConverter;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fuml.extlib.UML2Preparer;
import org.modelexecution.fuml.extlib.papyrus.PapyrusModelILExecutor;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.papyrus.util.DiResourceUtil;

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
public class PetstoreCaseStudy implements ExecutionEventListener {

	private ResourceSet resourceSet;
	private List<Event> eventlist = new ArrayList<Event>();
	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();
	
	// -----------------
	
	private static final ConverterRegistry converterRegistry = ConverterRegistry
			.getInstance();
	private static final String PLATFORM_RESOURCE = "platform:/resource";
	private IConversionResult conversionResult;
	private Resource diResource;
	
	// -----------------

	public PetstoreCaseStudy() {
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
		
		String inputFilePath = "models/petstoreCaseStudy/commons-email-1.3.1.uml";
		String outputFilePath = "models/petstoreCaseStudy/commons-email-1.3.1Converted.uml";
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
	
	private void clearLocus(PapyrusModelILExecutor executor) {
		executor.getExecutionContext().removeEventListener(this);
		executor.getExecutionContext().addEventListener(this);
		executor.getExecutionContext().getLocus().extensionalValues.clear();
	}
	
	// -------------------

	/**
	 * Tests {@link CreateObjectAction} that invokes an Object from an external
	 * library and a {@link CallOperationAction} on the invoked Object setting an input value field AND returning an output value
	 */
	@Test
	public void petstoreScenario7Test() {		
		String externalUmlFilePath = "models/petstoreCaseStudy/commons-email-1.3.1Converted.uml";
		String activityDiagramFilePath = "models/petstoreCaseStudy/petstore.uml";
		String activityName = "scenario7";

		Activity umlActivity = loadActivity(activityDiagramFilePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);
				
		// Replace Opaque Behaviors
		replaceOpaqueBehaviors(fUMLActivity);

		// Execute fUML Activity
		integrationLayer.getExecutionContext().execute(fUMLActivity, null, new ParameterValueList());

//		// Check if correct output ParameterValue exists in the
//		// IntegrationLayer's ExecutionContext.activityExecutionOutput
//		ParameterValue outputParameterValue = null;
//
//		for (Event event : eventlist) {
//			if (event.toString().contains("ActivityNodeEntryEvent node = SendCallOperationAction")) {
//				ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
//				outputParameterValue = (ParameterValue) integrationLayer.getExecutionContext()
//						.getActivityOutput(activityNodeEntryEvent.getActivityExecutionID()).get(0);
//			}
//		}
//
//		assertTrue(outputParameterValue != null);
//		assertTrue(outputParameterValue.values.get(0) instanceof StringValue);
//		// Check if operation's return value is correct (!)
//		assertTrue(((StringValue) outputParameterValue.values.get(0)).value.contains("JavaMail")); // otherwise it would throw an exception
	}
	
	@Test
	public void petstoreScenario7PapyrusTest() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);

		Trace trace = executor.executeActivity("scenario7", null, null);
		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
	}

}
