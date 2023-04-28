package Objects.Bee;

import Objects.Coordinates.Vector;

import java.util.UUID;

/*
Вариант 11
        Объект – пчела.
        Бывают 2 видов: трутень и рабочий.
        Трутни рождаются каждые N1 секунд, если их количество менее K% от общего числа пчел, в противном случае – не рождаются вовсе.
        Рабочие рождаются каждые N2 секунд с вероятностью P.
*/
public abstract class Bee {
    protected String typeBee;
    static public int countsAllBees = 0;
    protected UUID identifier;
    protected BeeGraphic beeGraphic;
    public BeeLife life;
    protected Vector speed;

    public Bee(){
        generateIdentifier();
        countsAllBees++;
    }

    private void generateIdentifier(){
        UUID randomIdentifier = UUID.randomUUID(); //UUID
        identifier = randomIdentifier;
    };

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

