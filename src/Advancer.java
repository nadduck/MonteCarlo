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
            
            Point xnew = mover.sampleNewPosition(segment);
            double tprob = mover.getLastTransitionProbability();
            
            double deltaS = action.getActionDifference(segment, xnew);
            
            double acceptanceProbability = Math.exp(-deltaS) / tprob;
            
            if (rand.nextDouble() < acceptanceProbability && xnew.x > 0) {  
                path.setPosition(slice, xnew);
            }
        }
    }

    private PathSegment getOldSegment(int slice) {
        int prevSlice = ((slice - 1) + sliceCount) % sliceCount;
        int nextSlice = (slice + 1) % sliceCount;
        
        Point xnext = path.getPosition(nextSlice);
        Point xprev = path.getPosition(prevSlice);
        Point x = path.getPosition(slice);
        
        PathSegment segment = new PathSegment(xprev, x, xnext);
        return segment;
    }

    private int chooseRandomSlice() {
        return rand.nextInt(sliceCount);
    }

}
