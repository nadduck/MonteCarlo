package org.phystools.monte;
import java.io.IOException;

import org.phystools.monte.estimator.Estimator;
import org.phystools.monte.geometry.Point;
import org.phystools.monte.path.Path;

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
        double B = 10.0;
        double cosphi = Math.cos(B*path.getPathArea());
        dataManager.writeData(j,avgx,xsquared,E, cosphi);
    }
    
    private double calculateXSquared() {
    	Point x = path.getPosition(0);
    	return x.getMagnitude2();
    }
    
    private double calculateAvgX() {
    	double sum = 0;
    	for (int i = 0; i < sliceCount; i++) {
            Point x = path.getPosition(i);
            sum += x.toArray()[0];
        }
    	return sum/sliceCount;
    }
}
