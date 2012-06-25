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
        
        PathSegment segment = new PathSegment();
        segment.xnext = path.getPosition(nextSlice);
        segment.xprev = path.getPosition(prevSlice);
        segment.x = path.getPosition(slice);
        return segment;
    }

    private double calculateActionDifference(PathSegment segment, double xnew) {
        double deltaS = 0;
        deltaS += action.calculateKinetic(xnew, segment.xprev);
        deltaS += action.calculateKinetic(xnew, segment.xnext);
        deltaS += action.calculatePotential(xnew);
        deltaS -= action.calculateKinetic(segment.x, segment.xprev);
        deltaS -= action.calculateKinetic(segment.x, segment.xnext);
        deltaS -= action.calculatePotential(segment.x);
        return deltaS;
    }

    private int chooseRandomSlice() {
        return rand.nextInt(sliceCount);
    }

}
