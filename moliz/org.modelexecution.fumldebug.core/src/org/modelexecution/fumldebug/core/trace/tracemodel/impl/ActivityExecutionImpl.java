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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.CallActionExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.ControlNodeExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Input;
import org.modelexecution.fumldebug.core.trace.tracemodel.Output;
import org.modelexecution.fumldebug.core.trace.tracemodel.TokenInstance;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelFactory;
import org.modelexecution.fumldebug.core.trace.tracemodel.UserParameterInput;

import fUML.Syntax.Actions.BasicActions.CallAction;
import fUML.Syntax.Activities.IntermediateActivities.Activity;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;
import fUML.Syntax.Activities.IntermediateActivities.ControlNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl#getUserParameterInputs <em>User Parameter Inputs</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl#getNodeExecutions <em>Node Executions</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl#getCaller <em>Caller</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl#getActivityExecutionID <em>Activity Execution ID</em>}</li>
 *   <li>{@link org.modelexecution.fumldebug.core.trace.tracemodel.impl.ActivityExecutionImpl#getTrace <em>Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityExecutionImpl extends EObjectImpl implements ActivityExecution {
	
	private static final TracemodelFactory TRACE_FACTORY = TracemodelFactory.INSTANCE;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2012 Vienna University of Technology.\r\nAll rights reserved. This program and the accompanying materials are made \r\navailable under the terms of the Eclipse Public License v1.0 which accompanies \r\nthis distribution, and is available at http://www.eclipse.org/legal/epl-v10.html\r\n\r\nContributors:\r\nTanja Mayerhofer - initial API and implementation";

	/**
	 * The cached value of the '{@link #getUserParameterInputs() <em>User Parameter Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserParameterInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<UserParameterInput> userParameterInputs;

	/**
	 * The cached value of the '{@link #getNodeExecutions() <em>Node Executions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityNodeExecution> nodeExecutions;

	/**
	 * The cached value of the '{@link #getCaller() <em>Caller</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCaller()
	 * @generated
	 * @ordered
	 */
	protected CallActionExecution caller;

	/**
	 * The default value of the '{@link #getActivity() <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected static final Activity ACTIVITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivity() <em>Activity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivity()
	 * @generated
	 * @ordered
	 */
	protected Activity activity = ACTIVITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getActivityExecutionID() <em>Activity Execution ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityExecutionID()
	 * @generated
	 * @ordered
	 */
	protected static final int ACTIVITY_EXECUTION_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getActivityExecutionID() <em>Activity Execution ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityExecutionID()
	 * @generated
	 * @ordered
	 */
	protected int activityExecutionID = ACTIVITY_EXECUTION_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracemodelPackageImpl.Literals.ACTIVITY_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<UserParameterInput> getUserParameterInputs() {
		if (userParameterInputs == null) {
			userParameterInputs = new BasicInternalEList<UserParameterInput>(UserParameterInput.class);
		}
		return userParameterInputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ActivityNodeExecution> getNodeExecutions() {
		if (nodeExecutions == null) {
			nodeExecutions = new BasicInternalEList<ActivityNodeExecution>(ActivityNodeExecution.class);
		}
		return nodeExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallActionExecution getCaller() {
		if (caller != null && caller.eIsProxy()) {
			InternalEObject oldCaller = (InternalEObject)caller;
			caller = (CallActionExecution)eResolveProxy(oldCaller);
			if (caller != oldCaller) {
			}
		}
		return caller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallActionExecution basicGetCaller() {
		return caller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCaller(CallActionExecution newCaller, NotificationChain msgs) {
		caller = newCaller;
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaller(CallActionExecution newCaller) {
		if (newCaller != caller) {
			NotificationChain msgs = null;
			if (caller != null)
				msgs = ((InternalEObject)caller).eInverseRemove(this, TracemodelPackageImpl.CALL_ACTION_EXECUTION__CALLEE, CallActionExecution.class, msgs);
			if (newCaller != null)
				msgs = ((InternalEObject)newCaller).eInverseAdd(this, TracemodelPackageImpl.CALL_ACTION_EXECUTION__CALLEE, CallActionExecution.class, msgs);
			msgs = basicSetCaller(newCaller, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(Activity newActivity) {
		activity = newActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getActivityExecutionID() {
		return activityExecutionID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityExecutionID(int newActivityExecutionID) {
		activityExecutionID = newActivityExecutionID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trace getTrace() {
		if (eContainerFeatureID() != TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE) return null;
		return (Trace)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrace(Trace newTrace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTrace, TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrace(Trace newTrace) {
		if (newTrace != eInternalContainer() || (eContainerFeatureID() != TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE && newTrace != null)) {
			if (EcoreUtil.isAncestor(this, newTrace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTrace != null)
				msgs = ((InternalEObject)newTrace).eInverseAdd(this, TracemodelPackageImpl.TRACE__ACTIVITY_EXECUTIONS, Trace.class, msgs);
			msgs = basicSetTrace(newTrace, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodeExecutions()).basicAdd(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__CALLER:
				if (caller != null)
					msgs = ((InternalEObject)caller).eInverseRemove(this, TracemodelPackageImpl.CALL_ACTION_EXECUTION__CALLEE, CallActionExecution.class, msgs);
				return basicSetCaller((CallActionExecution)otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTrace((Trace)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				return ((InternalEList<?>)getUserParameterInputs()).basicRemove(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return ((InternalEList<?>)getNodeExecutions()).basicRemove(otherEnd, msgs);
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__CALLER:
				return basicSetCaller(null, msgs);
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE:
				return basicSetTrace(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE:
				return eInternalContainer().eInverseRemove(this, TracemodelPackageImpl.TRACE__ACTIVITY_EXECUTIONS, Trace.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				return getUserParameterInputs();
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return getNodeExecutions();
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__CALLER:
				if (resolve) return getCaller();
				return basicGetCaller();
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY:
				return getActivity();
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				return getActivityExecutionID();
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE:
				return getTrace();
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
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				getUserParameterInputs().clear();
				getUserParameterInputs().addAll((Collection<? extends UserParameterInput>)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				getNodeExecutions().clear();
				getNodeExecutions().addAll((Collection<? extends ActivityNodeExecution>)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__CALLER:
				setCaller((CallActionExecution)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY:
				setActivity((Activity)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				setActivityExecutionID((Integer)newValue);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE:
				setTrace((Trace)newValue);
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
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				getUserParameterInputs().clear();
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				getNodeExecutions().clear();
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__CALLER:
				setCaller((CallActionExecution)null);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY:
				setActivity(ACTIVITY_EDEFAULT);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				setActivityExecutionID(ACTIVITY_EXECUTION_ID_EDEFAULT);
				return;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE:
				setTrace((Trace)null);
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
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__USER_PARAMETER_INPUTS:
				return userParameterInputs != null && !userParameterInputs.isEmpty();
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__NODE_EXECUTIONS:
				return nodeExecutions != null && !nodeExecutions.isEmpty();
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__CALLER:
				return caller != null;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY:
				return ACTIVITY_EDEFAULT == null ? activity != null : !ACTIVITY_EDEFAULT.equals(activity);
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__ACTIVITY_EXECUTION_ID:
				return activityExecutionID != ACTIVITY_EXECUTION_ID_EDEFAULT;
			case TracemodelPackageImpl.ACTIVITY_EXECUTION__TRACE:
				return getTrace() != null;
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
		result.append(" (activity: ");
		result.append(activity);
		result.append(", activityExecutionID: ");
		result.append(activityExecutionID);
		result.append(')');
		return result.toString();
	}

	public List<ActivityNodeExecution> getNodeExecutionsByNode(ActivityNode node) {
		List<ActivityNodeExecution> nodeExecutionsForNode = new ArrayList<ActivityNodeExecution>();
		for(ActivityNodeExecution nodeExecution : this.nodeExecutions) {
			if(nodeExecution.getNode().equals(node)) {
				nodeExecutionsForNode.add(nodeExecution);
			}
		}
		return nodeExecutionsForNode;
	}

	public CallActionExecution getActiveCallActionExecution(CallAction action) {
		List<CallActionExecution> activeCallActionExecutions = new ArrayList<CallActionExecution>();
		List<ActivityNodeExecution> nodeExecutionsForNode = getNodeExecutionsByNode(action);
		for(ActivityNodeExecution nodeExecution : nodeExecutionsForNode) {
			if(nodeExecution instanceof CallActionExecution) {
				CallActionExecution callActionExecution = (CallActionExecution)nodeExecution;
				if(callActionExecution.isExecuted() && callActionExecution.getOutputs().size()==0) {
					activeCallActionExecutions.add(callActionExecution);
				}
			}
		}
		
		if(activeCallActionExecutions.size() > 0) {
			return activeCallActionExecutions.get(0);
		}
		return null;
	} 
	
	/* TODO
	private void addParameterInput(ActivityParameterNode activityParameterNode, List<Value> values, boolean userInput) {
		ParameterInput parameterInput = new ParameterInputImpl();	
		if(userInput) {
			parameterInput = new UserParameterInputImpl();
		}
		parameterInput.setInputParameterNode(activityParameterNode);
		this.getParameterInputs().add(parameterInput);

		for(Value value : values) {
			ObjectTokenInstance tokenInstance = new ObjectTokenInstanceImpl();
			ValueInstance valueInstance = new ValueInstanceImpl();

			if(value instanceof Reference) {
				value = ((Reference)value).referent.copy();
			}
			valueInstance.setValue(value);
			tokenInstance.setValue(valueInstance);
			parameterInput.getParameterInputTokens().add(tokenInstance);
		}
	} */
	
	/* TODO
	@Override
	public void addParameterOutput(ActivityParameterNode activityParameterNode,
			List<Value> values) {
		ParameterOutput parameterOutput = new ParameterOutputImpl();
		parameterOutput.setOutputParameterNode(activityParameterNode);

		for(Value value : values) {
			ObjectTokenInstance objectTokenInstance = new ObjectTokenInstanceImpl();
			ValueInstance valueInstance = new ValueInstanceImpl();
			if(value instanceof Reference) {
				valueInstance.setValue(((Reference)value).referent.copy());
			} else {
				valueInstance.setValue(value.copy());
			}
			objectTokenInstance.setValue(valueInstance);

			parameterOutput.getParameterOutputTokens().add(objectTokenInstance);
		}
		this.getParameterOutputs().add(parameterOutput);
	} */

	public ActivityNodeExecution addActivityNodeExecution(ActivityNode activityNode) {
		ActivityNodeExecution activityNodeExecution;
		if(activityNode instanceof ActionExecution) {
			activityNodeExecution = TRACE_FACTORY.createActionExecution();
		} else if(activityNode instanceof CallAction) {
			activityNodeExecution = TRACE_FACTORY.createCallActionExecution();
		} else if(activityNode instanceof ControlNode) {
			activityNodeExecution = TRACE_FACTORY.createControlNodeExecution();
		} else {
			activityNodeExecution = TRACE_FACTORY.createActivityNodeExecution();
		}
		activityNodeExecution.setNode(activityNode);	
		activityNodeExecution.setActivityExecution(this);
		return activityNodeExecution;
	}
	
	public List<ActivityNodeExecution> getNodeExecutionsWithTokenOutput(TokenInstance tokenInstance) {
		Set<ActivityNodeExecution> nodeExecutions = new HashSet<ActivityNodeExecution>();	
		
		for(ActivityNodeExecution nodeExecution : this.getNodeExecutions()) {
			if(nodeExecution instanceof ActionExecution) {
				ActionExecution actionExecution = (ActionExecution)nodeExecution;
				List<Output> outputs = actionExecution.getOutputs();
				for(Output output : outputs) {
					if(output.getTokens().contains(tokenInstance)) {
						nodeExecutions.add(nodeExecution);
						break;
					}
				}
			} else if (nodeExecution instanceof ControlNodeExecution){
				ControlNodeExecution controlExecution = (ControlNodeExecution)nodeExecution;
				if(controlExecution.getRoutedTokens().contains(tokenInstance)) {
					nodeExecutions.add(nodeExecution);
				}
			}
		}			
		List<ActivityNodeExecution> nodeExecutionsList = new ArrayList<ActivityNodeExecution>(nodeExecutions);	
		return nodeExecutionsList;
	}

	public List<ActivityNodeExecution> getNodeExecutionsWithTokenInput(TokenInstance tokenInstance) {
		Set<ActivityNodeExecution> nodeExecutions = new HashSet<ActivityNodeExecution>();

		for(ActivityNodeExecution nodeExecution : this.getNodeExecutions()) {
			if(nodeExecution instanceof ActionExecution) {
				ActionExecution actionExecution = (ActionExecution)nodeExecution;
				List<Input> inputs = actionExecution.getInputs();
				for(Input input : inputs) {
					if(input.getTokens().contains(tokenInstance)) {
						nodeExecutions.add(nodeExecution);
					}
				}
			} else if(nodeExecution instanceof ControlNodeExecution) {
				ControlNodeExecution controlExecution = (ControlNodeExecution)nodeExecution;
				if(controlExecution.getRoutedTokens().contains(tokenInstance)) {
					nodeExecutions.add(nodeExecution);
				}
			}
		}
		
		List<ActivityNodeExecution> nodeExecutionsList = new ArrayList<ActivityNodeExecution>(nodeExecutions);
		return nodeExecutionsList;
	}			

	/* TODO
	public void setActivityNodeExecutionFinishedExecution(ActivityNodeExecution activityNodeExecution) {
		int indexOfLastExecutedNode = this.nodeExecutions.indexOf(activityNodeExecution);
		if(indexOfLastExecutedNode == -1) {
			return;
		}
		boolean found = false;
		while(!found && indexOfLastExecutedNode > 0) {
			--indexOfLastExecutedNode;
			ActivityNodeExecution nodeExecution = this.nodeExecutions.get(indexOfLastExecutedNode);
			if(nodeExecution.getOutputs() != null && nodeExecution.getOutputs().size() > 0) {
				found = true;
			}			
		}
		if(!found) {
			indexOfLastExecutedNode = -1;
		}
		nodeExecutions.remove(activityNodeExecution);
		nodeExecutions.add(indexOfLastExecutedNode + 1, activityNodeExecution);
		return;
	}*/
			
	public List<ActivityNodeExecution> getExecutionsForEnabledNode(ActivityNode node) {
		List<ActivityNodeExecution> executionsForEnabledNode = new ArrayList<ActivityNodeExecution>();
		List<ActivityNodeExecution> nodeExecutionsForNode = getNodeExecutionsByNode(node);
		for(ActivityNodeExecution nodeExecution : nodeExecutionsForNode) {
			if(!nodeExecution.isExecuted()) {
			//if(nodeExecution.getChronologicalPredecessor() == null && nodeExecution.getChronologicalSuccessor() == null) {
				executionsForEnabledNode.add(nodeExecution);
			}
		}
		return executionsForEnabledNode;
	} 
		
} //ActivityExecutionImpl
