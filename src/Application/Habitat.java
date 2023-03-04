package Application;

import Objects.Drone;
import Objects.Worker;
import javafx.scene.Node;
import javafx.scene.image.Image;

import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import Objects.Bee;


public class Habitat {

    private static final int SpaceWidth = 400;
    private static final int SpaceHeight = 400;
    private static final Image imageBackground = new Image();

    private static final Image imageLion = new Image();
    private static final Image imageWolf = new Image();

    private int NumberOfSecondsFirst;
    private int NumberOfCoefficientFirst;
    private int NumberOfSecondsSeconds;
    private int NumberOfProbabilitySeconds;
    private int timeLifeLion;
    private int timeLifeWolf;

     //массив циркачей
    private Collections collectionsAnimals = new Collections();

    public Habitat(){}

    public void update(int time, Pane pane) {
        if (this.canBornDrone(this.N1,this.P1,time))
        {
            Drone wolf= makeDrone(time);
            this.collectionsAnimals.adds(wolf);
            pane.getChildren().addAll(new Node[]{wolf.getImageView()});
        }

        if (this.canBornWorker(this.N2,this.K2,time))
        {
            Worker worker = makeWorker(time);
            this.collectionsAnimals.adds(worker);
            pane.getChildren().addAll(new Node[]{worker.getImageView()});
        }

        collectionsAnimals.updateCollectionsPerTime(pane);
    }

    //   Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.
    private boolean canBornDrone(int N1, int P1, int time){
        int randomVariation = (int)Math.floor(Math.random()*100);
        if(time % N1 == 0 && randomVariation<=P1) return true;
        return false;
    }

    private Drone makeDrone(int time){
        ImageView imageView = new ImageView(imageWolf);
        int x = (int)Math.floor(Math.random()*(SpaceWidth - Drone.ImageWidth));
        int y = (int)Math.floor(Math.random()*(SpaceHeight - Drone.ImageHeight));
        Drone drone = new Drone(imageView,x,y,time,timeLifeWolf);
        return drone;
    }

    //    Львы рождаются каждые N2 секунд
    private boolean canBornWorker(int N2, int P2, int time){
        int randomVariation = (int)Math.floor(Math.random()*100);
        if(time % N2 == 0 && randomVariation<=P2) return true;
        return false;
    }

    private Worker makeWorker(int time){
        ImageView imageView = new ImageView(imageLion);
        int x = (int)Math.floor(Math.random()*(SpaceWidth - Bee.ImageWidth));
        int y = (int)Math.floor(Math.random()*(SpaceHeight - Bee.ImageHeight));
        Worker Lion = new Worker(imageView,x,y,time,timeLifeLion);
        return Lion;
    }

    public void clear(){
        Bee.countsAllBees = 0;
        Worker.countWorker = 0;
        Drone.countDrone = 0;
        collectionsAnimals.clear();
    }

    public void setConditionsBornAnimals(int N1,int P1,int N2,int K2){
        this.N1 = N1;
        this.P1 = P1;
        this.N2 = N2;
        this.K2 = K2;
    };
    public void setConditionsTimeLifeAnimals(int timeLifeLion,int timeLifeWolf){
        this.timeLifeLion = timeLifeLion;
        this.timeLifeWolf = timeLifeWolf;
    };

    public ImageView getImageViewBackground() {
        ImageView imageViewBackground = new ImageView(imageBackground);
        imageViewBackground.setFitWidth(SpaceWidth);
        imageViewBackground.setFitHeight(SpaceHeight);
        return imageViewBackground;
    }

    public String getInfoAliveAnimals(){
        return collectionsAnimals.getAliveAnimals();
    }
}
