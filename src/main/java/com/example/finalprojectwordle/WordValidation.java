package com.example.finalprojectwordle;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class WordValidation {

    private Set<String> wordSet;

    public WordValidation() {
        wordSet = new HashSet<>();
        loadWordsFromFile();
    }

    public void loadWordsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/vedarth/college/CSCE314/FinalProject-Wordle/src/main/java/com/example/finalprojectwordle/word-list.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().toLowerCase().split("\\s+");
                for (String word : words) {
                    wordSet.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for(String s: wordSet) {
            System.out.println(s);
        }
    }

//    public static void main(String[] args) {
//        print();
//    }

    public String getRandomWord() {
        String[] wordArray = wordSet.toArray(new String[0]);

        Random random = new Random();
        int randomIndex = random.nextInt(wordArray.length);

        String randomWord = wordArray[randomIndex];

//        System.out.println("Random word: " + randomWord);
        return randomWord;
    }

    public boolean isValidWord(String guess) {
//        System.out.println("Testing valid word: " + guess);
//        System.out.println("Printing words: " );
//        print();
        return wordSet.contains(guess);
    }

    public Integer[] checkWord(String guess, String randomWord) {
        //0 - letter not in word
        //1 - letter in word, incorrect placement
        //2 - letter in word, correct placement

        Integer[] checkedWord = new Integer[5];
        for(int i = 0; i < guess.length(); i++) {
            if(guess.charAt(i) == randomWord.charAt(i)) {
                checkedWord[i] = 2;
            } else if (randomWord.indexOf(guess.charAt(i)) != -1) {
                checkedWord[i] = 1;
            } else {
                checkedWord[i] = 0;
            }
        }



        for(Integer x: checkedWord) {
            System.out.println(x);
        }

        return checkedWord;
    }
}


