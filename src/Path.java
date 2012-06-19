import java.util.Random;

public class Path {
	private double[] x;
	private int N;
	private double delta = 0.5;
	private double kT = 0.5;
	private double deltatau = 1/(kT*N);
	Random rand = new Random();

	
	public void setSlice(int numslice) {
		N = numslice;
		setX(new double[numslice]);
	}

	public void setX(double[] x) {
		this.x = x;
		for(int i = 0; i < N; i++) {
			x[i] = 0;
		}
	}
	
	public double getAvgx() {
		double sum = 0;
		for(int i = 0; i < N; i++) {
			sum += x[i];		
		}
		return sum/N;
	}
	
	public double getXsquared() {
		return x[0]*x[0];
	}
	
	public double getSumxsquared() {
		double sumxsquared = 0;
		for(int i = 0; i < N; i++) {
			sumxsquared += (x[(i+1)%N]-x[i])*(x[(i+1)%N]-x[i]);
		}
		return sumxsquared;
	}
	
	public double getEnergy() {
		double V = 0.5 * (getAvgx())*(getAvgx());
		double T = 0.5 * (getSumxsquared()/N)/(deltatau*deltatau);
		return 1/(2*deltatau) - T + V;
	}
	
	public void moveBead() {
		int a = rand.nextInt(N);
		int b = ((a-1)+N) % N;
		int c = (a+1) % N;
		double xnew = x[a] + delta*(2*rand.nextDouble() - 1);
		double deltaS = 0.5 * ((xnew-x[b])*(xnew-x[b]) + (xnew-x[c])*(xnew-x[c]) - (x[a]-x[b])*(x[a]-x[b]) - (x[a]-x[c])*(x[a]-x[c]))/deltatau + 0.5 * deltatau * (xnew*xnew - x[a]*x[a]); 
		if (rand.nextDouble() < Math.exp(-deltaS)) {
			x[a] = xnew;
		}
	}
		
}
