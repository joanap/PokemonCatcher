package com.pm.pokemoncatcher;

/**
 *
 * @author joanapinto
 */
public class Coordinate {

    long x;
    long y;

    public Coordinate(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 37 * result + (int) this.getX();
        result = 37 * result + (int) this.getY();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate pos = (Coordinate) o;
            return ((this.getX() == pos.getX()) && (this.getY() == pos.getY()));
        }
        return false;
    }
}
