package GO;

import Algoritms.LemmatizerAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LemmatizerGo {

    public ArrayList<String> lemmatizerArray = new ArrayList<>();
    public String lemmatizerWord;

    public LemmatizerGo(List<String> words) {
        LemmatizerAlgorithm lemmatizer = new LemmatizerAlgorithm(words);
        lemmatizerArray.addAll(lemmatizer.LemmatizerWords);
    }

    public LemmatizerGo(String word) {
        LemmatizerAlgorithm lemmatizer = new LemmatizerAlgorithm(Collections.singletonList(word));
        lemmatizerWord = String.valueOf(lemmatizer.LemmatizerWords);
    }
}