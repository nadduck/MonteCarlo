package org.phystools.monte.geometry;
import java.util.Random;


public class Displacement1D implements Displacement {

    public double[] x = new double[1];
    
    public Displacement1D() {
    }

    public Displacement1D(double x) {
        this.x[0] = x;
    }

    @Override
	public Displacement newGaussianRand(double sigma, Random rand) {
        Displacement delta = new Displacement1D();
        delta.makeGaussianRandom(sigma, rand);
        return delta;
    }

    @Override
	public Displacement newUniformDisplacement(double delta, Random rand) {
        Displacement deltaDisplacement = 
                new Displacement1D(delta * (2 * rand.nextDouble() - 1));
        return deltaDisplacement;
    }

    @Override
	public double getMagnitude() {
        return Math.abs(x[0]);
    }
   
    @Override
	public void makeGaussianRandom(double sigma, Random rand) {
        x[0] = sigma*rand.nextGaussian();
    }


    @Override
	public double getMagnitude2() {
        return x[0] * x[0];
    }

	@Override
	public double[] toArray() {
		return x;
	}
}
