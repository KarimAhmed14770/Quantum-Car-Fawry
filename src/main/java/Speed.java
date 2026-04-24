public enum Speed {
    CAR_MAX_SPEED(200),
    SPEED_RATIO(4),     //Engine speed=Car speed*speed ratio
    CAR_ACCELERATION(20),
    CAR_DECELERATION(-20),
    HYBRID_ENGINE_THRESHOLD(50);

    //just making this enum instead of hardcoding for more readability

    private final int speed;

    private Speed(int speed){
        this.speed=speed;
    }

    public int getSpeed() {
        return speed;
    }
}
