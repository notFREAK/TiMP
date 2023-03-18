package Objects.Drone;

import Objects.Bee.*;
import Objects.ILife;
import javafx.scene.image.ImageView;

public class Drone extends Bee implements ILife {
    public static int countDrone = 0;

    public Drone(int x, int y, int timeBorn, int timeLife){
        life = new DroneLife(timeBorn,timeLife);
        image = new DroneImage();
        image.setPosition(x,y);
        countDrone++;
        typeAnimals = "bee_drone";
    }

    @Override
    public void updateTimeLiveAnimals(){
        if (life.isDead()) {
            countDrone--;
            decrementCountsAllAnimals();
        }
    }
}
