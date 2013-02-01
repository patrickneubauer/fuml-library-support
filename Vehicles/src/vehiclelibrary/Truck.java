package vehiclelibrary;

/**
 * This class represents a Truck.
 * 
 * @author p.neubauer
 *
 */
public class Truck extends Vehicle {
	public boolean isPickup;
	
	// by default, the Truck is a Pickup Truck
	public Truck() {
		isPickup = true;
	}
	
	public Truck(boolean aPickup) {
		this();
		isPickup = aPickup;
	}
	
	public Truck(int numberOfDoors, int numberOfSeats, int numberOfWheels, boolean aPickup) {
		doors = numberOfDoors;
		seats = numberOfSeats;
		wheels = numberOfWheels;
		isPickup = aPickup;
	}
	
	public String toString() {
		return "This " + (isPickup ? "pickup-" : "") + "truck has " + seats + " seats, " + doors + " doors and " + wheels + " wheels.";
	}
	
}
