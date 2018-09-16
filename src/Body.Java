public class Body {
	
//Step 1
/**Initialize the private instance variables
 * use getter methods to access private data
 */
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/** create a body from parameters
	 * initial x pos xp
	 * initial y pos yp
	 * intial x velocity xv
	 * initial y velocity yv
	 * mass of object
	 * filename of image for object animation
	 * CONSTRUCTOR 1
	 */
	
public Body (double xp, double yp, double xv, double yv, double mass, String filename) {
myXPos = xp;
myYPos = yp;
myXVel= xv;
myYVel= yv;
myMass = mass;
myFileName = filename;

}

public Body(Body b) {
myXPos= b.getX();
myYPos = b.getY();
myXVel= b.getXVel();
myYVel= b.getYVel();
myMass = b.getMass();
myFileName = b.getName();

}

	/**
	 * copy constructor: copy instance variables from one body to this body
	 * @param b used to initialize this body
	 * CONSTRUCTOR 2
	 */
// use getter methods 
	public double getX(){
		return myXPos;
	}

	public double getY(){
		return myYPos;
	}

	public double getXVel(){
		return myXVel;
	}

	public double getYVel(){
		return myYVel;
	}

	public double getMass(){
		return myMass;
	}

	public String getName(){
		return myFileName;
	}
		


//Step 2
/**
 * Return distance between this body and another
 * @param b is the other body to which distance is calc
 * @return distance between this body and b
 */

public double calcDistance(Body b) {
double distance = Math.sqrt(Math.abs(Math.pow(myXPos- b.getX() ,2)) + Math.pow(myYPos- b.getY(), 2));
return distance;

}

//Step 3
public double calcForceExertedBy(Body p) {
double force= ((6.67e-11)* myMass * p.getMass())/(Math.pow(calcDistance(p), 2));
return force;
}

//Step 4
//order when calculating dy or dx matters
public double calcForceExertedByX(Body p) {
	double fx= (calcForceExertedBy(p)*( p.getX() - myXPos))/(calcDistance(p));
	return fx;
}
public double calcForceExertedByY(Body p) {
	double fy= (calcForceExertedBy(p)*(p.getY() - myYPos))/(calcDistance(p));
	return fy;
}

//Step 5
public double calcNetForceExertedByX(Body[] bodies) {
	double netForceX=0;
	for (Body b : bodies) {
		if (! b.equals(this)) {
			netForceX = netForceX + calcForceExertedByX(b);
	}
	}
return netForceX;
}
public double calcNetForceExertedByY(Body[] bodies) {
	double netForceY=0;
	for (Body b : bodies) {
		if (! b.equals(this)) {
			netForceY = netForceY + calcForceExertedByY(b);
	}
	}
return netForceY;
}

//Step 6
public void update(double deltaT, double xforce, double yforce) {
	//calculate accelerations
	double ax= xforce/ myMass;
	double ay= yforce/myMass;
	//calculate new x and y velocities
	double nvx= myXVel +deltaT*ax;
	double nvy= myYVel +deltaT*ay;
	// calculate new x and y positions
	double nx = myXPos + deltaT*nvx;
	double ny = myYPos + deltaT*nvy;
	
	//redefine instance variables
	myXPos= nx;
	myYPos= ny;
	myXVel= nvx;
	myYVel= nvy;
	
}
public void draw() {
	StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
}


}




















