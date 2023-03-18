package Application;

import Objects.Drone.*;
import Objects.Worker.Worker;
import javafx.scene.Node;
import javafx.scene.image.Image;

import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import Objects.Bee.Bee;


public class Habitat {

    private static final int SpaceWidth = 400;
    private static final int SpaceHeight = 400;
    private static final Image imageBackground = new Image("C:\\MyProjects\\TiMP\\src\\Pic\\Background.png");
    private int NumberOfSecondsDrone;
    private int NumberOfCoefficientDrone;
    private int NumberOfSecondsWorker;
    private int NumberOfProbabilityWorker;
    private int timeLifeWorker;
    private int timeLifeDrone;
    private Collections collectionsBees = new Collections();

    public Habitat(){}

    public void update(int time, Pane pane) {
        if (this.canBornDrone(this.NumberOfSecondsDrone,this.NumberOfCoefficientDrone,time))
        {
            Drone drone= makeDrone(time);
            this.collectionsBees.adds(drone);
            pane.getChildren().addAll(new Node[]{drone.image.getImageView()});
        }

        if (this.canBornWorker(this.NumberOfSecondsWorker,this.NumberOfProbabilityWorker,time))
        {
            Worker worker = makeWorker(time);
            this.collectionsBees.adds(worker);
            pane.getChildren().addAll(new Node[]{worker.image.getImageView()});
        }

        collectionsBees.updateCollectionsPerTime(pane);
    }

    //   Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.
    private boolean canBornDrone(int N1, int P1, int time){
        int randomVariation = (int)Math.floor(Math.random()*100);
        if(time % N1 == 0 && randomVariation<=P1) return true;
        return false;
    }

    private Drone makeDrone(int time){
        int x = (int)Math.floor(Math.random()*(SpaceWidth - Drone.image.ImageWidth));
        int y = (int)Math.floor(Math.random()*(SpaceHeight - Drone.image.ImageHeight));
        Drone drone = new Drone(x,y,time, timeLifeDrone);
        return drone;
    }

    //    Львы рождаются каждые N2 секунд
    private boolean canBornWorker(int N2, int P2, int time){
        int randomVariation = (int)Math.floor(Math.random()*100);
        if(time % N2 == 0 && randomVariation<=P2) return true;
        return false;
    }

    private Worker makeWorker(int time){
        int x = (int)Math.floor(Math.random()*(SpaceWidth - Worker.image.ImageWidth));
        int y = (int)Math.floor(Math.random()*(SpaceHeight - Worker.image.ImageHeight));
        Worker worker = new Worker(x,y,time, timeLifeWorker);
        return worker;
    }

    public void clear(){
        Bee.countsAllBees = 0;
        Worker.countWorker = 0;
        Drone.countDrone = 0;
        collectionsBees.clear();
    }

    public void setConditionsBornAnimals(int NumberOfSecondsDrone,int NumberOfCoefficientDrone,int NumberOfSecondsWorker,int NumberOfProbabilityWorker){
        this.NumberOfSecondsDrone = NumberOfSecondsDrone;
        this.NumberOfCoefficientDrone = NumberOfCoefficientDrone;
        this.NumberOfSecondsWorker = NumberOfSecondsWorker;
        this.NumberOfProbabilityWorker = NumberOfProbabilityWorker;
    };
    public void setConditionsTimeLifeAnimals(int timeLifeLion,int timeLifeWolf){
        this.timeLifeWorker = timeLifeLion;
        this.timeLifeDrone = timeLifeWolf;
    };

    public ImageView getImageViewBackground() {
        ImageView imageViewBackground = new ImageView(imageBackground);
        imageViewBackground.setFitWidth(SpaceWidth);
        imageViewBackground.setFitHeight(SpaceHeight);
        return imageViewBackground;
    }

    public String getInfoAliveAnimals(){
        return collectionsBees.getAliveAnimals();
    }
}
