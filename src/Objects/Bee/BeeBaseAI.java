package Objects.Bee;

import Application.AppManager;
import Application.Simulation.Simulation;
import Application.TImer.Time;
import Objects.IBehaviour;
import javafx.application.Platform;

public abstract class BeeBaseAI extends Thread {
    public abstract void move();
    private volatile boolean isActive = false;

    protected AppManager appManager;

    public synchronized void startAI(AppManager appManager) {
        isActive = true;
        notify();
        this.appManager = appManager;
    }

    public synchronized void stopAI() {
        isActive = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!isActive) {
                    synchronized (this) {
                        wait();
                    }
                }
                /*for (Bee bee : appManager.getHabitat().getCollectionsBees().getArrayList()) {
                    if (bee.getTypeBees() == "bee_worker") {
                        int x = bee.beeGraphic.getX();
                        int y = bee.beeGraphic.getY();
                        double[] speedPolar = bee.getVectorSpeedPolar();

                        Graphic.setX((int) (x + (speedPolar[0] * Math.cos(speedPolar[1]))));
                        bee.beeGraphic.setY((int) (y + (speedPolar[0] * Math.sin(speedPolar[1]))));
                        if (Math.abs(bee.positionHabitat[0] - bee.beeGraphic.getX()) < 10 || Math.abs(bee.positionHabitat[1] - bee.beeGraphic.getY()) < 10)
                            goHome = true;
                        bee.setVectorSpeedPolar(speedPolar);
                    }
                }*/
                Thread.sleep((int)Math.round(Simulation.getSimulationSpeed()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setAIPriority(int priority) {
        setPriority(priority);
    }

    public int getAIPriority() {
        return getPriority();
    }
}
