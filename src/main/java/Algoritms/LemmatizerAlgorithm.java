package Algoritms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.github.demidko.aot.WordformMeaning.lookupForMeanings;

public class LemmatizerAlgorithm {

    public ArrayList<String> LemmatizerWords = new ArrayList<>();

    public LemmatizerAlgorithm(List<String> words) {
        for (String word : words) {
            try {
                LemmatizerWords.add(String.valueOf(lookupForMeanings(word).get(0).getLemma()));
            } catch (IndexOutOfBoundsException | IOException ex) {
                LemmatizerWords.add(word.toLowerCase(Locale.ROOT));
            }
        }


    }
}