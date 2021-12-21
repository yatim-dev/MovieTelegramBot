package GO;

import Algoritms.LevenshteinAlgorithm;

public class LevenshteinGo {

    public String LevenshteinWord;

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
    }
}