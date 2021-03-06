package org.phystools.monte.mover;
import java.util.Random;

import org.phystools.monte.geometry.*;
import org.phystools.monte.path.PathSegment;

public class UniformMover implements Mover {
    
    private double delta;
    private Random rand;

    public UniformMover(Random rand, double delta) {
        this.rand = rand;
        this.delta = delta;
    }

    @Override
    public Point sampleNewPosition(PathSegment segment) {
        Point newPosition = segment.getX();
        Displacement deltaDisplacement = new Displacement1D();
                
        newPosition.move(deltaDisplacement.newUniformDisplacement(delta, rand));
    	return newPosition;
    }

    @Override
    public double getLastTransitionProbability() {
        return 1.0;
    }
}
