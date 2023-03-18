package Objects.Bee;

import Objects.IBehaviour;

public abstract class BeeLife {

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

    public void setTImeLife(int timeLife){
        this.timeLife = timeLife;
    }

    public int getTimeLife() {
        return timeLife;
    }

    public boolean isDead() {
        return isDead;
    }

    public void updateTimeLiveAnimals(){
    }

}
