package Objects.Worker;

import Objects.Bee.Bee;
import Objects.Drone.DroneImage;
import Objects.Drone.DroneLife;
import Objects.ILife;
import javafx.scene.image.ImageView;

public class Worker extends Bee implements ILife {
    public static int countWorker = 0;

    public Worker(int x, int y, int timeBorn, int timeLife){
        life = new WorkerLife(timeBorn,timeLife);
        image = new WorkerImage();
        image.setPosition(x,y);
        countWorker++;
        typeAnimals = "bee_worker";
    }

    @Override
    public void updateTimeLiveAnimals(){
        if (life.isDead()) {
            countWorker--;
            decrementCountsAllAnimals();
        }
    }
}
