
public class Reward extends TripItem {

	private int pointsNeeded;
	
	public Reward(String name, double price, int pointsNeeded) { 
		super(name,price);
		this.pointsNeeded = pointsNeeded;
	}

	public int getPointsNeeded() {
		return pointsNeeded;
	
	}

	public void setPointsNeeded(int pointsNeeded) {
		this.pointsNeeded = pointsNeeded;
	}
	
	
	
}
