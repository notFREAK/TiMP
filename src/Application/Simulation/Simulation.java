package Application.Simulation;

public class Simulation {
    State state;

    Value simulationValue;

    public Simulation() {
        state = new State();
        simulationValue = new Value();
    }
    private int speedSimulation = 60;

    public int getSpeedSimulation() {
        return speedSimulation;
    }
    public State getState() {
        return state;
    }

    public Value getSimulationValue() {
        return simulationValue;
    }

    public void setSimulationValue(Value simulationValue) {
        this.simulationValue = simulationValue;
    }
}
