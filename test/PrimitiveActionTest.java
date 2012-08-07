import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PrimitiveActionTest {

	private double deltaTau;
	private Point xold;
	private Point xprev;
	private Action action;
	private double mass;

	@Before
	public void setUp() {
		deltaTau = 0.1;
		mass = 1.0;
		xold = new Point(0.5);
		xprev = new Point(0.3);
		action = new PrimitiveAction(deltaTau, mass);
	}
	
	@Test
	public void testMassDerivative() {
		double expect = -1/(2*mass); 
		Displacement difference = xold.getDifference(xprev);
		expect += 0.5 * difference.getMagnitude()*difference.getMagnitude()/ deltaTau;
		double xold2 = xold.getMagnitude()*xold.getMagnitude();
		double xprev2 = xprev.getMagnitude()*xprev.getMagnitude();
		expect += 0.25 * deltaTau *(xold2 + xprev2);
		double massderiv = action.getMassDerivative(xold, xprev);
		assertEquals(expect,massderiv,1e-14);
	}
	
	@Test
	public void testDeltaTauDerivative() {
		double expect = 1/(2*deltaTau);
		Displacement difference = xold.getDifference(xprev);
		expect -= 0.5 * mass * difference.getMagnitude()*difference.getMagnitude() / (deltaTau * deltaTau);
		double xold2 = xold.getMagnitude()*xold.getMagnitude();
		double xprev2 = xprev.getMagnitude()*xprev.getMagnitude();
		expect += 0.25 * mass * (xold2 + xprev2);
		double deltatauderiv = action.getDeltaTauDerivative(xold, xprev);
		assertEquals(expect,deltatauderiv,1e-14);		
	}

}
