package Objects.Bee;

import Application.AppManager;
import Application.Simulation.Simulation;
import Objects.IBehaviour;
import javafx.application.Platform;

public abstract class BeeBaseAI extends Thread {
    public abstract void move(Bee bee);
    private volatile boolean isActive = false;

    public synchronized void startAI() {
        isActive = true;
        notify();
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
                for (Bee bee : AppManager.getInstance().getHabitat().getCollectionsBees().getArrayList()) {
                    move(bee);
                    IBehaviour behaviour = bee;
                    behaviour.move();
                }
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
