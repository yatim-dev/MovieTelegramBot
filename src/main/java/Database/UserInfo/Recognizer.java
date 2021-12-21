package Database.UserInfo;

import Database.Arrays;
import GO.LevenshteinGo;
import GO.StemmerGo;
import Recognizers.FirstRecognizer;
import Recognizers.SecondRecognizer;
import Recognizers.ThirdRecognizer;

import java.util.ArrayList;

public class Recognizer {

    public int indexArray;
    public String output;
    private int distance = Integer.MAX_VALUE;
    ArrayList<String> recognizer1 = new ArrayList<>();
    ArrayList<String> recognizer2 = new ArrayList<>();
    ArrayList<String> recognizer3 = new ArrayList<>();

    public Recognizer(
            String word, FirstRecognizer firstRecognizer,
            SecondRecognizer secondRecognizer, ThirdRecognizer thirdRecognizer
    ) {
        ArrayList<String[]> dictionary = new ArrayList<>();
        dictionary.add(Arrays.Category);
        dictionary.add(Arrays.Country);
        dictionary.add(Arrays.Genre);
        dictionary.add(Arrays.Director);

        int index = 0;
        for (String[] array : dictionary) { //одно слово из юзера по всем категориям разными методами инжекс - ячейка
            recognizer1 = firstRecognizer.Search(array, word); //полное совпадение
            recognizer2 = secondRecognizer.Search(array, word);
            recognizer3 = thirdRecognizer.Search(array, word);
            getBestWord(word, index, recognizer1, recognizer2, recognizer3);
            recognizer1.clear();
            recognizer2.clear();
            recognizer3.clear();
            index++;
        }
    }

    private void getBestWord(String word, int index, ArrayList<String> recognizer1,
                             ArrayList<String> recognizer2, ArrayList<String> recognizer3) {
        if(recognizer1.size() == 0) return;
        ArrayList<String> all = new ArrayList<>();
        all.add(String.valueOf(recognizer1));
        all.add(String.valueOf(recognizer2));
        all.add(String.valueOf(recognizer3));
        StemmerGo stemmer = new StemmerGo(word);
        LevenshteinGo levenshtein = new LevenshteinGo(all, stemmer.stemmerWord);
        indexArray = index;
        if (distance > levenshtein.Distance){
            output = levenshtein.LevenshteinWord;
            distance = levenshtein.Distance;
        }
    }
}
