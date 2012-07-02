import java.io.IOException;

public class Measurer {
	
	private Path path;
	private Action action;
	
	private int sliceCount;
	private double deltaTau;
	
	Measurer(Path path, Action action) {
		this.path = path;
		this.action = action;
		
		sliceCount = path.getSliceCount();
		deltaTau = path.getDeltaTau();
	}
	
    public void doMeasurement(DataManager dataManager, int j) throws IOException {
        double avgx = calculateAvgX();      
        double xsquared = calculateXSquared();
        double E = calculateTotalEnergy();
        dataManager.writeData(j,avgx,xsquared,E);
    }
    
    private double calculateXSquared() {
    	double x = path.getPosition(0);
    	return x*x;
    }
    
    private double calculateAvgX() {
    	double sum = 0;
    	for (int i = 0; i < sliceCount; i++) {
            double x = path.getPosition(i);
            sum += x;
        }
    	return sum/sliceCount;
    }
    
    private double calculateTotalEnergy() {
    	double V = calculatePotentialEnergy();
    	double T = calculateKineticEnergy();
    	return 1/(2*deltaTau) - T + V;
    }

	private double calculateKineticEnergy() {
		double T = 0;
    	for (int i = 0; i < sliceCount; i++) {
    		double x = path.getPosition(i);
    		double xnext = path.getPosition((i+1)%sliceCount);
    		T += action.calculateKinetic(xnext, x) / deltaTau;    		
    	}
    	T /= sliceCount;
		return T;
	}

	private double calculatePotentialEnergy() {
		double avgx = calculateAvgX();
    	double V = action.calculatePotential(avgx) / deltaTau;
		return V;
	}
    
}
