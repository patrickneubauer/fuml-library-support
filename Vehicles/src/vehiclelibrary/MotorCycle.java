package vehiclelibrary;

/**
 * This class represents a Motorcycle.
 * 
 * @author p.neubauer
 *
 */
public class MotorCycle extends Vehicle {
	public MotorCycle() {
		seats = 1;
		doors = 0;
		wheels = 2;
	}
	
	public void setSeats(int numberOfSeats) {
		seats = numberOfSeats;
	}
	
	public String toString() {
		return "This motorcycle has " + seats + " seat(s), " + doors + " door(s) and " + wheels + " wheels."; 
	}
}
