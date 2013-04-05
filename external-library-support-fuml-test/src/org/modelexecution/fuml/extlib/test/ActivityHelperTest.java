/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.modelexecution.fuml.extlib.ActionHelper;
import org.modelexecution.fuml.extlib.ActivityHelper;

import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Classes.Kernel.BehavioralFeature;
import fUML.Syntax.Classes.Kernel.Namespace;
import fUML.Syntax.Classes.Kernel.Operation;
import fUML.Syntax.Classes.Kernel.Package;
import fUML.Syntax.Classes.Kernel.Parameter;
import fUML.Syntax.Classes.Kernel.ParameterDirectionKind;
import fUML.Syntax.CommonBehaviors.Communications.Reception;

/**
 * Class that tests the {@link ActivityHelper}
 * 
 * @author Patrick Neubauer
 *
 */
public class ActivityHelperTest {

	@Test
	public void getReturnParameterPositiveTest() throws Exception {
		Activity activity = new Activity();
		Parameter parameter = new Parameter();
		parameter.direction = ParameterDirectionKind.return_;
		activity.ownedParameter.add(parameter);
		
		assertEquals(parameter, ActivityHelper.getReturnParameter(activity));
	}
	
	@Test
	public void getReturnParameterNoReturnParameterTest() {
		boolean thrown = false;		
		Activity activity = new Activity();
		Parameter parameter = new Parameter();
		parameter.direction = ParameterDirectionKind.out;
		activity.ownedParameter.add(parameter);
		
		try {
			ActivityHelper.getReturnParameter(activity);
		} catch(Exception e) {
			assertTrue(e.getMessage().contains("Could not obtain return Parameter of Activity fUML.Syntax.Activities.IntermediateActivities.Activity"));
			thrown = true;
		}
		
		assertEquals(true, thrown);
	}
	
	@Test
	public void getReturnParameterNoParameterTest() {
		boolean thrown = false;		
		Activity activity = new Activity();
		
		try {
			ActivityHelper.getReturnParameter(activity);
		} catch(Exception e) {
			assertTrue(e.getMessage().contains("Could not obtain return Parameter of Activity fUML.Syntax.Activities.IntermediateActivities.Activity"));
			thrown = true;
		}
		
		assertEquals(true, thrown);
	}

	// --------------------------------------------------------------------------------------
	
	@Test
	public void getOperationNamePositiveTest() throws Exception {
		Activity activity = new Activity();
		Operation operation = new Operation();
		operation.name = "toString";
		activity.specification = operation;
		
		assertEquals("toString", ActivityHelper.getOperationName(activity));
	}
	
	@Test
	public void getOperationNameWrongSpecificationTest() {
		boolean thrown = false;
		
		Activity activity = new Activity();
		Reception reception = new Reception();
		reception.name = "";
		activity.specification = reception;
		
		try {
			String returnValue = ActivityHelper.getOperationName(activity);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Could not obtain Operation name of Activity fUML.Syntax.Activities.IntermediateActivities.Activity"));
			thrown = true;
		}
		
		assertEquals(true, thrown);
	}
	
	@Test
	public void getOperationNameEmptyNameTest() {
		boolean thrown = false;
		
		Activity activity = new Activity();
		Operation operation = new Operation();
		operation.name = "";
		activity.specification = operation;
		
		try {
			String returnValue = ActivityHelper.getOperationName(activity);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Could not obtain Operation name of Activity fUML.Syntax.Activities.IntermediateActivities.Activity"));
			thrown = true;
		}
		
		assertEquals(true, thrown);
	}

	// --------------------------------------------------------------------------------------
	
	@Test
	public void getOperationNamespaceAndNamePositiveTest() throws Exception {
		Activity activity = new Activity();
		Operation operation = new Operation();
		Namespace namespace = new Package();
		Namespace namespaceNamespace = new Package();
		
		namespaceNamespace.name = "vehiclelibrary";
		namespace.name = "Ship";
		namespace.namespace = namespaceNamespace;
		operation.name = "toString"; // not necessary
		operation.namespace = namespace;
		activity.specification = operation;
		
		String returnValue = ActivityHelper.getOperationNamespaceAndName(activity);
		
		assertEquals("vehiclelibrary.Ship", returnValue);
	}
	
	@Test
	public void getOperationNamespaceAndNameNoNamespaceNameTest() throws Exception {
		boolean thrown = false;
		
		Activity activity = new Activity();
		Operation operation = new Operation();
		Namespace namespace = new Package();
		Namespace namespaceNamespace = new Package();
		
		namespaceNamespace.name = "vehiclelibrary";

		namespace.namespace = namespaceNamespace;
		operation.name = "toString"; // not necessary
		operation.namespace = namespace;
		activity.specification = operation;
		
		try {
			String returnValue = ActivityHelper.getOperationNamespaceAndName(activity);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(
					"Error occured while trying to obtain the Class name from fUML.Syntax.Activities.IntermediateActivities.Activity"));
			thrown = true;
		}

		assertEquals(true, thrown);
	}
	
	@Test
	public void getOperationNamespaceAndNameNoNamespaceNamespaceTest() throws Exception {
		Activity activity = new Activity();
		Operation operation = new Operation();
		Namespace namespace = new Package();
		
		namespace.name = "Ship";
		operation.name = "toString"; // not necessary
		operation.namespace = namespace;
		activity.specification = operation;

		String returnValue = ActivityHelper.getOperationNamespaceAndName(activity);
		
		assertEquals("Ship", returnValue);
	}
	
	@Test
	public void getOperationNamespaceAndNameNoNamespaceNamespaceNameTest() throws Exception {
		Activity activity = new Activity();
		Operation operation = new Operation();
		Namespace namespace = new Package();
		Namespace namespaceNamespace = new Package();
		
		namespace.name = "Ship";
		namespace.namespace = namespaceNamespace;
		operation.name = "toString"; // not necessary
		operation.namespace = namespace;
		activity.specification = operation;
		
		String returnValue = ActivityHelper.getOperationNamespaceAndName(activity);
		
		assertEquals("Ship", returnValue);
	}

	
	@Test
	public void getOperationNamespaceAndNameNoNamespaceTest() throws Exception {
		boolean thrown = false;
		
		Activity activity = new Activity();
		Operation operation = new Operation();

		operation.name = "toString"; // not necessary
		activity.specification = operation;
		
		try {
			String returnValue = ActivityHelper.getOperationNamespaceAndName(activity);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(
					"Error occured while trying to obtain the Class name from fUML.Syntax.Activities.IntermediateActivities.Activity"));
			thrown = true;
		}

		assertEquals(true, thrown);
	}

}
