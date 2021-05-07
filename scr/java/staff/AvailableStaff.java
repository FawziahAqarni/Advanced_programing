package staff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
 * This class is used to store data of every staff member whom status is Available, as Nurse and Doctor classes stores data of every
 * staff member included by user but only those staff members will be stored in these list separately who are available for work.
 * */


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
