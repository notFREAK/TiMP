package Objects.Worker;

import Application.Habitat.HabitatSize;
import Objects.Bee.Bee;
import Objects.Bee.BeeBaseAI;
import Objects.Bee.BeeGraphic;
import Objects.Bee.BeeLife;
import Objects.Coordinates.Position;
import Objects.Coordinates.Vector.Cartesian;
import Objects.Coordinates.Vector.Polar;
import Objects.Coordinates.Vector.Vector;

import java.util.UUID;

public class Worker extends Bee{
    public static int countWorker = 0;

    protected Position positionBirth;

    protected Position positionHabitat;

    protected boolean isWorkerGoHabitat = true;

    public static BeeBaseAI WorkerBaseAI = new BeeBaseAI() {
        @Override
        public void move(Bee bee) {
            if (bee.getTypeBees().equals("bee_worker".toString())) {
                bee.ChangeDirection();
            }
        }
    };
    public Worker(){
    }
    public Worker(int x, int y, int timeBorn, int timeLife){
        current = new Position(x, y);
        life = new BeeLife(timeBorn,timeLife) {
                @Override
                public void updateCountOfDeadBees() {

                    countWorker--;
            }
        };
        beeGraphic = new BeeGraphic(x, y, getClass().getResource("/resourses/Pic/sprite_Worker.png").toString());
        positionBirth = new Position(x, y);
        positionHabitat = new Position(0, HabitatSize.getWidth() - BeeGraphic.getImageWidth(), 0, HabitatSize.getHeight() - BeeGraphic.getImageHeight());
        countWorker++;
        typeBee = "bee_worker";
    }

    @Override
    public void ChangeDirection() {
        if (isWorkerGoHabitat) {
            Polar toHab = getCurrent().BeeGoTo(positionHabitat).changeTypeToPolar();
            if (toHab.getFirstCoordinate() <= (int)Math.round(speed.getFirstCoordinate())) {
                isWorkerGoHabitat = !isWorkerGoHabitat;
            }
            speed.setSecondCoordinate(toHab.getSecondCoordinate());
            }
        else {
            Vector toBir = getCurrent().BeeGoTo(positionBirth).changeTypeToPolar();
            if (toBir.getFirstCoordinate() <= (int)Math.round(speed.getFirstCoordinate())) {
                positionHabitat = new Position(0, HabitatSize.getWidth() - BeeGraphic.getImageWidth(), 0, HabitatSize.getHeight() - BeeGraphic.getImageHeight());
                isWorkerGoHabitat = !isWorkerGoHabitat;
            }
            speed.setSecondCoordinate(toBir.getSecondCoordinate());
        }
    }

    @Override
    public String toString() {
        return new String(typeBee + ":" + identifier.toString() + ":" +
                positionBirth.getX() + ":" + positionBirth.getY() + ":" +
                positionHabitat.getX() + ":" + positionHabitat.getY() + ":" +
                getCurrent().getX() + ":" + getCurrent().getY() + ":" +
                life.getTimeBorn() + ":" + life.getTimeLife());
    }

    public Worker(String string){
        String[] values = string.split(":");
        identifier = UUID.fromString(values[1]);
        int x = Integer.parseInt(values[2]);
        int y = Integer.parseInt(values[3]);
        int xHabitat = Integer.parseInt(values[4]);
        int yHabitat = Integer.parseInt(values[5]);
        int xCurrent = Integer.parseInt(values[6]);
        int yCurrent = Integer.parseInt(values[7]);
        int timeBorn = Integer.parseInt(values[8]);
        int timeLife = Integer.parseInt(values[9]);
        life = new BeeLife(timeBorn,timeLife) {
            @Override
            public void updateCountOfDeadBees() {

                countWorker--;
            }
        };
        beeGraphic = new BeeGraphic(xCurrent, yCurrent, getClass().getResource("/resourses/Pic/sprite_Worker.png").toString());
        positionBirth = new Position(x, y);
        positionHabitat = new Position(xHabitat, yHabitat);
        countWorker++;
        typeBee = "bee_worker";
    }
}
