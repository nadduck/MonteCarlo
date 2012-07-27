import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class KEEstimatorTest {

	private Path path;
	private Action action;
	private double deltaTau;
	private double mass;
	private int sliceCount;
	private double kT;
	private Estimator kinetic;
	private double angfreq;
	
	@Before
	public void setUp() {
		sliceCount = 3;
		kT = 0.5;
		angfreq = 1.0;
		path = new Path(sliceCount, kT);
		path.setPosition(1, 1);
		path.setPosition(2,0.5);
		deltaTau = path.getDeltaTau();
		mass = 1.0;
		action = new ExactSHOAction(deltaTau, mass, angfreq);
		kinetic = new KineticEnergyEstimator(path, action, mass);
	}
	
	@Test
	public void testGetValue() {
		double expect = 0;
    	for (int i = 0; i < sliceCount; i++) {
    		double x = path.getPosition(i);
    		double xnext = path.getPosition((i+1)%sliceCount);
    		expect -= mass * action.getMassDerivative(x, xnext) / deltaTau;    		
    	}
    	expect /= sliceCount;
		double kineticEnergy = kinetic.getValue();
		assertEquals(expect, kineticEnergy,1e-14);
		
	}

}
