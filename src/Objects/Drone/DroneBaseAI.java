package Objects.Drone;

import Objects.Bee.Bee;
import Objects.Bee.BeeBaseAI;

public class DroneBaseAI extends BeeBaseAI {

    boolean directionIsChanged = false;
    @Override
    public void move() {
        /*for (Bee bee : appManager.getHabitat().getCollectionsBees().getArrayList()) {
            if (bee.getTypeBees() == "bee_drone") {
                double x = bee.beeGraphic.getX();
                double y = bee.beeGraphic.getY();
                double[] speedPolar = bee.getVectorSpeedPolar();
                {
                    if (directionIsChanged)
                        speedPolar[1] = Math.random()%Math.PI;
                }
                bee.beeGraphic.setX((int) (x + (speedPolar[0] * Math.cos(speedPolar[1]))));
                bee.beeGraphic.setY((int) (y + (speedPolar[0] * Math.sin(speedPolar[1]))));
                bee.setVectorSpeedPolar(speedPolar);
            }
        }*/
    }
}
