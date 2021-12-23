package Recognizers;

import Algoritms.Preventive;
import GO.StemmerGo;

import java.util.ArrayList;
import java.util.List;

public class FirstRecognizer {

    public ArrayList<String> FirstRecognizerArray = new ArrayList<>();

    public ArrayList<String> search(String[] collection, String userInput) {
        StemmerGo stemmerUserInput = new StemmerGo(userInput);
        Preventive preventive = new Preventive(stemmerUserInput.stemmerWord);
        StemmerGo stemmerPreventive = new StemmerGo(preventive.PreventiveWord);
        StemmerGo stemmerGoDictionary = new StemmerGo(List.of(collection));
        for (int j = 0; j < stemmerGoDictionary.stemmerArray.size(); j++)
            if (stemmerPreventive.stemmerWord.equals(stemmerGoDictionary.stemmerArray.get(j)))
                FirstRecognizerArray.add(collection[j]);

        return FirstRecognizerArray;
    }
}
