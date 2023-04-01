package Objects.Worker;

import Objects.Bee.BeeImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WorkerImage extends BeeImage {

    private static final Image image = new Image("C:\\MyProjects\\TiMP\\src\\Pic\\Worker.png");

    public WorkerImage() {
        imageView = new ImageView(image);
    }
}
