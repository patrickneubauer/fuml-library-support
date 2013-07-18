package org.modelexecution.fuml.extlib.ui.launch;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.UML2Preparer;
import org.modelexecution.fuml.extlib.plugin.process.internal.InternalActivityProcess;
import org.modelexecution.fuml.extlib.plugin.process.internal.InternalActivityProcess.Mode;
import org.modelexecution.fuml.extlib.ui.FUMLExtLibPlugin;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityLaunchDelegate extends LaunchConfigurationDelegate {

	private static final String ACTIVITY_EXEC_LABEL = "fUML Activity Execution Process";
	private ResourceSet resourceSet;

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		String resourcePath = getResourcePath(configuration);
		String activityName = getActivityName(configuration);
		IResource iResource = loadResource(resourcePath);
		
		/* TODO This has to be put in a tab like ExtlibSelectionTab */ 
		String inputFilePath = "models/modelsAccessingAnExternalLibrary/Vehicles.uml";
		String externalUmlFilePath = "models/modelsAccessingAnExternalLibrary/VehiclesConverted.uml";
		String jarFilePath = "extlibs/Vehicles.jar";
		
		UML2Preparer preparer = new UML2Preparer();
		preparer.load(inputFilePath);
		preparer.convert(jarFilePath);
		preparer.save(externalUmlFilePath);
		/* finished with extlibs */
		
		org.eclipse.uml2.uml.Activity umlActivity = loadActivity(resourcePath, activityName, externalUmlFilePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		InternalActivityProcess activityProcess = new InternalActivityProcess(
				fUMLActivity, getProcessMode(mode));

		IProcess process = DebugPlugin.newProcess(launch, activityProcess,
				ACTIVITY_EXEC_LABEL);

	}

	private String getResourcePath(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLExtLibPlugin.ATT_RESOURCE,
				(String) null);
	}

	private String getActivityName(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLExtLibPlugin.ATT_ACTIVITY_NAME,
				(String) null);
	}

	private IResource loadResource(String resourcePath) {
		return ResourcesPlugin.getWorkspace().getRoot()
				.findMember(resourcePath);
	}

	private Mode getProcessMode(String mode) {
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			return Mode.DEBUG;
		} else {
			return Mode.RUN;
		}
	}
	
	private org.eclipse.uml2.uml.Activity loadActivity(String path, String activityName, String... furtherPaths) {
		return obtainActivity(getResource(path, furtherPaths), activityName);
	}

	private Resource getResource(String activitypath, String... paths) {
		for (String path : paths) {
			resourceSet.getResource(URI.createFileURI(new File(path).getAbsolutePath()), true);
		}
		return resourceSet.getResource(URI.createFileURI(new File(activitypath).getAbsolutePath()), true);
	}

	private org.eclipse.uml2.uml.Activity obtainActivity(Resource resource, String activityName) {
		for (TreeIterator<EObject> iterator = resource.getAllContents(); iterator.hasNext();) {
			EObject next = iterator.next();
			if (next instanceof Activity) {
				org.eclipse.uml2.uml.Activity activity = (org.eclipse.uml2.uml.Activity) next;
				if (activityName.equals(activity.getName())) {
					return activity;
				}
			}
		}
		return null;
	}

	@Override
	public boolean buildForLaunch(ILaunchConfiguration configuration,
			String mode, IProgressMonitor monitor) throws CoreException {
		return false;
	}

}
