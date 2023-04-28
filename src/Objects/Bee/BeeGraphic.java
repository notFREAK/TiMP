package Objects.Bee;

import Objects.Coordinates.Position;
import Objects.IBehaviour;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BeeGraphic extends Pane implements IBehaviour {

    private ImageView imageView;
    private static int ImageWidth = 50;
    private static int ImageHeight = 50;
    Position current;
    public BeeGraphic(String path) {
        imageView = new ImageView(new Image(path));
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

    @Override
    public void setX(double x) {
        imageView.setTranslateX(x);
    }

    @Override
    public void setY(double y) {
        imageView.setTranslateY(y);
    }

    @Override
    public double getX() {
        return imageView.getX();
    }

    @Override
    public double getY() {
        return imageView.getY();
    }

    public void setPosition(int x, int y){
        this.setX(x);
        this.setY(y);
    }
}