package Objects.Drone;

import Objects.Bee.BeeLife;

public class DroneLife extends BeeLife {

    public DroneLife(int timeBorn, int timeLife) {
        super(timeBorn, timeLife);
    }

    @Override
    public void updateTimeLiveAnimals(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
        }
    }
}
