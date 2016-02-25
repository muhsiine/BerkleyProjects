public class Planet{

	double xxPos; // current x position
	double yyPos; // current y position
	double xxVel; // current x velocity
	double yyVel; // current y velocity
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){

		xxPos = xP;
	 	yyPos = yP; 
	 	xxVel = xV; 
	 	yyVel = yV;
	 	mass = m;
	 	imgFileName = img;
	}

	public Planet( Planet p){
		xxPos = p.xxPos; 
		yyPos = p.yyPos;
		xxVel = p.xxVel; 
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;

	}

	public double calcDistance(Planet p){

		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;

		double r2 = dx*dx + dy*dy;

		return Math.sqrt(r2);
	}

	public double calcForceExertedBy(Planet p){


		return (6.67E-11 * this.mass * p.mass)/(calcDistance(p)*calcDistance(p));
	}

	public double calcForceExertedByX(Planet p){
			double dx = p.xxPos - this.xxPos; 

			return  (calcForceExertedBy(p)*dx) / calcDistance(p);

	}

		public double calcForceExertedByY(Planet p){
			double dy = p.yyPos - this.yyPos; 

			return (calcForceExertedBy(p)*dy) / calcDistance(p);

	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double sum = 0; 
		for( Planet pl : allPlanets){
			if( !pl.equals(this)){
				sum += this.calcForceExertedByX(pl); 
			}		
		} // end for 
		return sum; 
	}

		public double calcNetForceExertedByY(Planet[] allPlanets){
		double sum = 0; 
		for( Planet pl : allPlanets){
			if( !pl.equals(this)){
				sum += this.calcForceExertedByY(pl); 
			}		
		} // end for 
		return sum; 
	}

	public void update(double dt, double fX, double fY){
		double ax = fX/this.mass; 
		double ay = fY/this.mass; 
		this.xxVel = this.xxVel + dt * ax; 
		this.yyVel = this.yyVel + dt * ay; 

		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;

	}

	public void draw(){		

		StdDraw.picture(this.xxPos, this.yyPos, imgFileName);
		StdDraw.show();		
		
	}
	
}