/**
 * 
 */
package org.modelexecution.fuml.extlib;

import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Syntax.Classes.Kernel.Namespace;

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
					
					if (featureValue.feature.featuringClassifier.get(0).namespace.owner != null) {
						// case: namespace has an owner namespace
						return obtainClassOwnerNamespace(featureValue.feature.featuringClassifier.get(0).namespace);
					} else if (featureValue.feature.featuringClassifier.get(0).namespace.name != null) {
						// case: namespace has NO owner namespace
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

	/**
	 * Obtains the Class owner's namespace recursively
	 * 
	 * @param namespace the {@link Namespace} to look for the owner namespace
	 * @return a String representing the owner namespace (recursively obtained) plus dot plus the namespace name itself
	 */
	private static String obtainClassOwnerNamespace(Namespace namespace) {
		if (namespace != null && namespace.owner != null && namespace.name != null) {
			return obtainClassOwnerNamespace((Namespace)namespace.owner) + "." + namespace.name;
		}
		return namespace.name;
	}// obtainClassOwnerNamespace
	
}
