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
        System.out.print(identifier + " " + positionHabitat.getX() + " " +positionHabitat.getY() + '\n');
        countWorker++;
        typeBee = "bee_worker";
    }

    @Override
    public void ChangeDirection() {

        System.out.print(identifier + " " + beeGraphic.getCurrent().getX() + " " +beeGraphic.getCurrent().getY() + " " + isWorkerGoHabitat + " ");
        if (isWorkerGoHabitat) {
            Vector toHab = beeGraphic.getCurrent().BeeGoTo(positionHabitat);
            System.out.print(positionHabitat.getX() + " " + positionHabitat.getY() + " " + toHab.getFirstCoordinate() + " " + toHab.getSecondCoordinate() +  " ");
            toHab.changeTypeToPolar();
            if (toHab.getFirstCoordinate() <= speed.getFirstCoordinate()) {
                isWorkerGoHabitat = !isWorkerGoHabitat;
            }
            speed.changeTypeToPolar();
            speed.setSecondCoordinate(toHab.getSecondCoordinate());
            System.out.print(toHab.getFirstCoordinate() + " " + toHab.getSecondCoordinate() + " " + speed.getFirstCoordinate() + " " + speed.getSecondCoordinate()+ "\n");
        }
        else {
            Vector toBir = beeGraphic.getCurrent().BeeGoTo(positionBirth).changeTypeToPolar();
            if (toBir.getFirstCoordinate() <= speed.getFirstCoordinate()) {
                isWorkerGoHabitat = !isWorkerGoHabitat;
            }
            speed.changeTypeToPolar();
            speed.setSecondCoordinate(toBir.getSecondCoordinate());
            System.out.print(positionBirth.getX() + " " + positionBirth.getY() + " " + toBir.getFirstCoordinate() + " " + toBir.getSecondCoordinate() +" " + speed.getFirstCoordinate() + " " + speed.getSecondCoordinate()+ "\n");
        }
    }
}
