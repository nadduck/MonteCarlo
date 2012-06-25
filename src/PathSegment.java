
public class PathSegment {
    public double x;
    public double xnext;
    public double xprev;
    public double getMidpoint() {
        return 0.5 * (xnext + xprev);
    }
}
