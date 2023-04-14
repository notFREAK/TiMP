package Application.Habitat;

import Application.Collections.Collections;
import Application.Simulation.Value;

public class HabitatObjects {
    protected Value value;
    protected  Collections collectionsBees = new Collections();
    protected  HabitatSize SpaceSize = new HabitatSize();

    protected int lastSecondsDrone = 0;

    protected int lastSecondsWorker = 0;
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
