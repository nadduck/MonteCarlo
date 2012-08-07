import java.util.Random;


public class Displacement {

    public double x;
    
    public Displacement() {
    }

    Displacement(double x) {
        this.x = x;
    }

    public static Displacement newGuassianRand(double sigma, Random rand) {
        Displacement delta = new Displacement();
        delta.makeGaussianRandom(sigma, rand);
        return delta;
    }

    public static Displacement newUniformDisplacement(double delta, Random rand) {
        Displacement deltaDisplacement = 
                new Displacement(delta * (2 * rand.nextDouble() - 1));
        return deltaDisplacement;
    }

    public double getMagnitude() {
        return Math.abs(x);
    }
   
    public void makeGaussianRandom(double sigma, Random rand) {
        x = sigma*rand.nextGaussian();
    }

    public double getMagnitude2() {
        return x * x;
    }
}
