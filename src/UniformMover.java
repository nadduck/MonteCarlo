import java.util.Random;

public class UniformMover implements Mover {
    
    private double delta;
    private Random rand;

    public UniformMover(Random rand, double delta) {
        this.rand = rand;
        this.delta = delta;
    }

    @Override
    public double sampleNewPosition(PathSegment segment) {
        return segment.getX() + delta * (2 * rand.nextDouble() - 1);
    }

    @Override
    public double getLastTransitionProbability() {
        return 1.0;
    }
}
