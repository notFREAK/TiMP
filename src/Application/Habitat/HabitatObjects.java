package Application.Habitat;

import Application.Collections.Collections;
import Application.Simulation.Value;
import Objects.Drone.DroneBaseAI;
import Objects.Worker.WorkerBaseAI;

public class HabitatObjects {
    protected Value value;
    protected  Collections collectionsBees = new Collections();
    protected  HabitatSize SpaceSize = new HabitatSize();

    protected int lastSecondsUpdate = 0;
    protected int lastSecondsDroneBirth = 0;

    protected int lastSecondsDroneDirectionChanged = 0;
    DroneBaseAI droneBaseAI = new DroneBaseAI();

    WorkerBaseAI workerBaseAI = new WorkerBaseAI();
    public void setSimulationValue(Value simulationValue){
        value = simulationValue;
    }

    public Value getValue() {
        return value;
    }

    public HabitatSize getSpaceSize() {
        return SpaceSize;
    }

    public Collections getCollectionsBees() {
        return collectionsBees;
    }

    public void setCollectionsBees(Collections collectionsBees) {
        this.collectionsBees = collectionsBees;
    }

    public void setSpaceSize(HabitatSize spaceSize) {
        SpaceSize = spaceSize;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
