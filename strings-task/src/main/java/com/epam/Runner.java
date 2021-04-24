package com.epam;

import com.epam.entities.textparts.words.Word;
import com.epam.textloaders.TxtTextLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Runner class
 *
 * @version    1.0.0 26 March 2021
 * @author     Belyack Maxim
 */
public class Runner {

    /**
     * Prints array list
     * @param list of elements to print
     * @param <T> any type
     */
    public static <T> void printArrayList(ArrayList<T> list) {
        System.out.print("[");
        for (T element : list) {
            System.out.print(element.toString());
            if (element == list.get(list.size() - 1)) {
                System.out.print("]");
            } else {
                System.out.print(", ");
            }
        }
        System.out.print("\n");
    }

    /**
     * Main method
     * Finds a word in the first sentence that is
     * never been used in any others sentences.
     */
    public static void main(String[] args )
            throws FileNotFoundException {
        TextManager textManager = new TextManager(new TxtTextLoader(
                new File("src" + File.separator + "main" + File.separator
                        + "java" + File.separator + "com" + File.separator
                        + "epam" + File.separator + "resources"
                        + File.separator + "book-part-1.txt")));
        ArrayList<Word> uniqueWords
                =  textManager.findUniqueWordInSentenceAtText(0);
        if (uniqueWords.size() != 0) {
            System.out.println("Unique words:");
            printArrayList(uniqueWords);
        } else{
            System.out.println("Sentence have no unique words.");
        }
    }
}
