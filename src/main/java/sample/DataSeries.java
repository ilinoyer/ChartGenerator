package sample;

public class DataSeries {
    private String label;
    private int number;

    public DataSeries(int number)
    {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public String toString() {
        return "Series number: " + number + "\nLabel: " + label;
    }
}
