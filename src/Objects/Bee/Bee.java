package Objects.Bee;

import Application.Simulation.Simulation;
import Objects.Coordinates.Vector.Polar;
import Objects.Coordinates.Vector.Vector;
import Objects.IBehaviour;

import java.util.UUID;

/*
Вариант 11
        Объект – пчела.
        Бывают 2 видов: трутень и рабочий.
        Трутни рождаются каждые N1 секунд, если их количество менее K% от общего числа пчел, в противном случае – не рождаются вовсе.
        Рабочие рождаются каждые N2 секунд с вероятностью P.
*/
public abstract class Bee implements IBehaviour{
    protected String typeBee;
    static public int countsAllBees = 0;
    protected UUID identifier;
    protected BeeGraphic beeGraphic;
    public BeeLife life;
    protected Polar speed ;

    public Bee(){
        generateIdentifier();
        countsAllBees++;
        speed = new Polar(0.1 * Simulation.getSimulationSpeed(), (int)(Math.random() * 2*Math.PI));
    }

    private void generateIdentifier(){
        UUID randomIdentifier = UUID.randomUUID();
        identifier = randomIdentifier;
    };

    @Override
    public void move() {
        beeGraphic.go(speed.changeTypeToCartesian());
    }
    public BeeGraphic getGraphic() {
        return beeGraphic;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getTypeBees() {
        return typeBee;
    }

    public static void decrementCountsAllBees(){
        Bee.countsAllBees--;
    }
}

