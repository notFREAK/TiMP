package Objects.Bee;

import Objects.ILife;

public abstract class BeeLife implements ILife {

    protected int timeBorn = 0;
    protected int timeLife = 0;
    protected boolean isDead = false;

    public BeeLife (int timeBorn, int timeLife) {
        this.timeBorn = timeBorn;
        this.timeLife = timeLife;
    }
    public int getTimeBorn() {
        return timeBorn;
    }

    public void setTimeLife(int timeLife){
        this.timeLife = timeLife;
    }

    public int getTimeLife() {
        return timeLife;
    }

    public boolean isDead() {
        return isDead;
    }

    public void update(){
        this.timeLife--;
        if (timeLife < 0) {
            isDead = true;
            updateCountOfDeadBees();
            Bee.decrementCountsAllBees();
        }
    }
}
