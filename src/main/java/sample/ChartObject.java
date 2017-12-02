package sample;

import javafx.scene.chart.*;

import java.io.File;

public class ChartObject {
    private XYChart chart;
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private DataReader dataReader;


    public ChartObject(String chartName, int seriesNumber, File dataFile)
    {
        dataReader = new DataReader(seriesNumber, dataFile);
        dataReader.loadData();
        switch(chartName)
        {
            case "Line Chart":
                this.chart = new LineChart<Number, Number>(xAxis, yAxis);
                break;
            case "Area Chart":
                this.chart = new AreaChart<Number, Number>(xAxis, yAxis);
                break;
            case "Bubble Chart":
                this.chart = new BubbleChart<Number, Number>(xAxis, yAxis);
                break;
            case "Scatter Chart":
                this.chart = new ScatterChart<Number, Number>(xAxis, yAxis);
                break;
        }

        for(int i = 0; i < seriesNumber; ++i)
            chart.getData().add(dataReader.getSeries(i));
    }

    public XYChart getChart()
    {
        return this.chart;
    }

}
