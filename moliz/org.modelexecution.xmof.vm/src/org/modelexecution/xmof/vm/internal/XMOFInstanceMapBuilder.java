/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.vm.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.modelexecution.fuml.convert.IConversionResult;
import org.modelexecution.xmof.Syntax.CommonBehaviors.BasicBehaviors.Behavior;
import org.modelexecution.xmof.vm.internal.LinkCreationData.LinkEndCreationData;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.EnumerationValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.Value;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Enumeration;
import fUML.Syntax.Classes.Kernel.EnumerationLiteral;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.PropertyList;

/**
 * Derives an {@link XMOFInstanceMap} from an {@link IConversionResult} and a
 * xMOF-based model.
 * 
 * @author Philip Langer (langer@big.tuwien.ac.at)
 * 
 */
public class XMOFInstanceMapBuilder {

	private XMOFInstanceMap map;
	private IConversionResult conversionResult;
	private Locus locus;
	private List<LinkCreationData.LinkEndCreationData> processedOpposites = new ArrayList<LinkCreationData.LinkEndCreationData>();

	protected XMOFInstanceMapBuilder(XMOFInstanceMap map) {
		this.map = map;
	}

	protected void build(IConversionResult result, List<EObject> modelElements,
			Locus locus) {
		this.conversionResult = result;
		this.locus = locus;	
		initializeClassMap();
		initialize(modelElements);
	}

	private void initializeClassMap() {
		if(this.conversionResult.getInput() instanceof EPackage) {
			EPackage input = (EPackage)this.conversionResult.getInput();
			for (TreeIterator<EObject> iterator = input.eAllContents(); iterator.hasNext();) {
				EObject next = iterator.next();
				if (next instanceof EClass && !(next instanceof Behavior)) {
					EClass eClass = (EClass) next;
					Class_ class_ = (Class_)this.conversionResult.getFUMLElement(eClass);
					if(class_ != null) {
						map.addMapping(class_, eClass);
					}
				}
			}
		}
		
	}

	private void initialize(List<EObject> modelElements) {
		createObjects(modelElements);
		setLinksAndValues();
	}

	private void createObjects(List<EObject> modelElements) {
		for (EObject eObject : modelElements) {
			createObject(eObject);
		}
	}

	private void createObject(EObject eObject) {
		if (map.getObject(eObject) != null)
			return;
		Class_ type = (Class_) conversionResult
				.getFUMLElement(eObject.eClass());
		Object_ object = locus.instantiate(type);
		map.addMapping(object, eObject);
		createChildObjects(eObject);
	}

	private void createChildObjects(EObject eObject) {
		for (EObject eChildObject : eObject.eContents()) {
			createObject(eChildObject);
		}
	}

	private void setLinksAndValues() {
		for (EObject eObject : map.getAllEObjects()) {
			setContainmentLink(eObject);
			setCrossReferenceLinks(eObject);
			setValues(eObject);
		}
	}

	private void setContainmentLink(EObject eObject) {
		if (eObject.eContainer() != null) {
			EObject eContainer = eObject.eContainer();
			EReference eReference = eObject.eContainmentFeature();
			int position = eReference.isMany() ? position = getContainmentPosition(
					eContainer, eObject) : 0;
			addLink(eContainer, eObject, eReference, position);
		}
	}

	private int getContainmentPosition(EObject eContainer, EObject eObject) {
		Object containmentReferenceValue = eContainer.eGet(eObject
				.eContainmentFeature());
		return ((List<?>) containmentReferenceValue).indexOf(eObject) + 1;
	}

