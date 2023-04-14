package Application.Simulation;

public class Value {
    int ValueSecondsWorker;
    int ValueProbabilityWorker;

    int ValueSecondsDrone;
    int ValueCoefficientDrone;

    int ValueLifeTimeDrone;
    int ValueLifeTimeWorker;

    public int getValueCoefficientDrone() {
        return ValueCoefficientDrone;
    }

    public int getValueLifeTimeDrone() {
        return ValueLifeTimeDrone;
    }

    public int getValueLifeTimeWorker() {
        return ValueLifeTimeWorker;
    }

    public int getValueProbabilityWorker() {
        return ValueProbabilityWorker;
    }

    public int getValueSecondsDrone() {
        return ValueSecondsDrone;
    }

    public int getValueSecondsWorker() {
        return ValueSecondsWorker;
    }

    public void setValueCoefficientDrone(int valueCoefficientDrone) {
        ValueCoefficientDrone = valueCoefficientDrone;
    }

    public void setValueLifeTimeDrone(int valueLifeTimeDrone) {
        ValueLifeTimeDrone = valueLifeTimeDrone;
    }

    public void setValueLifeTimeWorker(int valueLifeTimeWorker) {
        ValueLifeTimeWorker = valueLifeTimeWorker;
    }

    public void setValueProbabilityWorker(int valueProbabilityWorker) {
        ValueProbabilityWorker = valueProbabilityWorker;
    }

    public void setValueSecondsDrone(int valueSecondsDrone) {
        ValueSecondsDrone = valueSecondsDrone;
    }

    public void setValueSecondsWorker(int valueSecondsWorker) {
        ValueSecondsWorker = valueSecondsWorker;
    }

    public void setValue(int SecondsDrone,
                         int CoefficientDrone,
                         int LifeTimeDrone,
                         int SecondsWorker,
                         int ProbabilityWorker,
                         int LifeTimeWorker) {
        setValueSecondsDrone(SecondsDrone);
        setValueCoefficientDrone(CoefficientDrone);
        setValueLifeTimeDrone(LifeTimeDrone);
        setValueSecondsWorker(SecondsWorker);
        setValueProbabilityWorker(ProbabilityWorker);
        setValueLifeTimeWorker(LifeTimeWorker);
    }
}
