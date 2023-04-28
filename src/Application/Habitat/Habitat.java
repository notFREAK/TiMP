package Application.Habitat;

import Application.AppManager;
import Objects.Bee.BeeGraphic;
import Objects.Drone.*;
import Objects.Worker.Worker;
import javafx.scene.Node;

import javafx.scene.layout.Pane;
import Objects.Bee.Bee;


public class Habitat extends HabitatObjects{


    public Habitat(){
        Drone.DroneBaseAI.start();
        Worker.WorkerBaseAI.start();
    }

    public void StartAi() {
        Drone.DroneBaseAI.startAI();
        Worker.WorkerBaseAI.startAI();
    }

    public void StopAi() {
        Drone.DroneBaseAI.stopAI();
        Worker.WorkerBaseAI.stopAI();
    }

    public void Update(int time, Pane pane) {
        try {
            if (time != lastSecondsUpdate) {
                lastSecondsUpdate = time;
                System.out.print(Bee.countsAllBees +" "+ Drone.countDrone+ " " + Worker.countWorker + " ");
                if (this.canBornWorker(value.getValueSecondsWorker(), value.getValueProbabilityWorker(), time)) {
                    Worker worker = makeWorker(time);
                    this.collectionsBees.adds(worker);
                    pane.getChildren().addAll(new Node[]{worker.getGraphic().getImageView()});
                }
                if (this.canBornDrone(value.getValueSecondsDrone(), value.getValueCoefficientDrone(), time)) {
                    Drone drone = makeDrone(time);
                    this.collectionsBees.adds(drone);
                    pane.getChildren().addAll(new Node[]{drone.getGraphic().getImageView()});
                }
                collectionsBees.updateCollectionsPerTime();
                collectionsBees.updateCollectionsForDead(pane);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean canBornDrone(int ValueSecondsDrone, int ValueCoefficientDrone, int time){
        if ((time-lastSecondsDroneBirth) % ValueSecondsDrone == 0 && Drone.countsAllBees != 0 && ((Drone.countDrone+1)*100)/(Drone.countsAllBees+1) <= ValueCoefficientDrone) {
            lastSecondsDroneBirth = time;
            return true;
        }
        return false;
    }

    private Drone makeDrone(int time){
        int x = (int)Math.floor(Math.random()*(SpaceSize.getWidth() - BeeGraphic.getImageWidth()));
        int y = (int)Math.floor(Math.random()*(SpaceSize.getHeight() - BeeGraphic.getImageHeight()));
        Drone drone = new Drone(x,y,time, value.getValueLifeTimeDrone());
        return drone;
    }

    private boolean canBornWorker(int ValueSecondsWorker, int ValueProbabilityWorker, int time){
        if (time % ValueSecondsWorker == 0 && ((int)Math.floor(Math.random()*100))<=ValueProbabilityWorker) {
            return true;
        }
        return false;
    }

    private Worker makeWorker(int time){
        int x = (int)Math.floor(Math.random()*(SpaceSize.getWidth() - BeeGraphic.getImageWidth()));
        int y = (int)Math.floor(Math.random()*(SpaceSize.getHeight() - BeeGraphic.getImageHeight()));
        Worker worker = new Worker(x,y,time, value.getValueLifeTimeWorker());
        return worker;
    }

    public void clear(){
        Bee.countsAllBees = 0;
        Worker.countWorker = 0;
        Drone.countDrone = 0;
        collectionsBees.clear();
    }



   public String getInfoAliveAnimals(){
        return collectionsBees.getAliveAnimals();
    }
};
