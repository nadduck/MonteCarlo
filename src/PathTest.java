import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PathTest {

	private int sliceCount;
	private double kT;
	private double deltaTau;
	private Path path;
	
	@Before
	public void setUp() {
		sliceCount = 5;
		kT = 3.0;
		deltaTau = 1 / (kT * sliceCount);
		path = new Path(sliceCount, kT);
	}
	
	@Test
	public void testGetAndSetPosition() {
		int index = 3;
		double expect = 0.42;
		path.setPosition(index, expect);
		double testGetPosition = path.getPosition(index);
		assertEquals(expect, testGetPosition, 1e-14);
	}
	
	@Test
	public void testGetValues() {
		double testkT = path.getkT();
		int testSliceCount = path.getSliceCount();
		double testDeltaTau = path.getDeltaTau(); 
		assertEquals(kT, testkT, 1e-14);
		assertEquals(sliceCount, testSliceCount, 1e-14);
		assertEquals(deltaTau,testDeltaTau,1e-14);
	}

}
