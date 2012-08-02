import java.util.Random;


public class Displacement {

    double x;
    
    Displacement(double x) {
        this.x = x;
    }
    
    public double getMagnitude() {
        return Math.abs(x);
    }
   
    public void makeGuassianRandom(Random rand) {
        x = rand.nextGaussian();
    }
}
