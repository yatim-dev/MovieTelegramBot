package Algoritms;

import Database.Arrays;

import java.util.ArrayList;
import java.util.List;

public class Preventive {

    public String PreventiveWord;

    public Preventive(String word) {
        var synonymString = Arrays.SynonymDictionary.get(word);
        if (synonymString != null)
            PreventiveWord = synonymString;
        else
            PreventiveWord = word;
    }
}