import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proj3
{

    public static void main(String[] args) throws IOException {
        ArrayList<Stats> train1 = new ArrayList<>();
        ArrayList<Stats> train2 = new ArrayList<>();
        ArrayList<Stats> train3 = new ArrayList<>();
        ArrayList<Stats> test = new ArrayList<>();
        final double alpha = 0.3;

        train1 = readData("train_data_1.txt");
        train2 = readData("train_data_2.txt");
        train3 = readData("train_data_3.txt");
        test = readData("test_data_4.txt");
    }

    private static ArrayList<Stats> readData(String fileName) throws IOException {
        ArrayList<Stats> allDataPoints = new ArrayList<>();
        File file1 = new File(fileName);
        Scanner scanFile = new Scanner(file1);

        while (scanFile.hasNextLine()) {
            String dataPoint = scanFile.nextLine();
            String[] data = dataPoint.split(", ");
            allDataPoints.add(new Stats(Double.parseDouble(data[0]), Double.parseDouble(data[1])));
        }
        scanFile.close();

        return allDataPoints;
    }

    private static void linearActivationFunction(ArrayList<Stats> trainingData, double learning) {
        double weightMultiplier;
        double[] weights = {1, -1};
        double out;

         for (int i = 0; i < (trainingData.size()); i++) {

             out = trainingData.get(i).getHour()* weights[0] + weights[1];

             weightMultiplier = out;
             weights[0] +=  weightMultiplier * trainingData.get(i).getHour();
             weights[1] +=  weightMultiplier;
         }

    }

}

class Stats{

    double hour;
    double rate;

    Stats(double hour, double rate) {
        this.hour = hour;
        this.rate = rate;
    }

    double getHour() {
        return this.hour;
    }

    double getRate() {
        return this.rate;
    }

}
