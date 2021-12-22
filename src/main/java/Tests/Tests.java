package Tests;

import java.io.*;

import Database.UserInfo.FindUserResponse;
import Database.UserInfo.Recognizer;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;
import com.sun.tools.javac.Main;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class Tests {

    int index;
    public String TestsWord;
    ArrayList<String> endOutput = new ArrayList<>();
    ArrayList<String> endInput = new ArrayList<>();

    @Before
    public void ReadOutput() throws IOException {
        FileReader output = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\test2");
        Scanner scan = new Scanner(output);

        while (scan.hasNextLine()){
            endOutput.add(scan.nextLine());
        }
        output.close();
    }

    @Before
    public void ReadInput() throws IOException {
        FileReader input = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\test1");
        Scanner scan = new Scanner(input);

        while (scan.hasNextLine()){
            endInput.add(scan.nextLine());
        }
        input.close();
    }

    @Before
    public String GetTestWord(){
        for (int i = 0; endInput.size() > i; i ++){
            for(int j = 0; endInput.get(i).length() > j; j++){
                var str
                return endInput.get(index).split(" ")[j];

            }
        }
        return null;
    }

    @Test
    public void Tests() {
        var index = 0;
        for (int i = 0; endInput.size() > i; i ++){
            for(int j = 0; endInput.get(i).length() > j; j++){
                assertEquals(endOutput.get(index).split(" ")[j], Recognizer.);
            }
        }
        for(int j = 1; endInput.size() > j; j++){
            try {
                assertEquals(endOutput.get(index), Recognizer.Recognizer(endInput.get(index)));
            }
            catch (ComparisonFailure comparisonFailure){
                System.out.println(endInput.get(index) + "\n" + " - " + endOutput.get(index) + " : " + Main.First(endInput.get(index)));
                index += 1;
                continue;
            }
            index += 1;
        }
    }
}
