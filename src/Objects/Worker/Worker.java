package Objects.Worker;

import Application.Habitat.HabitatSize;
import Objects.Bee.Bee;
import Objects.Bee.BeeBaseAI;
import Objects.Bee.BeeGraphic;
import Objects.Bee.BeeLife;
import Objects.Coordinates.Position;
import Objects.Coordinates.Vector;

import static java.lang.Math.sqrt;

public class Worker extends Bee{
    public static int countWorker = 0;

    protected Position positionBirth;

    protected Position positionHabitat;

    protected boolean isWorkerGoHabitat = true;

    public static BeeBaseAI WorkerBaseAI = new BeeBaseAI() {
        @Override
        public void move(Bee bee) {
            if (bee.getTypeBees() == "bee_worker") {
                bee.ChangeDirection();
            }
        }
    };
    public Worker(){
    }
    public Worker(int x, int y, int timeBorn, int timeLife){
        life = new BeeLife(timeBorn,timeLife) {
                @Override
                public void updateCountOfDeadBees() {

                    countWorker--;
            }
        };
        beeGraphic = new BeeGraphic(x, y, getClass().getResource("/resourses/Pic/sprite_Worker.png").toString());
        positionBirth = new Position(x, y);
        positionHabitat = new Position(0, HabitatSize.getWidth(), 0, HabitatSize.getHeight());
        beeGraphic.setPosition(x,y);
        countWorker++;
        typeBee = "bee_worker";
    }

    @Override
    public void ChangeDirection() {
        if (isWorkerGoHabitat) {
            Vector toHab = beeGraphic.getCurrent().BeeGoTo(positionHabitat).changeTypeToPolar();
            if (toHab.getFirstCoordinate() <= speed.getFirstCoordinate()) {
                isWorkerGoHabitat = !isWorkerGoHabitat;
            }
            speed.setSecondCoordinate(toHab.getSecondCoordinate());
        }
        else {
            Vector toBir = beeGraphic.getCurrent().BeeGoTo(positionBirth).changeTypeToPolar();
            if (toBir.getFirstCoordinate() <= speed.getFirstCoordinate()) {
                isWorkerGoHabitat = !isWorkerGoHabitat;
            }
            speed.setSecondCoordinate(toBir.getSecondCoordinate());
        }
    }
}
