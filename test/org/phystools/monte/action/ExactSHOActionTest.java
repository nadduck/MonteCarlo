package org.phystools.monte.action;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.phystools.monte.geometry.GeometryFactory;
import org.phystools.monte.geometry.GeometryFactory1D;
import org.phystools.monte.geometry.Point;


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
	private int dimension;

	@Before
	public void setUp() {
		deltaTau = 0.1;
		mass = 1.0;
		angfreq = 1.0;
		dimension = 1;
		GeometryFactory factory = new GeometryFactory1D();
		xold = factory.createNewPoint(new double [] {0.5});
		xprev = factory.createNewPoint(new double [] {0.3});
		epsilon = 1e-6;
		coshwt = Math.cosh(deltaTau*angfreq);
		sinhwt = Math.sinh(deltaTau*angfreq);
		action = new ExactSHOAction(deltaTau, mass, angfreq, dimension);
	}

	@Test
	public void testGetMassDerivative() {
		double expect = -0.5 * 1/mass 
		        + getExponentialArg(xold.getMagnitude(),xprev.getMagnitude())/mass;
		double massDerivative = action.getMassDerivative(xold, xprev);
		assertEquals(expect,massDerivative,1e-14);
	}
	
	@Test
	public void testGetDeltaTauDerivative() {
		double expect = -0.5* 1/mass 
		        * getXDerivative(xold.toArray()[0], xprev.toArray()[0])
				*getXDerivative(xold.toArray()[0], xprev.toArray()[0]);
		expect += 0.5* 1/mass 
		        * getXsquaredDeriv(xold.toArray()[0], xprev.toArray()[0]);
		expect += 0.5 * mass * angfreq * angfreq 
		        * xold.toArray()[0] * xold.toArray()[0];
		double deltaTauDerivative = action.getDeltaTauDerivative(xold, xprev);
		assertEquals(expect,deltaTauDerivative,1e-3);
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
		double expArg = mass * angfreq *
		        ((xold*xold+xprev*xprev)*coshwt - 2*xold*xprev)/(2*sinhwt);
		return expArg;	
	}
}
