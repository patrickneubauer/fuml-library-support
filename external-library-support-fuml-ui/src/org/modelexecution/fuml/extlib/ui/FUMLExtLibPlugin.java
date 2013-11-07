/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.extlib.ui;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class FUMLExtLibPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.modelexecution.fuml.extlib.plugin"; //$NON-NLS-1$
	private final static String ICONS_PATH = "icons/"; //$NON-NLS-1$
	public final static String IMG_EXTLIB_ACTIVITY = "IMG_EXTLIB_ACTIVITY"; //$NON-NLS-1$
	
	public static final String PROCESS_FACTORY_ID = "org.modelexecution.fuml.extlib.activityProcessFactory";
	public static final String ATT_ACTIVITY_RESOURCE = "ATT_ACTIVITY_RESOURCE"; //$NON-NLS-1$
	public static final String ATT_ACTIVITY_NAME = "ATT_NAME"; //$NON-NLS-1$
	public static final String ATT_CLASS_RESOURCE = "ATT_CLASS_RESOURCE";
	
	private static BundleContext context;
	
	// The shared instance
	private static FUMLExtLibPlugin plugin;

	// Images
	public final static String IMG_EXTLIB_ACTIVITY_LAUNCH = "IMG_EXTLIB_ACTIVITY_LAUNCH"; //$NON-NLS-1$

	public FUMLExtLibPlugin() {
		super();
		setDefault(this);
	}
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext context) throws Exception {
		FUMLExtLibPlugin.context = context;
	}

	public void stop(BundleContext context) throws Exception {
		FUMLExtLibPlugin.context = null;
	}

	private static void setDefault(FUMLExtLibPlugin plugin_) {
		plugin = plugin_;
	}
	
	public static FUMLExtLibPlugin getDefault() {
		return plugin;
	}
	
	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}
	
	public static void log(Throwable t) {
		IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, t.getMessage(), t);
		log(status);
	}

	protected void initializeImageRegistry(ImageRegistry reg) {
		declareImage(IMG_EXTLIB_ACTIVITY_LAUNCH, ICONS_PATH + "ExtLibActivityLaunch.png"); //$NON-NLS-1$
	}

	private void declareImage(String key, String path) {
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
		if (path != null) {
			URL iconURL = FileLocator.find(bundle, new Path(path), null);
			if (iconURL != null) {
				ImageDescriptor descriptor = ImageDescriptor
						.createFromURL(iconURL);
				super.getImageRegistry().put(key, descriptor);
			}
		}
	}

	public static Image getImage(String key) {
		return getDefault().getImageRegistry().get(key);
	}
}
