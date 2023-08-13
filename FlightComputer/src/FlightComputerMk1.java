
public class FlightComputerMk1 {
	
	private double airDensity; //assumed as constant, will become a function of temperature and pressure
	private double surfaceArea; //assumed as constant, will become a function of deployment angle of airbreak
	private double mass;
	private double weight;
	
	public FlightComputerMk1(double aD, double sA, double m, double w) {
		this.airDensity = aD;
		this.surfaceArea = sA;
		this.mass = m;
		this.weight = w;
	}
	
	public double estimateApogee(double currAlt, double currVel, double currAcc) {
		double calcAlt = currAlt;
		double calcVel = currVel;
		double calcAcc = currAcc;
		double t = 0;
		
		while (calcVel > 0.5) {
			calcVel = estimateVelocity(calcVel, calcAcc);
			calcAlt = 0.1*calcVel + calcAlt;
			calcAcc = getK(calcVel, calcAcc)*calcVel*calcVel - 9.807;
			t += 0.1;
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
	
	private double estimateVelocity(double currVel, double currAcc) {
		return 0.1*currAcc + currVel;
	}
	
	public double getK(double currVel, double currAcc) {
		double K = 0;
		
		double den = -1 * calculateDragCoefficient(currVel, currAcc) * airDensity * surfaceArea;
		double num = 2 * mass;
		
		K = den/num;
		return K;
	}
	 
	public static void main (String[] args) {
		FlightComputerMk1 ToBoldyGo = new FlightComputerMk1(1.16, 0.004046, 0.573, 5.62113);
		System.out.println(ToBoldyGo.estimateApogee(160, 57, -15));
	}
}










//FlightComputerMk1 ToBodlyGo = new FlightComputerMk1(0.385, 1.16, )
/*
double deltaVel = currAcc/10;
double estimVel = currVel + deltaVel;
return estimVel;
*/
