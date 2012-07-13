import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PrimitiveActionTest {

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
		action = new PrimitiveAction(deltaTau, mass);
	}
	
	@Test
	public void testMassDerivative() {
		double expect = 0.25 * deltaTau *(xold*xold + xprev*xprev);
		double kineticAction = action.getMassDerivative(xold, xprev);
		assertEquals(expect,kineticAction,1e-14);
	}
	
	@Test
	public void testDeltaTauDerivative() {
		double expect = 0.25 * mass * (xold*xold + xprev*xprev);
		double potentialAction = action.getDeltaTauDerivative(xold, xprev);
		assertEquals(expect,potentialAction,1e-14);		
	}

}
