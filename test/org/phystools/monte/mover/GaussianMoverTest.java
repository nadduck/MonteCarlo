package org.phystools.monte.mover;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.phystools.monte.geometry.*;
import org.phystools.monte.path.PathSegment;

public class GaussianMoverTest {

    private Random rand;
    private Random randCopy;
    private double deltaTau;
    private double mass;
    private Mover mover;
    private PathSegment segment;
    private double sigma;
    private double sigma2;
    

    @Before
    public void setUp() throws Exception {
        deltaTau = 0.1;
        mass = 1.0;
        rand = new Random(42L);
        randCopy = new Random(42L);
        GeometryFactory factory = new GeometryFactory1D();
        mover = new GaussianMover(rand, deltaTau, mass, factory);
        Point zero = factory.createNewPoint(new double [] {0.0});
        segment = new PathSegment(zero,zero,zero);
        segment.setNext(factory.createNewPoint(new double [] {1.0}));
        sigma2 = 0.5 * deltaTau / mass;
        sigma = Math.sqrt(sigma2);
    }

    @Test
    public void testNewPosition() {
        Displacement delta = new Displacement1D();
        Point expect = segment.getMidpoint();
        expect.move(delta.newGaussianRand(sigma, randCopy));
        Point actual = mover.sampleNewPosition(segment);
        assertArrayEquals(expect.toArray(), actual.toArray(), 1e-14);
    }

    @Test
    public void testTransitionProbability() {
    	Point xold = segment.getX();
        Point xnew = mover.sampleNewPosition(segment);
        Point xmid = segment.getMidpoint();
        Displacement xnewDiff = xnew.getDifference(xmid);
        double deltaXNew2 = xnewDiff.getMagnitude2();
        Displacement xoldDiff = xold.getDifference(xmid);
        double deltaXOld2 = xoldDiff.getMagnitude2();
        double expect = Math.exp(-0.5 * (deltaXNew2 - deltaXOld2) / sigma2 );
        double tranProb = mover.getLastTransitionProbability();
        assertEquals(expect, tranProb, 1e-14);
    }
    
}
