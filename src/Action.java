
public class Action {

    private double deltaTau;
    
    Action(double deltaTau) {
        this.deltaTau = deltaTau;
    }


    public double calculateKinetic(double xold, double xprev) {
        return 0.5 * (xold-xprev)*(xold-xprev) / deltaTau;
    }


    public double calculatePotential(double xold) {
        return 0.5 *  deltaTau * xold * xold;
    }
}
