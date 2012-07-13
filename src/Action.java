
public interface Action {
	public abstract double getMassDerivative(double xold, double xprev);
	public abstract double getDeltaTauDerivative(double xold, double xprev);
	public abstract double getActionDifference(PathSegment segment, double xnew);
	public abstract double getKinetic(double xold, double xprev);
	public abstract double getPotential(double xold);
}
