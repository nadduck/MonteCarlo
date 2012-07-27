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
		double expect = -1/(2*mass); 
		expect += 0.5 * (xold - xprev)*(xold - xprev) / deltaTau;
		expect += 0.25 * deltaTau *(xold*xold + xprev*xprev);
		double massderiv = action.getMassDerivative(xold, xprev);
		assertEquals(expect,massderiv,1e-14);
	}
	
	@Test
	public void testDeltaTauDerivative() {
		double expect = 1/(2*deltaTau);
		expect -= 0.5 * mass * (xold - xprev)*(xold - xprev) / (deltaTau * deltaTau);
		expect += 0.25 * mass * (xold*xold + xprev*xprev);
		double deltatauderiv = action.getDeltaTauDerivative(xold, xprev);
		assertEquals(expect,deltatauderiv,1e-14);		
	}

}
