public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double g = 6.67e-11; 

	public Body(double xP, double yP, double xV, 
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body a){
		double dx = this.xxPos - a.xxPos;
		double dy = this.yyPos - a.yyPos;
		double r_squre = dx * dx + dy * dy;
		double r = Math.sqrt(r_squre);

		return r;
	}

	public double calcForceExertedBy(Body a){		
		double r = calcDistance(a);
		double force = (g * this.mass * a.mass) / (r * r);

		return force;
	}

	public double calcForceExertedByX(Body a){
		double r = calcDistance(a);
		double f = calcForceExertedBy(a);
		double dx = a.xxPos - this.xxPos;
		double fx = f * dx / r;

		return fx;
	}

	public double calcForceExertedByY(Body a){
		double r = calcDistance(a);
		double f = calcForceExertedBy(a);
		double dy = a.yyPos - this.yyPos;
		double fy = f * dy / r;

		return fy;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double fxTotal = 0;

		for (int i = 0; i < allBodys.length; i++){
			if (this.equals(allBodys[i])){
				continue;
			}
			fxTotal = fxTotal + calcForceExertedByX(allBodys[i]);
		}

		return fxTotal;
	}


	public double calcNetForceExertedByY(Body[] allBodys){
		double fyTotal = 0;

		for (int i = 0; i < allBodys.length; i++){
			if (this.equals(allBodys[i])){
				continue;
			}
			fyTotal = fyTotal + calcForceExertedByY(allBodys[i]);
		}

		return fyTotal;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;

	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}