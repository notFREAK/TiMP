package Objects.Bee;

import Application.AppManager;
import Application.Simulation.Simulation;
import Objects.IBehaviour;
import javafx.application.Application;
import javafx.application.Platform;

public abstract class BeeBaseAI extends Thread {
    public abstract void move(Bee bee);
    private volatile boolean isActive = false;

    int priority = 5;
    String typeBees;

    public void setTypeBees(String string) {
        typeBees = string;
    }
    public synchronized void startAI() {
        isActive = true;
        setAIPriority(priority);
        notify();
        AppManager.getInstance().logsPrint("Bees AI has been turned on. Current priority - " + priority);
    }

    public synchronized void stopAI() {
        isActive = false;
        AppManager.getInstance().logsPrint("Bees AI has been turned off");
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
                    if (bee.getTypeBees() == typeBees) {
                        move(bee);
                        IBehaviour behaviour = bee;
                        behaviour.move();
                    }
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
        if (isActive())
            setPriority(priority);
        else
            this.priority = priority;
    }

    public int getAIPriority() {
        if (isActive())
            return getPriority();
        else
            return this.priority;
    }
}
