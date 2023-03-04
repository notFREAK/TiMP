package Objects;
//Собаки
//Собаки генерируются каждые N2 секунд с вероятностью P2.
import javafx.scene.image.ImageView;
public class Drone extends Bee {
    public static int countWolves = 0;

    public Drone(ImageView imageView, int x, int y, int timeBorn, int timeLife){
        super(imageView,timeBorn,timeLife);
        this.setPosition(x,y);
        countWolves++;
        typeAnimals = "Волк цирковой";
    }

    @Override
    public void updaTimeLiveAnimals(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
            countWolves--;
            super.dicreementcountsAllAnimals();
        }
    }
}
