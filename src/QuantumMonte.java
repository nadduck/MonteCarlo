import java.util.Random;

public class QuantumMonte {

    public static void main(String[] args){
		int N = 10;
		double[] x = new double[N];
		
		for(int i = 0; i < N; i++) {
			x[i] = 0;
		}

		double delta = 0.5;
		double kT = 0.5;
		double deltatau = 1/(kT*N);
		Random rand = new Random();
						
		try {
			DataManager dataManager = new DataManager();
			dataManager.openOutputFile("something.dat");			
			int j = 1;
			
			while(j < 5000) {
				j++;
				double sum = 0;
				double sumxsquared = 0;
				
				for(int i = 0; i < N; i++) {
					sum += x[i];
					sumxsquared += (x[(i+1)%N]-x[i])*(x[(i+1)%N]-x[i]);
				}
				double avgx = sum/N;
				double xsquared = x[0]*x[0];
				double V = 0.5 * avgx * avgx;
				double T = 0.5 * (sumxsquared/N)/(deltatau*deltatau);
				double E = 1/(2*deltatau) - T + V;
				dataManager.writeData(j,avgx,xsquared,E);				
				for(int step = 0; step < 100; step++){
					int z = rand.nextInt(N);
					int u = ((z-1)+N) % N;
					int w = (z+1) % N;
					// double xnew = x[z] + delta*(2*rand.nextDouble() - 1);
					double xnew = x[z] + delta*rand.nextGaussian();
					double deltaS = 0.5 * ((xnew-x[u])*(xnew-x[u]) + (xnew-x[w])*(xnew-x[w]) - (x[z]-x[u])*(x[z]-x[u]) - (x[z]-x[w])*(x[z]-x[w]))/deltatau + 0.5 * deltatau * (xnew*xnew - x[z]*x[z]);
					if(rand.nextDouble() < Math.exp(-deltaS)) {
						x[z] = xnew;
					}		
				}		
			}
			dataManager.close();
		}
		catch (Exception e) {
			System.err.println("You broke something: " + e.getMessage());
		}
	}
}
