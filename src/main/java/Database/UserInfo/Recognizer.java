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
    ArrayList<String> a1 = new ArrayList<>();
    ArrayList<String> b1 = new ArrayList<>();
    ArrayList<String> c1 = new ArrayList<>();

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
            a1 = firstRecognizer.Search(array, word); //полное совпадение
            b1 = secondRecognizer.Search(array, word);
            c1 = thirdRecognizer.Search(array, word);
            getBestWord(word, index, a1, b1, c1);
            a1.clear();
            b1.clear();
            c1.clear();
            index++;
        }
    }

    private void getBestWord(String word, int index, ArrayList<String> a1, ArrayList<String> b1, ArrayList<String> c1) {
        if(a1.size() == 0) return;
        ArrayList<String> all = new ArrayList<>();
        all.add(String.valueOf(a1));
        all.add(String.valueOf(b1));
        all.add(String.valueOf(c1));
        StemmerGo stemmer = new StemmerGo(word);
        LevenshteinGo levenshtein = new LevenshteinGo(all, stemmer.stemmerWord);
        indexArray = index;
        if (distance > levenshtein.Distance){
            output = levenshtein.LevenshteinWord;
            distance = levenshtein.Distance;
        }
    }
}
