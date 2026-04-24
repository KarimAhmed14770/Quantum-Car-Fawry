public interface Engine {
    void startEngine();
    void stopEngine();
    int getSpeed();
    public void increaseSpeed();
    public void decreaseSpeed();
    int advice(int carSpeed,int carAcceleration);

}
