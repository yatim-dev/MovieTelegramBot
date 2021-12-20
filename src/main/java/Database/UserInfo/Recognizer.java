package Database.UserInfo;

import Database.Arrays;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;

import java.util.ArrayList;
import java.util.List;

public class Recognizer {

    public Recognizer(
            List<String> words, FirstRecognizer firstRecognizer,
            SecondRecognizer secondRecognizer, ThirdRecognizer thirdRecognizer
    )
    {
        ArrayList<String[]> dictionary = new ArrayList<>();
        dictionary.add(Arrays.Category);
        dictionary.add(Arrays.Country);
        dictionary.add(Arrays.Genre);
        dictionary.add(Arrays.Director);

        for (String[] array : dictionary) {
            firstRecognizer.Search(array, words);
            secondRecognizer.Search(array, words);
            thirdRecognizer.Search(array, words);
        }

    }
}
