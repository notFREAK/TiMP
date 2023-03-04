package Objects;

import javafx.scene.image.ImageView;
public class Drone extends Bee {
    public static int countDrone = 0;

    public Drone(ImageView imageView, int x, int y, int timeBorn, int timeLife){
        super(imageView,timeBorn,timeLife);
        this.setPosition(x,y);
        countDrone++;
        typeAnimals = "Волк цирковой";
    }

    @Override
    public void updateTimeLiveAnimals(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
            countDrone--;
            super.decrementCountsAllAnimals();
        }
    }
}
