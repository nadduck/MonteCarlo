
public class PrimitiveAction implements Action{

    private double deltaTau;
	private double mass;
    
    PrimitiveAction(double deltaTau, double mass) {
        this.deltaTau = deltaTau;
        this.mass = mass;
    }

	@Override
	public double getMassDerivative(double xold, double xprev) {
		return 0.25 * deltaTau *(xold*xold + xprev*xprev);
	}

	@Override
	public double getDeltaTauDerivative(double xold, double xprev) {
		return 0.25 * mass * (xold*xold + xprev*xprev);
	}

	@Override
	public double getActionDifference(PathSegment segment, double xnew) {
		double deltaS = 0;
        deltaS += getKinetic(xnew, segment.getPrev());
        deltaS += getKinetic(xnew, segment.getNext());
        deltaS += getPotential(xnew);
        deltaS -= getKinetic(segment.getX(), segment.getPrev());
        deltaS -= getKinetic(segment.getX(), segment.getNext());
        deltaS -= getPotential(segment.getX());
        return deltaS;
	}

	@Override
	public double getKinetic(double xold, double xprev) {
		return 0.5 * mass * (xold-xprev)*(xold-xprev) / deltaTau;
	}
	
	@Override
	public double getPotential(double xold) {
        return 0.5 * mass * deltaTau * xold * xold;
    }
    
}
