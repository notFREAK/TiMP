package Objects.Coordinates.Vector;

import javafx.application.Application;
import javafx.stage.Stage;

public class Cartesian extends Vector {
    public Cartesian(int firstCoordinate, int secondCoordinate) {
        super(firstCoordinate, secondCoordinate);
    }

    public Polar changeTypeToPolar() {
            double x = vector[0];
        return new Polar(Math.sqrt(Math.pow(this.vector[0], 2) + Math.pow(this.vector[1], 2)), Math.atan(vector[1]/vector[0]));
    }
}
