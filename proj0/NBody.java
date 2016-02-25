public class NBody{

	public static double readRadius(String s){
		In in = new In(s); 
		int val = in.readInt();
		double raduis = in.readDouble();
		return raduis; 
	}

	public static Planet[] readPlanets(String s){
		In in = new In(s); 
		int N = in.readInt();
		Planet[] p = new Planet[N];
		double raduis = in.readDouble();


		for( int i = 0; i < N; i++){
			double x = in.readDouble(); 
			double y = in.readDouble(); 
			double vx = in.readDouble(); 
			double vy = in.readDouble(); 
			double m = in.readDouble(); 
			String img = "images/"+in.readString(); 
			p[i] = new Planet(x, y, vx, vy, m, img); 

		}

		return p; 
	}

	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("Please enter arguments.");
			
			System.exit(0);
		}

		double T = Double.parseDouble(args[0]); 
		double dt = Double.parseDouble(args[1]); 
		String filename = args[2];
		 
		Planet[] planets = readPlanets(filename); 
		double radius = readRadius(filename); 

		for( double time = 0; time <= T; ){
			double [] xForces = new double[planets.length]; 
			double [] yForces = new double[planets.length]; 
			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < planets.length; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}	
			// drawin' backgraound
			draw(radius, "images/starfield.jpg"); 
			// drawin' planets 
			for(Planet p : planets){
				p.draw();  
			}	

			time += dt;
		} // end animation 

		// prints laast state of universe 
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}		




		
		

	}

	public static void draw(double r, String imageToDraw) {
	
		StdDraw.setScale(-r, r);

		

		/* Stamps three copies of advice.png in a triangular pattern. */
		StdDraw.picture(0, 75, imageToDraw);
		

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */	
		StdDraw.show(10);		
		

	}
}