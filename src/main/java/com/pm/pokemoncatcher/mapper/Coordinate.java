package com.pm.pokemoncatcher.mapper;

/**
 *
 * @author joanapinto
 */
public class Coordinate {

    public final long x;
    public final long y;

    public Coordinate(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 37 * result + (int) this.x;
        result = 37 * result + (int) this.y;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate pos = (Coordinate) o;
            return ((this.x == pos.x) && (this.y == pos.y));
        }
        return false;
    }
}
