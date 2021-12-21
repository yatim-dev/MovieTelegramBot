package Recognizers;

import Algoritms.Preventive;
import GO.LevenshteinGo;
import GO.StemmerGo;

import java.util.ArrayList;

public class ThirdRecognizer {

    public ArrayList<String> ThirdRecognizerArray = new ArrayList<>();

    public ArrayList<String> Search(String[] collection, String userInput) {
        StemmerGo stemmerUserInput = new StemmerGo(userInput);
        Preventive preventive = new Preventive(stemmerUserInput.stemmerWord);
        LevenshteinGo levenshteinGo = new LevenshteinGo(collection, preventive.PreventiveWord);
        for (String string : collection)
            if (levenshteinGo.LevenshteinWord.equals(string))
                ThirdRecognizerArray.add(string);

        return ThirdRecognizerArray;
    }
}