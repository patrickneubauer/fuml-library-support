/**
 * 
 */
package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.modelexecution.fuml.extlib.FeatureHelper;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Classifier;
import fUML.Syntax.Classes.Kernel.Namespace;
import fUML.Syntax.Classes.Kernel.Package;
import fUML.Syntax.Classes.Kernel.Property;

/**
 * Class that tests the {@link FeatureHelper}
 * 
 * @author Patrick Neubauer
 *
 */
public class FeatureHelperTest {

	@Test
	public void getFeatureValueNamespaceAndClassPositiveTest() throws Exception {
		FeatureValue featureValue = new FeatureValue();
		Property feature = new Property();
		Classifier featuringClassifier = new Class_();
		Namespace namespace = new Package();
		
		namespace.name = "vehiclelibrary";
		featuringClassifier.name = "Ship";
		featuringClassifier.namespace = namespace;
		feature.featuringClassifier.add(featuringClassifier);
		
		featureValue.feature = feature;
		
		String returnValue = FeatureHelper.getFeatureValueNamespaceAndClass(featureValue);
		
		assertEquals("vehiclelibrary.Ship", returnValue);
	}
	
	@Test
	public void getFeatureValueNamespaceAndClassNoClassTest() {
		boolean thrown = false;
		
		FeatureValue featureValue = new FeatureValue();
		Property feature = new Property();
		
		featureValue.feature = feature;
		
		try {
			String returnValue = FeatureHelper.getFeatureValueNamespaceAndClass(featureValue);
		} catch(Exception e) {
			thrown = true;
			assertTrue(e.getMessage().contains("Error occured while trying to obtain the Class name from fUML.Semantics.Classes.Kernel.FeatureValue"));
		}
		
		assertEquals(true, thrown);
	}
	
	@Test
	public void getFeatureValueNamespaceAndClassNoClassNameTest() {
		boolean thrown = false;
		
		FeatureValue featureValue = new FeatureValue();
		Property feature = new Property();
		Classifier featuringClassifier = new Class_();
		Namespace namespace = new Package();
		
		namespace.name = "vehiclelibrary";
		featuringClassifier.namespace = namespace;
		feature.featuringClassifier.add(featuringClassifier);
		
		featureValue.feature = feature;
		
		try {
			String returnValue = FeatureHelper.getFeatureValueNamespaceAndClass(featureValue);
		} catch(Exception e) {
			thrown = true;
			assertTrue(e.getMessage().contains("Error occured while trying to obtain the Class name from fUML.Semantics.Classes.Kernel.FeatureValue"));
		}
		
		assertEquals(true, thrown);
	}
	
	@Test
	public void getFeatureValueNamespaceAndClassEmptyClassNameTest() {
		boolean thrown = false;
		
		FeatureValue featureValue = new FeatureValue();
		Property feature = new Property();
		Classifier featuringClassifier = new Class_();
		Namespace namespace = new Package();
		
		namespace.name = "vehiclelibrary";
		featuringClassifier.name = "";
		featuringClassifier.namespace = namespace;
		feature.featuringClassifier.add(featuringClassifier);
		
		featureValue.feature = feature;
		
		try {
			String returnValue = FeatureHelper.getFeatureValueNamespaceAndClass(featureValue);
		} catch(Exception e) {
			thrown = true;
			assertTrue(e.getMessage().contains("Error occured while trying to obtain the Class name from fUML.Semantics.Classes.Kernel.FeatureValue"));
		}
		
		assertEquals(true, thrown);
	}
	
	@Test
	public void getFeatureValueNamespaceAndClassNoNamespaceTest() throws Exception {
		FeatureValue featureValue = new FeatureValue();
		Property feature = new Property();
		Classifier featuringClassifier = new Class_();

		featuringClassifier.name = "Ship";
		feature.featuringClassifier.add(featuringClassifier);
		
		featureValue.feature = feature;
		
		String returnValue = FeatureHelper.getFeatureValueNamespaceAndClass(featureValue);
		
		assertEquals("Ship", returnValue);
	}
	
	@Test
	public void getFeatureValueNamespaceAndClassNoNamespaceNameTest() throws Exception {
		FeatureValue featureValue = new FeatureValue();
		Property feature = new Property();
		Classifier featuringClassifier = new Class_();
		Namespace namespace = new Package();
		
		featuringClassifier.name = "Ship";
		featuringClassifier.namespace = namespace;
		feature.featuringClassifier.add(featuringClassifier);
		
		featureValue.feature = feature;
		
		String returnValue = FeatureHelper.getFeatureValueNamespaceAndClass(featureValue);
		
		assertEquals("Ship", returnValue);
	}
	
	@Test
	public void getFeatureValueNamespaceAndClassEmptyNamespaceNameTest() throws Exception {
		FeatureValue featureValue = new FeatureValue();
		Property feature = new Property();
		Classifier featuringClassifier = new Class_();
		Namespace namespace = new Package();
		
		namespace.name = "";
		featuringClassifier.name = "Ship";
		featuringClassifier.namespace = namespace;
		feature.featuringClassifier.add(featuringClassifier);
		
		featureValue.feature = feature;
		
		String returnValue = FeatureHelper.getFeatureValueNamespaceAndClass(featureValue);
		
		assertEquals("Ship", returnValue);
	}
	
}
