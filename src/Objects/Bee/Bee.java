package Objects.Bee;

import Objects.Drone.DroneImage;
import Objects.Drone.DroneLife;
import Objects.IBehaviour;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/*
Вариант 11
        Объект – пчела.
        Бывают 2 видов: трутень и рабочий.
        Трутни рождаются каждые N1 секунд, если их количество менее K% от общего числа пчел, в противном случае – не рождаются вовсе.
        Рабочие рождаются каждые N2 секунд с вероятностью P.
*/
public abstract class Bee{
    protected String typeAnimals;
    static public int countsAllBees = 0;
    protected int identifier = 0;

    public BeeImage image;
    public BeeLife life;

    public Bee(){
        generateIdentifier();
        countsAllBees++;
    }

    private void generateIdentifier(){
        int randomIdentifier = (int)Math.floor(Math.random()*10000);
        identifier = randomIdentifier;
    };

    public int getIdentifier() {
        return identifier;
    }

    public String getTypeAnimals() {
        return typeAnimals;
    }

    protected void decrementCountsAllAnimals(){
        countsAllBees--;
    }

}
