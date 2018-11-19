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

        train1 = readData("train_data_1.txt");
        train2 = readData("train_data_2.txt");
        train3 = readData("train_data_3.txt");
        test = readData("test_data_4.txt");
        double[] weights = new double[3];
        weights[0] = 1;
        weights[1] = 2;
        weights[2] = 3;
        double training_const = 0.005;
        int k = 1;
        learn(train1, weights, training_const, k);
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

    //while X iterations
    public static int learn(ArrayList<Stats> train, double[] weight, double learning_const, int k )
    {
        //call total error method, if epsilon > total error, break
        //new weights = calc new waights, weights = new weights
        double net;
        double out;
        for (int i = 0; i<train.size(); i++)
        {
            net = train.get(i).getHour() * weight[0] + weight[1];
            out = net;
        }
        return 0;
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


