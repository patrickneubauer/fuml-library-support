/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.fuml.extlib.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.modelexecution.fuml.extlib.plugin.logger.ConsoleLogger;
import org.modelexecution.fuml.extlib.plugin.process.internal.InternalActivityProcess;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.ExecutionEventProvider;
import org.modelexecution.fumldebug.core.event.Event;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class ActivityProcess extends PlatformObject implements IProcess, ExecutionEventProvider {

	private InternalActivityProcess internalActivityProcess;
	private ILaunch launch;
	private String name;
	@SuppressWarnings("rawtypes")
	private Map attributes;

	private List<Event> allEvents = new ArrayList<Event>();
	private ExecutionEventProvider eventEmitter = new ExecutionEventProviderImpl();

	private ConsoleLogger consoleLogger = new ConsoleLogger();
	private boolean isStarted = false;
	private boolean isTerminated = false;

	public ActivityProcess(ILaunch launch, Process process, String name, @SuppressWarnings("rawtypes") Map attributes) {
		setFields(launch, process, name, attributes);
		if (!isInDebugMode()) {
			runActivityProcess();
		}
	}

	private void setFields(ILaunch launch, Process process, String name, @SuppressWarnings("rawtypes") Map attributes) {
		this.launch = launch;
		assertActivityProcess(process);
		this.internalActivityProcess = (InternalActivityProcess) process;
		launch.addProcess(this);
		this.name = name;
		this.attributes = attributes;
	}

	private void assertActivityProcess(Process process) {
		Assert.isTrue(process instanceof InternalActivityProcess, "Process must be of type InternalActivityProcess.");
	}

	private boolean isInDebugMode() {
		return ILaunchManager.DEBUG_MODE.equals(launch.getLaunchMode());
	}

	public void runActivityProcess() {
		this.internalActivityProcess.run();
		
	}

	public List<Event> getAllEvents() {
		return Collections.unmodifiableList(allEvents);
	}

	public Activity getRootActivity() {
		return internalActivityProcess.getRootActivity();
	}

	@Override
	public synchronized boolean canTerminate() {
		return internalActivityProcess != null && isStarted && !isTerminated;
	}

	@Override
	public synchronized boolean isTerminated() {
		return isTerminated;
	}

	public boolean isStarted() {
		return isStarted;
	}

	@Override
	public void terminate() throws DebugException {
		internalActivityProcess.destroy();
		isTerminated = true;
		fireTerminateEvent();
	}

	@Override
	public IStreamsProxy getStreamsProxy() {
		return consoleLogger;
	}

	public String getName() {
		return internalActivityProcess.getActivityName() + " [" //$NON-NLS-1$
				+ internalActivityProcess.getRootExecutionID() + "]"; //$NON-NLS-1$
	}

	public int getRootExecutionId() {
		return internalActivityProcess.getRootExecutionID();
	}

	protected void fireEvent(DebugEvent event) {
		DebugPlugin manager = DebugPlugin.getDefault();
		if (manager != null) {
			manager.fireDebugEventSet(new DebugEvent[] { event });
		}
	}

	protected void fireTerminateEvent() {
		fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
	}

	protected void fireSuspendEvent() {
		fireEvent(new DebugEvent(this, DebugEvent.SUSPEND));
	}

	protected void fireChangeEvent() {
		fireEvent(new DebugEvent(this, DebugEvent.CHANGE));
	}

	@Override
	public synchronized int getExitValue() throws DebugException {
		return InternalActivityProcess.EXIT_VALUE;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter.equals(IProcess.class)) {
			return this;
		}
		if (adapter.equals(IDebugTarget.class)) {
			ILaunch launch = getLaunch();
			IDebugTarget[] targets = launch.getDebugTargets();
			for (int i = 0; i < targets.length; i++) {
				if (this.equals(targets[i].getProcess())) {
					return targets[i];
				}
			}
			return null;
		}
		if (adapter.equals(ILaunch.class)) {
			return getLaunch();
		}
		// CONTEXTLAUNCHING
		if (adapter.equals(ILaunchConfiguration.class)) {
			return getLaunch().getLaunchConfiguration();
		}
		return super.getAdapter(adapter);
	}

	@Override
	public String getLabel() {
		return name;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setAttribute(String key, String value) {
		if (attributes == null) {
			attributes = new HashMap(5);
		}
		Object origVal = attributes.get(key);
		if (origVal != null && origVal.equals(value)) {
			return; // nothing changed.
		}

		attributes.put(key, value);
		fireChangeEvent();
	}

	@Override
	public String getAttribute(String key) {
		if (attributes == null) {
			return null;
		}
		return (String) attributes.get(key);
	}

	@Override
	public void addEventListener(ExecutionEventListener listener) {
		eventEmitter.addEventListener(listener);
	}

	@Override
	public void removeEventListener(ExecutionEventListener listener) {
		eventEmitter.removeEventListener(listener);
	}

	@Override
	public void notifyEventListener(Event event) {
		eventEmitter.notifyEventListener(event);
	}

	

	private class ExecutionEventProviderImpl implements ExecutionEventProvider {
		private Set<ExecutionEventListener> listeners = new HashSet<ExecutionEventListener>();

		@Override
		public void addEventListener(ExecutionEventListener listener) {
			listeners.add(listener);
		}

		@Override
		public void removeEventListener(ExecutionEventListener listener) {
			listeners.remove(listener);
		}

		@Override
		public void notifyEventListener(Event event) {
			for (ExecutionEventListener listener : new ArrayList<ExecutionEventListener>(listeners)) {
				listener.notify(event);
			}
		}
	}

	public Locus getLocus() {
		return this.internalActivityProcess.getLocus();
	}

}
