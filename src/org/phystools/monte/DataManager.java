package org.phystools.monte;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.phystools.monte.action.Action;


public class DataManager {

    private FileWriter fstream;
	private double kT;
	private double angfreq;
	private double mass;
	private Action action;
	private int dimension;
    private static BufferedWriter out;
    
    DataManager(SimulationInfo simulationInfo) {
    	kT = simulationInfo.getkT();
    	angfreq = simulationInfo.getAngfreq();
    	mass = simulationInfo.getMass();
    	action = simulationInfo.getAction();
    	dimension = simulationInfo.getDimension();
    }
    
    public void openOutputFile(String filename) throws IOException {
        fstream = new FileWriter(filename);
        out = new BufferedWriter(fstream);
        out.write("# index x x2 E");
        out.newLine();
        out.write("# kT = " + kT);
        out.newLine();
        out.write("# angfreq = " + angfreq);
        out.newLine();
        out.write("# mass = " + mass);
        out.newLine();
        out.write("# action = " + action.getName());
        out.newLine();
        out.write("# dimension = " + dimension);
        out.newLine();
    }
    
    public void writeData(int j, double avgx, double xsquared, double E)
            throws IOException {
        out.write(j + " " + avgx + " " + xsquared + " " + E);
        out.newLine();
    }

    public void close() throws IOException {
        out.close();
    }
}
