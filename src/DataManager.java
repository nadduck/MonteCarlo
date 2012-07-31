import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class DataManager {

    private FileWriter fstream;
	private double kT;
	private double angfreq;
	private double mass;
	private Action action;
    private static BufferedWriter out;
    
    DataManager(double kT, double angfreq, double mass, Action action) {
    	this.kT = kT;
    	this.angfreq = angfreq;
    	this.mass = mass;
    	this.action = action;
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
