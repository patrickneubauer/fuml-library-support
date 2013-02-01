package vehiclelibrary;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Car car1 = new Car();
		System.out.println(car1);
		
		MotorCycle motorCycle1 = new MotorCycle();
		System.out.println(motorCycle1);
		
		Truck truck1 = new Truck();
		System.out.println(truck1);		
		
		System.out.println("car1 current average air pressure = " + car1.currentAverageWheelAirPressure());
		System.out.println("car1 next turn = " + car1.nextTurn());
		System.out.println("car1 next turn = " + car1.nextTurn());
		System.out.println("car1 next turn = " + car1.nextTurn());
	}
	
}
