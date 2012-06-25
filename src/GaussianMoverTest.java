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
        segment = new PathSegment();
        segment.xnext = 1.0;
        sigma2 = 0.5 * deltaTau / mass;
        sigma = Math.sqrt(sigma2);
    }

    @Test
    public void testNewPosition() {
        double gaussRand = randCopy.nextGaussian();
        double expect = sigma * gaussRand + segment.getMidpoint();
        double xnew = mover.sampleNewPosition(segment);
        assertEquals(expect, xnew, 1e-14);
    }

    @Test
    public void testTransitionProbability() {
        double xold = segment.x;
        double xnew = mover.sampleNewPosition(segment);
        double xmid = segment.getMidpoint();
        double deltaXNew2 = (xnew - xmid) * (xnew - xmid);
        double deltaXOld2 = (xold - xmid) * (xold - xmid);
        double expect = Math.exp(-0.5 * (deltaXNew2  - deltaXOld2) / sigma2 );
        double tranProb = mover.getLastTransitionProbability();
        assertEquals(expect, tranProb, 1e-14);
    }
    
}
