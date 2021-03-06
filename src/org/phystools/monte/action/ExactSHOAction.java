package org.phystools.monte.action;

import org.phystools.monte.geometry.Point;
import org.phystools.monte.path.PathSegment;

public class ExactSHOAction implements Action {

	private double mass;
	private double angfreq;
	private int ndim;
	
	private double sinhwt;
	private double coshwt;
	private double cothwt;
	private double cschwt;
	
	public ExactSHOAction(double deltaTau, double mass, double angfreq, int ndim) {
		this.mass = mass;
		this.angfreq = angfreq;
		this.ndim = ndim;
		
		sinhwt = Math.sinh(deltaTau*angfreq);
		coshwt = Math.cosh(deltaTau*angfreq);
		cothwt = coshwt/sinhwt;
		cschwt = 1/sinhwt;
	}
	
	@Override
	public double getMassDerivative(Point xold, Point xprev) {
		double expArg = getExponentialArg(xold, xprev);
		
		double dSdm = - ndim * 0.5 * 1/mass + expArg/mass;
		return dSdm;
	}

	@Override
	public double getDeltaTauDerivative(Point xold, Point xprev) { 
		double angfreq2 = angfreq*angfreq;
		double dSdtau = ndim * 0.5 * angfreq * cothwt;
		double xold2 = xold.getMagnitude2();
		double xprev2 = xprev.getMagnitude2();
		dSdtau -= 0.5*mass*angfreq2*cschwt*cschwt*(xold2+xprev2);
		dSdtau += mass*angfreq2*xold.dot(xprev)*cothwt*cschwt;
		return dSdtau;
	}

	@Override
	public double getActionDifference(PathSegment segment, Point xnew) {
		double deltaS = 0; 
		deltaS += getExponentialArg(xnew,segment.getPrev());
		deltaS += getExponentialArg(xnew,segment.getNext());
		deltaS -= getExponentialArg(segment.getX(),segment.getPrev());
		deltaS -= getExponentialArg(segment.getX(),segment.getNext());
		return deltaS;
	}
	
	private double getExponentialArg(Point xold, Point xprev) {
		double xold2 = xold.getMagnitude2();
		double xprev2 = xprev.getMagnitude2();
		double expArg = mass*angfreq*((xold2+xprev2)*coshwt - 2*xold.dot(xprev))/(2*sinhwt);
		return expArg;
	}

	@Override
	public String getName() {
		return "ExactSHOAction";
	}


}
