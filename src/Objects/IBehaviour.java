package Objects;
/*-	Создать интерфейс IBehaviour, задающий поведение объекта
 (далее будут реализоваться алгоритмы движения объектов в окне программы).*/
public interface IBehaviour {
    void setX(double x);
    void setY(double y);
    double getX();
    double getY();
}
