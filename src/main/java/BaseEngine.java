abstract public class BaseEngine implements Engine{
    protected int speed; //in RPM

    //created a base engine class so that I don't repeat the speed variable in
    //all engines that implement engine interface

    @Override
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    public void increaseSpeed(){
        speed++;
    }


    @Override
    public void decreaseSpeed(){
        if(speed>0){
            speed--;
        }
    }


    @Override
    public int advice(int carSpeed,int carAcceleration){
        if(carAcceleration>0){//checking whether it is acceleration or deceleration
            //checking the speed limit
            if(this.speed<(Speed.CAR_MAX_SPEED.getSpeed()*Speed.SPEED_RATIO.getSpeed()))
            {
                for(int i=0; i<carAcceleration*Speed.SPEED_RATIO.getSpeed();i++){
                    increaseSpeed();
                    carSpeed=this.speed/Speed.SPEED_RATIO.getSpeed();//updating car speed
                    System.out.println("Engine's Speed= "+this.speed +" RPM"+
                            " Car's speed= "+carSpeed+" Km/h");
                }

            }else{
                System.out.println("Max speed reached");
            }

        }else if(carAcceleration<0){
            if(carSpeed==0) return 0;//decelerating while car speed is already zero
            for(int i=0; i<Math.abs(carAcceleration)*Speed.SPEED_RATIO.getSpeed();i++){
                decreaseSpeed();
                carSpeed=this.speed/Speed.SPEED_RATIO.getSpeed();//updating car speed
                System.out.println("Engine's Speed= "+this.speed +" RPM"+
                        " Car's speed= "+carSpeed+" Km/h");
                if(carSpeed==0){
                    this.speed=0;
                    break;
                }
            }
        }

        return carSpeed;
    }
}
