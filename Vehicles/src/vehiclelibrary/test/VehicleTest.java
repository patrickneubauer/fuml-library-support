package vehiclelibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;
import vehiclelibrary.Vehicle;
import vehiclelibrary.VehicleTurn;

/**
 * This class tests the vehicle class.
 * 
 * @author p.neubauer
 *
 */
public class VehicleTest {
	
	@Test
	public void VehicleConstructorTest() {
		Vehicle vehicle1 = new Vehicle();
		assertTrue(vehicle1 instanceof Vehicle);
		assertEquals(vehicle1.doors, 4);
		assertEquals(vehicle1.seats, 4);
		assertEquals(vehicle1.wheels, 4);
	}
	
	@Test
	public void lastTurnTest() {
		Vehicle vehicle1 = new Vehicle();
		VehicleTurn lastTurn = vehicle1.lastTurn();
		assertEquals(VehicleTurn.NOTURN, lastTurn);
		vehicle1.nextTurn();
		assertTrue(vehicle1.lastTurn() != VehicleTurn.NOTURN);
	}
	
	@Test
	public void nextTurnTest() {
		Vehicle vehicle1 = new Vehicle();
		VehicleTurn nextTurn = vehicle1.nextTurn();
		assertTrue(nextTurn instanceof VehicleTurn);
	}

}
