public interface Mover {

    public abstract double sampleNewPosition(PathSegment segment);

    public abstract double getLastTransitionProbability();

}