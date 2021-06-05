package staff;

import java.io.Serializable;

import location.Location;


public class Nurse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String name;
	String shiftTiming;
	String status;
	int shiftStart;
	int shiftEnd;
	Location locationAssignedTo;
	
	public Nurse() {
		super();
	}
	
		
	
	/**
	 * @param id
	 * @param name
	 * @param status
	 * @param shiftStart
	 * @param shiftEnd
	 */
	public Nurse(int id, String name, String status, int shiftStart, int shiftEnd) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
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
	
	
	/**
	 * @return the shiftStart
	 */
	public int getShiftStart() {
		return shiftStart;
	}
	
	/**
	 * @param shiftStart the shiftStart to set
	 */
	public void setShiftStart(int shiftStart) {
		this.shiftStart = shiftStart;
	}
	
	/**
	 * @return the shiftEnd
	 */
	public int getShiftEnd() {
		return shiftEnd;
	}
	
	/**
	 * @param shiftEnd the shiftEnd to set
	 */
	public void setShiftEnd(int shiftEnd) {
		this.shiftEnd = shiftEnd;
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



	
}
