
public class ExactSHOAction implements Action {

	private double mass;
	private double angfreq;
	
	private double sinhwt;
	private double coshwt;
	private double cothwt;
	private double cschwt;
	private double pi;
	private double sqrtExpression;
	
	ExactSHOAction(double deltaTau, double mass, double angfreq) {
		this.mass = mass;
		this.angfreq = angfreq;
		
		pi = Math.PI;
		sinhwt = Math.sinh(deltaTau*angfreq);
		coshwt = Math.cosh(deltaTau*angfreq);
		cothwt = coshwt/sinhwt;
		cschwt = 1/sinhwt;
		sqrtExpression = 0.5*mass*angfreq / (pi*sinhwt);
	}
	
	@Override
	public double getMassDerivative(double xold, double xprev) {
		double expArg = getExponentialArg(xold, xprev);
		
		double dSdm = 0.5 * Math.sqrt(sqrtExpression)/mass * Math.exp(expArg);
		dSdm += Math.sqrt(sqrtExpression) * Math.exp(expArg) * expArg / mass;
		return dSdm;
	}

	@Override
	public double getDeltaTauDerivative(double xold, double xprev) { 
		double expArg = getExponentialArg(xold, xprev);
		
		double angfreq2 = angfreq*angfreq;
		double dSdtau = -0.5*Math.sqrt(sqrtExpression)*angfreq*cothwt*Math.exp(expArg);
		dSdtau += Math.sqrt(sqrtExpression)*Math.exp(expArg)*(0.5*mass*angfreq2*cschwt*cschwt)*(xold*xold+xprev*xprev);
		dSdtau += Math.sqrt(sqrtExpression)*Math.exp(expArg)*(-mass*angfreq2*xold*xprev*cothwt*cschwt);
		return dSdtau;
	}

	@Override
	public double getActionDifference(PathSegment segment, double xnew) {
		double deltaS = 0; 
		deltaS += Math.sqrt(sqrtExpression)*Math.exp(getExponentialArg(xnew,segment.getPrev()));
		deltaS += Math.sqrt(sqrtExpression)*Math.exp(getExponentialArg(xnew,segment.getNext()));
		deltaS -= Math.sqrt(sqrtExpression)*Math.exp(getExponentialArg(segment.getX(),segment.getPrev()));
		deltaS -= Math.sqrt(sqrtExpression)*Math.exp(getExponentialArg(segment.getX(),segment.getNext()));
		return deltaS;
	}
	
	private double getExponentialArg(double xold, double xprev) {
		double expArg = -mass*angfreq*((xold*xold+xprev*xprev)*coshwt - 2*xold*xprev)/(2*sinhwt);
		return expArg;	
	}

}
