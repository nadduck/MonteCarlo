
public class TotalEnergyEstimator implements Estimator {

	
	private Estimator potential;
	private Estimator kinetic;
	private double deltaTau;
	
	public TotalEnergyEstimator(Estimator potential, Estimator kinetic, double deltaTau) {
		this.potential = potential;
		this.kinetic = kinetic;
		this.deltaTau = deltaTau;
	}
	
	@Override
	public double getValue() {
		double V = potential.getValue();
		double T = kinetic.getValue();
		return 1/(2*deltaTau) - T + V;
	}

}
