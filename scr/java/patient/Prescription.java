package patient;

import java.io.Serializable;



public class Prescription implements Serializable{
	private static final long serialVersionUID = 1L;
	String medicine;
	String time;
	String dose;
	int staffID;
	/**
	 * 
	 */
	public Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param medicine
	 * @param time
	 * @param dose
	 * @param staffID
	 */
	public Prescription(String medicine, String time, String dose, int staffID) {
		super();
		this.medicine = medicine;
		this.time = time;
		this.dose = dose;
		this.staffID = staffID;
	}
	/**
	 * @return the medicine
	 */
	public String getMedicine() {
		return medicine;
	}
	/**
	 * @param medicine the medicine to set
	 */
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the dose
	 */
	public String getDose() {
		return dose;
	}
	/**
	 * @param dose the dose to set
	 */
	public void setDose(String dose) {
		this.dose = dose;
	}
	/**
	 * @return the staffID
	 */
	public int getStaffID() {
		return staffID;
	}
	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	
	
}
