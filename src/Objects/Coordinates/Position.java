package Objects.Coordinates;

import Objects.IBehaviour;

public class Position  {

    private int[] position = {0, 0};

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        position[0] = x;
    }

    public void setY(int y) {
        position[1] =  y;
    }

    public double getX() {
        return position[0];
    }

    public double getY() {
        return position[1];
    }
}
