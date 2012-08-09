package org.phystools.monte.estimator;

import org.phystools.monte.action.Action;
import org.phystools.monte.geometry.Point;
import org.phystools.monte.path.Path;

public class PotentialEnergyEstimator implements Estimator {

	private Path path;
	private Action action;
	private double deltaTau;
	private int sliceCount;
	private double mass;

	public PotentialEnergyEstimator(Path path, Action action, double mass) {
		this.path = path;
		this.action = action;
		this.mass = mass;
		
		deltaTau = path.getDeltaTau();
		sliceCount = path.getSliceCount();
	}

	@Override
	public double getValue() {
		double V = 0;
		for (int i = 0; i < sliceCount; i++) {
    		Point x = path.getPosition(i);
    		Point xnext = path.getPosition((i+1)%sliceCount);
    		V += action.getDeltaTauDerivative(xnext, x);
    		V += mass * action.getMassDerivative(xnext, x) / deltaTau;
		}
		V /= sliceCount;
		return V;
	}
	

}
