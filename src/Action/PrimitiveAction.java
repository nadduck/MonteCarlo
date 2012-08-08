

public class PrimitiveAction implements Action{

    private double deltaTau;
	private double mass;
    
    PrimitiveAction(double deltaTau, double mass) {
        this.deltaTau = deltaTau;
        this.mass = mass;
    }

	@Override
	public double getMassDerivative(Point xold, Point xprev) {
		double dSdm = -1/(2*mass); 
		Displacement difference = xold.getDifference(xprev);
		dSdm += 0.5 * difference.getMagnitude2() / deltaTau;
		dSdm += 0.25 * deltaTau *(xold.getMagnitude2() + xprev.getMagnitude2());
		return dSdm;
	}

	@Override
	public double getDeltaTauDerivative(Point xold, Point xprev) {
		double dSdtau = 1/(2*deltaTau);
		Displacement difference = xold.getDifference(xprev);
		dSdtau -= 0.5 * mass * difference.getMagnitude2() / (deltaTau * deltaTau);
		dSdtau += 0.25 * mass * (xold.getMagnitude2() + xprev.getMagnitude2());
		return dSdtau;
	}

	@Override
	public double getActionDifference(PathSegment segment, Point xnew) {
		double deltaS = 0;
        deltaS += getKinetic(xnew, segment.getPrev());
        deltaS += getKinetic(xnew, segment.getNext());
        deltaS += getPotential(xnew);
        deltaS -= getKinetic(segment.getX(), segment.getPrev());
        deltaS -= getKinetic(segment.getX(), segment.getNext());
        deltaS -= getPotential(segment.getX());
        return deltaS;
	}

	private double getKinetic(Point xold, Point xprev) {
		Displacement difference = xold.getDifference(xprev);
		return 0.5 * mass * difference.getMagnitude2() / deltaTau;
	}
	
	private double getPotential(Point xnew) {
        return 0.5 * mass * deltaTau * xnew.getMagnitude2();
    }

	@Override
	public String getName() {
		return "PrimitiveAction";
	}
    
}
