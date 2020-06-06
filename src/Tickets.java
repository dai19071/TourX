
public class Tickets extends TripItem{
//Η Συγκεκριμενη κλαση χρησιμοποιειται για την δημιουργια των εισιτηριων
	private Destination destination;
	public Tickets(String name, double price,Destination destination) { 
			super(name,price);
			this.destination = destination;
			}
	
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}


	}
