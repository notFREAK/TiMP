package Application.Simulation;

public class Simulation {
    State state;

    Value simulationValue;

    public Simulation() {
        state = new State();
        simulationValue = new Value();
    }
    private int SimulationFPS = 60;

    public int getSimulationFPS() {
        return SimulationFPS;
    }

    public double getSimulationSpeed() {
        return 1000.0/((double)getSimulationFPS());
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
