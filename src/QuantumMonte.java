import java.io.IOException;
import java.util.Random;

public class QuantumMonte {
    
    private Advancer advancer;
	private Measurer measurer;    
     
	
    QuantumMonte(Advancer advancer, Measurer measurer) {
        this.advancer = advancer;
        this.measurer = measurer;
    }


    private void run(DataManager dataManager) throws IOException {
        dataManager.openOutputFile("something.dat");
        for (int j = 0; j < 5000; j++) {
            measurer.doMeasurement(dataManager, j);
            advancer.advance(100);
        }
        dataManager.close();
    }


    public static void main(String[] args) throws IOException {
		double kT = 0.5;
        int sliceCount = 10;
        Path path = new Path(sliceCount, kT);
        
        double deltaTau = path.getDeltaTau();        
        double mass = 1.0;
        Action action = new PrimitiveAction(deltaTau, mass);

        Random rand = new Random(2012L);        
        double delta = 1.0;
        Mover mover = new UniformMover(rand, delta);
        
        Estimator potential = new PotentialEnergyEstimator(path, action);
		Estimator kinetic = new KineticEnergyEstimator(path, action);
		Estimator total = new TotalEnergyEstimator(potential, kinetic, deltaTau);
		
        Measurer measurer = new Measurer(path, total);
        
        mover = new GaussianMover(rand, deltaTau, mass);
        
        Advancer advancer = new Advancer(path, action, mover, rand);
    	
		QuantumMonte qmc = new QuantumMonte(advancer, measurer);
		
		DataManager dataManager = new DataManager();
		qmc.run(dataManager);
	}

}
