import java.io.Serializable;
import java.util.ArrayList;


//klasi gia toys proorismoys ton taxidion

public class Destination implements Serializable{

	private String name;		//onoma proorismoy
    private ArrayList<String> activities;    //drastiriotites poy einai diathesimes ston sigkekrimeno proorismo

    public Destination() {
        name = "";
        activities = new ArrayList<>();
    }

    public Destination(String name, ArrayList<String> activities)
    {
        this.name = name;
        this.activities = activities;
    }

    public void addActivities(String activity)
    {
        activities.add(activity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }
    
    public boolean existActivity(String name)
    {
    	if(activities.contains(name))
    		return true;
    	else
    		return false;
    }
}
