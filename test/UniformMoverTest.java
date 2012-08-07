import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class UniformMoverTest {

	private Random rand;
	private Random randCopy;
	private double delta;
	private PathSegment segment;
	private UniformMover mover;

	@Before
	public void setUp() {
		rand = new Random(42L);
		randCopy = new Random(42L);
		delta = 0.5;
		Point zero = new Point(0);
		segment = new PathSegment(zero, zero, zero);
		mover = new UniformMover(rand,delta);		
	}
	
	@Test
	public void testNewPosition() {
		double random = 2 * randCopy.nextDouble() - 1;
		double expect = segment.getX().getMagnitude() + random * delta;
		double xnew = mover.sampleNewPosition(segment).getMagnitude();
		assertEquals(expect,xnew,1e-14);
	}

}
