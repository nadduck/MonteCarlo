import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


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
        mover = new GaussianMover(rand, deltaTau, mass);
        Point zero = new Point(0);
        segment = new PathSegment(zero,zero,zero);
        segment.setNext(new Point(1.0));
        sigma2 = 0.5 * deltaTau / mass;
        sigma = Math.sqrt(sigma2);
    }

    @Test
    public void testNewPosition() {
        double gaussRand = randCopy.nextGaussian();
        double expect = sigma * gaussRand + segment.getMidpoint().getMagnitude();
        Point xnew = mover.sampleNewPosition(segment);
        assertEquals(expect, xnew.getMagnitude(), 1e-14);
    }

    @Test
    public void testTransitionProbability() {
    	Point xold = segment.getX();
        Point xnew = mover.sampleNewPosition(segment);
        Point xmid = segment.getMidpoint();
        Displacement xnewDiff = xnew.getDifference(xmid);
        double deltaXNew2 = xnewDiff.getMagnitude() * xnewDiff.getMagnitude();
        Displacement xoldDiff = xold.getDifference(xmid);
        double deltaXOld2 = xoldDiff.getMagnitude() * xoldDiff.getMagnitude();
        double expect = Math.exp(-0.5 * (deltaXNew2 - deltaXOld2) / sigma2 );
        double tranProb = mover.getLastTransitionProbability();
        assertEquals(expect, tranProb, 1e-14);
    }
    
}
