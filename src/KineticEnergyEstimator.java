
public class KineticEnergyEstimator implements Estimator {

	private Path path;
	private Action action;
	private double deltaTau;
	private int sliceCount;
	private double mass;

	public KineticEnergyEstimator(Path path, Action action, double mass) {
		this.path = path;
		this.action = action;
		this.mass = mass;
		
		deltaTau = path.getDeltaTau();
		sliceCount = path.getSliceCount();
	}

	@Override
	public double getValue() {
		double T = 0;
    	for (int i = 0; i < sliceCount; i++) {
    		double x = path.getPosition(i);
    		double xnext = path.getPosition((i+1)%sliceCount);
    		T += 1/(2*deltaTau);
    		T -= action.getKinetic(xnext,x)/ deltaTau;
    		T -= mass * action.getMassDerivative(xnext, x) / deltaTau;    		
    	}
    	T /= sliceCount;
		return T;
	}

}
