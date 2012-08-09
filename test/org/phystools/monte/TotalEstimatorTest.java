package org.phystools.monte;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.phystools.monte.action.*;
import org.phystools.monte.estimator.*;
import org.phystools.monte.geometry.*;
import org.phystools.monte.path.Path;


public class TotalEstimatorTest {
	private Path path;
	private Action action;
	private double deltaTau;
	private double mass;
	private int sliceCount;
	private double kT;
	private Estimator potential;
	private Estimator kinetic;
	private Estimator total;
	private double angfreq;
	
	@Before
	public void setUp() {
		sliceCount = 3;
		kT = 0.5;
		path = new Path(sliceCount, kT);
		path.setPosition(1, new Point1D(1));
		path.setPosition(2,new Point1D(0.5));
		deltaTau = path.getDeltaTau();
		mass = 1.0;
		angfreq = 1.0;
		action = new ExactSHOAction(deltaTau, mass, angfreq);
		kinetic = new KineticEnergyEstimator(path, action, mass);
		potential = new PotentialEnergyEstimator(path, action, mass);		
		total = new TotalEnergyEstimator(potential, kinetic);
	}
	@Test
	public void testGetValue() {
		double expect = potential.getValue() + kinetic.getValue();
		
		double totalEnergy = total.getValue();
		assertEquals(expect,totalEnergy,1e-14);
	}

}
