public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int numOfBodys = in.readInt();
		double radius = in.readDouble();

		return radius;
	}


	public static Body[] readBodies(String filename){
		In in = new In(filename);
		int numOfBodys = in.readInt();
		double radius = in.readDouble();
		Body[] allBodys = new Body[numOfBodys];
		int i = 0;

		while (i < numOfBodys){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Body body = new Body(xP, yP, xV, yV, m, img);
			allBodys[i] = body;
			i += 1;
		}

		return allBodys;

	}

	public static void main(String[] args){
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] allBodys = readBodies(filename);
		double[] xForces = new double[allBodys.length];
		double[] yForces = new double[allBodys.length];

		for (int i = 0; i < allBodys.length; i++){
			xForces[i] = allBodys[i].calcNetForceExertedByX(allBodys);
			yForces[i] = allBodys[i].calcNetForceExertedByY(allBodys);
		}

		double time = 0;
		StdDraw.enableDoubleBuffering();

		while (time <= t){
			for (int i = 0; i < allBodys.length; i++){
				allBodys[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (int i = 0; i < allBodys.length; i++){
				allBodys[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(2000);

			time = time + dt;

		}

		StdOut.printf("%d\n", allBodys.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allBodys.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allBodys[i].xxPos, allBodys[i].yyPos, allBodys[i].xxVel,
                  allBodys[i].yyVel, allBodys[i].mass, allBodys[i].imgFileName);   
			}	

	}

}