package solid;

public class Car {
	private int max_fuel;
	private int remain_fuel;
	
	Car(int fuel) {
		max_fuel = fuel;
		remain_fuel = fuel;
	}

	void setRemainFuel(int fuel) {
		remain_fuel = fuel;
	}

	int getRemainFuel() {
		return remain_fuel;
	}

	int getMaxFuel()
	{
		return max_fuel;
	}

	void run() {
		setRemainFuel(getRemainFuel()-1);
	}

	void refill() {
		remain_fuel = max_fuel;
	}
	//주유 refill() 기능은 Car 클래스보다는 주유소 클래스에서 처리해야할 기능이다

}


//public class Car {
//	private int max_fuel;
//	private int remain_fuel;
//	
//	Car(int fuel) {
//		max_fuel = fuel;
//		remain_fuel = fuel;
//	}
//
//	void setRemainFuel(int fuel) {
//		remain_fuel = fuel;
//	}
//
//	int getRemainFuel() {
//		return remain_fuel;
//	}
//
//	int getMaxFuel()
//	{
//		return max_fuel;
//	}
//
//	void run() {
//		setRemainFuel(getRemainFuel()-1);
//	}
//
//	void refill() {
//		remain_fuel = max_fuel;
//	}
//	//주유 refill() 기능은 Car 클래스보다는 주유소 클래스에서 처리해야할 기능이다
//
//}
//
//
//public class GasStation extends Car{
//	void refill() {
//		super.setRemainFuel(getMaxFuel());
//	}
//}
