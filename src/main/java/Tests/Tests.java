package Tests;

import java.io.*;

import org.junit.ComparisonFailure;
import org.junit.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class Tests {

    int index;
    int i;
    int j;
    public String TestWord;
    ArrayList<String> output = new ArrayList<>();
    ArrayList<String> input = new ArrayList<>();


    public void ReadOutput() throws IOException {
        FileReader output = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\test2");
        Scanner scan = new Scanner(output);

        while (scan.hasNextLine()){
            this.output.add(scan.nextLine());
        }
        output.close();
    }


    public void ReadInput() throws IOException {
        FileReader input = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\test1");
        Scanner scan = new Scanner(input);

        while (scan.hasNextLine()){
            this.input.add(scan.nextLine());
        }
        input.close();
    }


    public String GetTestWord(){
        //for (; input.size() > i; i ++){
          //  for(; input.get(i).length() > j; j++){
            //    TestWord = input.get(index).split(" ")[j];
              //  index++;
                //i++;
                //j++;
              //  return TestWord;
            //}
        //}
        //return null;
        StringBuilder str = new StringBuilder();
        for (String word : input)
            str.append(word).append(" ");
        return str.toString();

    }

    @Test
   public void Calculate(String output) {
        for (; i < input.size(); i++) {
            for (; j < input.get(i).length(); j++) {
                TestWord = input.get(index).split(" ")[j].toLowerCase(Locale.ROOT);
                index++;
                break;
            }

            try {
                assertEquals(TestWord, output);
            } catch (ComparisonFailure comparisonFailure) {
                System.out.println(TestWord + " - " + output);
            }
        }
    }
}
