
public class PrimitiveAction implements Action{

    private double deltaTau;
	private double mass;
    
    PrimitiveAction(double deltaTau, double mass) {
        this.deltaTau = deltaTau;
        this.mass = mass;
    }

    @Override
    public double calculateKinetic(double xold, double xprev) {
        return 0.5 * mass * (xold-xprev)*(xold-xprev) / deltaTau;
    }

    @Override
    public double calculatePotential(double xold) {
        return 0.5 * mass * deltaTau * xold * xold;
    }

    
}
