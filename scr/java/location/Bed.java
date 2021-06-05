package location;

import java.io.Serializable;

import patient.Patient;

public class Bed implements Serializable{
	private static final long serialVersionUID = 1L;
	boolean vacant; 
	Patient patientAssigned; 
	int bedID; 
	
	/**
	 * Constructor to set status and id of bed
	 */
	public Bed(int bID) {
		vacant = true;
		bedID =bID;
	}
	/**
	 * @return the vacant
	 */
	public boolean isVacant() {
		return vacant;
	}
	/**
	 * @param vacant the vacant to set
	 */
	public void setVacant(boolean vacant) {
		this.vacant = vacant;
	}
	/**
	 * @return the patientAssigned
	 */
	public Patient getPatientAssigned() {
		return patientAssigned;
	}
	/**
	 * @param patientAssigned the patientAssigned to set
	 */
	public void setPatientAssigned(Patient patientAssigned) {
		this.patientAssigned = patientAssigned;
	}
	/**
	 * @return the bedID
	 */
	public int getBedID() {
		return bedID;
	}
	/**
	 * @param bedID the bedID to set
	 */
	public void setBedID(int bedID) {
		this.bedID = bedID;
	}
	
	
}
