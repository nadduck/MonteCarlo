import java.util.Random;

public class UniformMover implements Mover {
    
    private double delta;
    private Random rand;

    public UniformMover(Random rand, double delta) {
        this.rand = rand;
        this.delta = delta;
    }

    @Override
    public Point sampleNewPosition(PathSegment segment) {
        Point newPosition = segment.getX();
        Displacement deltaDisplacement = 
                Displacement.newUniformDisplacement(delta, rand);
        newPosition.move(deltaDisplacement);
    	return newPosition;
    }

    @Override
    public double getLastTransitionProbability() {
        return 1.0;
    }
}
