package org.example;

import java.util.Iterator;

public class NumberRange implements Iterable<Integer>{
   private final int start ,end ;

    public NumberRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = start;

            public boolean hasNext() {
                return index <= end;
            }

            public Integer next() {
                return index++;
            }
        };
    }
}

