import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TotalEstimatorTest {
	private Path path;
	private Action action;
	private double deltaTau;
	private double mass;
	private int sliceCount;
	private double kT;
	private Estimator potential;
	private Estimator kinetic;
	private Estimator total;
	private double angfreq;
	
	@Before
	public void setUp() {
		sliceCount = 3;
		kT = 0.5;
		path = new Path(sliceCount, kT);
		path.setPosition(1, 1);
		path.setPosition(2,0.5);
		deltaTau = path.getDeltaTau();
		mass = 1.0;
		angfreq = 1.0;
		action = new ExactSHOAction(deltaTau, mass, angfreq);
		kinetic = new KineticEnergyEstimator(path, action, mass);
		potential = new PotentialEnergyEstimator(path, action, mass);		
		total = new TotalEnergyEstimator(potential, kinetic);
	}
	@Test
	public void testGetValue() {
		double expect = potential.getValue() + kinetic.getValue();
		
		double totalEnergy = total.getValue();
		assertEquals(expect,totalEnergy,1e-14);
	}

}
