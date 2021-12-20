package Recognizers;

import Algoritms.Preventive;
import Database.Arrays;
import GO.LevenshteinGo;
import GO.StemmerGo;

import java.util.ArrayList;
import java.util.List;

public class ThirdRecognizer {

    public ArrayList<String> ThirdRecognizerArray = new ArrayList<>();

    public void Search(String[] collection, List<String> userInput){
        StemmerGo stemmerGo = new StemmerGo(userInput);
        Preventive preventive = new Preventive(stemmerGo.stemmerArray);
        LevenshteinGo levenshteinGo = new LevenshteinGo(preventive.PreventiveArray);
        for (int i = 0; i < levenshteinGo.LevenshteinArray.size(); i++){
            for (int j = 0; j < collection.length; j++){
                if (levenshteinGo.LevenshteinArray.get(i).equals(collection[j])){
                    ThirdRecognizerArray.add(collection[j]);
                }
            }
        }
    }
}