package Objects.Worker;

import Objects.Bee.Bee;
import Objects.Bee.BeeGraphic;
import Objects.Bee.BeeLife;
import Objects.Coordinates.Position;

public class Worker extends Bee{
    public static int countWorker = 0;

    protected Position Birth;

    public Worker(){
    }
    public Worker(int x, int y, int timeBorn, int timeLife){
        life = new BeeLife(timeBorn,timeLife) {
                @Override
                public void updateCountOfDeadBees() {

                    countWorker--;
            }
        };
        beeGraphic = new BeeGraphic(getClass().getResource("/resourses/Pic/sprite_Worker.png").toString());
        Birth = new Position(x, y);
        beeGraphic.setPosition(x,y);
        countWorker++;
        typeBee = "bee_worker";
    }

}
