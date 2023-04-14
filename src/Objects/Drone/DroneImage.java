package Objects.Drone;

import Objects.Bee.BeeImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DroneImage extends BeeImage {
    private static final Image image = new Image("C:\\MyProjects\\TiMP\\src\\Pic\\sprite_Drone.png");
    public DroneImage() {
        imageView = new ImageView(image);
        setImageViewSize();
    }
}
