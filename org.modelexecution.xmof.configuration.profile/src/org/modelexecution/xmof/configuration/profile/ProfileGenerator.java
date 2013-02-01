/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.configuration.profile;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelexecution.xmof.Syntax.Classes.Kernel.BehavioredEClass;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.OpaqueBehavior;
import org.modelversioning.emfprofile.EMFProfileFactory;
import org.modelversioning.emfprofile.Extension;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;

public class ProfileGenerator {

	private Collection<EPackage> configurationPackages;

	public ProfileGenerator(
			Collection<EPackage> configurationPackages) {
		super();
		this.configurationPackages = configurationPackages;
	}

	public Collection<Profile> generateConfigurationProfile() {
		Collection<Profile> configurationProfiles = new ArrayList<Profile>();
		for (EPackage configurationPackage : configurationPackages) {
			Profile configurationProfile = generateConfProfile(configurationPackage);
			configurationProfiles.add(configurationProfile);
		}
		return configurationProfiles;
	}

	private Profile generateConfProfile(EPackage confPackage) {
		Profile profile = EMFProfileFactory.eINSTANCE.createProfile();

		profile.setName(confPackage.getName() + "Profile");
		profile.setNsPrefix(confPackage.getNsPrefix() + "_profile");
		profile.setNsURI(confPackage.getNsURI() + "/profile");

		EList<EClassifier> confClasses = confPackage.getEClassifiers();
		Collection<EClassifier> confStereotypes = generateConfStereotypes(confClasses);
		profile.getEClassifiers().addAll(confStereotypes);

		for (EPackage subPackage : confPackage.getESubpackages()) {
			Profile subConfProfile = generateConfProfile(subPackage);
			if (subConfProfile != null)
				profile.getESubpackages().add(subConfProfile);
		}

		return profile;
	}

	private Collection<EClassifier> generateConfStereotypes(
			EList<EClassifier> eClassifiers) {
		Collection<EClassifier> confStereotypes = new ArrayList<EClassifier>();
		for (EClassifier eClassifier : eClassifiers) {
			EClassifier confStereotype = generateConfStereotype(eClassifier);
			if (confStereotype != null)
				confStereotypes.add(confStereotype);
		}
		return confStereotypes;
	}

	private EClassifier generateConfStereotype(EClassifier eClassifier) {
		if (eClassifier instanceof BehavioredEClass
				&& !(eClassifier instanceof OpaqueBehavior)) {
			BehavioredEClass confClass = (BehavioredEClass) eClassifier;
			Stereotype confStereotype = EMFProfileFactory.eINSTANCE
					.createStereotype();
			confStereotype.setName(confClass.getName() + "Stereotype");
			addStructuralFeatures(confClass, confStereotype);
			confStereotype.getExtensions().add(
					createExtension(confClass, confStereotype));
			return confStereotype;
		}
		return null;
	}

	private Extension createExtension(BehavioredEClass confClass,
			Stereotype confStereotype) {
		EClass baseClass = obtainBaseClass(confClass);
		Extension extension = EMFProfileFactory.eINSTANCE.createExtension();
		extension.setLowerBound(0);
		extension.setUpperBound(1);
		extension.setTarget(baseClass);
		return extension;
	}

	private EClass obtainBaseClass(BehavioredEClass confClass) {
		if (confClass.getESuperTypes().size() > 0) {
			return confClass.getESuperTypes().get(0);
		}
		return null;
	}

	private void addStructuralFeatures(BehavioredEClass eClass,
			Stereotype confStereotype) {
		for (EStructuralFeature feature : eClass.getEStructuralFeatures()) {
			EStructuralFeature copy = EcoreUtil.copy(feature);
			confStereotype.getEStructuralFeatures().add(copy);
		}
	}

}
