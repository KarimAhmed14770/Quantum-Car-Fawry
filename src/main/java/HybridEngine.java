public class HybridEngine extends BaseEngine {
    private Engine currentEngine;
    private  ElectricEngine electricEngine;
    private  GasEngine gasEngine;

    public HybridEngine(ElectricEngine electricEngine, GasEngine gasEngine){
        this.electricEngine=electricEngine;
        this.gasEngine=gasEngine;
        currentEngine=electricEngine;//always start with the electric engine at speed =0
    }

    public ElectricEngine getElectricEngine() {
        return electricEngine;
    }

    public void setElectricEngine(ElectricEngine electricEngine) {
        this.electricEngine = electricEngine;
    }

    public GasEngine getGasEngine() {
        return gasEngine;
    }

    public void setGasEngine(GasEngine gasEngine) {
        this.gasEngine = gasEngine;
    }

    @Override
    public void startEngine(){
        System.out.println("Started Hybrid Engine, engine speed= "+speed);
    }

    @Override
    public void stopEngine(){
        if (speed == 0) {
            System.out.println("Stopped Hybrid Engine");
        }else{
            System.out.println("can't stop the car while engine speed>0," +
                    "please decelerate first");
        }
    }


    @Override
    public int advice(int carSpeed,int carAcceleration) {
        if (carAcceleration > 0) {//checking whether we are accelerating or decelerating
            if(carSpeed>Speed.HYBRID_ENGINE_THRESHOLD.getSpeed()){
                //if the speed is already above the threshold at acceleration
                //then simply we are using the gasEngine for accelerating

                //here i could simply use currentEngine.advice(carSpeed,carAcceleration)
                //however i didn't use it to visualize the internal speed logs when testing
                for(int i=0; i<carAcceleration*Speed.SPEED_RATIO.getSpeed();i++){
                    currentEngine.increaseSpeed();
                    this.speed=currentEngine.getSpeed();
                    carSpeed=this.speed/Speed.SPEED_RATIO.getSpeed();//updating car speed
                    System.out.println("Gas Engine's Speed= "+this.currentEngine.getSpeed() +" RPM"+
                            "Electric Engine's Speed= "+this.electricEngine.getSpeed()+" RPM"+
                            "Hybrid Engine's Speed= "+this.speed+" RPM"+
                            " Car's speed= "+carSpeed+" Km/h");
                }
            }
            else{
                //if the speed is below the threshold at acceleration
                //then a switch might happen between the electric and gas engine if
                //the acceleration resulted into passing the threshold
                for(int i=0; i<carAcceleration*Speed.SPEED_RATIO.getSpeed();i++){
                    currentEngine.increaseSpeed();
                    this.speed=currentEngine.getSpeed();
                    carSpeed=this.speed/Speed.SPEED_RATIO.getSpeed();//updating car speed
                    if(carSpeed>Speed.HYBRID_ENGINE_THRESHOLD.getSpeed() && currentEngine!=gasEngine){
                        //we must decelerate the electric engine and engage the gas engine

                        //first step is to increase gas engine speed
                        gasEngine.startEngine();//starting the gas engine
                        while(gasEngine.getSpeed()<Speed.HYBRID_ENGINE_THRESHOLD.getSpeed()*Speed.SPEED_RATIO.getSpeed()){
                            gasEngine.increaseSpeed();
                            System.out.println("Engaging gas engine, Gas engine speed: "+gasEngine.getSpeed()+ " RPM");
                        }
                        //engage the gas engine
                        currentEngine=gasEngine;
                        //second step is to decelerate the electric engine and stop it
                        while(electricEngine.getSpeed()>0){
                            electricEngine.decreaseSpeed();
                            System.out.println("Disengaging electric engine, electric engine speed: "+electricEngine.getSpeed()
                            +" RPM");
                        }
                        electricEngine.stopEngine();//stopping the electric engine

                    }
                    System.out.println("Gas Engine's Speed= "+this.gasEngine.getSpeed() +" RPM "+
                            "Electric Engine's Speed= "+this.electricEngine.getSpeed()+" RPM "+
                            "Hybrid Engine's Speed= "+this.speed+" RPM, "+
                            "Car's speed= "+carSpeed+" Km/h");
                }


            }

        } else if (carAcceleration < 0) {
            if(carSpeed<Speed.HYBRID_ENGINE_THRESHOLD.getSpeed()){
                //if the speed is already below the threshold at deceleration
                //then simply decelerate the electric engine
                for(int i=0; i<Math.abs(carAcceleration*Speed.SPEED_RATIO.getSpeed());i++){
                    currentEngine.decreaseSpeed();
                    this.speed=currentEngine.getSpeed();
                    carSpeed=this.speed/Speed.SPEED_RATIO.getSpeed();//updating car speed
                    System.out.println("Gas Engine's Speed= "+this.gasEngine.getSpeed() +" RPM, "+
                            "Electric Engine's Speed= "+this.electricEngine.getSpeed()+" RPM, "+
                            "Hybrid Engine's Speed= "+this.speed+" RPM, "+
                            "Car's speed= "+carSpeed+" Km/h");
                }
            }
            else{
                //if the speed is higher than the threshold at deceleration
                //then a switch might happen between the electric and gas engine if
                //the deceleration resulted into reaching speed below the threshold
                for(int i=0; i<Math.abs(carAcceleration*Speed.SPEED_RATIO.getSpeed());i++){
                    currentEngine.decreaseSpeed();
                    this.speed=currentEngine.getSpeed();
                    carSpeed=this.speed/Speed.SPEED_RATIO.getSpeed();//updating car speed
                    if(carSpeed<Speed.HYBRID_ENGINE_THRESHOLD.getSpeed() && currentEngine!=electricEngine){
                        //we must decelerate the gas engine and engage the electric engine

                        //first step is to increase electric engine speed
                        electricEngine.startEngine();//starting the gas engine
                        while(electricEngine.getSpeed()<Speed.HYBRID_ENGINE_THRESHOLD.getSpeed()*Speed.SPEED_RATIO.getSpeed()){
                            electricEngine.increaseSpeed();
                            System.out.println("Engaging electric engine, electric engine speed: "+electricEngine.getSpeed()+ " RPM");
                        }
                        //engage the gas engine
                        currentEngine=electricEngine;
                        //second step is to decelerate the electric engine and stop it
                        while(gasEngine.getSpeed()>0){
                            gasEngine.decreaseSpeed();
                            System.out.println("Disengaging gas engine, gas engine speed: "+gasEngine.getSpeed()
                                    +" RPM");
                        }
                        gasEngine.stopEngine();//stopping the gas engine

                    }
                    System.out.println("Gas Engine's Speed= "+this.gasEngine.getSpeed() +" RPM "+
                            "Electric Engine's Speed= "+this.electricEngine.getSpeed()+" RPM "+
                            "Hybrid Engine's Speed= "+this.speed+" RPM, "+
                            "Car's speed= "+carSpeed+" Km/h");
                }


            }
        }

        return carSpeed;
    }
}
