
public class FlightComputerMk2 {
	private double dragCoefficient;
	private double airDensity; //assumed as constant, will become a function of temperature and pressure
	private double surfaceArea; //assumed as constant, will become a function of deployment angle of airbreak
	private double mass;
	private double weight;
	
	public FlightComputerMk2(double aD, double sA, double m, double w) {
		this.airDensity = aD;
		this.surfaceArea = sA;
		this.mass = m;
		this.weight = w;
	}
	
	public double estimateApogee(double currAlt, double currVel, double currAcc) {
		dragCoefficient = calculateDragCoefficient(currVel, currAcc);
		double calcAlt = currAlt;
		double calcVel = currVel;
		double calcAcc = currAcc;
		double t = 0;
		
		while (calcVel > 0.5) {
			calcVel = 0.1*calcAcc + calcVel;
			calcAlt = 0.1*calcVel + calcAlt;
			calcAcc = getK(calcVel, calcAcc)*calcVel*calcVel - 9.807;
			t += 0.1;
			System.out.println("Time: " + t + ", Altitude: " + calcAlt + ", Velocity: " + calcVel + ", Acceleration: " + calcAcc);
		}
		
		return calcAlt; 
	}
	
	private double calculateDragCoefficient(double currVel, double currAcc) {
		double Cd = 0;
		
		double den = -2 * (currAcc * mass + weight);
		double num = airDensity * surfaceArea * currVel * currVel;
		
		Cd = den/num;
		return Cd;
	}
	
	public double getK(double currVel, double currAcc) {
		double K = 0;
		
		double den = -1 * dragCoefficient * airDensity * surfaceArea;
		double num = 2 * mass;
		
		K = den/num;
		return K;
	}
	 
	public static void main (String[] args) {
		FlightComputerMk2 ToBoldyGo = new FlightComputerMk2(1.16, 0.004046, 0.573, 5.62113);
		System.out.println(ToBoldyGo.estimateApogee(293, 11.5, -10));
	}
}










//FlightComputerMk1 ToBodlyGo = new FlightComputerMk1(0.385, 1.16, )
/*
double deltaVel = currAcc/10;
double estimVel = currVel + deltaVel;
return estimVel;
*/

/*
private double estimateVelocity(double currVel, double currAcc) {
		return 0.1*currAcc + currVel;
	}
*/
