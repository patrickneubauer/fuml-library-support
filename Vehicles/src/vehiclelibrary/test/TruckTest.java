package vehiclelibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import vehiclelibrary.Truck;

/**
 * This class tests the truck class.
 * 
 * @author p.neubauer
 *
 */
public class TruckTest {

	@Test
	public void TruckDefaultConstructorTest() {
		Truck truck1 = new Truck();
		assertTrue(truck1 instanceof Truck);
		assertEquals(true, truck1.isPickup);
	}
	
	@Test
	public void TruckPickupContructorTest() {
		Truck truck1 = new Truck(false);
		assertTrue(truck1 instanceof Truck);
		assertEquals(false, truck1.isPickup);
	}
	
	@Test
	public void TruckAllValuesConstructorTest() {
		int numberOfDoors = 2;
		int numberOfSeats = 3;
		int numberOfWheels = 4;
		boolean aPickup = true;
		
		Truck truck1 = new Truck(numberOfDoors, numberOfSeats, numberOfWheels, aPickup);
		assertTrue(truck1 instanceof Truck);
		assertEquals(numberOfDoors, truck1.doors);
		assertEquals(numberOfSeats, truck1.seats);
		assertEquals(numberOfWheels, truck1.wheels);
		assertEquals(aPickup, truck1.isPickup);
	}
	
	@Test
	public void toStringTest() {
		Truck truck1 = new Truck();
		assertEquals("This pickup-truck has 4 seats, 4 doors and 4 wheels.", truck1.toString());
	}
	
}
