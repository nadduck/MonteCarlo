import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ExactSHOActionTest {

	private double deltaTau;
	private double mass;
	private Point xold;
	private Point xprev;
	private double angfreq;
	private Action action;
	private double coshwt;
	private double sinhwt;
	private double epsilon;

	@Before
	public void setUp() throws Exception {
		deltaTau = 0.1;
		mass = 1.0;
		angfreq = 1.0;
		xold = new Point(0.5);
		xprev = new Point(0.3);
		epsilon = 1e-6;
		coshwt = Math.cosh(deltaTau*angfreq);
		sinhwt = Math.sinh(deltaTau*angfreq);
		action = new ExactSHOAction(deltaTau, mass, angfreq);
	}

	@Test
	public void testGetMassDerivative() {
		double expect = -0.5 * 1/mass + getExponentialArg(xold.getMagnitude(),xprev.getMagnitude())/mass;
		double massDerivative = action.getMassDerivative(xold, xprev);
		assertEquals(expect,massDerivative,1e-14);
	}
	
	@Test
	public void testGetDeltaTauDerivative() {
		double expect = -0.5* 1/mass * getXDerivative(xold.getMagnitude(),xprev.getMagnitude())
				*getXDerivative(xold.getMagnitude(),xprev.getMagnitude());
		expect += 0.5* 1/mass * getXsquaredDeriv(xold.getMagnitude(),xprev.getMagnitude());
		expect += 0.5 * mass * angfreq * angfreq * xold.getMagnitude() * xold.getMagnitude();
		double deltaTauDerivative = action.getDeltaTauDerivative(xold, xprev);
		assertEquals(expect,deltaTauDerivative,1e-1);
	}
		
	private double getXDerivative(double xold, double xprev) {
		double dSdx = getExponentialArg(xold+epsilon,xprev);
		dSdx -= getExponentialArg(xold-epsilon,xprev);
		dSdx /= 2*epsilon;
		return dSdx;
	}
	
	private double getXsquaredDeriv(double xold, double xprev) {
		double d2Sdx2 = getExponentialArg(xold+epsilon,xprev);
		d2Sdx2 += getExponentialArg(xold-epsilon,xprev);
		d2Sdx2 -= 2*getExponentialArg(xold,xprev);
		d2Sdx2 /= epsilon*epsilon;
		return d2Sdx2 ;
	}

	private double getExponentialArg(double xold, double xprev) {
		double expArg = mass*angfreq*((xold*xold+xprev*xprev)*coshwt - 2*xold*xprev)/(2*sinhwt);
		return expArg;	
	}
}
