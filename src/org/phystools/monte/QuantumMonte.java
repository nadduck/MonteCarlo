package org.phystools.monte;

import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.phystools.monte.action.Action;
import org.phystools.monte.estimator.*;
import org.phystools.monte.geometry.*;
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

    public static void main(String[] args) throws IOException,
            ParserConfigurationException, SAXException {
        InputParser inputParser = new InputParser();
        SimulationInfo simulationInfo = inputParser
                .parseXMLFromFile("input.xml");

        double kT = simulationInfo.getkT();
        int sliceCount = simulationInfo.getSliceCount();

        int ndim = simulationInfo.getDimension();
        GeometryFactory factory = createGeometryFactory(ndim);
        Path path = new Path(sliceCount, kT, factory);

        double deltaTau = path.getDeltaTau();
        double mass = simulationInfo.getMass();
        Action action = simulationInfo.getAction();
        double magneticfield = simulationInfo.getMagneticField();

        Random rand = new Random(5000L);
        double delta = 1.0;
        Mover mover = new UniformMover(rand, delta);

        Estimator potential = new PotentialEnergyEstimator(path, action, mass);
        Estimator kinetic = new KineticEnergyEstimator(path, action, mass);
        Estimator total = new TotalEnergyEstimator(potential, kinetic);

        Measurer measurer = new Measurer(path, total, magneticfield);

        mover = new GaussianMover(rand, deltaTau, mass, factory);

        Advancer advancer = new Advancer(path, action, mover, rand);

        QuantumMonte qmc = new QuantumMonte(advancer, measurer);

        DataManager dataManager = new DataManager(simulationInfo);
        qmc.run(dataManager);
    }

    private static GeometryFactory createGeometryFactory(int n) {
        GeometryFactory factory = null;
        switch (n) {
        case 1:
            factory = new GeometryFactory1D();
            break;
        case 2:
            factory = new GeometryFactory2D();
            break;
        case 3:
            factory = new GeometryFactory3D();
            break;
        case 4:
        	factory = new GeometryFactory4D();
        	break;
        }
        return factory;
    }

}
