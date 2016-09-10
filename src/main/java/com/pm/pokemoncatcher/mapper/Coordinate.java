package com.pm.pokemoncatcher.mapper;

/**
 *
 * @author joanapinto
 */
public class Coordinate {

    /**
     * component x of coordinate
     */
    public final long x;

    /**
     * component x of coordinate
     */
    public final long y;

    /**
     * Coordinate constructor
     * @param x
     * @param y
     */
    public Coordinate(long x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Assign a hashcode to a coordinate
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = 37 * result + (int) this.x;
        result = 37 * result + (int) this.y;
        return result;
    }

    /**
     * Compare if two coordinates are the same
     * @param o coordinate to compare
     * @return true if same coordinate
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            Coordinate pos = (Coordinate) o;
            return ((this.x == pos.x) && (this.y == pos.y));
        }
        return false;
    }
}
