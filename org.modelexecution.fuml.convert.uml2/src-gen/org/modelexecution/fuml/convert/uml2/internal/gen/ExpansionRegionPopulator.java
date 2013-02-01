/*
* Copyright (c) 2012 Vienna University of Technology.
* All rights reserved. This program and the accompanying materials are made 
* available under the terms of the Eclipse Public License v1.0 which accompanies 
* this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors:
* Philip Langer - initial API and generator
*/
package org.modelexecution.fuml.convert.uml2.internal.gen;
    	
import javax.annotation.Generated;
import org.modelexecution.fuml.convert.impl.ConversionResultImpl;
import org.modelexecution.fuml.convert.uml2.internal.IElementPopulator;

@Generated(value="Generated by org.modelexecution.fuml.convert.uml2.gen.ElementPopulatorGenerator.xtend")
public class ExpansionRegionPopulator implements IElementPopulator {

	@Override
	public void populate(fUML.Syntax.Classes.Kernel.Element fumlElement,
		org.eclipse.uml2.uml.Element uml2Element, ConversionResultImpl result) {
			
		if (!(uml2Element instanceof org.eclipse.uml2.uml.ExpansionRegion) ||
			!(fumlElement instanceof fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion)) {
			return;
		}
		
		fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion fumlNamedElement = (fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionRegion) fumlElement;
		org.eclipse.uml2.uml.ExpansionRegion uml2NamedElement = (org.eclipse.uml2.uml.ExpansionRegion) uml2Element;
		
		fumlNamedElement.mode = castExpansionKind(uml2NamedElement.getMode());
		
		for (org.eclipse.uml2.uml.ExpansionNode value : uml2NamedElement.getInputElements()) {
					fumlNamedElement.inputElement.add((fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode) result.getFUMLElement(value));
		}
		
		
		for (org.eclipse.uml2.uml.ExpansionNode value : uml2NamedElement.getOutputElements()) {
					fumlNamedElement.outputElement.add((fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionNode) result.getFUMLElement(value));
		}
		
		
	}
	
	private fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind castExpansionKind(org.eclipse.uml2.uml.ExpansionKind enumVal) {
		switch (enumVal.getValue()) {
			case org.eclipse.uml2.uml.ExpansionKind.PARALLEL:
				return fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind.parallel;
			case org.eclipse.uml2.uml.ExpansionKind.ITERATIVE:
				return fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind.iterative;
			case org.eclipse.uml2.uml.ExpansionKind.STREAM:
				return fUML.Syntax.Activities.ExtraStructuredActivities.ExpansionKind.stream;
		}
		return null;
	}
}
