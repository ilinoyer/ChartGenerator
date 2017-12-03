package sample;

import javafx.scene.chart.XYChart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    private int seriesNumber;
    private File dataFile;
    private ArrayList<XYChart.Series<Double, Double>> seriesArrayList;

    public DataReader(int seriesNumber, File dataFile)
    {
        this.dataFile = dataFile;
        this.seriesNumber = seriesNumber - 1;
        seriesArrayList = new ArrayList<>();
    }

    void loadData()
    {
        for(int i = 0; i <= seriesNumber; ++i)
        {
            seriesArrayList.add(new XYChart.Series<>());
            System.out.println("Dodano serie: " + i);
        }


        try{
            Scanner scanner = new Scanner(dataFile);
            String line;
            List<String> splittedString = new ArrayList<>();
            while(scanner.hasNext())
            {
                line = scanner.nextLine();
                line = line.replace(",",".");

                splittedString = Arrays.asList(line.split(";"));

                for(int i = 0; i <= seriesNumber; ++i)
                {
                    seriesArrayList.get(i).getData().add(new XYChart.Data<>(Double.parseDouble(splittedString.get(0)), Double.parseDouble(splittedString.get(i + 1))));
                    System.out.println("Wczytano serie: " + i);
                }
            }
            scanner.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public XYChart.Series<Double, Double> getSeries(int i)
    {
        return seriesArrayList.get(i);
    }

}
