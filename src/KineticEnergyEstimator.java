
public class KineticEnergyEstimator implements Estimator {

	private Path path;
	private Action action;
	private double deltaTau;
	private int sliceCount;

	public KineticEnergyEstimator(Path path, Action action) {
		this.path = path;
		this.action = action;
		
		deltaTau = path.getDeltaTau();
		sliceCount = path.getSliceCount();
	}

	@Override
	public double getValue() {
		double T = 0;
    	for (int i = 0; i < sliceCount; i++) {
    		double x = path.getPosition(i);
    		double xnext = path.getPosition((i+1)%sliceCount);
    		T += action.calculateKinetic(xnext, x) / deltaTau;    		
    	}
    	T /= sliceCount;
		return T;
	}

}
