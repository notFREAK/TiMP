package Objects;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/*
Вариант 11
        Объекты – домашние животные.
        Бывают 2 видов: кошки и собаки.
        Кошки генерируются каждые N1 секунд с вероятностью P1.
        Собаки генерируются каждые N2 секунд с вероятностью P2.
*/
public abstract class Bee extends Pane implements IBehaviour{
    protected String typeAnimals;
    private ImageView imageView;
    public static final int ImageWidth = 50;
    public static final int ImageHeight = 50;
    static public int countsAllBees = 0;
    protected int identifier = 0;
    protected int timeBorn = 0;
    protected int timeLife = 0;
    protected boolean isDead = false;

    public Bee(ImageView imageView, int timeBorn, int timeLife){
        this.imageView = imageView;
        this.timeBorn = timeBorn;
        this.timeLife = timeLife;
        imageView.setFitWidth(ImageWidth);
        imageView.setFitHeight(ImageHeight);
        generateIdentifier();
        countsAllBees++;
    }

    private void generateIdentifier(){
        int randomIdentifier = (int)Math.floor(Math.random()*10000);
        identifier = randomIdentifier;
    };

    @Override
    public void move(int x, int y) {

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

    @Override
    public void updateTimeLiveAnimals(){
    }

    public ImageView getImageView(){
        return imageView;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getTimeBorn() {
        return timeBorn;
    }

    public String getTypeAnimals() {
        return typeAnimals;
    }

    public void setTImeLife(int timeLife){
        this.timeLife = timeLife;
    }

    public int getTimeLife() {
        return timeLife;
    }

    public boolean isDead() {
        return isDead;
    }

    protected void decrementCountsAllAnimals(){
        countsAllBees--;
    }
}
