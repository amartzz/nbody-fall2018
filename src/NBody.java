	

/**
 * @author Ana Martinez apm36
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */

	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
		// DONE=  read values at beginning of file to
		// find the radius
		int nb= s.nextInt();
		double radius= s.nextDouble();
		
		s.close();
		
		// DONE= TODO: return radius read
		return radius;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */

	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			// DONE: read # bodies, create array, ignore radius
			int nb = s.nextInt(); // # bodies to be read
			double radius= s.nextDouble();
			Body[] bodies= new Body[nb];
			
			
			for(int k=0; k < nb; k++) {
				double ixpos= s.nextDouble();
				double iypos= s.nextDouble();
				double ixvel= s.nextDouble();
				double iyvel= s.nextDouble();
				double imass= s.nextDouble();
				String ifname= s.next();
				bodies[k]= new Body(ixpos, iypos, ixvel, iyvel, imass, ifname);
				
				// DONE: read data for each body
				// construct new body object and add to array
			}
			
			s.close();
			
			// DONE: return array of body objects read
			return bodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 157788000.0;
		double dt = 25000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			// DONE: create double arrays xforces and yforces
			// to hold forces on each body
			int nb=bodies.length;
			double[] xforces= new double[nb];
			double[] yforces= new double[nb];
			

			// TODO: loop over all bodies, calculate
			// net forces and store in xforces and yforces
			int i=1;
			for (Body b : bodies) {
		 xforces[i]= b.calcNetForceExertedByX(bodies);
			i+=1; }
			int l=1;
			for (Body b : bodies) {
				 yforces[l]= b.calcNetForceExertedByY(bodies);
					l+=1; }
			
					
			
			
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			int z= 1;
			for (Body b : bodies) {
				b.update(dt, xforces[z], yforces[z]);
				z+=1;
			}
			
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			
			StdDraw.show(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
