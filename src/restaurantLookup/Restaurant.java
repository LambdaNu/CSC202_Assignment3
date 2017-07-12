package restaurantLookup;


public class Restaurant implements Comparable<Restaurant>{

	String restaurantName; 
	String restaurantAddress; 
	String phonenumber;
	GeoLocation location; 
	String phoneNumber; 
	String restaurantImage; 
	
	public Restaurant(){}; 
	
	public Restaurant(String restaurantName, String restaurantAddress, String phonenumber, GeoLocation location,
			String phoneNumber, String restaurantImage) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.phoneNumber = phonenumber;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.restaurantImage = restaurantImage;
	}
	
	public Restaurant(String restaurantName, String restaurantAddress, String phonenumber, double latitude, double longitude,
			String phoneNumber, String restaurantImage) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.phoneNumber = phonenumber;
		this.location.setLatitude(latitude);
		this.location.setLongitude(longitude);
		this.phoneNumber = phoneNumber;
		this.restaurantImage = restaurantImage;
	}
	
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getPhonenubmer() {
		return phoneNumber;
	}
	public void setPhonenubmer(String phonenubmer) {
		this.phoneNumber = phonenubmer;
	}
	public GeoLocation getLocation() {
		return location;
	}
	public void setLocation(GeoLocation location) {
		this.location = location;
	}
	public void setLocation(double latitude, double longitude) {
		this.location.setLatitude(latitude);
		this.location.setLongitude(longitude);
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRestaurantImage() {
		return restaurantImage;
	}
	public void setRestaurantImage(String restaurantImage) {
		this.restaurantImage = restaurantImage;
	}
	
	@Override
	public String toString(){
		String returnval= ""; 
		returnval = this.restaurantName +"," + this.restaurantAddress +"," + this.phoneNumber +"," + this.location.getLatitude() +"," + this.location.getLongitude() +"," + this.restaurantImage;
		return returnval; 
	}

	
	//really just for distances....
	public double compareGeoLocation(Restaurant x){
		double distance = this.location.getDistance(x.getLocation());
		return distance; 
	}

	@Override
	public int compareTo(Restaurant o) {
		int distance = (int) this.location.getDistance(o.getLocation());
		return distance; 
	}
	
	
	
}
