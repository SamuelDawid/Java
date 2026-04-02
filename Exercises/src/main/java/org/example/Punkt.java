package org.example;

import java.util.Objects;

public class Punkt {
    int x,y;

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Punkt punkt)) return false;
        Punkt p = (Punkt) o;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
