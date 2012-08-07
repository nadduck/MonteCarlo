
public interface Action {
	public abstract double getMassDerivative(Point xold, Point xprev);
	public abstract double getDeltaTauDerivative(Point xold, Point xprev);
	public abstract double getActionDifference(PathSegment segment, Point xnew);
	public abstract String getName();
}
