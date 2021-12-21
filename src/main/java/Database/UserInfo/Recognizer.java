package Database.UserInfo;

import Database.Arrays;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;

import java.util.ArrayList;
import java.util.List;

public class Recognizer {

    ArrayList<String> a1 = new ArrayList<>();
    ArrayList<String> b1 = new ArrayList<>();
    ArrayList<String> c1 = new ArrayList<>();

    public Recognizer(
            List<String> words, FirstRecognizer firstRecognizer,
            SecondRecognizer secondRecognizer, ThirdRecognizer thirdRecognizer
    ) {
        ArrayList<String[]> dictionary = new ArrayList<>();
        dictionary.add(Arrays.Category);
        dictionary.add(Arrays.Country);
        dictionary.add(Arrays.Genre);
        dictionary.add(Arrays.Director);

        var index = 0;
        for(String word : words)
            for (String[] array : dictionary)
            {
                a1 = firstRecognizer.Search(array, word);
                b1 = secondRecognizer.Search(array, word);
                c1 = thirdRecognizer.Search(array, word);
                index++;
            }

    }
}
