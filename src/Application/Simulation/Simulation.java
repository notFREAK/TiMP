package Application.Simulation;

public class Simulation { //Singletone
    State state;

    Value simulationValue;

    public Simulation() {
        state = new State();
        simulationValue = new Value();
    }
    private static int SimulationFPS = 60;

    public static int getSimulationFPS() {
        return SimulationFPS;
    }

    public static double getSimulationSpeed() {
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
