
public class Action {

    private double deltaTau;
	private double mass;
    
    Action(double deltaTau, double mass) {
        this.deltaTau = deltaTau;
        this.mass = mass;
    }


    public double calculateKinetic(double xold, double xprev) {
        return 0.5 * mass * (xold-xprev)*(xold-xprev) / deltaTau;
    }


    public double calculatePotential(double xold) {
        return 0.5 * mass * deltaTau * xold * xold;
    }
}
