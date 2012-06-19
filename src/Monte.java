import java.util.*;
import java.io.*;

public class Monte {
	public static void main(String[] args){
		
		double x, xnew, delta, kT, deltaV, V;
		x = 0;
		delta = 100;
		kT = 1;
		V = 0.5 * (x*x);
		Random rand = new Random();
		int j = 1;
				
		try {
			FileWriter fstream = new FileWriter("data.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(j + " " + x + " " + V);
			out.newLine();
			
			while(j < 5000) {
				j++;
				xnew = x + delta*(2*rand.nextDouble() - 1);
				deltaV = 0.5 * (xnew*xnew - x*x);
				if(rand.nextDouble() < Math.exp(-deltaV/kT)) {
					x = xnew;
				}				
				V = 0.5 * (x*x);
				out.write(j + " " + x + " " + V);
				out.newLine();
			}
			out.close();
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
}
