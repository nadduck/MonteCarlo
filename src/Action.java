
public interface Action {
	public abstract double calculateKinetic(double xold, double xprev);
	public abstract double calculatePotential(double xold);
}
