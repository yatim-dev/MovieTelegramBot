package GO;

import Algoritms.LevenshteinAlgorithm;

import java.util.ArrayList;

public class LevenshteinGo {

    public String LevenshteinWord;
    public Integer Distance;

    public LevenshteinGo(String[] collection, String word) {
        int min = Integer.MAX_VALUE;
        int distance;
        String str = null;
        LevenshteinAlgorithm levenshtein = new LevenshteinAlgorithm();
        for (String string : collection) {
            distance = levenshtein.calculateDistance(string, word);
            if (min > distance) {
                min = distance;
                str = string;
            }
        }
        LevenshteinWord = str;
        Distance = min;
    }

    public LevenshteinGo(ArrayList<String> collection, String word) {
        int min = Integer.MAX_VALUE;
        int distance;
        String str = null;
        LevenshteinAlgorithm levenshtein = new LevenshteinAlgorithm();
        for (String string : collection) {
            distance = levenshtein.calculateDistance(string, word);
            if (min > distance) {
                min = distance;
                str = string;
            }
        }
        LevenshteinWord = str;
        Distance = min;
    }
}