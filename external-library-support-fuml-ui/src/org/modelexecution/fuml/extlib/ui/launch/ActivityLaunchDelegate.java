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
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.modelexecution.fuml.convert.uml2.UML2Converter;
import org.modelexecution.fuml.extlib.UML2Preparer;
import org.modelexecution.fuml.extlib.plugin.process.internal.InternalActivityProcess;
import org.modelexecution.fuml.extlib.plugin.process.internal.InternalActivityProcess.Mode;
import org.modelexecution.fuml.extlib.ui.FUMLExtLibPlugin;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;
import org.modelexecution.fumldebug.debugger.provider.IActivityProviderFactory;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityLaunchDelegate extends LaunchConfigurationDelegate {

	private static final String ACTIVITY_EXEC_LABEL = "fUML ExtLib Activity Execution Process";
	private ResourceSet resourceSet;

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		String activityResourcePath = getActivityResourcePath(configuration);
		String activityName = getActivityName(configuration);
		String classResourcePath = getClassResourcePath(configuration);

		prepareResourceSet();
		
		org.eclipse.uml2.uml.Activity umlActivity = loadActivity(activityResourcePath, activityName, classResourcePath);
		fUML.Syntax.Activities.IntermediateActivities.Activity fUMLActivity = new UML2Converter().convert(umlActivity).getActivities().iterator()
				.next();

		InternalActivityProcess activityProcess = new InternalActivityProcess(
				fUMLActivity, getProcessMode(mode));

		IProcess process = DebugPlugin.newProcess(launch, activityProcess,
				ACTIVITY_EXEC_LABEL);
		
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			/* NOTE: Not yet supported */
		}

	}
	
	private void prepareResourceSet() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	private String getClassResourcePath(ILaunchConfiguration configuration) 
			throws CoreException {
		return configuration.getAttribute(FUMLExtLibPlugin.ATT_CLASS_RESOURCE,
				(String) null);
	}

	private String getActivityResourcePath(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLExtLibPlugin.ATT_ACTIVITY_RESOURCE,
				(String) null);
	}

	private String getActivityName(ILaunchConfiguration configuration)
			throws CoreException {
		return configuration.getAttribute(FUMLExtLibPlugin.ATT_ACTIVITY_NAME,
				(String) null);
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

	private Resource getResource(String activityPath, String... paths) {
		String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().getAbsolutePath();
		for (String path : paths) {
			resourceSet.getResource(URI.createFileURI(new File(workspacePath + path).getAbsolutePath()), true);
		}
		return resourceSet.getResource(URI.createFileURI(new File(workspacePath + activityPath).getAbsolutePath()), true);
	}

	private org.eclipse.uml2.uml.Activity obtainActivity(Resource resource, String activityName) {
		for (TreeIterator<EObject> iterator = resource.getAllContents(); iterator.hasNext();) {
			EObject next = iterator.next();
			if (next instanceof org.eclipse.uml2.uml.Activity) {
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
