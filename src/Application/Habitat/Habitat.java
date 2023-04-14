package Application.Habitat;

import Application.Collections.Collections;
import Application.Simulation.Value;
import Objects.Drone.*;
import Objects.Worker.Worker;
import javafx.scene.Node;

import javafx.scene.layout.Pane;
import Objects.Bee.Bee;


public class Habitat extends HabitatObjects{


    public Habitat(){}

    public void update(int time, Pane pane) {
        if (this.canBornDrone(value.getValueSecondsDrone(), value.getValueCoefficientDrone(), time))
        {
            Drone drone= makeDrone(time);
            //this.collectionsBees.adds(drone);
            pane.getChildren().addAll(new Node[]{drone.image.getImageView()});
        }

        if (this.canBornWorker(value.getValueSecondsWorker(),value.getValueProbabilityWorker(),time))
        {
            Worker worker = makeWorker(time);
            //this.collectionsBees.adds(worker);
            pane.getChildren().addAll(new Node[]{worker.image.getImageView()});
        }

       // collectionsBees.updateCollectionsPerTime(pane);
    }

    private boolean canBornDrone(int ValueSecondsDrone, int ValueCoefficientDrone, int time){
        if (time != lastSecondsDrone && time % ValueSecondsDrone == 0 && Drone.countsAllBees != 0 && ((Drone.countDrone*100)/Drone.countsAllBees) < ValueCoefficientDrone) {
            lastSecondsWorker = time;
            return true;
        }
        lastSecondsDrone = time;
        return false;
    }

    private Drone makeDrone(int time){
        int x = (int)Math.floor(Math.random()*(SpaceSize.getWidth() - new Drone().image.ImageWidth));
        int y = (int)Math.floor(Math.random()*(SpaceSize.getHeight() - new Drone().image.ImageHeight));
        Drone drone = new Drone(x,y,time, value.getValueLifeTimeDrone());
        return drone;
    }

    private boolean canBornWorker(int ValueSecondsWorker, int ValueProbabilityWorker, int time){
        if (time != lastSecondsWorker && time % ValueSecondsWorker == 0 && ((int)Math.floor(Math.random()*100))<=ValueProbabilityWorker) {
            lastSecondsWorker = time;
            return true;
        }
        lastSecondsWorker = time;
        return false;
    }

    private Worker makeWorker(int time){
        int x = (int)Math.floor(Math.random()*(SpaceSize.getWidth() - new Worker().image.ImageWidth));
        int y = (int)Math.floor(Math.random()*(SpaceSize.getHeight() - new Worker().image.ImageHeight));
        Worker worker = new Worker(x,y,time, value.getValueLifeTimeWorker());
        return worker;
    }

    public void clear(){
        Bee.countsAllBees = 0;
        Worker.countWorker = 0;
        Drone.countDrone = 0;
       // collectionsBees.clear();
    }



   /* public String getInfoAliveAnimals(){
        return collectionsBees.getAliveAnimals();
    }*/
};
