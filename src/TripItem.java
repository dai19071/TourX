import java.io.Serializable;

//i vasi gia kathe antikeimeno poy xrisimopoieitai ston prosdiorismo enos taxidiou


public abstract class TripItem implements Serializable{

	protected String name;      //onoma stoixeioy
	protected double price;     //timi stoixeioy
	
	public TripItem(String name, double price) {
		this.name = name;
		this.price = price;
	}
	

	public String getName() {
		return name; 
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
