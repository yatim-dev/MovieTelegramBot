package GO;
import Algoritms.LemmatizerAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class LemmatizerGo {

    public ArrayList<String> lemmatizerArray = new ArrayList<>();

    public LemmatizerGo(List<String> words) {
        LemmatizerAlgorithm lemmatizer = new LemmatizerAlgorithm(words);
        lemmatizerArray.addAll(lemmatizer.LemmatizerWords);
    }
}