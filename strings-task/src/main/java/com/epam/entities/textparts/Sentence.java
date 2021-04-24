package com.epam.entities.textparts;

import com.epam.entities.textparts.words.Word;

import java.util.ArrayList;

/**
 * Word definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class Sentence {

    /** Words */
    private final ArrayList<Word> words;

    /**
     * Sequence constructor
     */
    public Sentence() {
        words = new ArrayList<>();
    }

    /**
     * Sequence constructor with words list
     * @param words to set up
     */
    public Sentence(ArrayList<Word> words) {
        this.words = words;
    }

    /**
     * Add word
     * @param word to add
     */
    public void addWord(Word word) {
        this.words.add(word);
    }

    /**
     * Remove word
     * @param word to remove
     */
    public void removeWord(Word word) {
        this.words.remove(word);
    }

    /**
     * Sentence getter
     * @return sentence (list of words)
     */
    public ArrayList<Word> get() {
        return words;
    }

    /**
     * Sentence from text getter
     * @return sentence
     */
    public Word get(int wordIdx) {
        if (wordIdx < 0)  {
            throw new IllegalArgumentException(
                    "Word idx is less than 0");
        }
        if (wordIdx >= words.size()) {
            throw new IllegalArgumentException(
                    "Word idx is more than words count");
        }
        return words.get(wordIdx);
    }

    /**
     * Sentence length getter
     * @return sentence length
     */
    public int getLength() {
        return words.size();
    }

    /**
     * Sequence in string getter
     * @return sequence in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Word word : words) {
            stringBuilder.append(word.toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Override equal method
     * @param obj to check
     * @return is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Sentence sentence = (Sentence) obj;
        return words.equals(sentence.words);
    }
}
