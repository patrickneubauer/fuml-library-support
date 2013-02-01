/*
 * Copyright (c) 2012 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Philip Langer - initial API and implementation
 */
package org.modelexecution.xmof.diagram.features;

import static org.modelexecution.xmof.diagram.DiagramDimensions.INITIAL_NODE_SIZE;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.modelexecution.xmof.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity;
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.ControlNode;

public class AddControlNodeFeature extends AbstractAddFeature {

	public AddControlNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof ControlNode && (getTargetActivity(context) != null || getTargetExpansionRegion(context) != null);
	}
	
	private Activity getTargetActivity(IAddContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof Activity) {
				return (Activity) object;
			}
		}
		return null;
	}
	
	private ExpansionRegion getTargetExpansionRegion(IAddContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getTargetContainer());
		if (object != null) {
			if (object instanceof ExpansionRegion) {
				return (ExpansionRegion) object;
			}
		}
		return null;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Object initialNode = context.getNewObject();
		ContainerShape targetContainer = (ContainerShape) context
				.getTargetContainer();
		Shape initialNodeShape = getPeCreateService().createShape(
				targetContainer, true);

		Ellipse initialNodeEllipse = getGaService().createEllipse(
				initialNodeShape);
		getGaService().setLocationAndSize(initialNodeEllipse, context.getX(),
				context.getY(), INITIAL_NODE_SIZE, INITIAL_NODE_SIZE);

		getPeCreateService().createChopboxAnchor(initialNodeShape);

		link(initialNodeShape, initialNode);
		return initialNodeShape;
	}

	private IPeCreateService getPeCreateService() {
		return Graphiti.getPeCreateService();
	}

	private IGaService getGaService() {
		return Graphiti.getGaService();
	}
}
