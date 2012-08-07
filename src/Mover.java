public interface Mover {

    public abstract Point sampleNewPosition(PathSegment segment);

    public abstract double getLastTransitionProbability();

}