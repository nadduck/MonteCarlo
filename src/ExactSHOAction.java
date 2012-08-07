
public class ExactSHOAction implements Action {

	private double mass;
	private double angfreq;
	
	private double sinhwt;
	private double coshwt;
	private double cothwt;
	private double cschwt;
	
	ExactSHOAction(double deltaTau, double mass, double angfreq) {
		this.mass = mass;
		this.angfreq = angfreq;
		
		sinhwt = Math.sinh(deltaTau*angfreq);
		coshwt = Math.cosh(deltaTau*angfreq);
		cothwt = coshwt/sinhwt;
		cschwt = 1/sinhwt;
	}
	
	@Override
	public double getMassDerivative(Point xold, Point xprev) {
		double expArg = getExponentialArg(xold, xprev);
		
		double dSdm = -0.5 * 1/mass + expArg/mass;
		return dSdm;
	}

	@Override
	public double getDeltaTauDerivative(Point xold, Point xprev) { 
		double angfreq2 = angfreq*angfreq;
		double dSdtau = 0.5*angfreq*cothwt;
		double xold2 = xold.getMagnitude()*xold.getMagnitude();
		double xprev2 = xprev.getMagnitude()*xprev.getMagnitude();
		dSdtau -= 0.5*mass*angfreq2*cschwt*cschwt*(xold2+xprev2);
		dSdtau += mass*angfreq2*xold.getMagnitude()*xprev.getMagnitude()*cothwt*cschwt;
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
		double xold2 = xold.getMagnitude()*xold.getMagnitude();
		double xprev2 = xprev.getMagnitude()*xprev.getMagnitude();
		double expArg = mass*angfreq*((xold2+xprev2)*coshwt - 2*xold.getMagnitude()*xprev.getMagnitude())/(2*sinhwt);
		return expArg;	
	}

	@Override
	public String getName() {
		return "ExactSHOAction";
	}

}
