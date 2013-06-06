/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.lang.reflect.Field;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Syntax.Actions.IntermediateActions.CreateObjectAction;
import fUML.Syntax.Classes.Kernel.Class_;

/**
 * Transformer for the {@link Object_} class
 * 
 * Java Object + fUML Placeholder Object_ ==== TRANSFORM ====> fUML Object_
 * 
 * @author Patrick Neubauer
 * 
 */
public class Object_Transformer {

	private Object_ fUmlObject;
	private Object javaObject;
	private Event event;
	private ExecutionContext executionContext;

	public Object_Transformer() throws Exception {
		throw new Exception("Calling the default constructor is NOT ALLOWED.");
	}

	public Object_Transformer(Object_ fUmlPlaceholderObject, Object javaObject, Event event, ExecutionContext executionContext) {

		this.fUmlObject = fUmlPlaceholderObject;
		this.javaObject = javaObject;
		this.event = event;
		this.executionContext = executionContext;
		transform();

	}

	/**
	 * Transforms the fUML {@link Object_} to represent the Java {@link Object}
	 */
	private void transform() {
		// TODO Create fUmlObject out of javaObject AND fUmlPlaceholderObject

		try {
			Class_ fUmlClass = fUmlObject.types.get(0);
			Class<?> javaClass = javaObject.getClass();

			/*
			 * ... TODO add stuff to "fUmlClass" (Class_) if necessary
			 */

			fUmlObject.types.set(0, fUmlClass);

			// -------- end of transforming TYPE of fUML Object_ --------

			for (FeatureValue featureValue : fUmlObject.featureValues) {

				/**
				 * The following condition checks if the {@code featureValue}
				 * exists in the {@code javaClass}. NOTE: inherited fields are
				 * currently SKIPPED (!) but could be somehow recursively
				 * accessed through getSuperclass()
				 */
				if (FeatureHelper.getFeatureValueNamespaceAndClass(featureValue).equals(javaClass.getName())) {

					Field javaField = javaClass.getDeclaredField(featureValue.feature.name);
					javaField.setAccessible(true); // shut down security

					if (javaField.getType().getName().equals("boolean")) {

						boolean javaFieldValue = (boolean) javaField.get(javaObject);
						BooleanValue fUmlFieldValue = new BooleanValue();
						fUmlFieldValue.value = javaFieldValue;
						if (featureValue.values.size() == 0) {
							featureValue.values.add(0, fUmlFieldValue);
						} else {
							featureValue.values.set(0, fUmlFieldValue);
						}

					} else if (javaField.getType().getName().equals("int")) {

						int javaFieldValue = (int) javaField.get(javaObject);
						IntegerValue fUmlFieldValue = new IntegerValue();
						fUmlFieldValue.value = javaFieldValue;
						if (featureValue.values.size() == 0) {
							featureValue.values.add(0, fUmlFieldValue);
						} else {
							featureValue.values.set(0, fUmlFieldValue);
						}

					} else if (javaField.getType().getName().equals("java.lang.String")) {

						String javaFieldValue = (String) javaField.get(javaObject);
						StringValue fUmlFieldValue = new StringValue();
						fUmlFieldValue.value = javaFieldValue;
						if (featureValue.values.size() == 0) {
							featureValue.values.add(0, fUmlFieldValue);
						} else {
							featureValue.values.set(0, fUmlFieldValue);
						}

					} else {
						/*
						 * field is a {@link StructuredValue} i.e. an Object_
						 * itself (!)
						 * 
						 * TODO implement the case when the field is itself an
						 * Object (i.e. a corresponding Object_ has itself to be
						 * transformed probably using recursion on
						 * Object_Transformer
						 */
						System.out.println("Object_Transformer: Creating a new complex object");
						
//						Object newJavaObject = javaField.get(javaObject);
//						Object_Creator object_Creator = new Object_Creator(newJavaObject);
//						Object_ newFUmlObject = object_Creator.getfUmlObject();
//						
//						if (featureValue.values.size() == 0) {
//							featureValue.values.add(0, newFUmlObject);
//						} else {
//							featureValue.values.set(0, newFUmlObject);
//						}
						
					}

				}

			}// end of for each FeatureValue loop

			// -------- end of adding FEATUREVALUE to fUML Object_ --------

		} catch (Exception e) {
			System.out.println("Error occured while transforming the Java Object to a fUML Object_ representation. " + e);
		}

	}// transform
	
	/**
	 * Returns the {@link Object_} that has been build when calling the
	 * constructor of {@link Object_Builder}
	 * 
	 * @return the {@link Object_} builded
	 */
	public Object_ getObject_() {
		return fUmlObject;
	}// getObject_

}
