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

public class PetstoreExecutionTest implements ExecutionEventListener {

	private StringBuffer log = new StringBuffer();

	@After
	public void printLog() {
		System.out.println(log.toString());
		log = new StringBuffer();
	}

	/**
	 * login: Successful login
	 */
	@Test
	public void testScenario1() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);
		Trace trace = executor.executeActivity("scenario1", null, null);

		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(1, output.get(0).values.size());
		Assert.assertEquals(1,
				((IntegerValue) output.get(0).values.get(0)).value);

		Set<Object_> objects = getObjects(executor, "ApplicationController");
		Assert.assertEquals(1, objects.size());
		Object_ applicationController = objects.iterator().next();
		objects = getObjects(executor, "Session");
		Assert.assertEquals(1, objects.size());
		Object_ session = objects.iterator().next();
		Set<Link> links = getLinks(executor, "applicationController_session_1");
		Assert.assertEquals(1, links.size());
		Link sessionlink = links.iterator().next();
		Assert.assertEquals(1, getFeatureValue(sessionlink, "sessions").size());
		Assert.assertEquals(
				session,
				((Reference) getFeatureValue(sessionlink, "sessions").get(0)).referent);
		Assert.assertEquals(1,
				getFeatureValue(sessionlink, "applicationController").size());
		Assert.assertEquals(
				applicationController,
				((Reference) getFeatureValue(sessionlink,
						"applicationController").get(0)).referent);

		Assert.assertEquals(0,
				getFeatureValue(applicationController, "foundCustomer").size());
	}

	/**
	 * login: Unsuccessful login due to wrong password
	 */
	@Test
	public void testScenario2() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);
		Trace trace = executor.executeActivity("scenario2", null, null);

		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(1, output.get(0).values.size());
		Assert.assertEquals(-1,
				((IntegerValue) output.get(0).values.get(0)).value);

		Set<Object_> objects = getObjects(executor, "ApplicationController");
		Assert.assertEquals(1, objects.size());
		Object_ applicationController = objects.iterator().next();
		objects = getObjects(executor, "Session");
		Assert.assertEquals(0, objects.size());
		Set<Link> links = getLinks(executor, "applicationController_session_1");
		Assert.assertEquals(0, links.size());

		Assert.assertEquals(0,
				getFeatureValue(applicationController, "foundCustomer").size());
	}

	/**
	 * findItem: Find single item by name
	 */
	@Test
	public void testScenario3() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);
		Trace trace = executor.executeActivity("scenario3", null, null);

		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(1, output.get(0).values.size());
		Assert.assertTrue(output.get(0).values.get(0) instanceof Reference);
		Object_ item_poodle = ((Reference) output.get(0).values.get(0)).referent;
		Assert.assertEquals("Item", item_poodle.types.get(0).name);
		Assert.assertEquals(1, getFeatureValue(item_poodle, "name").size());
		Assert.assertEquals(
				"Poodle",
				((StringValue) getFeatureValue(item_poodle, "name").get(0)).value);
	}

	/**
	 * findItem: Find two items with same name
	 */
	@Test
	public void testScenario4() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);
		Trace trace = executor.executeActivity("scenario4", null, null);

		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(2, output.get(0).values.size());

		Object_ item_bulldog_1 = ((Reference) output.get(0).values.get(0)).referent;
		Assert.assertEquals("Item", item_bulldog_1.types.get(0).name);
		Assert.assertEquals(1, getFeatureValue(item_bulldog_1, "name").size());
		Assert.assertEquals(
				"Bulldog",
				((StringValue) getFeatureValue(item_bulldog_1, "name").get(0)).value);

		Object_ item_bulldog_2 = ((Reference) output.get(0).values.get(1)).referent;
		Assert.assertEquals("Item", item_bulldog_2.types.get(0).name);
		Assert.assertEquals(1, getFeatureValue(item_bulldog_2, "name").size());
		Assert.assertEquals(
				"Bulldog",
				((StringValue) getFeatureValue(item_bulldog_2, "name").get(0)).value);

		Assert.assertTrue(item_bulldog_1.equals(item_bulldog_2));
		Assert.assertFalse(item_bulldog_1 == item_bulldog_2);
	}

	/**
	 * findItem: Find no item with corresponding name
	 */
	@Test
	public void testScenario5() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);
		Trace trace = executor.executeActivity("scenario5", null, null);

		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(0, output.get(0).values.size());
	}

	/**
	 * addItemToCart: Add first item once, add second item twice
	 */
	@Test
	public void testScenario6() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);
		executor.executeActivity("scenario6", null, null);


		Object_ liz = getCustomerByLogin(executor, "liz");
		Assert.assertNotNull(liz);
 
		Object_ bulldogItem = getItemByName(executor, "Bulldog");
		Assert.assertNotNull(bulldogItem);

		Object_ poodleItem = getItemByName(executor, "Poodle");
		Assert.assertNotNull(poodleItem);
		
		Set<Object_> cartObjects = getObjects(executor, "Cart");
		// there is one cart
		Assert.assertEquals(1, cartObjects.size()); 

		Set<Object_> cartitems = getObjects(executor, "CartItem");
		// there are two cartItems
		Assert.assertEquals(2, cartitems.size()); 				
		
		Set<Link> cartLinks = getLinks(executor, "cart_customer_1");
		// there is one link between Customer and Cart
		Assert.assertEquals(1, cartLinks.size()); 
		
		Set<Link> cartItemLinks = getLinks(executor, "cart_cartItem_1");
		// there are two links between Cart and CartItem
		Assert.assertEquals(2, cartItemLinks.size());  
		
		Set<Object_> lizCarts = getLinkedObjects(executor, "cart_customer_1", liz, "customer");
		// liz has one cart
		Assert.assertEquals(1,  lizCarts.size()); 
		Object_ lizCart = lizCarts.iterator().next();
		
		Set<Object_> lizCartItems = getLinkedObjects(executor, "cart_cartItem_1", lizCart, "cart");
		// liz cart contains two cartItems
		Assert.assertEquals(2, lizCartItems.size()); 
		
		Object_ lizCartItemPoodle = null;
		Object_ lizCartItemBulldog = null;
		
		Iterator<Object_> cartitemsIterator = lizCartItems.iterator();
		while(cartitemsIterator.hasNext()) {
			Object_ cartitem = cartitemsIterator.next();
			Set<Object_> items = getLinkedObjects(executor, "cartItem_item_1", cartitem, "cartItem");
			Assert.assertEquals(1, items.size());
			Object_ item = items.iterator().next();
			if(item.equals(bulldogItem)) {
				lizCartItemBulldog = cartitem;
			} else if(item.equals(poodleItem)) {
				lizCartItemPoodle = cartitem;
			}
		}		
		Assert.assertNotNull(lizCartItemBulldog);
		Assert.assertNotNull(lizCartItemPoodle);
		
		// the quantity of the cartItem for Bulldog is set
		Assert.assertEquals(1, getFeatureValue(lizCartItemBulldog, "quantity").size());
		// liz added 2 Bulldogs to the cart
		Assert.assertEquals(2,
				((IntegerValue) getFeatureValue(lizCartItemBulldog, "quantity") 
						.get(0)).value);
		// the quantity of the cartItem for Poodle is set
		Assert.assertEquals(1, getFeatureValue(lizCartItemPoodle, "quantity").size());
		// liz added 1 Poodle to the cart
		Assert.assertEquals(1,
				((IntegerValue) getFeatureValue(lizCartItemPoodle, "quantity") 
						.get(0)).value);
		
		Set<Object_> orderServices = getObjects(executor, "OrderService");
		Assert.assertEquals(1, orderServices.size());
		Object_ orderService = orderServices.iterator().next();
		// helper variable was cleaned up
		Assert.assertEquals(0,
				getFeatureValue(orderService, "foundCartItem").size()); 
	}

	/**
	 * confirmOrder: Confirm order created in scenario6
	 */
	@Test
	public void testScenario7() {
		PapyrusModelILExecutor executor = new PapyrusModelILExecutor(
				"models/petstoreCaseStudy/petstore.di");
		clearLocus(executor);

		Trace trace = executor.executeActivity("scenario7", null, null);
		ParameterValueList output = executor.getExecutionContext()
				.getActivityOutput(
						trace.getActivityExecutions().get(0)
								.getActivityExecutionID());

		Object_ liz = getCustomerByLogin(executor, "liz");
		Assert.assertNotNull(liz);
		
		Object_ bulldogItem = getItemByName(executor, "Bulldog");
		Assert.assertNotNull(bulldogItem);
		
		Object_ poodleItem = getItemByName(executor, "Poodle");
		Assert.assertNotNull(bulldogItem);
		
		Set<Object_> orderObjects = getObjects(executor, "Order");
		// there is one order
		Assert.assertEquals(1, orderObjects.size()); 
		Object_ order = orderObjects.iterator().next();
		
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(1, output.get(0).values.size());
		Assert.assertTrue(output.get(0).values.get(0) instanceof Reference); 
		// the order corresponds to the output
		Assert.assertEquals(order, ((Reference)output.get(0).values.get(0)).referent); 
		
		Set<Object_> orderLines = getObjects(executor, "OrderLine");
		// there are two orderLines
		Assert.assertEquals(2, orderLines.size()); 
		
		Set<Link> orderLinks = getLinks(executor, "order_customer_1");
		// there is one link between Customer and Order
		Assert.assertEquals(1, orderLinks.size()); 
		
		Set<Link> orderLineLinks = getLinks(executor, "order_orderLine_1");
		// there are two links between Order and OrderLine
		Assert.assertEquals(2, orderLineLinks.size()); 
		
		Set<Object_> lizOrders = getLinkedObjects(executor, "order_customer_1",
				liz, "customer");
		// liz has one order
		Assert.assertEquals(1, lizOrders.size()); 		
		Object_ lizOrder = lizOrders.iterator().next();
		
		Set<Object_> lizOrderLines = getLinkedObjects(executor, "order_orderLine_1", lizOrder, "order");
		// liz order contains two orderLines
		Assert.assertEquals(2, lizOrderLines.size()); 

		Object_ orderLineBulldog = null;
		Object_ orderLinePoodle = null;

		Iterator<Object_> orderLinesIterator = lizOrderLines.iterator();
		while (orderLinesIterator.hasNext()) { 
			Object_ orderLine = orderLinesIterator.next();
			Set<Object_> items = getLinkedObjects(executor,
					"orderLine_item_1", orderLine, "orderLine");
			Assert.assertEquals(1, items.size());
			Object_ item = items.iterator().next();
			if (item.equals(bulldogItem)) {
				orderLineBulldog = orderLine;
			} else if (item.equals(poodleItem)) {
				orderLinePoodle = orderLine;
			}
		}
		Assert.assertNotNull(orderLineBulldog);
		Assert.assertNotNull(orderLinePoodle);

		// the quantity of the orderLine for Bulldog is set
		Assert.assertEquals(1, getFeatureValue(orderLineBulldog, "quantity").size());
		// liz ordered 2 Bulldogs
		Assert.assertEquals(2,
				((IntegerValue) getFeatureValue(orderLineBulldog, "quantity")
						.get(0)).value); 
		// the quantity of the orderLine for Poodle is set
		Assert.assertEquals(1, getFeatureValue(orderLinePoodle, "quantity").size());
		// liz ordered 1 Poodle
		Assert.assertEquals(1,
				((IntegerValue) getFeatureValue(orderLinePoodle, "quantity")
						.get(0)).value); 

		Set<Object_> carts = getObjects(executor, "Cart");
		// no cart exists
		Assert.assertEquals(0, carts.size()); 

		Set<Object_> cartitems = getObjects(executor, "CartItem");
		// no cartItem exists
		Assert.assertEquals(0, cartitems.size()); 		
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
