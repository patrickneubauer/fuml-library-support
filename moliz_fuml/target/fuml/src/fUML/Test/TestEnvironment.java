
/*
 * Initial version copyright 2008 Lockheed Martin Corporation, except  
 * as stated in the file entitled Licensing-Information. 
 * 
 * All modifications copyright 2009 Data Access Technologies, Inc.
 *
 * Licensed under the Academic Free License version 3.0 
 * (http://www.opensource.org/licenses/afl-3.0.php), except as stated 
 * in the file entitled Licensing-Information. 
 *
 * Contributors:
 *   MDS - initial API and implementation
 *
 */

package fUML.Test;

import fUML.Debug;
import UMLPrimitiveTypes.*;

import fUML.Syntax.Classes.Kernel.*;
import fUML.Syntax.CommonBehaviors.Communications.*;

import fUML.Semantics.Classes.Kernel.*;
import fUML.Semantics.CommonBehaviors.Communications.*;
import fUML.Semantics.Loci.LociL1.*;
import fUML.Semantics.Loci.LociL3.ExecutionFactoryL3;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>fUML::Test::TestEnvironment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link TestEnvironment#TestEnvironment <em>TestEnvironment</em>}</li>
 * <li>{@link TestEnvironment#makePrimitiveValue <em>makePrimitiveValue</em>}</li>
 * <li>{@link TestEnvironment#makeEnumerationValue <em>makeEnumerationValue
 * </em>}</li>
 * <li>{@link TestEnvironment#makeStructuredValue <em>makeStructuredValue</em>}</li>
 * <li>{@link TestEnvironment#makeValue <em>makeValue</em>}</li>
 * <li>{@link TestEnvironment#addElement <em>addElement</em>}</li>
 * <li>{@link TestEnvironment#getElement <em>getElement</em>}</li>
 * <li>{@link TestEnvironment#removeElement <em>removeElement</em>}</li>
 * <li>{@link TestEnvironment#setVariable <em>setVariable</em>}</li>
 * <li>{@link TestEnvironment#getVariable <em>getVariable</em>}</li>
 * <li>{@link TestEnvironment#getType <em>getType</em>}</li>
 * <li>{@link TestEnvironment#printElements <em>printElements</em>}</li>
 * <li>{@link TestEnvironment#printExtent <em>printExtent</em>}</li>
 * <li>{@link TestEnvironment#removeElement <em>removeElement</em>}</li>
 * <li>{@link TestEnvironment#variables <em>variables</em>}</li>
 * <li>{@link TestEnvironment#primitiveTypes <em>primitiveTypes</em>}</li>
 * <li>{@link TestEnvironment#elements <em>elements</em>}</li>
 * <li>{@link TestEnvironment#locus <em>locus</em>}</li>
 * <li>{@link TestEnvironment#integerFunctions <em>integerFunctions</em>}</li>
 * <li>{@link TestEnvironment#systemIO <em>systemIO</em>}</li>
 * <li>{@link TestEnvironment#standardIO <em>standardIO</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */

public class TestEnvironment extends org.modeldriven.fuml.FumlObject {

	public fUML.Test.VariableList variables = new fUML.Test.VariableList();
	public fUML.Library.PrimitiveTypes primitiveTypes = null;
	public fUML.Syntax.Classes.Kernel.NamedElementList elements = new fUML.Syntax.Classes.Kernel.NamedElementList();
	public fUML.Semantics.Loci.LociL1.Locus locus = null;
	public fUML.Library.IntegerFunctions integerFunctions = null;
	public fUML.Library.SystemIO systemIO = null;
	public fUML.Library.StandardIOClasses standardIO = null;

	/**
	 * operation TestEnvironment <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TestEnvironment() {
		try {

			this.locus = new Locus();
			this.locus.setFactory(new ExecutionFactoryL3());
			this.locus.setExecutor(new Executor());

			this.locus.factory
					.setStrategy(new fUML.Semantics.Classes.Kernel.RedefinitionBasedDispatchStrategy());
			this.locus.factory
					.setStrategy(new fUML.Semantics.CommonBehaviors.Communications.FIFOGetNextEventStrategy());
			this.locus.factory
					.setStrategy(new fUML.Semantics.Loci.LociL1.FirstChoiceStrategy());

			this.primitiveTypes = new fUML.Library.PrimitiveTypes(
					this.locus.factory);
			this.addElement(this.primitiveTypes.Boolean);
			this.addElement(this.primitiveTypes.Integer);
			this.addElement(this.primitiveTypes.String);
			this.addElement(this.primitiveTypes.UnlimitedNatural);

			this.integerFunctions = new fUML.Library.IntegerFunctions(
					this.primitiveTypes.Integer, this.primitiveTypes.Boolean,
					this.locus.factory);
			this.addElement(this.integerFunctions.integerPlus);
			this.addElement(this.integerFunctions.integerMinus);
			this.addElement(this.integerFunctions.integerTimes);
			this.addElement(this.integerFunctions.integerDivide);
			this.addElement(this.integerFunctions.integerNegate);
			this.addElement(this.integerFunctions.integerGreater);

			this.systemIO = new fUML.Library.SystemIO(this.locus.factory);
			this.addElement(this.systemIO.WriteLine);

			this.standardIO = new fUML.Library.StandardIOClasses(
					this.primitiveTypes);
			this.addElement(this.standardIO.Channel);
			this.addElement(this.standardIO.OutputChannel);
			this.addElement(this.standardIO.TextOutputChannel);
			this.addElement(this.standardIO.StandardOutputChannel);

			fUML.Library.ChannelImplementation.StandardOutputChannelObject standardOutputChannel = new fUML.Library.ChannelImplementation.StandardOutputChannelObject();
			standardOutputChannel.types
					.addValue(this.standardIO.StandardOutputChannel);
			standardOutputChannel.open();
			this.locus.add(standardOutputChannel);

			fUML.Library.PipeImplementation.PipeInputChannelObject pipeInputChannel = new fUML.Library.PipeImplementation.PipeInputChannelObject(
					"PipedInput");
			pipeInputChannel.types.addValue(this.standardIO.InputChannel);
			pipeInputChannel.open();
			this.locus.add(pipeInputChannel);

			fUML.Library.PipeImplementation.PipeOutputChannelObject pipeOutputChannel = new fUML.Library.PipeImplementation.PipeOutputChannelObject(
					"PipedOutput", pipeInputChannel);
			pipeOutputChannel.types.addValue(this.standardIO.OutputChannel);
			pipeOutputChannel.open();
			this.locus.add(pipeOutputChannel);

		} catch (Throwable e) {
			Debug.println("[TestEnvironment] Terminated due to "
					+ e.getClass().getName() + "...");
			StackTraceElement[] stackTrace = e.getStackTrace();
			if (stackTrace.length > 0)
				Debug.println(stackTrace[0] + ".");
		}

	} // TestEnvironment

	/**
	 * operation makePrimitiveValue <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.PrimitiveValue makePrimitiveValue(
			fUML.Syntax.Classes.Kernel.Classifier classifier) {
		PrimitiveType type = (PrimitiveType) classifier;
		PrimitiveValue primitiveValue = null;

		// Debug.println("[makePrimitiveValue] type = " + type.name);

		if (type == this.primitiveTypes.Boolean) {
			primitiveValue = new BooleanValue();
		} else if (type == this.primitiveTypes.Integer) {
			primitiveValue = new IntegerValue();
		} else if (type == this.primitiveTypes.String) {
			primitiveValue = new StringValue();
		} else if (type == this.primitiveTypes.UnlimitedNatural) {
			primitiveValue = new UnlimitedNaturalValue();
			((UnlimitedNaturalValue) primitiveValue).value = new UnlimitedNatural();
		}

		if (primitiveValue != null) {
			primitiveValue.type = type;
		} else {
			Debug.println("[makePrimitiveValue] " + type.name
					+ " not understood.");
		}

		// Debug.println("[makePrimitiveValue] value = " + primitiveValue);

		return primitiveValue;
	} // makePrimitiveValue

	/**
	 * operation makeEnumerationValue <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.EnumerationValue makeEnumerationValue(
			fUML.Syntax.Classes.Kernel.Classifier classifier) {
		Enumeration type = (Enumeration) classifier;
		EnumerationValue enumerationValue = new EnumerationValue();

		enumerationValue.type = type;
		enumerationValue.literal = type.ownedLiteral.getValue(0);

		return enumerationValue;
	} // makeEnumerationValue

	/**
	 * operation makeStructuredValue <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.StructuredValue makeStructuredValue(
			fUML.Syntax.Classes.Kernel.Classifier classifier) {
		StructuredValue structuredValue = null;
		PropertyList attributes = null;

		if (classifier instanceof DataType) {
			structuredValue = new DataValue();
			((DataValue) structuredValue).type = (DataType) classifier;
			structuredValue.createFeatureValues();
		} else if (classifier instanceof Class_) {
			structuredValue = new Reference();
			((Reference) structuredValue).referent = this.locus
					.instantiate((Class_) classifier);
		} else if (classifier instanceof Signal) {
			structuredValue = new SignalInstance();
			((SignalInstance) structuredValue).type = (Signal) classifier;
			structuredValue.createFeatureValues();
		}

		FeatureValueList featureValues = structuredValue.getFeatureValues();

		// Debug.println("[makeStructuredValue] " + featureValues.size() +
		// " structural features.");
		for (int i = 0; i < featureValues.size(); i++) {
			StructuralFeature feature = featureValues.getValue(i).feature;
			// Debug.println("[makeStructuredValue] feature = " + feature.name +
			// ", type = " + feature.typedElement.type.name);
			ValueList valueList = new ValueList();
			valueList.addValue(this
					.makeValue((Classifier) (feature.typedElement.type)));
			structuredValue.setFeatureValue(feature, valueList, 0);
		}

		return structuredValue;

	} // makeStructuredValue

	/**
	 * operation makeValue <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Semantics.Classes.Kernel.Value makeValue(
			fUML.Syntax.Classes.Kernel.Classifier type) {
		// if (type == null) {
		// Debug.println("[makeValue} type is null");
		// } else {
		// Debug.println("[makeValue] type = " + type.name);
		// }

		if (type == null) {
			return this.makePrimitiveValue(this.primitiveTypes.String);
		} else if (type instanceof PrimitiveType) {
			return this.makePrimitiveValue(type);
		} else if (type instanceof Enumeration) {
			return this.makeEnumerationValue(type);
		} else {
			return this.makeStructuredValue(type);
		}
	} // makeValue

	/**
	 * operation addElement <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void addElement(fUML.Syntax.Classes.Kernel.NamedElement element) {
		if (this.getElement(element.name) == null) {
			this.elements.addValue(element);
		} else {
			Debug.println("[addElement] There is already an element named "
					+ element.name + ".");
		}
	} // addElement

	/**
	 * operation getElement <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.NamedElement getElement(String name) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.getValue(i).name.equals(name))
				return elements.getValue(i);
		}
		return null;
	} // getElement

	/**
	 * operation removeElement <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void removeElement(fUML.Syntax.Classes.Kernel.NamedElement element) {
		for (int i = 0; i < this.elements.size(); i++) {
			if (this.elements.getValue(i) == element) {
				this.elements.remove(i);
				return;
			}
		}
	} // removeElement

	/**
	 * operation setVariable <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVariable(String name,
			fUML.Semantics.Classes.Kernel.Value value) {
		Variable variable = this.getVariable(name);

		if (variable == null) {
			variable = new Variable();
			variable.name = name;
			this.variables.addValue(variable);
		}

		variable.value = value;
	} // setVariable

	/**
	 * operation getVariable <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Test.Variable getVariable(String name) {
		for (int i = 0; i < this.variables.size(); i++) {
			if (this.variables.getValue(i).name.equals(name)) {
				return this.variables.getValue(i);
			}
		}

		return null;
	} // getVariable

	/**
	 * operation getType <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public fUML.Syntax.Classes.Kernel.Classifier getType(String typeName) {
		NamedElement element = this.getElement(typeName);

		if ((element == null) || !(element instanceof Classifier)) {
			return null;
		} else {
			return (Classifier) element;
		}
	} // getType

	/**
	 * operation printElements <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void printElements() {
		Debug.println("");
		Debug.println(elements.size() + " element(s)");
		Debug.println("---------");

		for (int i = 0; i < elements.size(); i++) {
			Debug.println(elements.getValue(i).name + ": "
					+ elements.getValue(i).getClass().getName());
		}
	} // printElements

	/**
	 * operation printExtent <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void printExtent(String classifierName) {
		Element element = this.getElement(classifierName);

		if (element == null) {
			Debug.println("[printExtent] " + classifierName + " not found.");
			return;
		}

		if (!(element instanceof Classifier)) {
			Debug.println("[printExtent] " + classifierName
					+ " is not a classifier.");
			return;
		}

		ExtensionalValueList extent = this.locus
				.getExtent((Classifier) element);

		Debug.println("");
		Debug.println(classifierName + " has " + extent.size()
				+ " instance(s):");

		for (int i = 0; i < extent.size(); i++) {
			Debug.println("[" + i + "] " + extent.getValue(i));
		}
	} // printExtent

	/**
	 * operation removeElement <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void removeElement(String elementName) {
		NamedElement element = this.getElement(elementName);

		if (element != null) {
			this.removeElement(element);
		} else {
			Debug
					.println("[removeElement] " + elementName
							+ " does not exist.");
		}
	} // removeElement

} // TestEnvironment
