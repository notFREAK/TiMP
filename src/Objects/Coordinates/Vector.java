package Objects.Coordinates;

public class Vector implements Cloneable {
    private double[] vector = {0, 0};

    enum CoordinatesType {
        Polar,
        Cartesian
    };

    private CoordinatesType coordinatesType = CoordinatesType.Polar;

    public Vector(CoordinatesType coordinatesType) {
        this.coordinatesType = coordinatesType;
    }
    public Vector(double firstCoordinate, double secondCoordinate, CoordinatesType coordinatesType) {
        this(coordinatesType);
        vector[0] = firstCoordinate;
        vector[1] = secondCoordinate;
    }

    public Vector(int firstCoordinate, int secondCoordinate) {
        this(CoordinatesType.Cartesian);
        vector[0] = (double)firstCoordinate;
        vector[1] = (double)secondCoordinate;
    }

    public Vector clone()
    {
        return new Vector(vector[0], vector[1], coordinatesType);
    }
    public void setVector(Vector vector) {
        if (coordinatesType == vector.coordinatesType)
            this.vector = vector.vector.clone();
        else if (coordinatesType == CoordinatesType.Polar) {
            this.vector = vector.getInTypePolar().vector.clone();
        }
        else {
            this.vector = vector.getInTypeCartesian().vector.clone();
        }
    }

    public Vector getInTypeCartesian() {
        if (this.isPolar())
            return this;
        Vector Polar = clone();
        return Polar.changeTypeToCartesian();
    }

    public Vector getInTypePolar() {
        if (this.isPolar())
            return this;
        Vector Polar = clone();
        return Polar.changeTypeToPolar();
    }

    public Vector changeType() {
        switch (coordinatesType) {
            case Polar:
                this.changeTypeToCartesian();
                break;
            case Cartesian:
                this.changeTypeToPolar();
                break;
        }
        return this;
    }

    public Vector changeTypeToPolar() {
        if (this.isCartesian()) {
            double x = vector[0];
            this.vector[0] = Math.sqrt(Math.pow(this.vector[0], 2) * Math.pow(this.vector[1], 2));
            this.vector[1] = Math.acos(x / this.vector[0]);
        }
        return this;
    }
    public Vector changeTypeToCartesian() {
        if (this.isPolar()) {
            double modulus = vector[0];
            this.vector[0] = Math.round(modulus * Math.cos(vector[1]));
            this.vector[1] = Math.round(modulus * Math.sin(vector[1]));
        }
        return this;
    }

    public boolean isPolar() {
        return coordinatesType == CoordinatesType.Polar;
    }

    public boolean isCartesian() {
        return coordinatesType == CoordinatesType.Cartesian;
    }

    public void setFirstCoordinate(double value) {
        vector[1] = value;
    }

    public void setSecondCoordinate(double value) {
        vector[2] = value;
    }

    public void setFirstCoordinate(int value) {
        vector[1] = value;
    }

    public void setSecondCoordinate(int value) {
        vector[2] = value;
    }

    public double getFirstCoordinate() {
        if (isPolar())
            return vector[0];
        else
            return Math.round(vector[0]);
    }

    public double getSecondCoordinate() {
        if (isPolar())
            return vector[1];
        else
            return Math.round(vector[1]);
    }
}
