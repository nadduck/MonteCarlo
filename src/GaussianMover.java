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
    public Point sampleNewPosition(PathSegment segment) {
/*    	Point xmid = segment.getMidpoint();
    	Displacement x = new Displacement(0);
    	x.makeGaussianRandom(sigma, rand);
        Point xnew = new Point(xmid.getMagnitude());
        xnew.move(x);
        
        tranProb = calculateTransitionProbability(xmid, xnew);
        
        Point xold = segment.getX();
        tranProb /= calculateTransitionProbability(xmid, xold);
        
        return xnew;*/
        Point xmid = segment.getMidpoint();
        Point xnew = new Point(xmid.getMagnitude() + sigma * rand.nextGaussian());
        
        tranProb = calculateTransitionProbability(xmid, xnew);
        
        Point xold = segment.getX();
        tranProb /= calculateTransitionProbability(xmid, xold);
        
        return xnew;
    }

    private double calculateTransitionProbability(Point xmid, Point xnew) {
        Displacement difference = xnew.getDifference(xmid);
    	double x2 = difference.getMagnitude() * difference.getMagnitude();
        return Math.exp(-0.5 * x2  / sigma2);
    }

    @Override
    public double getLastTransitionProbability() {
        return tranProb;
    }

}
