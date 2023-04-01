package Application.Habitat;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HabitatSize {
    public static final Image imageBackground = new Image("C:\\MyProjects\\TiMP\\src\\Pic\\Background.png");
    private static final int Width = 400;
    private static final int Height = 400;

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }
    public ImageView getImageViewBackground() {
        ImageView imageViewBackground = new ImageView(imageBackground);
        imageViewBackground.setFitWidth(Width);
        imageViewBackground.setFitHeight(Height);
        return imageViewBackground;
    }
}
