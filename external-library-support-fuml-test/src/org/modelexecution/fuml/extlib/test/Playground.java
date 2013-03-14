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
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;

/**
 * Playground
 * 
 * @author Patrick Neubauer
 * 
 */
public class Playground {

	private ResourceSet resourceSet;
	private List<Event> eventlist = new ArrayList<Event>();

	@Before
	public void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI,
				UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	private Activity loadActivity(String path, String activityName,
			String... furtherPaths) {
		return obtainActivity(getResource(path, furtherPaths), activityName);
	}

	private Resource getResource(String activitypath, String... paths) {
		for (String path : paths) {
			resourceSet.getResource(
					URI.createFileURI(new File(path).getAbsolutePath()), true);
		}
		return resourceSet.getResource(
				URI.createFileURI(new File(activitypath).getAbsolutePath()),
				true);
	}

	private Activity obtainActivity(Resource resource, String activityName) {
		for (TreeIterator<EObject> iterator = resource.getAllContents(); iterator
				.hasNext();) {
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
	 * This code takes a UML file (created by MoDisco using reverse
	 * engineering), converts it into a fUML Activity and then executes the
	 * Activity in the ExecutionContext.
	 */
	@Test
	public void play() {
		Activity umlActivity = loadActivity(
				"models/VehiclesActivityDiagram.uml", "VehiclesActivity",
				"models/VehiclesConverted.uml");
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter()
				.convert(umlActivity).getActivities().iterator().next();

		Assert.assertEquals(umlActivity.getName(), fUMLActivity.name);
		Assert.assertEquals(umlActivity.isAbstract(), fUMLActivity.isAbstract);
		Assert.assertEquals(umlActivity.isActive(), fUMLActivity.isActive);

		Object_ objcar = new Object_();

		ExecutionContext executionContext = org.modelexecution.fumldebug.core.ExecutionContext
				.getInstance();

		IntegrationLayer integrationLayer = new IntegrationLayerImpl("path1",
				"path2", "path3");
		
		// execute CreateObjectAction
		integrationLayer.getExecutionContext().execute(fUMLActivity, objcar, new ParameterValueList());

		System.out.println(objcar);

	}

}
