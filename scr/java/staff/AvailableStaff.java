package staff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




public class AvailableStaff implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
List<Nurse> availableNurses = new ArrayList<Nurse>();
List<Doctor> availableDoctors = new ArrayList<Doctor>();
/**
 * @return the availableNurses
 */
public List<Nurse> getAvailableNurse() {
	return availableNurses;
}
/**
 * @param availableNurses the availableNurses to set
 */
public void setAvailableNurse(Nurse availableNurse) {
	this.availableNurses.add(availableNurse);
}
/**
 * @return the availableDoctors
 */
public List<Doctor> getAvailableDoctor() {
	return availableDoctors;
}
/**
 * @param availableDoctors the availableDoctors to set
 */
public void setAvailableDoctor(Doctor availableDoctor) {
	this.availableDoctors.add(availableDoctor);
}



}
