package com.epam.entities.textparts.words.simpleword.composite;

import com.epam.entities.textparts.words.Word;
import com.epam.entities.textparts.words.simpleword.SimpleWordComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple word definition class
 *
 * @version    1.0.0 05 April 2021
 * @author     Belyack Maxim
 */
public class SimpleWord implements Word, SimpleWordComponent {

    /** Simple word components */
    private List<SimpleWordComponent> simpleWordParts;

    /**
     * Default constructor
     */
    public SimpleWord() {
        simpleWordParts = new ArrayList<>();
    }

    /**
     * Simple word constructor from array list
     * @param simpleWord string to parse
     */
    public SimpleWord(ArrayList<SimpleWordComponent> simpleWord) {
        simpleWordParts = simpleWord;
    }

    /**
     * Add simple word component
     * @param simpleWordComponent to add
     */
    public void add(SimpleWordComponent simpleWordComponent) {
        simpleWordParts.add(simpleWordComponent);
    }

    /**
     * Remove simple word component
     * @param simpleWordComponent to remove
     */
    public void remove(SimpleWordComponent simpleWordComponent) {
        simpleWordParts.remove(simpleWordComponent);
    }

    /**
     * Simple word getter
     * @return simple word
     */
    @Override
    public String get() {
        return this.toString();
    }

    /**
     * Length getter
     * @return length
     */
    @Override
    public int getLength() {
        int length = 0;
        for (SimpleWordComponent simpleWord : simpleWordParts) {
            length += simpleWord.getLength();
        }
        return length;
    }

    /**
     * Simple word in string getter
     * @return simple word in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SimpleWordComponent simpleWordComponent
                : simpleWordParts) {
            stringBuilder.append(simpleWordComponent.toString());
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
        SimpleWord simpleWord = (SimpleWord) obj;
        return simpleWordParts != null
                && simpleWordParts.equals(simpleWord.simpleWordParts);
    }
}
