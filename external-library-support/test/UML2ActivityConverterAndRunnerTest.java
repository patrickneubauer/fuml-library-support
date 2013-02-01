package org.modelexecution.fuml.convert.uml2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.util.ActivityFactory;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

/**
 * This class tests converting a UML file into a fUML Activity and then executing it in the ExecutionContext.
 * 
 * @author p.neubauer
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
	
	private Activity loadActivity(String path, String activityName) {
		return obtainActivity(getResource(path), activityName);
	}
	
	private Resource getResource(String path) {
		return resourceSet.getResource(URI.createFileURI(new File(path).getAbsolutePath()), true);
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
	 * This code shows the steps necessary to create a fUML Activity out of a UML file without execution.
	 * 
	 * @author p.neubauer
	 */
	@Test
	public void createfUMLActivityOutOfUMLFile() {
		String umlFileLocation = "models/VehiclesActivityDiagram.uml";
		String activityName = "TestActivity";
		
		Activity umlActivity = loadActivity(umlFileLocation, activityName);
		
		UML2Converter umlConverter = new UML2Converter();
		IConversionResult umlTofUmlConversionResult = umlConverter.convert(umlActivity);
		
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = umlTofUmlConversionResult.getActivities().iterator().next();
		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);
	}
	
	/**
	 * This code takes a UML file containing one activity only, converts it into a fUML Activity and then executes the Activity in the ExecutionContext.
	 * 
	 * @author p.neubauer
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileContainingOneActivityOnly() {
		Activity umlActivity = loadActivity("models/OneActivityOnlyModel.uml", "TestActivity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator().next();
		
		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, null, new ParameterValueList());
		
		assertEquals(0, eventlist.size());
	}
	
	/**
	 * This code takes a UML file, created with Papyrus, converts it into a fUML Activity and then executes the Activity in the ExecutionContext.
	 * 
	 * @author p.neubauer
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileCreatedWithPapyrus() {
		Activity umlActivity = loadActivity("models/SimplePapyrusCreatedModel.uml", "TestActivity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator().next();
		
		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, null, new ParameterValueList());
	}
	
	/**
	 * This code takes a UML file (activityWithCreateObjectAction/model.uml), converts it into a fUML Activity and then executes the Activity in the ExecutionContext.
	 * 
	 * @author p.neubauer
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileActivityWithCreateObjectAction() {
		Activity umlActivity = loadActivity("models/activityWithCreateObjectAction/model.uml", "TestActivity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator().next();
		
		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, null, new ParameterValueList());
	}
	
	/**
	 * This code takes a UML file (created by MoDisco using reverse engineering), converts it into a fUML Activity and then executes the Activity in the ExecutionContext.
	 * 
	 * @author p.neubauer
	 */
	@Test
	public void createAndExecutefUMLActivityOutOfUMLFileCreatedByReverseEngineering() {
		Activity umlActivity = loadActivity("VehiclesActivityDiagram.uml", "TestActivity");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator().next();
		
		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		org.modelexecution.fumldebug.core.ExecutionContext.getInstance().execute(fUMLActivity, null, new ParameterValueList());
	}
	
}
