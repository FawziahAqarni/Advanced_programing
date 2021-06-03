package patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AdministerPrescription implements Serializable {
	private static final long serialVersionUID = 1L;
	List<Patient> administerPatientPrescriptions = new ArrayList<Patient>();

	/**
	 * @return the administerPatientPrescriptions
	 */
	public List<Patient> getAdministerPatientPrescriptions() {
		return administerPatientPrescriptions;
	}

	/**
	 * @param administerPatientPrescriptions the administerPatientPrescriptions to set
	 */
	public void setAdministerPatientPrescriptions(List<Patient> administerPatientPrescriptions) {
		this.administerPatientPrescriptions = administerPatientPrescriptions;
	}

	
	
}
