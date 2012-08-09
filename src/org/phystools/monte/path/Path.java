package org.phystools.monte.path;

import org.phystools.monte.geometry.*;

public class Path {

    private int sliceCount;
    private Point[] position;
    private double kT;
    private double deltaTau;
    
    
    public Path(int sliceCount, double kT, GeometryFactory factory) {
        this.kT = kT;
        this.deltaTau = 1.0 / (kT * sliceCount);
        this.sliceCount = sliceCount;
        
        position = new Point[sliceCount];
    
        for (int i = 0; i < sliceCount; i++) {
            position[i] = factory.createNewPoint();
        }
    }

    public void setPosition(int index, Point xnew) {
        position[index] = xnew;
    }

    public Point getPosition(int index) {
        return position[index];
    }

    public int getSliceCount() {
        return sliceCount;
    }

    public double getkT() {
        return kT;
    }

    public double getDeltaTau() {
        return deltaTau;
    }

}
