package Objects;
/*-	Создать интерфейс IBehaviour, задающий поведение объекта
 (далее будут реализоваться алгоритмы движения объектов в окне программы).*/
public interface IBehaviour {
    void move(int x, int y);
    void setX(int x);
    void setY(int y);
    double getX();
    double getY();

    void updaTimeLiveAnimals();
}
