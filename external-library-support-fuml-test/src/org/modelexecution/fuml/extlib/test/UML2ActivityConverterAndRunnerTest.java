package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

/**
 * This class tests converting a UML file into a fUML Activity and then
 * executing it in the ExecutionContext
 * 
 * IMPORTANT: Must be run as a JUnit Plug-in Test Run Configuration: Test runner
 * = "JUnit 4" and run vm arguments include "-javaagent:lib/aspectjweaver.jar"
 * 
 * @author Patrick Neubauer
 * 
 */
public class UML2ActivityConverterAndRunnerTest {

	private ResourceSet resourceSet;
	private List<Event> eventlist = new ArrayList<Event>();

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
	 * This code shows the steps necessary to create a fUML Activity out of a
	 * UML file without execution.
	 * 
	 * @author patrickneubauer
	 */
	@Test
	public void createfUMLActivityOutOfUMLFile() {
		String umlFileLocation = "models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesNoInputAndNoReturnValueActivityDiagram.uml";
		String activityName = "NoInputAndNoReturnValueActivity";

		Activity umlActivity = loadActivity(umlFileLocation, activityName);

		UML2Converter umlConverter = new UML2Converter();
		IConversionResult umlTofUmlConversionResult = umlConverter.convert(umlActivity);

		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = umlTofUmlConversionResult.getActivities().iterator().next();
		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);
	}

	/**
	 * This code takes a UML file containing one activity only, converts it into
	 * a fUML Activity and then executes the Activity in the ExecutionContext.
	 * 
	 * @author patrickneubauer
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileContainingOneActivityOnly() {
		Activity umlActivity = loadActivity("models/OneActivityOnlyModel.uml", "OneActivityOnlyModelActivity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, null, new ParameterValueList());

		assertEquals(0, eventlist.size());
	}

	/**
	 * This code takes a UML file, created with Papyrus, converts it into a fUML
	 * Activity and then executes the Activity in the ExecutionContext.
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileCreatedWithPapyrus1() {
		Activity umlActivity = loadActivity("models/SimplePapyrusCreatedModel1.uml", "SimplePapyrusCreatedModel1Activity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, null, new ParameterValueList());
	}

	/**
	 * This code takes a UML file, created with Papyrus, converts it into a fUML
	 * Activity and then executes the Activity in the ExecutionContext.
	 */
	@Test
	@Ignore
	public void createAndExecutefUMLActivityOutOfUMLFileCreatedWithPapyrus2() {
		Activity umlActivity = loadActivity("models/SimplePapyrusCreatedModel2.uml", "SimplePapyrusCreatedModel2Activity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, null, new ParameterValueList());
	}

	/**
	 * This code takes a UML file (activityWithCreateObjectAction/model.uml),
	 * converts it into a fUML Activity and then executes the Activity in the
	 * ExecutionContext.
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileActivityWithCreateObjectAction() {
		Activity umlActivity = loadActivity("models/activityWithCreateObjectAction/model.uml", "ActivityWithCreateObjectActionActivity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// It is possible to create your own fUML object, set it up with values
		// (not done here) and pass it to the ExecutionContext
		Object_ objperson = new Object_();

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, objperson, new ParameterValueList());

		System.out.println(objperson);

	}

	/**
	 * This code takes a UML file (created by MoDisco using reverse
	 * engineering), converts it into a fUML Activity and then executes the
	 * Activity in the ExecutionContext.
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileCreatedByReverseEngineering() {
		Activity umlActivity = loadActivity("models/modelsAccessingAnExternalLibrary/activityWithPrimitiveValues/VehiclesNoInputAndNoReturnValueActivityDiagram.uml", "NoInputAndNoReturnValueActivity", "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		// It is possible to create your own fUML object, set it up with values
		// (not done here) and pass it to the ExecutionContext
		Object_ objcar = new Object_();

		ExecutionContext executionContext = ExecutionContext.getInstance();

		IntegrationLayer integrationLayer = new IntegrationLayerImpl();

		executionContext.addEventListener(integrationLayer);
		executionContext.execute(fUMLActivity, objcar, new ParameterValueList());

		System.out.println(objcar);

	}

}
