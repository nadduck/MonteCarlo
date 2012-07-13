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
	
	@Before
	public void setUp() {
		sliceCount = 3;
		kT = 0.5;
		path = new Path(sliceCount, kT);
		path.setPosition(1, 1);
		path.setPosition(2,0.5);
		deltaTau = path.getDeltaTau();
		mass = 1.0;
		action = new PrimitiveAction(deltaTau, mass);
		kinetic = new KineticEnergyEstimator(path, action, mass);
		potential = new PotentialEnergyEstimator(path, action, mass);		
		total = new TotalEnergyEstimator(potential, kinetic);
	}
	@Test
	public void testGetValue() {
		double kineticexpect = 0;
		for(int i = 0; i < sliceCount; i++) {
			double x = path.getPosition(i);
			double xnext = path.getPosition((i+1)%sliceCount);
			kineticexpect -= 0.5 * mass * (xnext-x)*(xnext-x) / (deltaTau*deltaTau);
		}
		kineticexpect /= sliceCount;
		kineticexpect += 1/(2*deltaTau);
		
		double avgx = 0;
    	for (int i = 0; i < sliceCount; i++) {
            double x = path.getPosition(i);
            avgx += x;
        }
    	avgx /= sliceCount;
		double potentialexpect = 0.5 * mass * avgx * avgx;
		
		double expect = potentialexpect + kineticexpect;
		
		double totalEnergy = total.getValue();
		assertEquals(expect,totalEnergy,1e-14);
	}

}
