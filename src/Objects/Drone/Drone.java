package Objects.Drone;

import Application.AppManager;
import Application.Habitat.HabitatSize;
import Application.Simulation.Simulation;
import Objects.Bee.*;

public class Drone extends Bee {
    public static int countDrone = 0;
    public int ValueDroneChangeDirection = 3;
    public int DroneLastChangeDirectionTime = 0;
    public static BeeBaseAI DroneBaseAI = new BeeBaseAI() {
        @Override
        public void move(Bee bee) {
            if (bee.getTypeBees() == "bee_drone") {
                bee.ChangeDirection();

            }
        }
    };

    public Drone() {
    }

    public Drone(int x, int y, int timeBorn, int timeLife) {
        life = new BeeLife(timeBorn, timeLife) {
            @Override
            public void updateCountOfDeadBees() {
                countDrone--;
            }
        };
        beeGraphic = new BeeGraphic(x, y, getClass().getResource("/resourses/Pic/sprite_Drone.png").toString());
        countDrone++;
        typeBee = "bee_drone";
    }

    @Override
    public void ChangeDirection() {
        if (DroneLastChangeDirectionTime != AppManager.getInstance().getTimer().getSeconds() && DroneLastChangeDirectionTime % ValueDroneChangeDirection != 0) {
            DroneLastChangeDirectionTime = AppManager.getInstance().getTimer().getSeconds();
            speed.setSecondCoordinate(Math.random() * Math.PI);
            speed.setFirstCoordinate(0.1* Simulation.getSimulationFPS());
        }
        if (beeGraphic.getCurrent().getX() < 0 || beeGraphic.getCurrent().getX() > HabitatSize.getWidth() - BeeGraphic.getImageWidth()) {
            speed.setFirstCoordinate(0);
        }
        if (beeGraphic.getCurrent().getX() < 0 || beeGraphic.getCurrent().getX() > HabitatSize.getHeight() - BeeGraphic.getImageHeight()) {
            speed.setSecondCoordinate(0);
        }
    }

}
