package Application.Simulation;

public class State {

    private StateSimulation stateSimulation;

    public StateSimulation getStateSimulation() {
        return stateSimulation;
    }

    public void setRunning() {
        stateSimulation = StateSimulation.RUNNING;
    }

    public void setPause() {
        stateSimulation = StateSimulation.PAUSE;
    }

    public void setStop() {
        stateSimulation = StateSimulation.STOP;
    }

    public boolean isRunning() {
        if (stateSimulation == StateSimulation.RUNNING) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isPause() {
        if (stateSimulation == StateSimulation.PAUSE) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isStop() {
        if (stateSimulation == StateSimulation.STOP) {
            return true;
        }
        else {
            return false;
        }
    }
}
