import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Proj3 {

    public static void main(String[] args) throws IOException {
        ArrayList<Stats> train1 = new ArrayList<>();
        ArrayList<Stats> train2 = new ArrayList<>();
        ArrayList<Stats> train3 = new ArrayList<>();
        ArrayList<Stats> test = new ArrayList<>();

        train1 = normalizeData(readData("train_data_1.txt"));
        train2 = normalizeData(readData("train_data_2.txt"));
        train3 = normalizeData(readData("train_data_3.txt"));
        test = normalizeData(readData("test_data_4.txt"));
        double training_const = 0.000001;

        System.out.println("\n\nlinear architecture------------------");
        double[] linearWeights = {1,0,1};
        System.out.println("\n\nTraining set 1: ");
        linearWeights = linearLearning(train1, linearWeights, training_const);
        System.out.println("\n\nTraining set 2: ");
        linearWeights = linearLearning(train2, linearWeights, training_const);
        System.out.println("\n\nTraining set 3: ");
        linearWeights = linearLearning(train3, linearWeights, training_const);
        System.out.println("\n\nTest: ");
        linearTesting(test, linearWeights);

        System.out.println("\n\nquadratic architecture------------------");
        double[] quadraticWeights = {1,0,1};
        System.out.println("\n\nTraining set 1: ");
        quadraticWeights = quadraticLearning(train1, quadraticWeights, training_const);
        System.out.println("\n\nTraining set 2: ");
        quadraticWeights = quadraticLearning(train2, quadraticWeights, training_const);
        System.out.println("\n\nTraining set 3: ");
        quadraticWeights = quadraticLearning(train3, quadraticWeights, training_const);
        System.out.println("\n\nTest: ");
        quadraticTesting(test, quadraticWeights);

        System.out.println("\n\ncubic architecture------------------");
        double[] cubicWeights = {1,-1,1,-1};
        System.out.println("\n\nTraining set 1: ");
        cubicWeights = quadraticLearning(train1, cubicWeights, training_const);
        System.out.println("\n\nTraining set 2: ");
        cubicWeights = quadraticLearning(train2, cubicWeights, training_const);
        System.out.println("\n\nTraining set 3: ");
        cubicWeights = cubicLearning(train3, cubicWeights, training_const);
        System.out.println("\n\nTest: ");
        cubicTesting(test, cubicWeights);
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

    private static ArrayList<Stats> normalizeData(ArrayList<Stats> toNormalize) {
        ArrayList<Stats> normalized = new ArrayList<Stats>();
        double minHours = 0;
        double maxHours = 0;
        double minRate = 0;
        double maxRate = 0;

        for(Stats row: toNormalize) {
            if(maxHours == 0) {
                minHours = row.getHour();
                maxHours = row.getHour();
            }

            if(maxRate == 0) {
                minRate = row.getRate();
                maxRate = row.getRate();
            }

            if(row.getHour() > maxHours) {
                maxHours = row.getHour();
            }

            if(row.getHour() < minHours) {
                minHours = row.getHour();
            }

            if(row.getRate() > maxRate) {
                maxRate = row.getRate();
            }

            if(row.getRate() < minRate) {
                minRate = row.getRate();
            }
        }

        for(Stats row: toNormalize) {
            double normalizedHour = (row.getHour() - minHours) / (maxHours-minHours);
            double normalizedRate = (row.getRate() - minRate) / (maxRate-minRate);
            normalized.add(new Stats(normalizedHour, normalizedRate));
        }

        return normalized;
    }

    public static double[] linearLearning(ArrayList<Stats> train, double[] weight, double learning_const) {
        double out;
        double weightMultiplier = 1;
        double totalError = 0;
        int count  = 0;
        while(count < 201) {
            for (int i = 0; i < train.size(); i++) {
                out = train.get(i).getHour() * weight[0] + weight[1];
                weightMultiplier = learning_const * (train.get(i).getRate() - out);
                weight[0] += weightMultiplier * train.get(i).getHour();
                weight[1] += weightMultiplier;
                totalError += Math.pow(train.get(i).getRate() - out, 2);
            }
            count++;
        }
        System.out.println("Mean Square Error = " + Math.sqrt(totalError) / train.size());
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

            testTotalError += Math.pow((test.get(i).getRate() - testOut), 2);
        }
        System.out.println("Mean Square Error = " + Math.sqrt(testTotalError)/ test.size());
    }

    public static double[] quadraticLearning(ArrayList<Stats> train, double[] weight, double learning_const) {
        double out;
        double weightMultiplier = 1;
        double totalError = 0;
        int count = 0;

        for(double w : weight) {
            System.out.println(w);
        }

        while(count <= 1000000) {
            for (int i = 0; i < train.size(); i++) {
                out = (Math.pow(train.get(i).getHour(), 2) * weight[0]) + (train.get(i).getHour() * weight[1]) + weight[2];
                weightMultiplier = learning_const * (train.get(i).getRate() - out);
                weight[0] += weightMultiplier * Math.pow(train.get(i).getHour(), 2);
                weight[1] += weightMultiplier * train.get(i).getHour();
                weight[2] += weightMultiplier;
                totalError += Math.pow(train.get(i).getRate() - out, 2);
            }
            count++;
        }
        System.out.println("Mean Square Error = " + Math.sqrt(totalError) / train.size());
        return weight;
    }

    public static void quadraticTesting(ArrayList<Stats> test, double[] weight) {
        double testOut;
        ArrayList<Stats> results = new ArrayList<>();
        double testTotalError = 0;
        for(double w : weight) {
            System.out.println(w);
        }
            for (int i = 0; i < test.size(); i++) {
                testOut = (Math.pow(test.get(i).getHour(), 2) * weight[0]) + (test.get(i).getHour() * weight[1]) + weight[2];
                results.add(new Stats(test.get(i).getHour(), testOut));

                System.out.println("Expected: " + test.get(i).getRate() + " Predicted: " + testOut);

                testTotalError += Math.pow((test.get(i).getRate() - testOut), 2);
            }
        System.out.println("Mean Square Error = " + Math.sqrt(testTotalError)/ test.size());
    }

    public static double[] cubicLearning(ArrayList<Stats> train, double[] weight, double learning_const) {
        double out;
        double weightMultiplier = 1;
        double totalError = 0;
        int count = 0;
        while(count < 1000001) {
            for (int i = 0; i < train.size(); i++) {
                out = (Math.pow(train.get(i).getHour(), 3) * weight[0]) + (Math.pow(train.get(i).getHour(), 2) * weight[1]) + (train.get(i).getHour() * weight[2]) + weight[3];
                weightMultiplier = learning_const * (train.get(i).getRate() - out);
                weight[0] += weightMultiplier * Math.pow(train.get(i).getHour(), 3);
                weight[1] += weightMultiplier * Math.pow(train.get(i).getHour(), 2);
                weight[2] += weightMultiplier * train.get(i).getHour();
                weight[3] += weightMultiplier;
                totalError += Math.pow(train.get(i).getRate() - out, 2);
            }
            count++;
        }
        System.out.println("Mean Square Error = " + Math.sqrt(totalError) / train.size());
        return weight;
    }

    public static void cubicTesting(ArrayList<Stats> test, double[] weight) {
        double testOut;
        ArrayList<Stats> results = new ArrayList<>();
        double testTotalError = 0;
        for (int i = 0; i < test.size(); i++) {
            testOut = (Math.pow(test.get(i).getHour(),3) * weight[0]) + (Math.pow(test.get(i).getHour(),2) * weight[1]) + (test.get(i).getHour() * weight[2]) + weight[3];
            results.add(new Stats(test.get(i).getHour(), testOut));

            testTotalError += Math.pow((test.get(i).getRate() - testOut), 2);
            System.out.println("Expected: " + test.get(i).getRate()+ " Predicted: " + testOut);
        }
        System.out.println("Mean Square Error = " + Math.sqrt(testTotalError)/ test.size());
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
