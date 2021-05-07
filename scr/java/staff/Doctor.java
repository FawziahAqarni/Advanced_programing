package staff;

import java.io.Serializable;

import location.Location;

/*
 * This class is created to store data for each Doctor entered by the user of the system.
 * */

public class Doctor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String name;
	String shiftTiming;
	String status;
	Location locationAssignedTo;
	
	
	public Doctor() {
		super();
	}
	
	
	/**
	 * @param id
	 * @param name
	 * @param status
	 * @param shiftStart
	 * @param shiftEnd
	 * @param locationAssignedTo
	 */
	public Doctor(int id, String name, String status, Location locationAssignedTo) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.locationAssignedTo = locationAssignedTo;
	}




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @return the locationAssignedTo
	 */
	public Location getLocationAssignedTo() {
		return locationAssignedTo;
	}




	/**
	 * @param locationAssignedTo the locationAssignedTo to set
	 */
	public void setLocationAssignedTo(Location locationAssignedTo) {
		this.locationAssignedTo = locationAssignedTo;
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the shiftTiming
	 */
	public String getShiftTiming() {
		return shiftTiming;
	}
	
	
	/**
	 * @param shiftTiming the shiftTiming to set
	 */
	public void setShiftTiming(String shiftTiming) {
		this.shiftTiming = shiftTiming;
	}
	
	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
