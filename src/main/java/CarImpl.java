public class CarImpl implements Car{

    private Engine engine;//assuming car only has engine for simplicity
    //a real car would have other parts for sure

    private int speed;// in Km/hr
    //there is a direct relation between car speed and engine speed, illustrated in the
    //Speed enum

    public CarImpl(Engine engine){
        this.engine=engine;
        speed=0;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void startCar(){
        System.out.println("Starting car, car speed= "+speed);
        engine.startEngine();
    }

    @Override
    public void stopCar(){
        if(speed>0){
            System.out.println("can't stop the car while it is moving" +
                    " please decelerate to speed=0 first");
        }
        else {
            engine.stopEngine();
            System.out.println("Stopped the car");
        }
    }

    @Override
    public void AccelerateCar(){
        speed=engine.advice(speed,Speed.CAR_ACCELERATION.getSpeed());
    }

    @Override
    public void brakeCar(){
        speed=engine.advice(speed,Speed.CAR_DECELERATION.getSpeed());
    }

}
