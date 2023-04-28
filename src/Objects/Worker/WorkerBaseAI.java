package Objects.Worker;

import Objects.Bee.Bee;
import Objects.Bee.BeeBaseAI;

public class WorkerBaseAI extends BeeBaseAI {

    boolean goHome = false;
    @Override
    public void move() {
        /*for (Bee bee : appManager.getHabitat().getCollectionsBees().getArrayList()) {
            if (bee.getTypeBees() == "bee_worker") {
                double x = bee.beeGraphic.getX();
                double y = bee.beeGraphic.getY();
                double[] speedPolar = bee.getVectorSpeedPolar();
                {
                    if (goHome)
                        speedPolar[1] = Math.atan((bee.positionBirth[1]-y)/(bee.positionBirth[0]-x));
                    else
                        speedPolar[1] = Math.atan((bee.positionHabitat[1]-y)/(bee.positionHabitat[0]-x));
                }
                bee.beeGraphic.setX((int) (x + (speedPolar[0] * Math.cos(speedPolar[1]))));
                bee.beeGraphic.setY((int) (y + (speedPolar[0] * Math.sin(speedPolar[1]))));
                if (Math.abs(bee.positionHabitat[0] - bee.beeGraphic.getX()) < 10 || Math.abs(bee.positionHabitat[1] - bee.beeGraphic.getY()) < 10)
                    goHome = true;
                bee.setVectorSpeedPolar(speedPolar);
            }
        }*/
    }
}
