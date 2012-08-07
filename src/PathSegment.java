
public class PathSegment {
    private Point x;
    private Point xnext;
    private Point xprev;
    
    PathSegment(Point xprev2, Point x2, Point xnext2) {
    	this.xprev = xprev2;
    	this.x = x2;
    	this.xnext = xnext2;
    }
    
    public Point getPrev() {
    	return xprev;
    }
    
    public void setPrev(Point xprev) {
    	this.xprev = xprev;
    }
    
    public Point getX() {
    	return x;
    }
    
    public void setX(Point x) {
    	this.x = x;
    }
    
    public Point getNext() {
    	return xnext;
    }
    
    public void setNext(Point xnext) {
    	this.xnext = xnext;
    }
    
    public Point getMidpoint() {
        Point midPoint = Point.midpoint(xprev, xnext);
    	return midPoint;
    }
}
