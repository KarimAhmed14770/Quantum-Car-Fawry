public class ElectricEngine extends BaseEngine{

    @Override
    public void startEngine(){
        System.out.println("Started Electric Engine, engine speed= "+speed);
    }

    @Override
    public void stopEngine() {
        if (speed == 0) {
            System.out.println("Stopped Electric Engine");
        }else{
            System.out.println("can't stop the car while engine speed>0," +
                    "please decelerate first");
        }
    }

}
