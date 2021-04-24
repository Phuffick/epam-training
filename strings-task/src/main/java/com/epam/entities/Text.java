package com.epam.entities;

import com.epam.entities.textparts.Sentence;
import com.epam.entities.textparts.words.Word;

import java.util.ArrayList;

/**
 * Text definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class Text {

    /** Sentences */
    private final ArrayList<Sentence> sentences;

    /**
     * Text constructor
     */
    public Text() {
        sentences = new ArrayList<>();
    }

    /**
     * Text constructor with sentences list
     */
    public Text(ArrayList<Sentence> sentences) {
        this.sentences = sentences;
    }

    /**
     * Add sentence
     * @param sentence to add
     */
    public void addSentence(Sentence sentence) {
        this.sentences.add(sentence);
    }

    /**
     * Add sentence from words
     * @param words to add
     */
    public void addSentence(Word[] words) {
        Sentence sentenceToAdd = new Sentence();
        for (Word word : words) {
            sentenceToAdd.addWord(word);
        }
        this.sentences.add(sentenceToAdd);
    }

    /**
     * Remove sentence
     * @param sentence to remove
     */
    public void removeSentence(Sentence sentence) {
        this.sentences.remove(sentence);
    }

    /**
     * Text getter
     * @return text (list of sentences)
     */
    public ArrayList<Sentence> get() {
        return sentences;
    }

    /**
     * Sentence from text getter
     * @return sentence
     */
    public Sentence get(int sentenceIdx) {
        if (sentenceIdx < 0)  {
            throw new IllegalArgumentException(
                    "Sentence idx is less than 0");
        }
        if (sentenceIdx >= sentences.size()) {
            throw new IllegalArgumentException(
                    "Sentence idx is more than sentences count");
        }
        return sentences.get(sentenceIdx);
    }

    /**
     * Text length getter
     * @return text length
     */
    public int getLength() {
        return sentences.size();
    }

    /**
     * Book in string getter
     * @return book in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Sentence sentence : sentences) {
            stringBuilder.append(sentence.toString());
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
        Text book = (Text) obj;
        return sentences.equals(book.sentences);
    }
}
