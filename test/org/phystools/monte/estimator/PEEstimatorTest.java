package org.phystools.monte.estimator;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.phystools.monte.action.*;
import org.phystools.monte.geometry.*;
import org.phystools.monte.path.Path;


public class PEEstimatorTest {
	private Path path;
	private Action action;
	private double deltaTau;
	private double mass;
	private int sliceCount;
	private double kT;
	private Estimator potential;
	private double angfreq;
	
	@Before
	public void setUp() {
		sliceCount = 3;
		kT = 0.5;
		mass = 1.0;
		angfreq = 1.0;
		GeometryFactory1D factory = new GeometryFactory1D();
		path = new Path(sliceCount, kT,  factory);
		path.setPosition(1, factory .createNewPoint(new double [] {1.0}));
		path.setPosition(2, factory.createNewPoint(new double [] {0.5}));
		deltaTau = path.getDeltaTau();
		action = new ExactSHOAction(deltaTau, mass, angfreq);
		potential = new PotentialEnergyEstimator(path, action, mass);
	}
	
	@Test
	public void testGetValue() {
		double expect = 0;
		for (int i = 0; i < sliceCount; i++) {
    		Point x = path.getPosition(i);
    		Point xnext = path.getPosition((i+1)%sliceCount);
    		expect += action.getDeltaTauDerivative(xnext, x);
    		expect += mass * action.getMassDerivative(xnext, x) / deltaTau;
		}
		expect /= sliceCount;
		double potentialEnergy = potential.getValue();
		assertEquals(expect,potentialEnergy,1e-14);
	}

}
