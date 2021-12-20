package Recognizers;

import Algoritms.Preventive;
import Database.Arrays;
import GO.StemmerGo;

import java.util.ArrayList;
import java.util.List;

public class FirstRecognizer {

    public ArrayList<String> FirstRecognizerArray = new ArrayList<>();

    public void Search(String[] collection, List<String> userInput){
        StemmerGo stemmerGoUserInput = new StemmerGo(userInput);
        Preventive preventive = new Preventive(stemmerGoUserInput.stemmerArray);
        StemmerGo stemmerPreventive = new StemmerGo(preventive.PreventiveArray);
        StemmerGo stemmerGoDictionary = new StemmerGo(List.of(collection));
        for (int i = 0; i < stemmerPreventive.stemmerArray.size(); i++)
            for (int j = 0; j < stemmerGoDictionary.stemmerArray.size(); j++)
                if (stemmerPreventive.stemmerArray.get(i).equals(stemmerGoDictionary.stemmerArray.get(j)))
                    FirstRecognizerArray.add(collection[j]);
    }
}
