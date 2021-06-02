package patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Patient implements Serializable{
	private static final long serialVersionUID = 1L;
	int patientID;
	List<Prescription> prescription = new ArrayList<Prescription>();
	/**
	 * 
	 */
	public Patient() {
		super();
	}
	/**
	 * @param patientID
	 */
	public Patient(int patientID) {
		super();
		this.patientID = patientID;
	}
	/**
	 * @param patientID
	 * @param prescription
	 */
	public Patient(int patientID, Prescription prescription) {
		super();
		this.patientID = patientID;
		this.prescription.add(prescription);
	}
	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return patientID;
	}
	/**
	 * @param patientID the patientID to set
	 */
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	/**
	 * @return the prescription
	 */
	public List<Prescription> getPrescription() {
		return prescription;
	}
	/**
	 * @param prescription the prescription to set
	 */
	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}
	
	
	
}
