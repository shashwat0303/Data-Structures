/**
 * 
 * @author Shashwat Koranne
 *
 */
public class Crime {
	/**
	 * X coordinate of the location where crime was committed.
	 */
	double x;
	/**
	 * Y coordinate of the location where crime was committed.
	 */
	double y;
	/**
	 * Time at which the crime was committed at the given location.
	 */
	int time;
	/**
	 * Street where the crime was committed at the given location.
	 */
	String street;
	/**
	 * Nature of the crime that was committed.
	 */
	String offense;
	/**
	 * Date on which the crime was committed at the given location.
	 */
	String date;
	int tract;
	/**
	 * Latitude of the location at which the crime was committed.
	 */
	double latitude;
	/**
	 * Longitude of the location at which the crime was committed.
	 */
	double longitude;
	
	/**
	 * Constructor to initialize the crime object.
	 * @param x X coordinate of the location on map
	 * @param y Y coordinate of the location on map
	 */
	public Crime(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setTime (int time) {
		this.time = time;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setOffense(String offense) {
		this.offense = offense;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTract(int tract) {
		this.tract = tract;
	}
	
	public void setLatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public String getOffense() {
		return this.offense;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getTract() {
		return this.tract;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Method to change the way Crime object needs to be displayed.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append(this.x + ", ");
		str.append(this.y + ", ");
		str.append(this.getTime() + ", ");
		str.append(this.getStreet() + ", ");
		str.append(this.getOffense() + ", ");
		str.append(this.getDate() + ", ");
		str.append(this.getTract() + ", ");
		str.append(this.getLatitude() + ", ");
		str.append(this.getLongitude());
		return str.toString();
	}
}
