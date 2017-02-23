import java.util.Scanner;

public class rovDistanceTestingMain{
	/**
	 * Finds the lengthA using law of sines.
	 * @param a	Degree across from lengthA.
	 * @param b Degree across from lengthB. 
	 * @param c The known distance between the lasers.
	 * @param lengthC 
	 * @return The length of lengthA.
	 */
	private static double getLengthA(double a, double b, double g, double c) {
		return (c*Math.sin(a))/(Math.sin(g));
	}
	
	/**
	 * Finds the lengthB using law of sines.
	 * @param a	Degree across from lengthA.
	 * @param b Degree across from lengthB. 
	 * @param c The known distance between the lasers.
	 * @return The length of lengthB.
	 */
	private static double getLengthB(double a, double b, double g, double c){
		return (c*Math.sin(b))/(Math.sin(g));
	}
	
	/**
	 * Finds length x using Cosine and lengthA.
	 * @param lengthA The value of lengthA as calculated previously.
	 * @param degreeZeta The value of degreeZeta as calculated previously.
	 * @return
	 */
	private static double getLengthX(double lengthA, double degreeZeta) {
		return lengthA*Math.cos(degreeZeta);
	}	
	
	/**
	 * Finds length x using Cosine and lengthB.
	 * @param lengthA The value of lengthA as calculated previously.
	 * @param degreeZeta The value of degreeZeta as calculated previously.
	 * @return
	 */
	private static double getLengthXAlt(double lengthB, double degreeGamma) {
		return lengthB*Math.cos(degreeGamma);
	}	

	/**
	 * Finds length y using Sine and lengthB.
	 * @param lengthA The value of lengthA as calculated previously.
	 * @param degreeZeta The value of degreeZeta as calculated previously.
	 * @return
	 */
	private static double getLengthYAlt(double lengthB, double degreeGamma) {
		return lengthB*Math.sin(degreeGamma);
	}	

	/**
	 * Finds length y using Sine and lengthA.
	 * @param lengthA The value of lengthA as calculated previously.
	 * @param degreeZeta The value of degreeZeta as calculated previously.
	 * @return
	 */
	private static double getLengthY(double lengthA, double degreeZeta) {
		return lengthA*Math.sin(degreeZeta);
	}	
	
	/**
	 * Code execution point
	 */
	public static void main(String[] args){

		try(Scanner userIn = new Scanner(System.in);) {
			
			double lengthA	 	= 0;	// The length A of the triangle, emitted by the left laser
			double lengthB		= 0;	// The length B of the triangle, emitted by the right laser
			double lengthC		= 5;	// The length C of the triangle, between the two lasers
			double lengthX		= 0;	// The X distance of the object from the right laser
			double lengthX2		= 0;	// An alt version of X, calculated differently just in case
			double lengthY		= 0;	// The Y distance of the object from the right laser
			double lengthY2		= 0;	// An alt version of y
			double degreeAlpha	= 0;	// The degree of the left laser, across from lengthA
			double degreeBeta	= 0;	// The degree of the right laser, across from lengthB
			
			/* Getting the degree of the laser from the user */
			System.out.print("Enter degree of left laser (degrees): ");
			degreeAlpha = userIn.nextLong();
			System.out.print("Enter degree of right laser (degrees): ");
			degreeBeta = userIn.nextLong();

			double degreeGamma	= 180-(degreeAlpha+degreeBeta);	// The degree between lengthA and lengthB, located on the target
			double degreeIota	= 90-(180-degreeBeta);				// The degree adjacent to degreeGamma, between lengthA and lengthY
			double degreeZeta	= 180-degreeBeta;				// The degree adjacent to degreeBeta, between lengthA and lengthX
		
			// Convert all angles to radians to make usable
			degreeAlpha = Math.toRadians(degreeAlpha);
			degreeBeta = Math.toRadians(degreeBeta);
			degreeGamma = Math.toRadians(degreeGamma);
			degreeIota = Math.toRadians(degreeIota);
			degreeZeta = Math.toRadians(degreeZeta);
			
			
			lengthA = getLengthA(degreeAlpha, degreeBeta, degreeGamma, lengthC);	// Getting lengthA using law of sines
			lengthB = getLengthB(degreeAlpha, degreeBeta, degreeGamma, lengthC);	// Getting lengthB using law of sines
			lengthX = getLengthX(lengthA, degreeZeta);
			lengthY = getLengthY(lengthA, degreeZeta);
			lengthX2 = getLengthXAlt(lengthA, degreeZeta);
			lengthY2 = getLengthYAlt(lengthA, degreeZeta);
			
			System.out.print(	
					"\nLength A       "+lengthA+
					"\nLength B       "+lengthB+
					"\nLength C       "+lengthC+
					"\nDegree Alpha   "+degreeAlpha+" radians, "+Math.toDegrees(degreeAlpha)+" degrees"+
					"\nDegree Beta    "+degreeBeta+" radians, "+Math.toDegrees(degreeBeta)+" degrees"+
					"\nDegree Gamma   "+degreeGamma+" radians, "+Math.toDegrees(degreeGamma)+" degrees"+
					"\nDegree Iota    "+degreeIota+" radians, "+Math.toDegrees(degreeIota)+" degrees"+
					"\nDegree Zeta    "+degreeZeta+" radians, "+Math.toDegrees(degreeZeta)+" degrees"+
					"\n\n"+
					"\n X distance of object  "+ lengthX +
					"\n X2 distance of object "+lengthX2+
					"\n Y distance of object  "+ lengthY+
					"\n Y2 distance of object "+lengthY2
					);
			
		} catch (Exception e) {
			System.out.println("\nSomething borke'd\n");
			e.printStackTrace();
		}	
	}	
}
