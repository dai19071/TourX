import java.io.Serializable;

public class Hotel extends TripItem implements Serializable{
	
	private int capacity;
	private Destination dest;

	public Hotel(String name, int capacity, double price, Destination dest) {
		super(name,price);
		this.capacity = capacity;
		this.dest = dest;
		
	}


	public Destination getDest() {
		return dest;
	}



	public void setDest(Destination dest) {
		this.dest = dest;
	}



	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	
}
