import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Proj3
{

    public static void main(String[] args) throws IOException {
        List<Double> allData = readData();
    }

    private static List<Double> readData() throws IOException {
        ArrayList<Double> train1 = new ArrayList<>();
        ArrayList<Double> train2 = new ArrayList<>();
        ArrayList<Double> train3 = new ArrayList<>();
        ArrayList<Double> test = new ArrayList<>();
        File file1 = new File("/Users/Bansri/Desktop/CSAI/Project3/train_data_1.txt");
        Scanner scanFile = new Scanner(file1);

        File file2 = new File("/Users/Bansri/Desktop/CSAI/Project3/train_data_2.txt");
        Scanner scanFile2 = new Scanner(file2);

        File file3 = new File("/Users/Bansri/Desktop/CSAI/Project3/train_data_3.txt");
        Scanner scanFile3 = new Scanner(file3);

        File file4 = new File("/Users/Bansri/Desktop/CSAI/Project3/test_data_4.txt");
        Scanner scanFile4 = new Scanner(file4);

        return train3;

    }


}
