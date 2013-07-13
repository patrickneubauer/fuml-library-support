/*
 * Copyright (c) 2013 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.modelexecution.fuml.extlib.papyrus.PapyrusModelILExecutor;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;

import fUML.Semantics.Classes.Kernel.CompoundValue;
import fUML.Semantics.Classes.Kernel.ExtensionalValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Property;

public class PapyrusILTest implements ExecutionEventListener {

	private StringBuffer log = new StringBuffer();

	@After
	public void printLog() {
		System.out.println(log.toString());
		log = new StringBuffer();
	}
	
	@Test
	public void papyrusWithNoExtLibTest() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/activityWithCreateObjectAction/model.di");
		clearLocus(executor);
		Trace trace = executor.executeActivity("ActivityWithCreateObjectActionActivity", null, null);

		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		
		Reference referenceToObject_ = (Reference) output.get(0).values.get(0);
		Object_ referingObject_ = referenceToObject_.referent;
		StringValue stringValue = (StringValue) referingObject_.featureValues.get(0).values.get(0);
		
		assertEquals("Patrick", stringValue.value);
		
	}

	/**
	 * Tests the Integration Layer functionality on models created by the Papyrus Model Editor
	 */
	@Test
	public void shipToString() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/modelsAccessingAnExternalLibrary/papyrusCreated/papyrusTest.di");
		clearLocus(executor);
		Trace trace = executor.executeActivity("PapyrusTestAD", null, null);

		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		
		System.out.println("finished. TODO: check for correct output!");
//		Assert.assertEquals(1, output.size());
//		Assert.assertEquals(1, output.get(0).values.size());
//		Assert.assertEquals(1,
//				((IntegerValue) output.get(0).values.get(0)).value);
//
//		Set<Object_> objects = getObjects(executor, "ApplicationController");
//		Assert.assertEquals(1, objects.size());
//		Object_ applicationController = objects.iterator().next();
//		objects = getObjects(executor, "Session");
//		Assert.assertEquals(1, objects.size());
//		Object_ session = objects.iterator().next();
//		Set<Link> links = getLinks(executor, "applicationController_session_1");
//		Assert.assertEquals(1, links.size());
//		Link sessionlink = links.iterator().next();
//		Assert.assertEquals(1, getFeatureValue(sessionlink, "sessions").size());
//		Assert.assertEquals(
//				session,
//				((Reference) getFeatureValue(sessionlink, "sessions").get(0)).referent);
//		Assert.assertEquals(1,
//				getFeatureValue(sessionlink, "applicationController").size());
//		Assert.assertEquals(
//				applicationController,
//				((Reference) getFeatureValue(sessionlink,
//						"applicationController").get(0)).referent);
//
//		Assert.assertEquals(0,
//				getFeatureValue(applicationController, "foundCustomer").size());
	}


	private Object_ getItemByName(PapyrusModelILExecutor executor, String name) {
		Set<Object_> items = getObjects(executor, "Item");
		Iterator<Object_> itemsIterator = items.iterator();
		while (itemsIterator.hasNext()) {
			Object_ item = itemsIterator.next();
			if (((StringValue) getFeatureValue(item, "name").get(0)).value
					.equals(name)) {
				return item;
			}
		}
		return null;
	}

	private Object_ getCustomerByLogin(PapyrusModelILExecutor executor,
			String customerlogin) {
		Set<Object_> customers = getObjects(executor, "Customer");
		Iterator<Object_> customersIterator = customers.iterator();
		while (customersIterator.hasNext()) {
			Object_ customer = customersIterator.next();
			if (((StringValue) getFeatureValue(customer, "login").get(0)).value
					.equals(customerlogin)) {
				return customer;
			}
		}
		return null;
	}

	private void clearLocus(PapyrusModelILExecutor executor) {
		executor.getExecutionContext().removeEventListener(this);
		executor.getExecutionContext().addEventListener(this);
		executor.getExecutionContext().getLocus().extensionalValues.clear();
	}

	private ValueList getFeatureValue(CompoundValue value, String featurename) {
		for (FeatureValue fv : value.featureValues) {
			if (fv.feature.name.equals(featurename)) {
				return fv.values;
			}
		}
		return null;
	}

	private Set<Object_> getObjects(PapyrusModelILExecutor executor,
			String classifiername) {
		Set<Object_> objects = new HashSet<Object_>();
		for (ExtensionalValue extensionalValue : executor.getExecutionContext()
				.getLocus().extensionalValues) {
			if (extensionalValue.getClass() == Object_.class) {
				Object_ object_ = (Object_) extensionalValue;
				for (Classifier c : object_.getTypes()) {
					if (c.name.equals(classifiername)) {
						objects.add(object_);
					}
				}
			}
		}
		return objects;
	}

	private Set<Link> getLinks(PapyrusModelILExecutor executor,
			String associationname) {
		Set<Link> links = new HashSet<Link>();
		for (ExtensionalValue extensionalValue : executor.getExecutionContext()
				.getLocus().extensionalValues) {
			if (extensionalValue instanceof Link) {
				Link link = (Link) extensionalValue;
				for (Classifier c : link.getTypes()) {
					if (c.name.equals(associationname)) {
						links.add(link);
					}
				}
			}
		}
		return links;
	}

	private Set<Object_> getLinkedObjects(PapyrusModelILExecutor executor,
			String associationname, Object_ knownObject, String knownEndName) {
		Set<Object_> linkedObjects = new HashSet<Object_>();
		Association association = getAssociation(executor, associationname);
		Property knownEnd = getAssociationEnd(association, knownEndName);
		Property otherEnd = getOtherAssociationEnd(association, knownEnd);
		Set<Link> links = getLinks(executor, associationname);
		if (links.size() > 0 && otherEnd != null && knownEnd != null) {
			Iterator<Link> linkIterator = links.iterator();
			while (linkIterator.hasNext()) {
				Link link = linkIterator.next();
				if (getLinkedObject(link, knownEnd).equals(knownObject)) {
					linkedObjects.add(getLinkedObject(link, otherEnd));
				}
			}
		}
		return linkedObjects;
	}

	private Property getAssociationEnd(Association association, String endName) {
		for (Property memberEnd : association.memberEnd) {
			if (memberEnd.name.equals(endName)) {
				return memberEnd;
			}
		}
		return null;
	}

	private Property getOtherAssociationEnd(Association association,
			Property end) {
		for (Property memberEnd : association.memberEnd) {
			if (!end.equals(memberEnd)) {
				return memberEnd;
			}
		}
		return null;
	}

	private Association getAssociation(PapyrusModelILExecutor executor,
			String associationname) {
		Iterator<Link> linksIterator = getLinks(executor, associationname)
				.iterator();
		while (linksIterator.hasNext()) {
			Link link = linksIterator.next();
			if (link.type != null) {
				return link.type;
			}
		}
		return null;
	}

	private Object_ getLinkedObject(Link link, Property end) {
		FeatureValue featureValue = link.getFeatureValue(end);
		for (Value value : featureValue.values) {
			if (value instanceof Object_) {
				return (Object_) value;
			} else if (value instanceof Reference) {
				return ((Reference) value).referent;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.modelexecution.fumldebug.core.ExecutionEventListener#notify(org.
	 * modelexecution.fumldebug.core.event.Event)
	 */
	@Override
	public void notify(Event event) {
		log.append(event.toString());
		log.append(System.getProperty("line.separator"));
	}
}
