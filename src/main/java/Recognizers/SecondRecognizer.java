package Recognizers;

import GO.LemmatizerGo;
import GO.LevenshteinGo;

import java.util.ArrayList;

public class SecondRecognizer {

    public ArrayList<String> SecondRecognizerArray = new ArrayList<>();

    public ArrayList<String> search(String[] collection, String userInput) {
        LemmatizerGo lemmatizerUserInput = new LemmatizerGo(userInput);
        LevenshteinGo levenshteinGo = new LevenshteinGo(collection, lemmatizerUserInput.lemmatizerWord);
        for (String string : collection)
            if (levenshteinGo.LevenshteinWord.equals(string))
                SecondRecognizerArray.add(string);

        return SecondRecognizerArray;
    }
}
