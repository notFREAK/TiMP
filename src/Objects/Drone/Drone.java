package Objects.Drone;

import Application.Simulation.Simulation;
import Objects.Bee.*;

public class Drone extends Bee {
    public static int countDrone = 0;

    //private static BeeBaseAI;

    public Drone(){
    }
    public Drone(int x, int y, int timeBorn, int timeLife){
        life = new BeeLife(timeBorn, timeLife) {
            @Override
            public void updateCountOfDeadBees() {
                countDrone--;
            }
        };
        beeGraphic = new BeeGraphic(getClass().getResource("/resourses/Pic/sprite_Drone.png").toString());
        countDrone++;
        typeBee = "bee_drone";
    }
}
