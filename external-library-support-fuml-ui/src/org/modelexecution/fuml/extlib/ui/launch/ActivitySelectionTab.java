/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.extlib.ui.launch;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.modelexecution.fuml.extlib.ui.FUMLExtLibPlugin;
import org.modelexecution.fuml.extlib.ui.commons.FUMLLibraryUICommons;
import org.modelexecution.fumldebug.debugger.provider.IActivityProvider;
import org.modelexecution.fumldebug.debugger.provider.IActivityProviderFactory;
import org.modelexecution.fumldebug.ui.commons.provider.ActivityContentProvider;
import org.modelexecution.fumldebug.ui.commons.provider.ActivityLabelProvider;

import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivitySelectionTab extends AbstractLaunchConfigurationTab {

	private Text activityResourceText;
	private Button browseActivityResourceButton;
	private Text classResourceText;
	private Button browseClassResourceButton;
	private Collection<Activity> activities = Collections.emptyList();
	private TreeViewer activityList;
	private Activity selectedActivity = null;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createControl(Composite parent) {
		Font font = parent.getFont();
		Composite comp = createContainerComposite(parent, font);
		createVerticalSpacer(comp, 3);
		
		createActivityResourceSelectionControls(font, comp);		
		createVerticalSpacer(comp, 3);
		
		createClassResourceSelectionControls(font, comp);
		createVerticalSpacer(comp, 10);
		
		createActivitySelectionControls(font, comp);
		createVerticalSpacer(comp, 3);
	}

	private Composite createContainerComposite(Composite parent, Font font) {
		Composite comp = new Composite(parent, SWT.NONE);
		setControl(comp);
		GridLayout topLayout = new GridLayout();
		topLayout.verticalSpacing = 0;
		topLayout.numColumns = 3;
		comp.setLayout(topLayout);
		comp.setFont(font);
		return comp;
	}

	private void createActivityResourceSelectionControls(Font font, Composite comp) {
		createActivityResourceLabel(font, comp);
		createActivityResourceTextControl(font, comp);
		createActivityResourceBrowseButton(comp);
	}
	
	private void createClassResourceSelectionControls(Font font, Composite comp) {
		createClassResourceLabel(font, comp);
		createClassResourceTextControl(font, comp);
		createClassResourceBrowseButton(comp);
	}

	private void createActivityResourceBrowseButton(Composite comp) {
		browseActivityResourceButton = createPushButton(comp, "&Browse", null);
		browseActivityResourceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				browseActivityResource();
			}
		});
	}
	
	private void createClassResourceBrowseButton(Composite comp) {
		browseClassResourceButton = createPushButton(comp, "&Browse", null);
		browseClassResourceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				browseClassResource();
			}
		});
	}

	private void createActivityResourceTextControl(Font font, Composite comp) {
		GridData gd;
		activityResourceText = new Text(comp, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		activityResourceText.setLayoutData(gd);
		activityResourceText.setFont(font);
		activityResourceText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateActivities();
				updateLaunchConfigurationDialog();
			}
		});
	}
	
	private void createClassResourceTextControl(Font font, Composite comp) {
		GridData gd;
		classResourceText = new Text(comp, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		classResourceText.setLayoutData(gd);
		classResourceText.setFont(font);
		classResourceText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				//updateActivities();
				updateLaunchConfigurationDialog();
			}
		});
	}

	private void createActivityResourceLabel(Font font, Composite comp) {
		Label programLabel = new Label(comp, SWT.NONE);
		programLabel.setText("&Activity Resource:");
		GridData gd = new GridData(GridData.BEGINNING);
		programLabel.setLayoutData(gd);
		programLabel.setFont(font);
	}
	
	private void createClassResourceLabel(Font font, Composite comp) {
		Label programLabel = new Label(comp, SWT.NONE);
		programLabel.setText("&Class Resource:");
		GridData gd = new GridData(GridData.BEGINNING);
		programLabel.setLayoutData(gd);
		programLabel.setFont(font);
	}

	protected void browseActivityResource() {
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
		dialog.setTitle("Activity Resource");
		dialog.setMessage("Select a resource to debug");
		if (dialog.open() == Window.OK) {
			Object[] files = dialog.getResult();
			IFile file = (IFile) files[0];
			activityResourceText.setText(file.getFullPath().toString());
		}

	}
	
	protected void browseClassResource() {
		ResourceListSelectionDialog dialog = new ResourceListSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(),
				IResource.FILE);
		dialog.setTitle("Class Resource");
		dialog.setMessage("Select a resource to debug");
		if (dialog.open() == Window.OK) {
			Object[] files = dialog.getResult();
			IFile file = (IFile) files[0];
			classResourceText.setText(file.getFullPath().toString());
		}

	}

	private void createActivitySelectionControls(Font font, Composite comp) {
		Group group = new Group(comp, SWT.BORDER);
		group.setText("Select Activity");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 100;
		gd.horizontalSpan = 3;
		group.setLayoutData(gd);
		GridLayout groupLayout = new GridLayout();
		group.setLayout(groupLayout);
		activityList = new TreeViewer(group, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		activityList.setSorter(new ViewerSorter());
		activityList.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		activityList.setLabelProvider(new ActivityLabelProvider());
		activityList.setContentProvider(new ActivityContentProvider(true));
		activityList.setInput(activities.toArray());
		activityList
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						updateSelectedActivity();
						updateLaunchConfigurationDialog();
					}
				});
	}

	private void updateSelectedActivity() {
		IStructuredSelection selection = (IStructuredSelection) activityList
				.getSelection();
		Object selectedElement = selection.getFirstElement();
		if (selectedElement instanceof Activity) {
			selectedActivity = (Activity) selectedElement;
		} else {
			selectedActivity = null;
		}
	}

	private void updateActivities() {
		IResource iResource = getResource();
		if (existsAndIsSupported(iResource)) {
			IActivityProvider activityProvider = createActivityProvider(iResource);
			activities = activityProvider.getActivities();
			activityProvider.unload();
		} else {
			activities = Collections.emptyList();
		}
		refreshActivityListViewer();
	}

	private boolean existsAndIsSupported(IResource iResource) {
		return exists(iResource)
				&& IActivityProviderFactory.instance.supports(iResource);
	}

	private boolean exists(IResource iResource) {
		return iResource != null && iResource.exists();
	}

	private IActivityProvider createActivityProvider(IResource iResource) {
		return IActivityProviderFactory.instance
				.createActivityProvider(iResource);
	}

	private void refreshActivityListViewer() {
		activityList.setInput(activities.toArray());
		activityList.refresh(true);
		selectedActivity = null;
	}

	protected IResource getResource() {
		return ResourcesPlugin.getWorkspace().getRoot()
				.findMember(activityResourceText.getText());
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}

	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		if (isResourceEmpty()) {
			setErrorMessage("Select a resource containing an activity.");
			return false;
		} else if (isSelectedResourceInvalid()) {
			setErrorMessage("Cannot obtain activities from selected resource.");
			return false;
		} else if (isSelectedResourceValidButNoActivitySelected()) {
			setErrorMessage("Select an activity.");
			return false;
		} else if (isActivitySelected()) {
			setErrorMessage(null);
			setMessage(null);
			return super.isValid(launchConfig);
		}
		return false;
	}

	private boolean isActivitySelected() {
		return selectedActivity != null;
	}

	private boolean isSelectedResourceValidButNoActivitySelected() {
		return isSelectedResourceValid() && !isActivitySelected();
	}

	private boolean isSelectedResourceValid() {
		return !isResourceEmpty() && activities.size() > 0;
	}

	private boolean isSelectedResourceInvalid() {
		return !isSelectedResourceValid();
	}

	private boolean isResourceEmpty() {
		return activityResourceText.getText().isEmpty();
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		String defValActivityResourceText = ""; //$NON-NLS-1$
		String defValActivityName = ""; //$NON-NLS-1$
		String defValClassResourceText = ""; //$NON-NLS-1$
		try {
			defValActivityResourceText = configuration.getAttribute(
					FUMLExtLibPlugin.ATT_ACTIVITY_RESOURCE, (String) null);
			defValClassResourceText = configuration.getAttribute(
					FUMLExtLibPlugin.ATT_CLASS_RESOURCE, (String) null);
			defValActivityName = configuration.getAttribute(
					FUMLExtLibPlugin.ATT_ACTIVITY_NAME, (String) null);
		} catch (CoreException e) {
			setErrorMessage(e.getMessage());
		}

		activityResourceText.setText(defValActivityResourceText == null ? "" //$NON-NLS-1$
				: defValActivityResourceText);
		classResourceText.setText(defValClassResourceText == null ? "" //$NON-NLS-1$
				: defValClassResourceText);
		updateActivities();
		Activity activity = getActivityByName(defValActivityName);
		
		if (activity != null) {
			activityList.setSelection(new StructuredSelection(activity));
		}
	}

	private Activity getActivityByName(String activityName) {
		for (Activity activity : activities) {
			if (activityName.equals(activity.name)) {
				return activity;
			}
		}
		return null;
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(DebugPlugin.ATTR_PROCESS_FACTORY_ID,
				FUMLExtLibPlugin.PROCESS_FACTORY_ID);
		configuration.setAttribute(FUMLExtLibPlugin.ATT_ACTIVITY_RESOURCE,
				activityResourceText.getText().trim());
		configuration.setAttribute(FUMLExtLibPlugin.ATT_CLASS_RESOURCE,
				classResourceText.getText().trim());
		if (selectedActivity != null) {
			configuration.setAttribute(FUMLExtLibPlugin.ATT_ACTIVITY_NAME,
					selectedActivity.name);
		}
	}

	@Override
	public String getName() {
		return "ExtLib Activity";
	}

	@Override
	public Image getImage() {
		return FUMLLibraryUICommons.getImage(FUMLLibraryUICommons.IMG_EXTLIB_ACTIVITY);
	}

}
