package Objects.Coordinates.Vector;

public class Vector implements Cloneable {
    protected double[] vector = {0, 0};

    public enum CoordinatesType {
        Polar,
        Cartesian
    };

    private CoordinatesType coordinatesType = CoordinatesType.Polar;

    public Vector(double firstCoordinate, double secondCoordinate) {
        vector[0] = firstCoordinate;
        vector[1] = secondCoordinate;
    }

    public Vector(int firstCoordinate, int secondCoordinate) {
        vector[0] = (double)firstCoordinate;
        vector[1] = (double)secondCoordinate;
    }

    public Vector clone()
    {
        return new Vector(vector[0], vector[1]);
    }

    public void setFirstCoordinate(double value) {
        vector[0] = value;
    }

    public void setSecondCoordinate(double value) {
        vector[1] = value;
    }

    public void setFirstCoordinate(int value) {
        vector[0] = value;
    }

    public void setSecondCoordinate(int value) {
        vector[1] = value;
    }

    public double getFirstCoordinate() {return vector[0];}
    public double getSecondCoordinate() {return vector[1];}
}
