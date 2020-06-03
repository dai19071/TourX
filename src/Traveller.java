import java.io.Serializable;
import java.util.ArrayList;
//Dhmiourgia ths klashs Traveller h opoia einai extends sthn user kai exoun kapoia epipleon xarakthristika
//opws to name kai to travelPoints mesw tou opoiou exei thn dunatothta na lamvanei ta  dwra 
public class Traveller extends User implements Serializable{


	private String name;
	private ArrayList<Reward> rewardsOwned = new ArrayList<Reward>();
	private int travelPoints;
	
	public Traveller(String username, String password,String name) {
		super(username, password);
		this.name = name;
		travelPoints = 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public void addReward(Reward reward) {
		rewardsOwned.add(reward);
		travelPoints-=reward.getPointsNeeded();
	}
	
	public void addNewReward(Reward reward) {
		rewardsOwned.add(reward);
	}
	
	public void useReward(Reward reward) {
			rewardsOwned.remove(reward);
	}

	public ArrayList<Reward> getRewards(){
		return rewardsOwned;
	}
	
	public int getTravelPoints() {
		return travelPoints;
	}
	
	public void addTravelPoints(int points) {
		travelPoints+=points;
	}
	
	

}
