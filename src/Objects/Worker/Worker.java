package Objects.Worker;

import Objects.Bee.Bee;
import Objects.ILife;

public class Worker extends Bee implements ILife {
    public static int countWorker = 0;

    public Worker(){
        image = new WorkerImage();
    }
    public Worker(int x, int y, int timeBorn, int timeLife){
        life = new WorkerLife(timeBorn,timeLife);
        image = new WorkerImage();
        image.setPosition(x,y);
        countWorker++;
        typeBee = "bee_worker";
    }

    @Override
    public void updateTimeLiveAnimals(){
        if (life.isDead()) {
            countWorker--;
            decrementCountsAllAnimals();
        }
    }
}
