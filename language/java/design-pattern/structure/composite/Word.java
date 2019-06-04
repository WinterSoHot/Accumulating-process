package gu.java.pattern.structure.composite;

import java.util.List;

public class Word extends LetterComposite {

    /**
     * Constructor
     */
    public Word(List<Letter> letters) {
        for (Letter l : letters) {
            this.add(l);
        }
    }
    @Override
    protected void printThisBefore() {
        System.out.print(" ");
    }
}