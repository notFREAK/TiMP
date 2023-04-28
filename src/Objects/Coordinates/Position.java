package Objects.Coordinates;

import Objects.IBehaviour;

import static java.lang.Math.*;

public class Position  {

    private int[] position = {0, 0};

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public Position(int x1, int x2,int y1, int y2) {
        if(random() < 0.5) {
            setX(x1);
        }
        else {
            setX(x2);
        }
        if(random() < 0.5) {
            setY(y1);
        }
        else {
            setY(y2);
        }
    }

    public void setX(int x) {
        position[0] = x;
    }

    public void setY(int y) {
        position[1] =  y;
    }

    public int getX() {
        return position[0];
    }

    public int getY() {
        return position[1];
    }
    public Vector BeeGoTo(Position End) {
        return new Vector(End.getX()-getX(),End.getY() - getY(), Vector.CoordinatesType.Cartesian ).changeTypeToPolar();
    }
}
