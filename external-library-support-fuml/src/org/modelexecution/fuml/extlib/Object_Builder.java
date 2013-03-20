/**
 * 
 */
package org.modelexecution.fuml.extlib;

import java.lang.reflect.Field;

import org.modelexecution.fumldebug.core.event.Event;

import fUML.Semantics.Classes.Kernel.BooleanValue;
import fUML.Semantics.Classes.Kernel.FeatureValue;
import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.Classes.Kernel.ValueList;
import fUML.Syntax.Classes.Kernel.Class_;
import fUML.Syntax.Classes.Kernel.PrimitiveType;
import fUML.Syntax.Classes.Kernel.Property;

/**
 * Builder for the {@link Object_} class
 * 
 * @author Patrick Neubauer
 * 
 */
public class Object_Builder {

	private Object_ fUmlObject;
	private Object javaObject;
	private Event event;

	public Object_Builder() throws Exception {
		throw new Exception("Calling the default constructor is NOT ALLOWED.");
	}

	/**
	 * Builds an instance of {@link Object_} out of a corresponding Java
	 * {@link Object}
	 * 
	 * @param event
	 *            {@link Event} representing a {@link CreateObjectAction}
	 * @param javaObject
	 *            Java {@link Object} to be used to build the {@link Object_}
	 */
	public Object_Builder(Event event, Object javaObject) {

		this.fUmlObject = new Object_();
		this.javaObject = javaObject;
		this.event = event;
		build();

	}

	/**
	 * Builds a fUML {@link Object_} out of a Java {@link Object}
	 */
	private void build() {

		try {
			// add Class_ as type to types (Class_List)
			Class_ clazz = new Class_();
			clazz.setName(EventHelper.obtainClassName(event));
			clazz.qualifiedName = javaObject.getClass().getName();
			// ... TODO add more stuff to "clazz" (Class_)

			// add Class_ to Object_ as its type
			fUmlObject.types.add(clazz);

			// -------- end of adding TYPE to fUML Object_ --------

			// add a FeatureValue for each Feature (Property) of Class_
			Field[] fields = javaObject.getClass().getFields(); // returns all public fields

			for (Field field : fields) {
				FeatureValue featureValue = new FeatureValue();
				Property feature = new Property(); // single Feature

				feature.setName(field.getName()); // variable name (e.g. "isPickup")
				feature.qualifiedName = field.getDeclaringClass().getName().replace(".", "::") + "::" + field.getName();
				// ... TODO add more stuff to "feature" (Property)

				// add Feature to FeatureValue
				featureValue.feature = feature;

				ValueList valueList = new ValueList(); // multiple Value's

				// find out the type of the field
				if (field.getType().getName().equals("boolean")) {
					// field is a {@link BooleanValue}
					BooleanValue fUmlFieldValue = new BooleanValue();

					// accessing the Java Object's value
					Field javaObjectField = javaObject.getClass().getDeclaredField(field.getName());
					javaObjectField.setAccessible(true);
					fUmlFieldValue.value = (boolean) javaObjectField.get(javaObject);

					PrimitiveType primitiveType = new PrimitiveType();
					primitiveType.name = "boolean";

					// add {@link PrimitiveType} to field value type
					fUmlFieldValue.type = primitiveType;

					// add single value to list of value's
					valueList.add(fUmlFieldValue);

				} else if (field.getType().getName().equals("int")) {
					// field is an {@link IntegerValue}
					IntegerValue fUmlFieldValue = new IntegerValue();

					// accessing the Java Object's value
					Field javaObjectField = javaObject.getClass().getDeclaredField(field.getName());
					javaObjectField.setAccessible(true);
					fUmlFieldValue.value = (int) javaObjectField.get(javaObject);

					PrimitiveType primitiveType = new PrimitiveType();
					primitiveType.name = "int";

					// add {@link PrimitiveType} to field value type
					fUmlFieldValue.type = primitiveType;

					// add single value to list of value's
					valueList.add(fUmlFieldValue);

				} else if (field.getType().getName().equals("java.lang.String")) {
					// field is a {@link StringValue}
					StringValue fUmlFieldValue = new StringValue();

					PrimitiveType primitiveType = new PrimitiveType();
					primitiveType.name = "java.lang.String";

					// add {@link PrimitiveType} to field value type
					fUmlFieldValue.type = primitiveType;

					// accessing the Java Object's value
					Field javaObjectField = javaObject.getClass().getDeclaredField(field.getName());
					javaObjectField.setAccessible(true);
					fUmlFieldValue.value = (String) javaObjectField.get(javaObject);

					// add single value to list of value's
					valueList.add(fUmlFieldValue);

				} else {
					// field is a {@link StructuredValue} i.e. an Object_ itself
					// (!)
					// TODO implement the case when the field is itself an
					// Object (i.e. an appropriate Object_ has to be created)
				}

				// add the value list to the FeatureValue's list
				featureValue.values = valueList;

				// add FeatureValues to Object_
				fUmlObject.featureValues.add(featureValue);

			}// end of for each field loop

			// -------- end of adding FEATUREVALUE to fUML Object_ --------

		} catch (Exception e) {
			System.out.println("Error occured while trying to obtain the fUML object. " + e);
		}

	}

	/**
	 * Returns the {@link Object_} that has been build when calling the
	 * constructor {@code Object_Builder(Event event, Object javaObject)} of
	 * {@link Object_Builder}
	 * 
	 * @return the {@link Object_} builded
	 */
	public Object_ getObject_() {
		return fUmlObject;
	}

}
