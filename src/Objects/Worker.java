package Objects;
//Кошки

import javafx.scene.image.ImageView;

public class Worker extends Bee {
    public static int countLions = 0;

    public Worker(ImageView imageView, int x, int y, int timeBorn, int timeLife){
        super(imageView,timeBorn,timeLife);
        setPosition(x,y);
        countLions++;
        typeAnimals = "Лев";
    }

    @Override
    public void updaTimeLiveAnimals(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
            countLions--;
            super.dicreementcountsAllAnimals();
        }
    }
}
