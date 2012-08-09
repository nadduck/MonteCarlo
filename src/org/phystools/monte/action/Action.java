package org.phystools.monte.action;

import org.phystools.monte.geometry.Point;
import org.phystools.monte.path.PathSegment;


public interface Action {
	public abstract double getMassDerivative(Point xold, Point xprev);
	public abstract double getDeltaTauDerivative(Point xold, Point xprev);
	public abstract double getActionDifference(PathSegment segment, Point xnew);
	public abstract String getName();
}
