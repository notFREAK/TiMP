package Objects.Bee;

import Objects.IBehaviour;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

abstract public class BeeImage extends Pane implements IBehaviour {

    private ImageView imageView;
    public static final int ImageWidth = 50;
    public static final int ImageHeight = 50;

    public BeeImage(ImageView imageView) {
        this.imageView = imageView;
        imageView.setFitWidth(ImageWidth);
        imageView.setFitHeight(ImageHeight);
    }
    @Override
    public void setX(int x) {
        imageView.setTranslateX(x);
    }

    @Override
    public void setY(int y) {
        imageView.setTranslateY(y);
    }
    @Override
    public void move(int x, int y) {

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

    public ImageView getImageView(){
        return imageView;
    }
}
