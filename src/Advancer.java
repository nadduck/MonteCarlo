import java.util.Random;

public class Advancer {
      
    private Action action;
    private Random rand;
    private int sliceCount;
    private Path path;
    private Mover mover;
    
    public Advancer(Path path, Action action, Mover mover, Random rand) {
        this.path = path;
        this.action = action;
        this.mover = mover;
        this.rand = rand;
        sliceCount = path.getSliceCount();
    }
    
    public void advance(int nstep) {
        for (int step = 0; step < nstep; step++) {
            int slice = chooseRandomSlice();

            PathSegment segment = getOldSegment(slice);
            
            double xnew = mover.sampleNewPosition(segment);
            double tprob = mover.getLastTransitionProbability();
            
            double deltaS = calculateActionDifference(segment, xnew);
            
            double acceptanceProbability = Math.exp(-deltaS) / tprob;
            
            if (rand.nextDouble() < acceptanceProbability) {
                path.setPosition(slice, xnew);
            }
        }
    }

    private PathSegment getOldSegment(int slice) {
        int prevSlice = ((slice - 1) + sliceCount) % sliceCount;
        int nextSlice = (slice + 1) % sliceCount;
        
        double xnext = path.getPosition(nextSlice);
        double xprev = path.getPosition(prevSlice);
        double x = path.getPosition(slice);
        
        PathSegment segment = new PathSegment(xprev, x, xnext);
        return segment;
    }

    private double calculateActionDifference(PathSegment segment, double xnew) {
        double deltaS = 0;
        deltaS += action.calculateKinetic(xnew, segment.getPrev());
        deltaS += action.calculateKinetic(xnew, segment.getNext());
        deltaS += action.calculatePotential(xnew);
        deltaS -= action.calculateKinetic(segment.getX(), segment.getPrev());
        deltaS -= action.calculateKinetic(segment.getX(), segment.getNext());
        deltaS -= action.calculatePotential(segment.getX());
        return deltaS;
    }

    private int chooseRandomSlice() {
        return rand.nextInt(sliceCount);
    }

}
