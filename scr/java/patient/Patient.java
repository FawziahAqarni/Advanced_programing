package patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;
	int patientID;
	String patientName;
	private int age;
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPatientName() {
		return patientName;
	}

	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", patientName=" + patientName + ", age=" + age + ", gender="
				+ gender + ", prescription=" + prescription + "]";
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

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
