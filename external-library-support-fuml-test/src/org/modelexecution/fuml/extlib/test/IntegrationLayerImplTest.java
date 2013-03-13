/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.modelexecution.fuml.extlib.IntegrationLayer;
import org.modelexecution.fuml.extlib.IntegrationLayerImpl;

/**
 * @author Patrick Neubauer
 * 
 */
public class IntegrationLayerImplTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void correctConstructorTest() {
		IntegrationLayer integrationLayer = new IntegrationLayerImpl("path1",
				"path2", "path3");
		assertTrue(integrationLayer instanceof IntegrationLayer);
	}

	@Test
	public void defaultConstructorTest() throws Exception {
		exception.expect(Exception.class);
		IntegrationLayer integrationLayer = new IntegrationLayerImpl();
		assertTrue(integrationLayer instanceof IntegrationLayer);
	}

}
