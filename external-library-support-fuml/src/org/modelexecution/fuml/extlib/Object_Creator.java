package org.modelexecution.fuml.extlib;

import java.lang.reflect.Field;

import org.modelexecution.fumldebug.core.ExecutionContext;
import org.modelexecution.fumldebug.core.event.Event;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Syntax.Classes.Kernel.Association;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.NamedElement;
import fUML.Syntax.Classes.Kernel.Property;
import fUML.Syntax.Classes.Kernel.Type;

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
	private ExecutionContext executionContext;
	

	public Object_Creator() throws Exception {
		throw new Exception("Calling the default constructor is NOT ALLOWED.");
	}
	
	public Object_Creator(Object_ fUmlObject, Object javaObject, ExecutionContext executionContext) {
	
		this.javaObject = javaObject;
		this.fUmlObject = fUmlObject;
		this.executionContext = executionContext;
		create();
		
	}

	private void create() {

		Class<?> javaClass = javaObject.getClass();
		Class_ fUmlClass = fUmlObject.types.get(0);
		
		for (Field javaField : javaClass.getDeclaredFields()) {
			
			try {

				javaField.setAccessible(true); // shut down security
				
				// Create new "feature" Property
				Property property = new Property();
				property.namespace = fUmlClass;
				property.owner = fUmlClass;
				property.redefinitionContext.add(fUmlClass);
				property.setName(javaField.getName());
		
				if (javaField.getType().getName().equals("boolean")) {
					
					boolean javaFieldValue = (boolean) javaField.get(javaObject);
					BooleanValue fUmlFieldValue = new BooleanValue();
					fUmlFieldValue.value = javaFieldValue;					
					
					FeatureValue featureValue = new FeatureValue();
					featureValue.values.add(fUmlFieldValue);
					featureValue.feature = property;
					
					fUmlObject.featureValues.add(featureValue);
					
				} else if (javaField.getType().getName().equals("int")) {
					
					int javaFieldValue = (int) javaField.get(javaObject);
					IntegerValue fUmlFieldValue = new IntegerValue();
					fUmlFieldValue.value = javaFieldValue;
					
					FeatureValue featureValue = new FeatureValue();
					featureValue.values.add(fUmlFieldValue);
					featureValue.feature = property;
					
					fUmlObject.featureValues.add(featureValue);
					
				} else if (javaField.getType().getName().equals("java.lang.String")) {
					
					String javaFieldValue = (String) javaField.get(javaObject);
					StringValue fUmlFieldValue = new StringValue();
					fUmlFieldValue.value = javaFieldValue;
					
					FeatureValue featureValue = new FeatureValue();
					featureValue.values.add(fUmlFieldValue);
					featureValue.feature = property;
					
					fUmlObject.featureValues.add(featureValue);
					
				} else if (((Class<?>)javaField.getType()).isEnum()) {
					
					/* TODO Implement the case of an Enum
					 * In this case  javaClassFromObjectWithFeature(..) must be fixed as it returns the wrong Class 
					 * in case of an Enum javaField
					 */

				} else {
					
					Debug.out("Object_Creator: Creating a new complex object");
					
					Link link = new Link();
					Association association = new Association();
					Property parentProperty = new Property();			// Car property
					Property childProperty = null;						// SimpleEngine property
					
					parentProperty.class_ = fUmlClass;
					parentProperty.association = association;
					
					// Get the field property from the fUML Class
					for (NamedElement namedElement : fUmlClass.ownedMember) {
						if (namedElement.name.equals(javaField.getName())) {
							childProperty = (Property)namedElement;
							// Add the field property to the Association
							association.memberEnd.add(childProperty);
							break; // since correct namedElement has been found
						}
					}
					
					// ------------------------------------------------
					
					Class<?> classOfJavaField = javaField.getType();
					Object newJavaObject = null;
					
					Object_ newFUmlObject = new Object_();
					
					if (childProperty != null && childProperty.association != null) {
						for (Type type : childProperty.association.endType) {
							if (!type.equals(fUmlClass)) {
								// This entails that there are only 2 endTypes and the one which is not the fUmlObject's Class
								// is the childProperty's Class
								childProperty.class_ = (Class_) type;
								newFUmlObject.types.add(childProperty.class_);
								break;  // since correct type has been found
							}
						}
					}
				
					// Trying to instantiate the Java Field using it's Classes default constructor (if available)
					// If the Java Field is instantiated successfully, a corresponding Object_ is created
						
					try {
						newJavaObject = classOfJavaField.newInstance();
						Object_Creator object_Creator = new Object_Creator(newFUmlObject, newJavaObject, executionContext);
						newFUmlObject = object_Creator.getfUmlObject();
					} catch(Exception e) {
						Debug.out("Object_Transformer: Java Field (" + javaField.getName() + ") of Type (" + classOfJavaField.getName() + ") is set to null. Private default constructor? Exception: " + e);
					}
					
					// ------------------------------------------------
					
					FeatureValue featureValue = new FeatureValue();
					featureValue.values.add(newFUmlObject);
					featureValue.feature = property;
					
					// ------------------------------------------------
					
					// Add a fUML Link (UML association) between the newly created complex Object_ and its parent Object_
					association.memberEnd.add(parentProperty);				
					link.type = association;
					//link.addTo(executionContext.getLocus()); // TODO Check where the Link instance has to be put
					
					fUmlObject.featureValues.add(featureValue);
					
				} 
				
			} catch (Exception e) {
				Debug.out("Error occured while transforming the Java Object to a fUML Object_ representation. " + e);
			}
			
		}// end of for each Field loop
			
	}// create
		


	public Object_ getfUmlObject() {
		return fUmlObject;
	}// getfUmlObject

}
