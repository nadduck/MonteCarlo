
public class PathSegment {
    private double x;
    private double xnext;
    private double xprev;
    
    PathSegment(double xprev, double x, double xnext) {
    	this.xprev = xprev;
    	this.x = x;
    	this.xnext = xnext;
    }
    
    public double getPrev() {
    	return xprev;
    }
    
    public void setPrev(double xprev) {
    	this.xprev = xprev;
    }
    
    public double getX() {
    	return x;
    }
    
    public void setX(double x) {
    	this.x = x;
    }
    
    public double getNext() {
    	return xnext;
    }
    
    public void setNext(double xnext) {
    	this.xnext = xnext;
    }
    
    public double getMidpoint() {
        return 0.5 * (xnext + xprev);
    }
}
