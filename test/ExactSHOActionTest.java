import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ExactSHOActionTest {

	private double deltaTau;
	private double mass;
	private double xold;
	private double xprev;
	private double angfreq;
	private Action action;
	private double expArg;
	private double coshwt;
	private double sinhwt;
	private double sqrtExpression;
	private double pi;

	@Before
	public void setUp() throws Exception {
		deltaTau = 0.1;
		mass = 1.0;
		angfreq = 0.0000001;
		xold = 0.5;
		xprev = 0.3;
		pi = Math.PI;
		coshwt = Math.cosh(deltaTau*angfreq);
		sinhwt = Math.sinh(deltaTau*angfreq);
		expArg = -mass*angfreq*((xold*xold+xprev*xprev)*coshwt - 2*xold*xprev)/(2*sinhwt);
		sqrtExpression = 0.5*mass*angfreq / (pi*sinhwt);
		action = new ExactSHOAction(deltaTau, mass, angfreq);
	}

	@Test
	public void testGetMassDerivative() {
		double expect = 0.5 * Math.sqrt(sqrtExpression)/mass * Math.exp(expArg);
		expect += Math.sqrt(sqrtExpression) * Math.exp(expArg) * expArg / mass;
		double massDerivative = action.getMassDerivative(xold, xprev);
		assertEquals(expect,massDerivative,1e-14);
	}
	
	@Test
	public void testGetDeltaTauDerivative() {
		double expect = -0.5*Math.sqrt(sqrtExpression)*angfreq*(coshwt/sinhwt)*Math.exp(expArg);
		expect += Math.sqrt(sqrtExpression)*Math.exp(expArg)*(0.5*mass*angfreq*angfreq*(1/sinhwt)*(1/sinhwt))*(xold*xold+xprev*xprev);
		expect += Math.sqrt(sqrtExpression)*Math.exp(expArg)*(-mass*angfreq*angfreq*xold*xprev*(coshwt/sinhwt)*(1/sinhwt));
		double deltaTauDerivative = action.getDeltaTauDerivative(xold, xprev);
		assertEquals(expect,deltaTauDerivative,1e-14);
	}

}
