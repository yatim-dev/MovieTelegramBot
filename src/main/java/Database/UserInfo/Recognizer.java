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
    ArrayList<String> stemmerAndEquals = new ArrayList<>();
    ArrayList<String> lemmatizerAndLevenshtein = new ArrayList<>();
    ArrayList<String> lemmatizerAndStemmer = new ArrayList<>();

    public Recognizer(
            String word, FirstRecognizer firstRecognizer,
            SecondRecognizer secondRecognizer, ThirdRecognizer thirdRecognizer
    ) {
        ArrayList<String[]> dictionary = new ArrayList<>();
        dictionary.add(Arrays.Category);
        dictionary.add(Arrays.Country);
        dictionary.add(Arrays.Genre);
        dictionary.add(Arrays.Lastname);

        int index = 0;
        for (String[] array : dictionary) { //одно слово из юзера по всем категориям разными методами инжекс - ячейка
            specialWordChecker(word);
            if(output != null) break;
            stemmerAndEquals = firstRecognizer.search(array, word); //полное совпадение
            lemmatizerAndLevenshtein = secondRecognizer.search(array, word);
            lemmatizerAndStemmer = thirdRecognizer.search(array, word);
            getBestWord(word, index, stemmerAndEquals, lemmatizerAndLevenshtein, lemmatizerAndStemmer);
            stemmerAndEquals.clear();
            lemmatizerAndLevenshtein.clear();
            lemmatizerAndStemmer.clear();
            index++;
        }
    }

    private void getBestWord(String word, int index, ArrayList<String> stemmerAndEquals,
                             ArrayList<String> lemmatizerAndLevenshtein, ArrayList<String> lemmatizerAndStemmer) {
        if(stemmerAndEquals.size() == 0) return;
        ArrayList<String> all = new ArrayList<>();
        all.add(stemmerAndEquals.get(0));
        all.add(lemmatizerAndLevenshtein.get(0));
        all.add(lemmatizerAndStemmer.get(0));
        StemmerGo stemmer = new StemmerGo(word);
        LevenshteinGo levenshtein = new LevenshteinGo(all, stemmer.stemmerWord);
        indexArray = index;
        if (distance > levenshtein.Distance){
            output = levenshtein.LevenshteinWord;
            distance = levenshtein.Distance;
        }
    }

    private void specialWordChecker(String word){
        StemmerGo stemmer = new StemmerGo(word);
        switch (stemmer.stemmerWord) {
            case "нов" -> {
                indexArray = 5;
                output = "2018";
            }
            case "стар" -> {
                indexArray = 5;
                output = "1990";
            }
            case "хорош" -> {
                indexArray = 4;
                output = "8.0";
            }
            case "плох" -> {
                indexArray = 4;
                output = "4.0";
            }
        }
    }
}
