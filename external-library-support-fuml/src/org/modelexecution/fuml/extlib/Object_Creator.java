package org.modelexecution.fuml.extlib;

import java.lang.reflect.Field;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;

/**
 * Creator for the {@link Object_} class
 * 
 * Java Object ==== CREATE ====> fUML Object_
 * 
 * @author Patrick Neubauer
 * 
 */
public class Object_Creator {
	
	private Object javaObject;
	private Object_ fUmlObject;
	

	public Object_Creator() throws Exception {
		throw new Exception("Calling the default constructor is NOT ALLOWED.");
	}
	
	public Object_Creator(Object javaObject) {
	
		this.javaObject = javaObject;
		this.fUmlObject = new Object_();
		create();
		
	}

	private void create() {

		Class<?> javaClass = javaObject.getClass();
		
		for (Field javaField : javaClass.getDeclaredFields()) {
			
			try {

				javaField.setAccessible(true); // shut down security
		
				if (javaField.getType().getName().equals("boolean")) {
					
					boolean javaFieldValue = (boolean) javaField.get(javaObject);
					BooleanValue fUmlFieldValue = new BooleanValue();
					fUmlFieldValue.value = javaFieldValue;
					
					FeatureValue featureValue = new FeatureValue();
					// TODO Set type etc. of the featureValue ?
					featureValue.values.add(fUmlFieldValue);
					
					if (fUmlObject.featureValues.size() == 0) {
						fUmlObject.featureValues.add(0, featureValue);
					} else {
						fUmlObject.featureValues.set(0, featureValue);
					}
					
				} else if (javaField.getType().getName().equals("int")) {
					
					int javaFieldValue = (int) javaField.get(javaObject);
					IntegerValue fUmlFieldValue = new IntegerValue();
					fUmlFieldValue.value = javaFieldValue;
					
					FeatureValue featureValue = new FeatureValue();
					featureValue.values.add(fUmlFieldValue);
					
					if (fUmlObject.featureValues.size() == 0) {
						fUmlObject.featureValues.add(0, featureValue);
					} else {
						fUmlObject.featureValues.set(0, featureValue);
					}
					
				} else if (javaField.getType().getName().equals("java.lang.String")) {
					
					String javaFieldValue = (String) javaField.get(javaObject);
					StringValue fUmlFieldValue = new StringValue();
					fUmlFieldValue.value = javaFieldValue;
					
					FeatureValue featureValue = new FeatureValue();
					featureValue.values.add(fUmlFieldValue);
					
					if (fUmlObject.featureValues.size() == 0) {
						fUmlObject.featureValues.add(0, featureValue);
					} else {
						fUmlObject.featureValues.set(0, featureValue);
					}
					
				} else {
					
					System.out.println("Object_Creator: Creating a new complex object");
					
					Object newJavaObject = javaField.get(javaObject);
					Object_Creator object_Creator = new Object_Creator(newJavaObject);
					Object_ newFUmlObject = object_Creator.getfUmlObject();
					
					FeatureValue featureValue = new FeatureValue();
					featureValue.values.add(newFUmlObject);
					
					if (fUmlObject.featureValues.get(0).values.size() == 0) {
						fUmlObject.featureValues.add(0, featureValue);
					} else {
						fUmlObject.featureValues.add(0, featureValue);
					}
					
				} 
				
			} catch (Exception e) {
				System.out.println("Error occured while transforming the Java Object to a fUML Object_ representation. " + e);
			}
			
		}// end of for each Field loop
			
	}// create
		


	public Object_ getfUmlObject() {
		return fUmlObject;
	}// getfUmlObject

}
