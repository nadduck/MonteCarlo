import java.util.Random;


public class Displacement {

    private double x;
    
    Displacement(double x) {
        this.x = x;
    }
    
    public double getMagnitude() {
        return Math.abs(x);
    }
   
    public void makeGaussianRandom(double sigma, Random rand) {
        x = sigma*rand.nextGaussian();
    }
}
