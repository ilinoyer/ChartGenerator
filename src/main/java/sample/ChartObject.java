package sample;

import javafx.geometry.Side;
import javafx.scene.chart.*;

import java.io.File;
import java.util.ArrayList;

public class ChartObject {
    private int seriesNumber;
    private XYChart chart;
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private DataReader dataReader;
    private ArrayList<DataSeries> dataSeriesList;
    private File dataFile;



    public ChartObject(String chartName, int seriesNumber, File dataFile)
    {
        initialize(chartName,dataFile, seriesNumber);
    }

    public ChartObject()
    {

    }

    public void initialize(String chartName, File dataFile, int seriesNumber)
    {
        this.dataFile = dataFile;
        this.dataSeriesList = new ArrayList<>();
        this.seriesNumber = seriesNumber;
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

        for(int i = 0 ; i < seriesNumber; ++i)
        {
            dataSeriesList.add(new DataSeries(i + 1));
        }
        chart.setLegendVisible(true);
    }

    public void loadSeries()
    {
        for(int i = 0; i < seriesNumber; ++i)
        {
            XYChart.Series<Double, Double> series = dataReader.getSeries(i);
            series.setName(dataSeriesList.get(i).getLabel());
            chart.getData().add(series);
        }

    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public XYChart getChart()
    {
        return this.chart;
    }

    public DataSeries getDataSeriesByIndex(int i) {
        return dataSeriesList.get(i);
    }

    public File getDataFile()
    {
        return dataFile;
    }

    public void setXLabel(String label)
    {
        xAxis.setLabel(label);
    }

    public void setYLabel(String label)
    {
        this.yAxis.setLabel(label);
    }

    public void setXAxisMaxValue(double maxValue)
    {
        xAxis.setUpperBound(maxValue);
    }

    public void setYAxisMaxValue(double maxValue)
    {
        yAxis.setUpperBound(maxValue);
    }

    public void setXAxisMinUnit(double minUnit)
    {
        xAxis.setTickUnit(minUnit);
    }

    public void setYAxisMinUnit(double minUnit)
    {
        yAxis.setTickUnit(minUnit);
    }

    public void setXAxisAutoRanging(boolean value)
    {
        xAxis.setAutoRanging(value);
    }

    public void setYAxisAutoRanging(boolean value)
    {
        yAxis.setAutoRanging(value);

    }

    public void setChartTitle(String title)
    {
        chart.setTitle(title);
        chart.setTitleSide(Side.TOP);
    }

}
