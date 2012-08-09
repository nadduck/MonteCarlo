package org.phystools.monte.mover;

import org.phystools.monte.geometry.Point;
import org.phystools.monte.path.PathSegment;

public interface Mover {

    public abstract Point sampleNewPosition(PathSegment segment);

    public abstract double getLastTransitionProbability();

}