package vehiclelibrary;

import java.util.Random;

/**
 * This class represents a Vehicle.
 * 
 * @author p.neubauer
 *
 */
public class Vehicle {
	public int doors;
	public int seats;
	public int wheels;
	public VehicleTurn lastTurn;
	
	public Vehicle() {
		doors = 4;
		seats = 4;
		wheels = 4;
		lastTurn = VehicleTurn.NOTURN;
	}
	
	/**
	 * This method returns the current average wheel air pressure.
	 * 
	 * @return the current average wheel air pressure.
	 */
	public double currentAverageWheelAirPressure() {
		Random random = new java.util.Random();
		return random.nextDouble();
	}
	
	/**
	 * This method returns the last turn performed by the vehicle.
	 * 
	 * @return last vehicle turn.
	 */
	public VehicleTurn lastTurn() {
		return lastTurn;
	}
	
	/**
	 * This method performs the next vehicle turn.
	 * 
	 * @return the turn the vehicle has performed.
	 */
	public VehicleTurn nextTurn() {
		Random random = new java.util.Random();
		int randomInt = random.nextInt(3);
		VehicleTurn turn;
		if (randomInt == 0) {
			turn = VehicleTurn.LEFT;
		} else if (randomInt == 1) {
			turn = VehicleTurn.RIGHT;
		} else if (randomInt == 2) {
			turn = VehicleTurn.UTURN;
		} else {
			turn = VehicleTurn.NOTURN;
		}
		lastTurn = turn;
		return lastTurn;
	}

}
