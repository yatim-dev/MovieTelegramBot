package GO;

import Algoritms.LevenshteinAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class LevenshteinGo {

    public ArrayList<String> LevenshteinArray = new ArrayList<>();

    public LevenshteinGo(List<String> words) {
        int min = 1000;
        int a;
        String str = "";
        LevenshteinAlgorithm levenshtein = new LevenshteinAlgorithm();

        for (String word : words) {
            for (int i = 0; i < DataBaseOfMovie.All.length; i++) {
                a = levenshtein.calculateDistance(DataBaseOfMovie.All[i], word);
                if (min > a) {
                    min = a;
                    str = DataBaseOfMovie.All[i];
                }
            }
            min = 1000;
            LevenshteinArray.add(str);
        }
    }


}