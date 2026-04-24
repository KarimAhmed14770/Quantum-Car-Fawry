

public class Main {
    public static void main(String[] args){

        /*Electric Engine test*/
        System.out.println("Electric Car test started");
        System.out.println();
        ElectricEngine electricEngine=new ElectricEngine();
        Car car=new CarImpl(electricEngine);


        car.startCar();
        car.AccelerateCar();
        car.AccelerateCar();
        car.brakeCar();
        car.stopCar();//testing if car could be stopped while it is moving, it should
        //throw an exception and handle it, but I will just print a statement for simplicity

        car.brakeCar();
        car.stopCar();

        System.out.println();
        System.out.println("Electric car test finished");
        System.out.println();

        /*replace the engine of the car with gasEngine, and testing it*/
        System.out.println("Gas Car test started");
        System.out.println();
        GasEngine gasEngine=new GasEngine();
        car.setEngine(gasEngine);
        car.startCar();
        car.AccelerateCar();
        car.AccelerateCar();
        car.brakeCar();
        car.stopCar();

        car.brakeCar();
        car.stopCar();

        System.out.println();
        System.out.println("Gas car test finished");
        System.out.println();


        /*replacing the engine with a hybrid engine*/
        System.out.println("Hybrid Car test started");
        System.out.println();
        HybridEngine hybridEngine =new HybridEngine(electricEngine,gasEngine);
        car.setEngine(hybridEngine);

        car.startCar();
        car.AccelerateCar();
        car.AccelerateCar();
        car.AccelerateCar();
        car.AccelerateCar();

        car.brakeCar();
        car.brakeCar();
        car.brakeCar();
        car.stopCar();//testing if car could be stopped while it is moving, it should
        //throw an exception and handle it, but I will just print a statement for simplicity

        car.brakeCar();
        car.stopCar();

        System.out.println();
        System.out.println("Hybrid car test finished");
        System.out.println();



    }
}
