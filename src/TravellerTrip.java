import java.io.Serializable;
//Η συγκεκριμενη κλαση χρησιμοποιειται για την αποθηκευση των ταξιδιων του τουριστικου γραφειου μαζι με καποια χαρακτηρα του καθε ταξιδιου

public class TravellerTrip implements Serializable{
	public String getDest() {
		return dest;
	}


	public void setDest(String dest) {
		this.dest = dest;
	}


	public String getHotel() {
		return hotel;
	}


	public void setHotel(String hotel) {
		this.hotel = hotel;
	}


	public String getTicket() {
		return ticket;
	}


	public void setTicket(String ticket) {
		this.ticket = ticket;
	}


	public Traveller getTraveller() {
		return traveller;
	}


	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}


	private String dest;
	private String hotel;
	private String ticket;
	private Traveller traveller;
	

	public TravellerTrip(String dest,String hotel,String ticket,Traveller traveller)
	{
		this.dest = dest;
		this.hotel = hotel;
		this.ticket = ticket;
		this.traveller = traveller;
	}



}
