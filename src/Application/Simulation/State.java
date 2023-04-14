package Application.Simulation;

public class State {

    private int stateSimulation = 3;

    public static final int  RUNNING = 1;
    public static final int  PAUSE = 2;
    public static final int  STOP = 3;

    public void setRunning() {
        stateSimulation = RUNNING;
    }

    public void setPause() {
        stateSimulation = PAUSE;
    }

    public void setStop() {
        stateSimulation = STOP;
    }

    public boolean isRunning() {
        if (stateSimulation == RUNNING) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isPause() {
        if (stateSimulation == PAUSE) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isStop() {
        if (stateSimulation == STOP) {
            return true;
        }
        else {
            return false;
        }
    }
}
