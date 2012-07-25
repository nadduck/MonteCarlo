
public class SimulationInfo {
    private double kT;
    private int sliceCount;
    private double mass;
    private double angfreq;

    public SimulationInfo() {
        kT = 0.5;
        sliceCount = 10;
        mass = 1.0;
        angfreq = 1.0;
    }

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
}