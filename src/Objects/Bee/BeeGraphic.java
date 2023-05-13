package Objects.Bee;

import Application.Habitat.HabitatSize;
import Objects.Coordinates.Position;
import Objects.Coordinates.Vector.Cartesian;
import Objects.Coordinates.Vector.Vector;
import Objects.Bee.BeeGraphic;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BeeGraphic extends Pane {

    private ImageView imageView;
    private static int ImageWidth = HabitatSize.getWidth()/12;
    private static int ImageHeight = HabitatSize.getHeight()/12;
    Position current;
    public BeeGraphic(int x, int y, String path) {
        imageView = new ImageView(new Image(path));
        current = new Position(x, y);
        setPosition(x, y);
        setImageViewSize();
    }

    protected void setImageViewSize() {
        imageView.setFitWidth(ImageWidth);
        imageView.setFitHeight(ImageHeight);
    }

    public ImageView getImageView(){
        return imageView;
    }
    public static int getImageWidth() {
        return ImageWidth;
    }

    public static void setImageWidth(int imageWidth) {
        ImageWidth = imageWidth;
    }


    public static int getImageHeight() {
        return ImageHeight;
    }

    public static void setImageHeight(int imageHeight) {
        ImageHeight = imageHeight;
    }

    public Position getCurrent() {
        return current;
    }

    public void setX(double x) {
        imageView.setTranslateX(x);
    }

    public void setY(double y) {
        imageView.setTranslateY(y);
    }

    public double getX() {
        return imageView.getX();
    }

    public double getY() {
        return imageView.getY();
    }

    public void setPosition(int x, int y){
        Platform.runLater(() -> {
            this.setX(x);
            this.setY(y);
        });
        current.setX(x);
        current.setY(y);
    }

    public void go(Cartesian speed) {
        int newX = (int)Math.round(current.getX()+ speed.getFirstCoordinate());
        int newY = (int)Math.round(current.getY()+ speed.getSecondCoordinate());
        if (newX <= 0)
            newX = 0;
        else if (newX >= HabitatSize.getWidth() - BeeGraphic.getImageWidth())
            newX = HabitatSize.getWidth() - BeeGraphic.getImageWidth();
        if (newY <= 0)
            newY = 0;
        else if (newY >= HabitatSize.getHeight() - BeeGraphic.getImageHeight())
            newY = HabitatSize.getHeight() - BeeGraphic.getImageHeight();
        setPosition(newX, newY);
    }
}