	private void setCrossReferenceLinks(EObject eObject) {
		for (EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) eObject
				.eCrossReferences().iterator(); featureIterator.hasNext();) {
			EObject referencedEObject = (EObject) featureIterator.next();
			EReference eReference = (EReference) featureIterator.feature();
			int position = getPosition(eObject, eReference, referencedEObject);
			addLink(eObject, referencedEObject, eReference, position);
		}
	}

	private int getPosition(EObject eObject, EReference eReference,
			EObject referencedEObject) {
		if (eReference.isMany()) {
			return ((List<?>) eObject.eGet(eReference))
					.indexOf(referencedEObject) + 1;
		}
		return 1;
	}

	private void addLink(EObject sourceEObject, EObject targetEObject,
			EReference eReference, int targetPosition) {
		Object_ sourceObject = map.getObject(sourceEObject);
		Object_ targetObject = map.getObject(targetEObject);
		Association association = (Association) conversionResult
				.getFUMLElement(eReference);

		if (hasOppositeReference(eReference)
				&& haveProcessedAsOpposite(eReference, sourceObject,
						targetObject, association)) {
			return;
		}

		// Setup target property end
		LinkCreationData linkData = new LinkCreationData(sourceObject,
				targetObject, association);
		LinkEndCreationData targetEndData = linkData
				.createLinkEndCreationData();
		targetEndData.setEnd(getTargetPropertyEnd(eReference, association));
		linkData.setTargetEndData(targetEndData);
		linkData.setTargetPosition(targetPosition);

		// Setup source property end
		LinkEndCreationData sourceEndData = linkData
				.createLinkEndCreationData();
		if (hasOppositeReference(eReference)) {
			EReference oppositeReference = eReference.getEOpposite();
			sourceEndData.setEnd(getTargetPropertyEnd(oppositeReference,
					association));
			int sourcePosition = getPosition(targetEObject, oppositeReference, sourceEObject);
			linkData.setSourcePosition(sourcePosition);
			addProcessedOpposite(sourceEndData);
		} else {
			sourceEndData.setEnd(getSourcePropertyEnd(eReference, association));
		}
		linkData.setSourceEndData(sourceEndData);
		Link link = linkData.createNewLink();
		map.addExtensionalValue(link);
		//link.addTo(locus);
		locus.add(link);
	}

	private boolean hasOppositeReference(EReference eReference) {
		return eReference.getEOpposite() != null;
	}

	private boolean haveProcessedAsOpposite(EReference eReference,
			Object_ sourceObject, Object_ targetObject, Association association) {
		for (LinkEndCreationData processedEnd : processedOpposites) {
			if (processedEnd.representsOpposite(eReference, sourceObject,
					targetObject)) {
				return true;
			}
		}
		return false;
	}

	private Property getSourcePropertyEnd(EReference eReference,
			Association association) {
		return getPropertyByName(association.memberEnd, eReference
				.getEContainingClass().getName().toLowerCase());
	}

	private Property getTargetPropertyEnd(EReference eReference,
			Association association) {
		return getPropertyByName(association.memberEnd, eReference.getName());
	}

	private Property getPropertyByName(PropertyList propertyList, String name) {
		for (Property property : propertyList) {
			if (name.equals(property.name))
				return property;
		}
		return null;
	}

	private void addProcessedOpposite(LinkEndCreationData sourceEndData) {
		processedOpposites.add(sourceEndData);
	}

	private void setValues(EObject eObject) {
		for (EAttribute eAttribute : eObject.eClass().getEAllAttributes()) {

			Object valueToSet = eObject.eGet(eAttribute, true);
			Object_ object = map.getObject(eObject);
			Property property = (Property) conversionResult
					.getFUMLElement(eAttribute);
			FeatureValue featureValue = object.getFeatureValue(property);

			if (featureValue == null) {
				object.setFeatureValue(property, new ValueList(), 0);
				featureValue = object.getFeatureValue(property);
			}

			if (eAttribute.isMany()) {
				setPrimitiveValue(featureValue, (List<?>) valueToSet,
						eAttribute.getEAttributeType());
			} else {
				setPrimitiveValue(featureValue, valueToSet,
						eAttribute.getEAttributeType());
			}
		}
	}

	private void setPrimitiveValue(FeatureValue featureValue, Object value,
			EDataType valueType) {
		featureValue.values.addValue(createFUMLValue(value, valueType));
	}

	private void setPrimitiveValue(FeatureValue featureValue,
			List<?> valueList, EDataType valueType) {
		for (Object value : valueList) {
			featureValue.values.addValue(createFUMLValue(value, valueType));
		}
	}

	private Value createFUMLValue(Object value, EDataType valueType) {
		Value fUMLValue = null;
		if (isEStringType(valueType)) {
			StringValue stringValue = new StringValue();
			stringValue.value = (String) value;
			fUMLValue = stringValue;
		} else if (isEBooleanType(valueType)) {
			BooleanValue booleanValue = new BooleanValue();
			booleanValue.value = (Boolean) value;
			fUMLValue = booleanValue;
		} else if (isEIntType(valueType)) {
			IntegerValue integerValue = new IntegerValue();
			integerValue.value = (int) value;
			fUMLValue = integerValue;
		} else if (isCustomEEnumType(valueType)) {
			EnumerationValue enumerationValue = new EnumerationValue();
			enumerationValue.literal = (EnumerationLiteral) conversionResult
					.getFUMLElement(value);
			enumerationValue.type = (Enumeration) conversionResult
					.getFUMLElement(valueType);
			fUMLValue = enumerationValue;
		}
		return fUMLValue;
	}

	private boolean isEBooleanType(EDataType valueType) {
		return EcorePackage.eINSTANCE.getEBoolean().equals(valueType);
	}

	private boolean isEIntType(EDataType valueType) {
		return EcorePackage.eINSTANCE.getEInt().equals(valueType);
	}

	private boolean isEStringType(EDataType valueType) {
		return EcorePackage.eINSTANCE.getEString().equals(valueType);
	}

	private boolean isCustomEEnumType(EDataType valueType) {
		return valueType instanceof EEnum;
	}

}
