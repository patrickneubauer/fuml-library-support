package org.modelexecution.fuml.extlib.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.modelexecution.fuml.extlib.DynamicClassLoader;

/**
 * Class that tests:
 * <ul>
 * <li>Create instances of Classes located in external Jar files</li>
 * <li>Invoke methods of Classes located in external Jar files</li>
 * </ul>
 * 
 * @author patrickneubauer
 * 
 */
public class DynamicClassLoaderTest {

	/**
	 * Test that creates an instance and calls a method of a Class (Car) from
	 * inside a JAR file using Java Reflection. The method's return type is a
	 * String.
	 */
	@Test
	public void carClassMethodTest() {
		String jarPath = "extlibs/Vehicles.jar";
		String classToLoad = "vehiclelibrary.Car";
		String methodToLoad = "toString";

		String[] jarPaths = new String[] { jarPath };
		DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(jarPaths);
		ClassLoader classLoader = dynamicClassLoader.getClassLoader();

		try {
			Class<?> carClass = classLoader.loadClass(classToLoad);
			Method toStringMethod = carClass.getDeclaredMethod(methodToLoad, null);
			toStringMethod.setAccessible(true); // shut down security

			Object carObject = carClass.newInstance();

			String toStringReturnValue = (String) toStringMethod.invoke(carObject, null);
			assertEquals("This car has 4 seats, 4 doors and 4 wheels.", toStringReturnValue);

		} catch (ClassNotFoundException | NoSuchMethodException e) {
			System.out.println("Couldn't find the requested Class and/or Method. Details: " + e);
		} catch (InstantiationException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + ". Details: " + e);
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + " and/or invoke the Method " + methodToLoad
					+ ". Details: " + e);
		} catch (IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Couldn't invoke the Method " + methodToLoad + ". Details: " + e);
		}
	}// carClassMethodTest

	/**
	 * Test that creates an instance and calls a method of a Class (Vehicle)
	 * from inside a JAR file using Java Reflection. The method's return type is
	 * an Enum.
	 */
	@Test
	public void vehicleClassEnumReturnNoTurnTest() {
		String jarPath = "extlibs/Vehicles.jar";
		String classToLoad = "vehiclelibrary.Vehicle";
		String methodToLoad = "lastTurn";

		String[] jarPaths = new String[] { jarPath };
		DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(jarPaths);
		ClassLoader classLoader = dynamicClassLoader.getClassLoader();

		try {
			Class<?> vehicleClass = classLoader.loadClass(classToLoad);
			Method lastTurnMethod = vehicleClass.getDeclaredMethod(methodToLoad, null);
			lastTurnMethod.setAccessible(true); // shut down security

			Object vehicleObject = vehicleClass.newInstance();

			Enum lastTurnReturnValue = (Enum) lastTurnMethod.invoke(vehicleObject, null);
			assertEquals("NOTURN", lastTurnReturnValue.toString());

		} catch (ClassNotFoundException | NoSuchMethodException e) {
			System.out.println("Couldn't find the requested Class and/or Method. Details: " + e);
		} catch (InstantiationException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + ". Details: " + e);
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + " and/or invoke the Method " + methodToLoad
					+ ". Details: " + e);
		} catch (IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Couldn't invoke the Method " + methodToLoad + ". Details: " + e);
		}
	}// vehicleClassEnumReturnNoTurnTest

	/**
	 * Test that creates an instance and calls 2 methods of a Class (Vehicle)
	 * from inside a JAR file using Java Reflection. The 1st method modifies a
	 * member variable of the object and returns it. The 2nd method (only)
	 * returns the modified member variable of the object. At the end both are
	 * compared, s.t. the test only succeeds if both return values are equal.
	 */
	@Test
	public void vehicleClassEnumReturnSomeTurnTest() {
		String jarPath = "extlibs/Vehicles.jar";
		String classToLoad = "vehiclelibrary.Vehicle";

		String[] jarPaths = new String[] { jarPath };
		DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(jarPaths);
		ClassLoader classLoader = dynamicClassLoader.getClassLoader();

		try {
			Class<?> vehicleClass = classLoader.loadClass(classToLoad);
			Method nextTurnMethod = vehicleClass.getDeclaredMethod("nextTurn", null);
			nextTurnMethod.setAccessible(true); // shut down security

			Object vehicleObject = vehicleClass.newInstance();

			Enum nextTurnReturnValue = (Enum) nextTurnMethod.invoke(vehicleObject, null);
			assertFalse(nextTurnReturnValue.toString().equals("NOTURN"));

			// check if the last turn equals the next turn just established
			// above
			Method lastTurnMethod = vehicleClass.getDeclaredMethod("lastTurn", null);
			Enum lastTurnReturnValue = (Enum) lastTurnMethod.invoke(vehicleObject, null);
			assertSame(nextTurnReturnValue, lastTurnReturnValue);

		} catch (ClassNotFoundException | NoSuchMethodException e) {
			System.out.println("Couldn't find the requested Class and/or Method. Details: " + e);
		} catch (InstantiationException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + ". Details: " + e);
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + " and/or invoke the Method. Details: " + e);
		} catch (IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Couldn't invoke a Method. Details: " + e);
		}
	}// vehicleClassEnumReturnSomeTurnTest

	/**
	 * Test that creates an instance and calls a method of a Class (Truck) from
	 * inside a JAR file using Java Reflection. The instance of Truck is created
	 * by calling its non-default constructor with a primitive parameter
	 * (boolean).
	 * 
	 * Instead of <code>
	 *   Method someMethod = someClass.getDeclaredMethod("methodName", parametersToPass);  
	 * </code>
	 * 
	 * we do (example) <code>
	 *   Class[] constructorParameters = new Class[]{Boolean.TYPE};
	 *   Constructor someConstructor = someClass.getDeclaredConstructor(constructorParameters);  
	 * </code>
	 */
	@Test
	public void truckClassPassParametersToConstructorTest() {
		String jarPath = "extlibs/Vehicles.jar";
		String classToLoad = "vehiclelibrary.Truck";

		String[] jarPaths = new String[] { jarPath };
		DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(jarPaths);
		ClassLoader classLoader = dynamicClassLoader.getClassLoader();

		try {
			Class<?> truckClass = classLoader.loadClass(classToLoad);
			Class[] constructorParameters = new Class[] { Boolean.TYPE };

			// Provide the parameter(s) with value(s)
			boolean isPickup = false; // by default it is a pickup truck
			Object[] params = new Object[] { new Boolean(isPickup) };

			Constructor truckConstructorWithPickupSpecification = truckClass.getDeclaredConstructor(constructorParameters);
			truckConstructorWithPickupSpecification.setAccessible(true); // shut
																			// down
																			// security

			// create an instance of Truck by passing a parameter to its
			// constructor
			Object truckObject = truckConstructorWithPickupSpecification.newInstance(isPickup);

			// check if truckObject is not a pickup truck
			Method toStringMethod = truckClass.getDeclaredMethod("toString", null);
			String toStringReturnValue = (String) toStringMethod.invoke(truckObject, null);
			// Note: a pickup truck is called "pickup-truck" in the toString
			// method
			// while a non pickup truck is simply called "truck"
			assertEquals("This truck has 4 seats, 4 doors and 4 wheels.", toStringReturnValue);

		} catch (ClassNotFoundException | NoSuchMethodException e) {
			System.out.println("Couldn't find the requested Class and/or Method. Details: " + e);
		} catch (InstantiationException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + ". Details: " + e);
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't create an instance of the Class " + classToLoad + " and/or invoke the Method. Details: " + e);
		} catch (IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Couldn't invoke a Method. Details: " + e);
		}

	}// truckClassPassParametersToConstructorTest

	/**
	 * Test that accesses a method in a class that is inherited from a super
	 * class
	 * 
	 * Requires a "getSuperClass()" call: <code>
	 *   whatEverClass.getSuperclass().getDeclaredMethod("methodName", parametersToPass);
	 * </code>
	 */
	@Test
	public void carClassInheritedMethodTest() {
		String jarPath = "extlibs/Vehicles.jar";
		String methodToLoad = "lastTurn";

		String[] jarPaths = new String[] { jarPath };
		DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(jarPaths);
		ClassLoader classLoader = dynamicClassLoader.getClassLoader();

		try {
			Class<?> carClass = classLoader.loadClass("vehiclelibrary.Car");
			Method lastTurnMethod = carClass.getSuperclass().getDeclaredMethod(methodToLoad, null);
			lastTurnMethod.setAccessible(true); // shut down security

			Object carObject = carClass.newInstance();

			Enum lastTurnReturnValue = (Enum) lastTurnMethod.invoke(carObject, null);
			assertEquals("NOTURN", lastTurnReturnValue.toString());

		} catch (ClassNotFoundException | NoSuchMethodException e) {
			System.out.println("Couldn't find the requested Class and/or Method. Details: " + e);
		} catch (InstantiationException e) {
			System.out.println("Couldn't create an instance of the Class. Details: " + e);
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't create an instance of the Class and/or invoke the Method " + methodToLoad + ". Details: " + e);
		} catch (IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Couldn't invoke the Method " + methodToLoad + ". Details: " + e);
		}
	}// carClassInheritedMethodTest

	/**
	 * Test that accesses a private field in a class that is inherited from a
	 * super class
	 * 
	 * Example if the field is of type "String": <code>
	 *   Field someField = someClass.getSuperclass().getDeclaredField("fieldName");
	 *   ...
	 *   String someFieldReturnValue = (String) someField.get(someObjectOfClass);
	 * </code>
	 */
	@Test
	public void carClassInheritedPrivateFieldTest() {
		String jarPath = "extlibs/Vehicles.jar";
		String fieldToLoad = "vehicleId";

		String[] jarPaths = new String[] { jarPath };
		DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(jarPaths);
		ClassLoader classLoader = dynamicClassLoader.getClassLoader();

		try {
			Class<?> carClass = classLoader.loadClass("vehiclelibrary.Car");
			Field vehicleIdField = carClass.getSuperclass().getDeclaredField(fieldToLoad);
			vehicleIdField.setAccessible(true); // shut down security

			Object carObject = carClass.newInstance();

			// reading from a private Field
			String vehicleIdReturnValue = (String) vehicleIdField.get(carObject);
			assertTrue(vehicleIdReturnValue.contains("vehicle_"));

			// writing to a private Field
			vehicleIdField.set(carObject, "vehicle_007");
			// reading the value back from the private Field to check if the
			// writing was successful
			String newVehicleIdReturnValue = (String) vehicleIdField.get(carObject);
			assertEquals("vehicle_007", newVehicleIdReturnValue);

		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find the requested Class and/or Method. Details: " + e);
		} catch (InstantiationException e) {
			System.out.println("Couldn't create an instance of the Class. Details: " + e);
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't create an instance of the Class and/or get the Field " + fieldToLoad + ". Details: " + e);
		} catch (IllegalArgumentException | NoSuchFieldException e) {
			System.out.println("Couldn't get the Field " + fieldToLoad + ". Details: " + e);
		}

	}// carClassInheritedPrivateFieldTest

}// DynamicClassLoaderTest
