package Algoritms;

import Database.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Preventive {

    public ArrayList<String> PreventiveArray = new ArrayList<>();

    public Preventive(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            var synonymString = Arrays.SynonymDictionary.get(words.get(i));
            if (synonymString != null){
                PreventiveArray.add(i, synonymString);
            }else {
                PreventiveArray.add(i, words.get(i));
            }
        }
    }
}