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
        System.out.println("\n\nTraining set 1: ");
        weights = linearLearning(train1, weights, training_const, k);
        System.out.println("\n\nTraining set 2: ");
        weights = linearLearning(train2, weights, training_const, k);
        System.out.println("\n\nTraining set 3: ");
        weights = linearLearning(train3, weights, training_const, k);
        System.out.println("\n\nTest: ");
        linearTesting(test, weights);
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
    public static double[] linearLearning(ArrayList<Stats> train, double[] weight, double learning_const, int k ) {
        //call total error method, if epsilon > total error, break
        //new weights = calc new weights, weights = new weights
        double out;
        double weightMultiplier = 1;
        double totalError = 0;
        for (int i = 0; i < train.size(); i++) {
            out = train.get(i).getHour() * weight[0] + weight[1];
            weightMultiplier = learning_const * (train.get(i).getRate() - out);
            weight[0] +=  weightMultiplier * train.get(i).getRate();
            totalError += Math.pow(train.get(i).getRate() - out , 2);
        }
        System.out.println("Total Error: " + totalError + " : RootMeanError = " + Math.sqrt(totalError) / train.size());
        return weight;
    }

    public static void linearTesting(ArrayList<Stats> test, double[] weight) {
        double testOut;
        ArrayList<Stats> results = new ArrayList<>();
        double incorrect = 0;
        double testTotalError = 0;
        for (int i = 0; i < test.size(); i++) {
            testOut = test.get(i).getHour() * weight[0] + weight[1];
            results.add(new Stats(test.get(i).getHour(), testOut));

            System.out.println("Expected: " + test.get(i).getRate()+ " Predicted: " + testOut);
            if (test.get(i).getRate() != testOut){
                incorrect++;
            }
            testTotalError += Math.pow((test.get(i).getRate() - testOut), 2);
        }
        System.out.println("Incorrect: " + incorrect + "Total Error: " + testTotalError + " : Mean Square Error = " + Math.sqrt(testTotalError)/ test.size());
    }

    public static void quadraticLearning(ArrayList<Stats> train, double[] weight, double learning_const, int k) {

    }
}

class Stats {

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
