import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ActionTest {

	private double deltaTau;
	private double xold;
	private double xprev;
	private Action action;
	private double mass;

	@Before
	public void setUp() {
		deltaTau = 0.1;
		mass = 1.0;
		xold = 0.5;
		xprev = 0.3;
		action = new Action(deltaTau, mass);
	}
	
	@Test
	public void testCalculateKinetic() {
		double expect = 0.5 * mass * (xold-xprev)*(xold-xprev) / deltaTau;
		double kineticAction = action.calculateKinetic(xold, xprev);
		assertEquals(expect,kineticAction,1e-14);
	}
	
	public void testCalculatePotential() {
		double expect = 0.5 * mass * deltaTau * xold * xold;
		double potentialAction = action.calculatePotential(xold);
		assertEquals(expect,potentialAction,1e-14);		
	}

}
