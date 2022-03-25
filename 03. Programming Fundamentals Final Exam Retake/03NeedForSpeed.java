import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class NeedForSpeed {
    static class Car{
        String carName;
        int mileage;
        int fuel;

        public String getCarName() {
            return carName;
        }

        public int getMileage() {
            return mileage;
        }

        public void setMileage(int mileage) {
            this.mileage = mileage;
        }

        public int getFuel() {
            return fuel;
        }

        public void setFuel(int fuel) {
            this.fuel = fuel;
        }

        public Car(String carName, int mileage, int fuel) {
            this.carName = carName;
            this.mileage = mileage;
            this.fuel = fuel;
        }
    }
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int number = Integer.parseInt(console.nextLine());
        HashMap <String, Car> carList = new LinkedHashMap<>();
        for (int i =0; i<number; i++){
            String[] carAttribute = console.nextLine().split("\\|");
            String carName = carAttribute[0];
            int mileage = Integer.parseInt(carAttribute[1]);
            int fuel = Integer.parseInt(carAttribute[2]);
            Car singleCar = new Car(carName, mileage, fuel);
            carList.put(carName, singleCar);
        }
        String command;
        while (!"Stop".equals(command=console.nextLine())){
            String[] commandAttribute = command.split(" : ");
            String event = commandAttribute[0];
            String carName = commandAttribute[1];
            switch (event){
                case "Drive":
                    int distance = Integer.parseInt(commandAttribute[2]);
                    int fuel = Integer.parseInt(commandAttribute[3]);
                    if(carList.get(carName).getFuel()<fuel) System.out.println("Not enough fuel to make that ride");
                    else {
                        carList.get(carName).setMileage(carList.get(carName).getMileage()+distance);
                        carList.get(carName).setFuel(carList.get(carName).getFuel()-fuel);
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", carName, distance, fuel);
                        if(carList.get(carName).getMileage()>=100000) {
                            carList.remove(carName);
                            System.out.println("Time to sell the " + carName + "!");
                        }
                    }
                    break;
                case "Refuel":
                    fuel = Integer.parseInt(commandAttribute[2]);
                    if(carList.get(carName).getFuel()+fuel > 75) {
                        fuel = 75 - carList.get(carName).getFuel();
                        carList.get(carName).setFuel(75);
                    }
                    else carList.get(carName).setFuel(carList.get(carName).getFuel()+fuel);
                    System.out.printf("%s refueled with %d liters%n", carName, fuel);
                    break;
                case "Revert":
                    int kilometers = Integer.parseInt(commandAttribute[2]);
                    carList.get(carName).setMileage(carList.get(carName).getMileage()-kilometers);
                    if(carList.get(carName).getMileage()< 10000) carList.get(carName).setMileage(10000);
                    else System.out.printf("%s mileage decreased by %d kilometers%n", carName, kilometers);
                    break;
            }
        }
        carList.forEach((key, value) -> System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n",
                value.getCarName(), value.getMileage(), value.getFuel()));
    }
}
