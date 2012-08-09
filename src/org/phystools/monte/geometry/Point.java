package org.phystools.monte.geometry;

public interface Point {

    public abstract double getMagnitude();

    public abstract double getMagnitude2();

    public abstract Displacement getDifference(Point xmid);

    public abstract void move(Displacement delta);

    public abstract double[] toArray();

    public abstract double dot(Point point);

    public abstract Point midpoint(Point xnext);

}