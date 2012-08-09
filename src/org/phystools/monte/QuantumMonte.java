package org.phystools.monte;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.phystools.monte.action.Action;
import org.phystools.monte.estimator.*;
import org.phystools.monte.geometry.GeometryFactory;
import org.phystools.monte.geometry.GeometryFactory1D;
import org.phystools.monte.mover.*;
import org.phystools.monte.path.Path;
import org.xml.sax.SAXException;

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
            advancer.advance(10000);
        }
        dataManager.close();
    }


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {     
    	InputParser inputParser = new InputParser();
    	SimulationInfo simulationInfo = inputParser.parseXMLFromFile("input.xml");
    	
		double kT = simulationInfo.getkT();
        int sliceCount = simulationInfo.getSliceCount();
        GeometryFactory factory = new GeometryFactory1D();
        Path path = new Path(sliceCount, kT, factory);
        
        double deltaTau = path.getDeltaTau();        
        double mass = simulationInfo.getMass();
        double angfreq = simulationInfo.getAngfreq();
        Action action = simulationInfo.getAction();

        Random rand = new Random(2012L);        
        double delta = 1.0;
        Mover mover = new UniformMover(rand, delta);
        
        Estimator potential = new PotentialEnergyEstimator(path, action, mass);
		Estimator kinetic = new KineticEnergyEstimator(path, action, mass);
		Estimator total = new TotalEnergyEstimator(potential, kinetic);
		
        Measurer measurer = new Measurer(path, total);
        
        mover = new GaussianMover(rand, deltaTau, mass);
        
        Advancer advancer = new Advancer(path, action, mover, rand);
    	
		QuantumMonte qmc = new QuantumMonte(advancer, measurer);
		
		DataManager dataManager = new DataManager(kT, angfreq, mass, action);
		qmc.run(dataManager);
	}

}
