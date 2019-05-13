import java.sql.Date;

public class Service extends EventProperties {

	private String name;
	private String description; 
	private String type;
	
	public Service(String name, String description, String type,
			double course, double price, Date dateOfEvent, int vehicleId) {
			this.name = name;
			this.description = description;
			this.type = type;
			this.course = course;
			this.price = price;
			this.dateOfEvent = dateOfEvent;
			this.vehicleId = vehicleId;
	}

	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}
			
			
}