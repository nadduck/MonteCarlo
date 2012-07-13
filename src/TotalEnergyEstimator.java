
public class TotalEnergyEstimator implements Estimator {

	
	private Estimator potential;
	private Estimator kinetic;
	
	public TotalEnergyEstimator(Estimator potential, Estimator kinetic) {
		this.potential = potential;
		this.kinetic = kinetic;
	}
	
	@Override
	public double getValue() {
		double V = potential.getValue();
		double T = kinetic.getValue();
		return T + V;
	}

}
