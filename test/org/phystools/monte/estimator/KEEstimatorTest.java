package org.phystools.monte.estimator;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.phystools.monte.action.*;
import org.phystools.monte.geometry.*;
import org.phystools.monte.path.Path;


public class KEEstimatorTest {

	private Path path;
	private Action action;
	private double deltaTau;
	private double mass;
	private int sliceCount;
	private double kT;
	private Estimator kinetic;
	private double angfreq;
	private int dimension;
	
	@Before
	public void setUp() {
		sliceCount = 3;
		kT = 0.5;
		angfreq = 1.0;
		dimension = 1;
		GeometryFactory1D factory = new GeometryFactory1D();
		path = new Path(sliceCount, kT, factory);
        path.setPosition(1, factory .createNewPoint(new double [] {1.0}));
		path.setPosition(2, factory.createNewPoint(new double [] {0.5}));
		deltaTau = path.getDeltaTau();
		mass = 1.0;
		action = new ExactSHOAction(deltaTau, mass, angfreq, dimension);
		kinetic = new KineticEnergyEstimator(path, action, mass);
	}
	
	@Test
	public void testGetValue() {
		double expect = 0;
    	for (int i = 0; i < sliceCount; i++) {
    		Point x = path.getPosition(i);
    		Point xnext = path.getPosition((i+1)%sliceCount);
    		expect -= mass * action.getMassDerivative(x, xnext) / deltaTau;    		
    	}
    	expect /= sliceCount;
		double kineticEnergy = kinetic.getValue();
		assertEquals(expect, kineticEnergy,1e-14);
		
	}

}
