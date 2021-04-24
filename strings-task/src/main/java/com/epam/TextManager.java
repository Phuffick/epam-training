package com.epam;

import com.epam.entities.Text;
import com.epam.entities.textparts.Sentence;
import com.epam.entities.textparts.words.Word;
import com.epam.parsers.TextParser;
import com.epam.textloaders.FileTextLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Text manager definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class TextManager {

    /** Text to be manipulated */
    private Text text;

    /**
     * Constructor with any loader
     * @param fileTextLoader any file loader class implementation
     */
    public TextManager(FileTextLoader fileTextLoader) {
        String textInStrings;
        try {
            textInStrings = fileTextLoader.load();
            text = TextParser.parse(textInStrings);
        } catch (IOException e) {
            System.out.println("File exception occurred.");
        }
    }

    /**
     * Find unique word(s) in one sequence method.
     * Finds unique word(s) from first param sequence in second param sequence
     * @param possiblyUniqueWords words of possibly unique words
     * @param wordsListToCheckUnique sequence to check unique
     * @return sequence (list of words) of unique words in sentence
     */
    public ArrayList<Word> findUniqueWordInSentence(
            ArrayList<Word> possiblyUniqueWords,
            Sentence wordsListToCheckUnique) {
        Iterator<Word> sentenceToFindUniqueWordIterator
                = possiblyUniqueWords.iterator();
        while (sentenceToFindUniqueWordIterator.hasNext()) {
            Iterator<Word> currSentenceIterator
                    = wordsListToCheckUnique.get().iterator();
            Word wordInCurrSentence
                    = sentenceToFindUniqueWordIterator.next();
            while (currSentenceIterator.hasNext()) {
                Word currWord = currSentenceIterator.next();
                if (wordInCurrSentence.equals(currWord)) {
                    sentenceToFindUniqueWordIterator.remove();
                    break;
                }
            }
        }
        return possiblyUniqueWords;
    }

    /**
     * Find unique word(s) in the whole text method.
     * Finds unique word(s) from "text(index)" sentence in the the text.
     * @param sentenceToFindUniqueWordIdx index of sentence to find
     *                                    there unique word(s) of the text
     * @return words that are unique from chosen sentence in the text
     */
    public ArrayList<Word> findUniqueWordInSentenceAtText(
            int sentenceToFindUniqueWordIdx) {
        if (sentenceToFindUniqueWordIdx < 0)  {
            throw new IllegalArgumentException(
                    "Sentence idx is less than 0");
        }
        if (sentenceToFindUniqueWordIdx >= text.getLength()) {
            throw new IllegalArgumentException(
                    "Sentence idx is more than sentences count");
        }
        ArrayList<Word> sentenceToFindUniqueWords
                = text.get(sentenceToFindUniqueWordIdx).get();
        for (int sentenceInTextIdx = 0;
             sentenceToFindUniqueWords.size() != 0
                     && sentenceInTextIdx < text.getLength();
             sentenceInTextIdx++) {
            if (sentenceInTextIdx != sentenceToFindUniqueWordIdx) {
                sentenceToFindUniqueWords = findUniqueWordInSentence(
                        sentenceToFindUniqueWords,
                        text.get().get(sentenceInTextIdx));
            }
        }
        return sentenceToFindUniqueWords;
    }
}
