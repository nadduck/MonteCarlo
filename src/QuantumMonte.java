import java.io.IOException;
import java.util.Random;

public class QuantumMonte {
    
    private double mass;

	private Path path;

    private double deltaTau;
    private int sliceCount;

    private Advancer advancer;    
    
    
    QuantumMonte(Path path, Advancer advancer, double mass) {
        this.path = path;
        this.advancer = advancer;

        sliceCount = path.getSliceCount();
        deltaTau = path.getDeltaTau();
        this.mass = mass;
    }


    private void run(DataManager dataManager) throws IOException {
        dataManager.openOutputFile("something.dat");

        for (int j = 0; j < 5000; ++j) {
            doMeasurement(dataManager, j);
            advancer.advance(100);
        }
        dataManager.close();
    }

    private void doMeasurement(DataManager dataManager, int j) throws IOException {
        double sum = 0;
        double sumxsquared = 0;
        
        for (int i = 0; i < sliceCount; i++) {
            double x = path.getPosition(i);
            sum += x;
            double x2 = path.getPosition((i+1)%sliceCount);
            sumxsquared += (x2-x)*(x2-x);
        }
        double avgx = sum/sliceCount;
        double x = path.getPosition(0);
        double xsquared = x*x;
        double V = 0.5 * mass * avgx * avgx;
        double T = 0.5 * mass * (sumxsquared/sliceCount)/(deltaTau *deltaTau);
        double E = 1/(2*deltaTau) - T + V;
        dataManager.writeData(j,avgx,xsquared,E);
    }

    public static void main(String[] args) throws IOException {
		
		double kT = 0.5;
        int sliceCount = 10;
        Path path = new Path(sliceCount, kT);
        
        double deltaTau = path.getDeltaTau();
        
        double mass = 1.0;
        Action action = new Action(deltaTau, mass);
        
        DataManager dataManager = new DataManager();

        Random rand = new Random(2012L);
        
        double delta = 1.0;
        Mover mover = new UniformMover(rand, delta);
        
        
        mover = new GaussianMover(rand, deltaTau, mass);
        
        Advancer advancer = new Advancer(path, action, mover, rand);
    	
		QuantumMonte qmc = new QuantumMonte(path, advancer, mass);
	
		qmc.run(dataManager);
	}

}
