import java.io.IOException;

public class Measurer {
	
	private Path path;
	private Estimator total;
	
	private int sliceCount;
	
	Measurer(Path path, Estimator total) {
		this.path = path;
		this.total = total;
		
		sliceCount = path.getSliceCount();
	}
	
    public void doMeasurement(DataManager dataManager, int j) throws IOException {
        double avgx = calculateAvgX();      
        double xsquared = calculateXSquared();
        double E = total.getValue();
        dataManager.writeData(j,avgx,xsquared,E);
    }
    
    private double calculateXSquared() {
    	Point x = path.getPosition(0);
    	return x.getMagnitude2();
    }
    
    private double calculateAvgX() {
    	double sum = 0;
    	for (int i = 0; i < sliceCount; i++) {
            Point x = path.getPosition(i);
            sum += x.x;
        }
    	return sum/sliceCount;
    }
}
