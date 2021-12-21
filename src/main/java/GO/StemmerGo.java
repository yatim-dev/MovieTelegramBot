package GO;

import Algoritms.StemmerAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class StemmerGo {

    public ArrayList<String> stemmerArray = new ArrayList<>();
    public String stemmerWord;

    public StemmerGo(List<String> words) {
        StemmerAlgorithm stemmer = new StemmerAlgorithm();
        for (String word : words) {
            stemmerArray.add(stemmer.stem(word));
        }
    }

    public StemmerGo(String word) {
        StemmerAlgorithm stemmer = new StemmerAlgorithm();
        stemmerWord = stemmer.stem(word);
    }
}