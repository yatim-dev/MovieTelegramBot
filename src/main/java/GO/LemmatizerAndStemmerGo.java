package GO;

import Algoritms.LemmatizerAlgorithm;
import Algoritms.StemmerAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class LemmatizerAndStemmerGo {

    public ArrayList<String> GetWords = new ArrayList<>();

    public LemmatizerAndStemmerGo(List<String> words) {
        LemmatizerAlgorithm lemmatizer = new LemmatizerAlgorithm(words);

        var lemmaWords = lemmatizer.LemmatizerWords;
        StemmerAlgorithm stemmer = new StemmerAlgorithm();

        for(int i = 0; i < words.size(); i++){
            GetWords.add(stemmer.stem(lemmaWords.get(i)));
        }
    }
}