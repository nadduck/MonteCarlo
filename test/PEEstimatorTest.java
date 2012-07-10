import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PEEstimatorTest {
	private Path path;
	private Action action;
	private double deltaTau;
	private double mass;
	private int sliceCount;
	private double kT;
	private Estimator potential;
	
	@Before
	public void setUp() {
		sliceCount = 3;
		kT = 0.5;
		mass = 1.0;
		path = new Path(sliceCount, kT);
		path.setPosition(1, 1);
		path.setPosition(2,0.5);
		deltaTau = path.getDeltaTau();
		action = new PrimitiveAction(deltaTau, mass);
		potential = new PotentialEnergyEstimator(path, action);
	}
	@Test
	public void testGetValue() {
		double avgx = 0;
    	for (int i = 0; i < sliceCount; i++) {
            double x = path.getPosition(i);
            avgx += x;
        }
    	avgx /= sliceCount;
		double expect = action.calculatePotential(avgx) / deltaTau;
		double potentialEnergy = potential.getValue();
		assertEquals(expect,potentialEnergy,1e-14);
	}

}
