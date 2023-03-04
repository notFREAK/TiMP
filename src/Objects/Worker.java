package Objects;

import javafx.scene.image.ImageView;

public class Worker extends Bee {
    public static int countWorker = 0;
    public Worker(ImageView imageView, int x, int y, int timeBorn, int timeLife){
        super(imageView,timeBorn,timeLife);
        setPosition(x,y);
        countWorker++;
        typeAnimals = "Лев";
    }
    @Override
    public void updateTimeLiveAnimals(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
            countWorker--;
            super.decrementCountsAllAnimals();
        }
    }
}
