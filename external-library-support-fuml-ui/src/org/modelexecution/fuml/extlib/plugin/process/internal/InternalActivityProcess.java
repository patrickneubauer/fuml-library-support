/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 * Patrick Neubauer - modified version that uses the IntegrationLayer's ExecutionContext
 */
package org.modelexecution.fuml.extlib.plugin.process.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.modelexecution.fuml.extlib.IntegrationLayerImpl;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.event.ExtensionalValueEvent;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Activities.IntermediateActivities.Activity;

public class InternalActivityProcess extends Process implements ExecutionEventListener {

	public enum Mode {
		DEBUG, RUN;
	}

	public static final int EXIT_VALUE = 0;

	private IntegrationLayerImpl integrationLayer = new IntegrationLayerImpl();
	private List<Event> eventlist = new ArrayList<Event>();
	
	private Queue<Event> eventQueue;
	
	private int rootExecutionID = -1;
	
	private Activity activity;
	public InternalActivityProcess(Activity activity, Mode mode) {
		this.activity = activity;
		integrationLayer.getExecutionContext().addEventListener(this);
	}

	public void run() {
		initialize();
		integrationLayer.getExecutionContext().execute(activity, null, new ParameterValueList());
	}
	
	public void initialize() {
		eventQueue = new LinkedList<Event>();
	}
	
	@Override
	public void notify(Event event) {
		if (!(event instanceof ExtensionalValueEvent)) {
			eventlist.add(event);
		}
	}

	@Override
	public OutputStream getOutputStream() {
		return new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				// we don't need anything from outside using this stream
				// as communication is done directly using commands
			}
		};
	}

	@Override
	public InputStream getInputStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				// we don't communicate via input stream
				// logging is done by ActivityProcess based on the events
				return 0;
			}
		};
	}

	@Override
	public InputStream getErrorStream() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				// we don't communicate via input stream
				// logging is done by ActivityProcess based on the events
				return 0;
			}
		};
	}

	@Override
	public int waitFor() throws InterruptedException {
		return EXIT_VALUE;
	}

	@Override
	public int exitValue() {
		return EXIT_VALUE;
	}

	@Override
	public void destroy() {
		terminate();
	}
	
	public void terminate() {
		setShouldTerminate(true);
		stopListeningToContext();
		integrationLayer.getExecutionContext().terminate(rootExecutionID);
	}
	
	private void stopListeningToContext() {
		removeExecutionEventListener(this);
	}
	
	private void setShouldTerminate(boolean terminated) {
	}
	
	public void removeExecutionEventListener(ExecutionEventListener listener) {
		integrationLayer.getExecutionContext().removeEventListener(listener);
	}

	public Locus getLocus() {
		return integrationLayer.getExecutionContext().getLocus();
	}
	
	public int getRootExecutionID() {
		return rootExecutionID;
	}
	
	public String getActivityName() {
		return activity.qualifiedName;
	}
	
	public Activity getRootActivity() {
		return activity;
	}
	
	public boolean isFinalActivityExitEvent(Event event) {
		return event instanceof ActivityExitEvent && rootExecutionID == ((ActivityExitEvent) event).getActivityExecutionID();
	}
	

	public List<Event> pollEvents() {
		List<Event> eventList = new ArrayList<Event>(eventQueue);
		eventQueue.clear();
		return eventList;
	}
	
}
