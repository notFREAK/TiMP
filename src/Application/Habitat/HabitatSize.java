package Application.Habitat;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HabitatSize {
    public static final Image imageBackground = new Image("C:\\MyProjects\\TiMP\\src\\resourses\\Pic\\background.gif");
    private static int Width = 400;
    private static int Height = 400;

    public static int getWidth() {
        return Width;
    }

    public static int getHeight() {
        return Height;
    }
    public static ImageView getImageViewBackground() {
        ImageView imageViewBackground = new ImageView(imageBackground);
        imageViewBackground.setFitWidth(Width);
        imageViewBackground.setFitHeight(Height);
        return imageViewBackground;
    }
}
