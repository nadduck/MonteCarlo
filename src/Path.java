
public class Path {

    private int sliceCount;
    private double[] position;
    private double kT;
    private double deltaTau;
    
    
    public Path(int sliceCount, double kT) {
        this.kT = kT;
        this.deltaTau = 1.0 / (kT * sliceCount);
        this.sliceCount = sliceCount;
        
        position = new double[sliceCount];
    
        for (int i = 0; i < sliceCount; i++) {
            position[i] = 0;
        }
    }

    public void setPosition(int index, double x) {
        position[index] = x;
    }

    public double getPosition(int index) {
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
