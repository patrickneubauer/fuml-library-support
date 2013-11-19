/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.Feature;
import fUML.Syntax.Classes.Kernel.NamedElement;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.Type;

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
	private HashMap<Class<?>, List<Field>> clazzFieldMap;
	private IntegrationLayer integrationLayer;

	public Object_Transformer() throws Exception {
		throw new Exception("Calling the default constructor is NOT ALLOWED.");
	}

	public Object_Transformer(Object_ fUmlPlaceholderObject, Object javaObject, IntegrationLayer integrationLayer) {

		this.fUmlObject = fUmlPlaceholderObject;
		this.javaObject = javaObject;
		this.integrationLayer = integrationLayer;
		this.clazzFieldMap = getAllFields(javaObject.getClass());
		transform();

	}

	/**
	 * Transforms the fUML {@link Object_} to represent the Java {@link Object}
	 */
	private void transform() {
		// TODO Create fUmlObject out of javaObject AND fUmlPlaceholderObject

		try {
			Class_ fUmlClass = fUmlObject.types.get(0);

			/*
			 * ... TODO add stuff to "fUmlClass" (Class_) if necessary
			 */

			fUmlObject.types.set(0, fUmlClass);

			// -------- end of transforming TYPE of fUML Object_ --------

			for (FeatureValue featureValue : fUmlObject.featureValues) {

				/**
				 * The following condition checks if the {@code featureValue}
				 * exists in the {@code javaClass}.
				 */				
				Class<?> javaClassFromObjectWithFeature = javaClassFromObjectWithFeature(javaObject, featureValue.feature);
				if (javaClassFromObjectWithFeature != null) {

					Field javaField = javaClassFromObjectWithFeature.getDeclaredField(featureValue.feature.name);
 					javaField.setAccessible(true); // shut down security
 					
					Debug.out(this, "Transforming field '" + javaField.getName() + "' of type " + javaField.getType().getName());
					
					if (javaField.get(javaObject) instanceof java.lang.Boolean) {

						boolean javaFieldValue = (boolean) javaField.get(javaObject);
						BooleanValue fUmlFieldValue = new BooleanValue();
						fUmlFieldValue.value = javaFieldValue;
						if (featureValue.values.size() == 0) {
							featureValue.values.add(0, fUmlFieldValue);
						} else {
							featureValue.values.set(0, fUmlFieldValue);
						}

					} else if (javaField.get(javaObject) instanceof java.lang.Integer) {

						int javaFieldValue = (int) javaField.get(javaObject);
						IntegerValue fUmlFieldValue = new IntegerValue();
						fUmlFieldValue.value = javaFieldValue;
						if (featureValue.values.size() == 0) {
							featureValue.values.add(0, fUmlFieldValue);
						} else {
							featureValue.values.set(0, fUmlFieldValue);
						}

					} else if (javaField.get(javaObject) instanceof java.lang.String) {

						String javaFieldValue = (String) javaField.get(javaObject);
						StringValue fUmlFieldValue = new StringValue();
						fUmlFieldValue.value = javaFieldValue;
						if (featureValue.values.size() == 0) {
							featureValue.values.add(0, fUmlFieldValue);
						} else {
							featureValue.values.set(0, fUmlFieldValue);
						}
						
					} else if (((Class<?>)javaField.getType()).isEnum()) {
						
						/* TODO Implement the case of an Enum
						 * In this case  javaClassFromObjectWithFeature(..) must be fixed as it returns the wrong Class 
						 * in case of an Enum javaField
						 */
						Debug.out(this, "Enum fields are currently not supported (skip)");

					} else if (javaField.get(javaObject) != null) {
						/*
						 * "javaField" is none of the above types (!)
						 * Therefore, it might be a {@link StructuredValue} i.e. an Object_ itself
						 */
						Debug.out(this, javaField.getType().getName() + " is a complex field");
						
						// Look for a corresponding fUML Object_
						Object_ fUmlFieldValue = integrationLayer.getFUmlObject(javaField.get(javaObject));
						
						if (fUmlFieldValue != null) {
							// Set corresponding fUML Object_ as feature value
							if (featureValue.values.size() == 0) {
								featureValue.values.add(0, fUmlFieldValue);
							} else {
								featureValue.values.set(0, fUmlFieldValue);
							}
						} else {
												
							Link link = new Link();
							Association association = new Association();
							Property parentProperty = new Property();			// Car property
							Property childProperty = null;						// SimpleEngine property
							
							parentProperty.class_ = fUmlClass;
							parentProperty.association = association;
							
							
							// Get the member property from the fUML Class
							for (NamedElement namedElement : fUmlClass.member) {
								if (namedElement.name.equals(featureValue.feature.name)) {
									childProperty = (Property)namedElement;
									// Add the field property to the Association
									association.memberEnd.add(childProperty);
								}
							}
							
							// ------------------------------------------------
							
							//Object newJavaObject = javaField.get(javaObject);
							Class<?> classOfJavaField = javaField.getType();
							Object newJavaObject = null;
							
							Object_ newFUmlObject = new Object_();
							
	 						/* TODO You may want to replace the following for loop by 
							 * childProperty.class_ = fUmlObject.types.get(0).ownerMember.FindTheCorrectPropertyByItsName.class_
							 * when "class_" becomes available (hence is not null anymore)
							 */
							if (childProperty != null && childProperty.association != null) {
		 						for (Type type : childProperty.association.endType) {
									if (!type.equals(fUmlClass)) {
										// This entails that there are only 2 endTypes and the one which is not the fUmlObject's Class
										// is the childProperty's Class
										childProperty.class_ = (Class_) type;
										newFUmlObject.types.add(childProperty.class_);
									}
								}
							}
	 						
	 						// Trying to instantiate the Java Field using it's Classes default constructor (if available)
	 						// If the Java Field is instantiated successfully, a corresponding Object_ is created
	 						
							try {
								newJavaObject = classOfJavaField.newInstance();
								Object_Creator object_Creator = new Object_Creator(newFUmlObject, newJavaObject, integrationLayer.getExecutionContext());
								newFUmlObject = object_Creator.getfUmlObject();
							} catch(Exception e) {
								/* NOTE: Exception of type InstantiationException will be thrown here for every Java Field 
								 * which is an unsupported complex field such as Lists, Maps etc.
								 * */
								Debug.out(this, "Java Field (" + javaField.getName() + ") of Type (" + classOfJavaField.getName() + ") is set to null. Default constructor private or not available or Java Field is an unsupported complex field. Skipping Java Field.");
							}
							
							// ------------------------------------------------
							
							association.memberEnd.add(parentProperty);				
							link.type = association;
							//link.addTo(executionContext.getLocus());  
							/* TODO Check where the Link instance has to be put
							 * Therefore, check where to add a fUML Link (UML association) between the newly 
							 * created complex Object_ and its parent Object_
							 */
							
							if (featureValue.values.size() == 0) {
								featureValue.values.add(0, newFUmlObject);
							} else {
								featureValue.values.set(0, newFUmlObject);
							}
						}
						
					}

				}

			}// end of for each FeatureValue loop

			// -------- end of adding FEATUREVALUE to fUML Object_ --------

		} catch (Exception e) {
			Debug.out(this, "Error occured while transforming the Java Object to a fUML Object_ representation. " + e);
		}

	}// transform

	/**
	 * Returns the {@link Class<?>} of a specific {@link Feature} in a given {@link Class<?>} by its name.
	 * If the given {@link Feature} is not found in the given {@link Class<?>}, it is looked up as an inherited field of {@link Class<?>}.
	 * 
	 * @param javaClass where to search the given {@link Feature}
	 * @param feature {@link Feature} to be searched
	 * @return java {@link Class<?>} that contains the given {@link Feature}, null otherwise (if it does not contain the feature)
	 */
	private Class<?> javaClassFromObjectWithFeature(Object javaObject, Feature feature) {
		Class<?> javaClass = javaObject.getClass();
		Field[] javaObjectFields = javaClass.getDeclaredFields();		// contains NON-inherited fields (only)
		
		if (feature.name != null && feature.name != "") {
			// Look if feature exists as a NON-inherited field
			for (Field javaObjectField : javaObjectFields) {
				if (javaObjectField.getName().equals(feature.name)) {
					return javaClass;
				}
			}
			
			// Look if feature exists as an inherited field
			for (Entry<Class<?>, List<Field>> entrySet : clazzFieldMap.entrySet()) {
				for (Field javaField : entrySet.getValue()) {
					if (javaField.getName().equals(feature.name)) {
						// return the corresponding Java Class (key) of the field (value)
						return entrySet.getKey();
					}
				}
			}
			
		}
		
		return null; // feature does NOT exist (neither NON-inherited nor inherited) in the Java Object
	}
	
	/**
	 * Initial idea comes from: http://stackoverflow.com/questions/3567372/access-to-private-inherited-fields-via-reflection-in-java
	 * It allows to access all fields (even private)
	 * 
	 * @param clazz
	 * @return a {@link HashMap} containing Java {@link Class<?>}'s and their corresponding {@link List<Field>}'s
	 */
	private HashMap<Class<?>, List<Field>> getAllFields(Class<?> clazz) {
		HashMap<Class<?>, List<Field>> clazzFieldBiMap = new HashMap<Class<?>, List<Field>>();
		return getAllFieldsRec(clazz, clazzFieldBiMap);
	}
	
	private HashMap<Class<?>, List<Field>> getAllFieldsRec(Class<?> clazz, HashMap<Class<?>, List<Field>> clazzFieldMap) {
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null) {
			getAllFieldsRec(superClazz, clazzFieldMap);
		}
		clazzFieldMap.put(clazz, Arrays.asList(clazz.getDeclaredFields()));
		return clazzFieldMap;
	}
	
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
