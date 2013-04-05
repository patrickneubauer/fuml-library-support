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

	public static String getFeatureValueNamespaceAndClass(FeatureValue featureValue) throws Exception {
		String namespace = obtainFeatureValueNamespace(featureValue);
		String className = obtainFeatureValueClassName(featureValue);

		if (namespace != null && namespace != "") {
			return namespace + "." + className;
		} else {
			return className;
		}
	}

	private static String obtainFeatureValueNamespace(FeatureValue featureValue) {
		if (featureValue.feature != null) {
			if (featureValue.feature.featuringClassifier.size() > 0) {
				if (featureValue.feature.featuringClassifier.get(0).namespace != null) {
					if (featureValue.feature.featuringClassifier.get(0).namespace.name != null) {
						return featureValue.feature.featuringClassifier.get(0).namespace.name;
					}
				}
			}
		}
		
		return null; // no namespace found
	}

	private static String obtainFeatureValueClassName(FeatureValue featureValue) throws Exception {
		if (featureValue.feature != null) {
			if (featureValue.feature.featuringClassifier.size() > 0) {
				if (featureValue.feature.featuringClassifier.get(0).name != null && featureValue.feature.featuringClassifier.get(0).name != "") {
					return featureValue.feature.featuringClassifier.get(0).name;
				}
			}
		}

		throw new Exception("Error occured while trying to obtain the Class name from " + featureValue);
	}

}
