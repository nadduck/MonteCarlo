package org.phystools.monte;

import org.phystools.monte.action.Action;

public class SimulationInfo {
    private double kT;
    private int sliceCount;
    private double mass;
    private double angfreq;
    private Action action;
    private int dimension;
	private double magneticfield;

    public double getkT() {
        return kT;
    }

    public void setkT(double kT) {
        this.kT = kT;
    }

    public int getSliceCount() {
        return sliceCount;
    }

    public void setSliceCount(int sliceCount) {
        this.sliceCount = sliceCount;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getAngfreq() {
        return angfreq;
    }

    public void setAngfreq(double angfreq) {
        this.angfreq = angfreq;
    }
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setMagneticField(double magneticfield) {
		this.magneticfield = magneticfield;		
	}
	
	public double getMagneticField() {
		return magneticfield;
	}
}