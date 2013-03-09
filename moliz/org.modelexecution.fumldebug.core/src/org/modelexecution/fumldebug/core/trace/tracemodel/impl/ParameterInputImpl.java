/**
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.modelexecution.fumldebug.core.trace.tracemodel.impl;

import fUML.Syntax.Activities.IntermediateActivities.ActivityParameterNode;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicInternalEList;

import org.modelexecution.fumldebug.core.trace.tracemodel.ObjectTokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.ParameterInput;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterInputImpl#getParameterInputTokens <em>Parameter Input Tokens</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ParameterInputImpl#getInputParameterNode <em>Input Parameter Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterInputImpl extends EObjectImpl implements ParameterInput {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getParameterInputTokens() <em>Parameter Input Tokens</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterInputTokens()
	 * @generated
	 * @ordered
	 */
	protected EList<ObjectTokenInstance> parameterInputTokens;

	/**
	 * The default value of the '{@link #getInputParameterNode() <em>Input Parameter Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameterNode()
	 * @generated
	 * @ordered
	 */
	protected static final ActivityParameterNode INPUT_PARAMETER_NODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInputParameterNode() <em>Input Parameter Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameterNode()
	 * @generated
	 * @ordered
	 */
	protected ActivityParameterNode inputParameterNode = INPUT_PARAMETER_NODE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterInputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackageImpl.Literals.PARAMETER_INPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ObjectTokenInstance> getParameterInputTokens() {
		if (parameterInputTokens == null) {
			parameterInputTokens = new BasicInternalEList<ObjectTokenInstance>(ObjectTokenInstance.class);
		}
		return parameterInputTokens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityParameterNode getInputParameterNode() {
		return inputParameterNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInputParameterNode(ActivityParameterNode newInputParameterNode) {
		inputParameterNode = newInputParameterNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackageImpl.PARAMETER_INPUT__PARAMETER_INPUT_TOKENS:
				return getParameterInputTokens();
			case TracemodelPackageImpl.PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				return getInputParameterNode();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracemodelPackageImpl.PARAMETER_INPUT__PARAMETER_INPUT_TOKENS:
				getParameterInputTokens().clear();
				getParameterInputTokens().addAll((Collection<? extends ObjectTokenInstance>)newValue);
				return;
			case TracemodelPackageImpl.PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				setInputParameterNode((ActivityParameterNode)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TracemodelPackageImpl.PARAMETER_INPUT__PARAMETER_INPUT_TOKENS:
				getParameterInputTokens().clear();
				return;
			case TracemodelPackageImpl.PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				setInputParameterNode(INPUT_PARAMETER_NODE_EDEFAULT);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TracemodelPackageImpl.PARAMETER_INPUT__PARAMETER_INPUT_TOKENS:
				return parameterInputTokens != null && !parameterInputTokens.isEmpty();
			case TracemodelPackageImpl.PARAMETER_INPUT__INPUT_PARAMETER_NODE:
				return INPUT_PARAMETER_NODE_EDEFAULT == null ? inputParameterNode != null : !INPUT_PARAMETER_NODE_EDEFAULT.equals(inputParameterNode);
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (inputParameterNode: ");
		result.append(inputParameterNode);
		result.append(')');
		return result.toString();
	}

} //ParameterInputImpl
