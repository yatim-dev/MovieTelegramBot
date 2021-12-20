package Recognizers;

import Database.Arrays;
import GO.LemmatizerGo;
import GO.LevenshteinGo;

import java.util.ArrayList;
import java.util.List;

public class SecondRecognizer {

    public ArrayList<String> SecondRecognizerArray = new ArrayList<>();

    public void Search(String[] collection, List<String> userInput){
        LemmatizerGo lemmatizerGo = new LemmatizerGo(userInput);
        LevenshteinGo levenshteinGo = new LevenshteinGo(lemmatizerGo.lemmatizerArray);
        for (int i = 0; i < levenshteinGo.LevenshteinArray.size(); i++)
            for (int j = 0; j < collection.length; j++)
                if (levenshteinGo.LevenshteinArray.get(i).equals(collection[j]))
                    SecondRecognizerArray.add(collection[j]);
    }
}
