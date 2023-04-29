package Objects.Coordinates.Vector;

public class Polar extends Vector {

    public Polar(double firstCoordinate, double secondCoordinate) {
        super(firstCoordinate, secondCoordinate);
    }

    public Cartesian changeTypeToCartesian() {
            double modulus = vector[0];
        return new Cartesian((int)Math.round(modulus * Math.cos(vector[1])), (int)Math.round((modulus * Math.sin(vector[1]))));
    }
}
