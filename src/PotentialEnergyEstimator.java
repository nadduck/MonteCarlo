
public class PotentialEnergyEstimator implements Estimator {

	private Path path;
	private Action action;
	private double deltaTau;
	private int sliceCount;

	public PotentialEnergyEstimator(Path path, Action action) {
		this.path = path;
		this.action = action;
		
		deltaTau = path.getDeltaTau();
		sliceCount = path.getSliceCount();
	}

	@Override
	public double getValue() {
		double avgx = calculateAvgX();
    	double V = action.calculatePotential(avgx) / deltaTau;
		return V;
	}
	
	private double calculateAvgX() {
    	double sum = 0;
    	for (int i = 0; i < sliceCount; i++) {
            double x = path.getPosition(i);
            sum += x;
        }
    	return sum/sliceCount;
    }

}
