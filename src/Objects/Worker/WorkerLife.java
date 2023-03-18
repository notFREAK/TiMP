package Objects.Worker;

import Objects.Bee.BeeLife;
import Objects.ILife;

public class WorkerLife extends BeeLife implements ILife {
    public WorkerLife(int timeBorn, int timeLife) {
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
