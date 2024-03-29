package Objects.Bee;

import Application.Simulation.Simulation;
import Objects.Coordinates.Position;
import Objects.Coordinates.Vector.Polar;
import Objects.Coordinates.Vector.Vector;
import Objects.IBehaviour;

import java.io.Serializable;
import java.util.UUID;

/*
Вариант 11
        Объект – пчела.
        Бывают 2 видов: трутень и рабочий.
        Трутни рождаются каждые N1 секунд, если их количество менее K% от общего числа пчел, в противном случае – не рождаются вовсе.
        Рабочие рождаются каждые N2 секунд с вероятностью P.
*/
public abstract class Bee implements IBehaviour,Serializable {
    protected String typeBee;
    static public int countsAllBees = 0;
    protected UUID identifier;
    protected transient BeeGraphic beeGraphic;
    public BeeLife life;
    protected Polar speed ;
    protected Position current;
    public Position getCurrent() {
        return current;
    }
    public Bee(){
        generateIdentifier();
        countsAllBees++;
        speed = new Polar(Simulation.getSimulationSpeed()/10, (int)(Math.random() * 2*Math.PI));
    }

    private void generateIdentifier(){
        UUID randomIdentifier = UUID.randomUUID();
        identifier = randomIdentifier;
    };

    @Override
    public void move() {
        beeGraphic.go(speed.changeTypeToCartesian(), current);
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

    public void updateGraphic() {
        if (getTypeBees().equals("bee_drone".toString())) {
            beeGraphic = new BeeGraphic(current.getX(), current.getY(), getClass().getResource("/resourses/Pic/sprite_Drone.png").toString());
        }
        else {
            beeGraphic = new BeeGraphic(current.getX(), current.getY(), getClass().getResource("/resourses/Pic/sprite_Worker.png").toString());
        }
    }
}

