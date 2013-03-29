/**
 * 
 */
package org.modelexecution.fuml.extlib;

import fUML.Semantics.Classes.Kernel.FeatureValue;

/**
 * @author Patrick Neubauer
 *
 */
public class FeatureHelper {
	
	public static String obtainFeatureValueNamespaceAndClass(FeatureValue featureValue) {
		String namespace = obtainFeatureValueNamespace(featureValue);
		String className = obtainFeatureValueClassName(featureValue);
		
		if (namespace != null) {
			return namespace + "." + className;
		} else {
			return className;
		}
	}

	private static String obtainFeatureValueNamespace(FeatureValue featureValue) {
		if (featureValue.feature.featuringClassifier.get(0).namespace != null) {
			if (featureValue.feature.featuringClassifier.get(0).namespace.name != null) {
				return featureValue.feature.featuringClassifier.get(0).namespace.name;
			}
		}

		return null; // no namespace found
	}

	private static String obtainFeatureValueClassName(FeatureValue featureValue) {
		if (featureValue.feature.featuringClassifier.get(0).name != null) {
			return featureValue.feature.featuringClassifier.get(0).name;
		}

		return null; // no name found
	}

}
