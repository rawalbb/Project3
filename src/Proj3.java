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
        List<Stats> train_1 = readData(train1,"/Users/Bansri/Desktop/CSAI/Project3/train_data_1.txt");
        List<Stats> train_2 = readData(train2,"/Users/Bansri/Desktop/CSAI/Project3/train_data_2.txt");
        List<Stats> train_3 = readData(train3,"/Users/Bansri/Desktop/CSAI/Project3/train_data_3.txt");
        List<Stats> train_4 = readData(test,"/Users/Bansri/Desktop/CSAI/Project3/test_data_4.txt");
    }

    private static List<Stats> readData(ArrayList<Stats> list, String filepath) throws IOException {
        File file1 = new File(filepath);
        Scanner scanFile = new Scanner(file1);

        while (scanFile.hasNextLine()) {
            String dataPoint = scanFile.nextLine();
            String[] data = dataPoint.split(", ");
            list.add(new Stats(Double.parseDouble(data[0]), Double.parseDouble(data[1])));
        }
        return list;

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
