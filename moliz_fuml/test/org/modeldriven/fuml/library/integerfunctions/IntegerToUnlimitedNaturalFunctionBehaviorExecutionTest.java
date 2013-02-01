/*
 * Copyright (c) 2008 Lockheed Martin Corporation.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Public License v1.0
 * which accompanies this distribution, and is available at
 * 
 *
 * Contributors:
 *   MDS - initial API and implementation
 *
 */

package org.modeldriven.fuml.library.integerfunctions;

import org.modeldriven.fuml.library.LibraryTestSetup;
import org.modeldriven.fuml.library.unlimitednaturalfunctions.UnlimitedNaturalConversion;

import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import junit.framework.TestCase;

public class IntegerToUnlimitedNaturalFunctionBehaviorExecutionTest extends TestCase {
	
	ParameterValueList inputParameters;
	ParameterValueList outputParameters;

	/**
	 * Set up the ParameterValueLists to simulate the fUML system before calling
	 * the doBody() method on the library classes.
	 */
	@Override
	public void setUp() {
		LibraryTestSetup libraryTestSetup = new LibraryTestSetup();
		inputParameters = libraryTestSetup.setupInputParameterList();
		outputParameters = libraryTestSetup.setupOutputParameterList();
	}
	
	/**
	 * Tests the doBody() method in the IntegerToIntegerFunctionBehaviorExecution
	 * 
	 * @throws Exception
	 */
	public void testDoBody() throws Exception {
		IntegerConversion.insertOneIntegerIntoParameterValueList(99, inputParameters);
		IntegerToUnlimitedNaturalFunctionBehaviorExecution obj = new IntegerToUnlimitedNaturalFunctionBehaviorExecution();
		obj.doBody(inputParameters, outputParameters);
		assertEquals(99, UnlimitedNaturalConversion.extractUnlimitedNaturalFromParameterValueList(outputParameters));
	}
		
	/**
	 * Tests the doBody() method in the IntegerToIntegerFunctionBehaviorExecution
	 * 
	 * @throws Exception
	 */
	public void testDoBody_negativeValue() throws Exception {
		IntegerConversion.insertOneIntegerIntoParameterValueList(-99, inputParameters);
		IntegerToUnlimitedNaturalFunctionBehaviorExecution obj = new IntegerToUnlimitedNaturalFunctionBehaviorExecution();
		obj.doBody(inputParameters, outputParameters);
		// Because of the invalid negative input arg, the output list should be empty
		assertEquals(0, outputParameters.getValue(0).values.size());
	}	
	
	/**
	 * Tests the new_() method in the IntegerToUnlimitedNaturalFunctionBehaviorExecution
	 * class
	 * 
	 * @throws Exception
	 */
	public void testNew_() throws Exception {
		IntegerToUnlimitedNaturalFunctionBehaviorExecution obj = new IntegerToUnlimitedNaturalFunctionBehaviorExecution();
		IntegerToUnlimitedNaturalFunctionBehaviorExecution newobj = (IntegerToUnlimitedNaturalFunctionBehaviorExecution) obj.new_();
		assertNotSame(obj, newobj);
	}	
}
