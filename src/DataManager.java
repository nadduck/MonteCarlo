import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class DataManager {

    private FileWriter fstream;
    private static BufferedWriter out;
    
    public void openOutputFile(String filename) throws IOException {
        fstream = new FileWriter(filename);
        out = new BufferedWriter(fstream);
        out.write("# index x x2 E");
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
