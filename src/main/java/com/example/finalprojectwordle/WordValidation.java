package com.example.finalprojectwordle;

import java.io.FileInputStream;
import java.util.*;
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
                wordSet.add(line.trim()); // Add the entire line as it is
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

    public String getRandomWord() {
        String[] wordArray = wordSet.toArray(new String[0]);

        Random random = new Random();
        int randomIndex = random.nextInt(wordArray.length);

        String randomWord = wordArray[randomIndex];

//        System.out.println("Random word: " + randomWord);
        return randomWord;
    }

    public boolean isValidWord(String guess) {
        return wordSet.contains(guess);
    }

        public Integer[] checkWord(String guess, String randomWord) {
            // 0 - letter not in word
            // 1 - letter in word, incorrect placement
            // 2 - letter in word, correct placement

            Integer[] checkedWord = new Integer[5];
            int[] guessCount = new int[26];
            int[] wordCount = new int[26];
            for (char c : guess.toCharArray()) {
                guessCount[c - 'a']++;
            }
            for (char c : randomWord.toCharArray()) {
                wordCount[c - 'a']++;
            }
            for (int i = 0; i < guess.length(); i++) {
                char guessChar = guess.charAt(i);
                char wordChar = randomWord.charAt(i);
                if (guessChar == wordChar && wordCount[guessChar - 'a'] > 0) {
                    checkedWord[i] = 2;
                    guessCount[guessChar - 'a']--;
                    wordCount[guessChar - 'a']--;
                } else if (wordCount[guessChar - 'a'] > 0) {
                    checkedWord[i] = 1;
                    wordCount[guessChar - 'a']--;
                } else {
                    checkedWord[i] = 0;
                }
            }
            return checkedWord;
        }
}


