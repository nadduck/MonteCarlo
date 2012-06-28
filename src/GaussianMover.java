import java.util.Random;

public class GaussianMover implements Mover {
    
    private Random rand;
    private double sigma;
    private double sigma2;
    private double tranProb;
    
    public GaussianMover(Random rand, double deltaTau, double mass) {
        this.rand = rand;        
        sigma2 = 0.5 * deltaTau / mass;
        sigma = Math.sqrt(sigma2);
    }

    @Override
    public double sampleNewPosition(PathSegment segment) {
        double xmid = segment.getMidpoint();
        double xnew = xmid + sigma * rand.nextGaussian();
        
        tranProb = calculateTransitionProbability(xmid, xnew);
        
        double xold = segment.getX();
        tranProb /= calculateTransitionProbability(xmid, xold);
        
        return xnew;
    }

    private double calculateTransitionProbability(double xmid, double xnew) {
        double x2 = (xnew - xmid) * (xnew - xmid);
        return Math.exp(-0.5 * x2  / sigma2);
    }

    @Override
    public double getLastTransitionProbability() {
        return tranProb;
    }

}
