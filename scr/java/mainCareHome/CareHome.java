package mainCareHome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import location.Location;
import location.Ward;
import logs.ActionLog;
import patient.AdministerPrescription;
import patient.Patient;
import patient.Prescription;
import staff.AvailableStaff;
import staff.Doctor;
import staff.Nurse;




public class CareHome implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient Scanner sc;
	List<Nurse> nursesData;
	List<Doctor> doctorsData;
	AvailableStaff availableStaff;
	Ward ward[];
	AdministerPrescription AP;
	List<ActionLog> logs;
	
	
//Constructor Function
	public CareHome() {
		super();
		nursesData = new ArrayList<Nurse>();
		doctorsData = new ArrayList<Doctor>();
		availableStaff = new AvailableStaff();
		AP = new AdministerPrescription();
		logs = new ArrayList<ActionLog>();
		ward = new Ward[2];
		ward[0] = new Ward(1);
		ward[1] = new Ward(2);
		sc = new Scanner(System.in);
	}


/**
	 * @return the ward
	 */
	public Ward[] getWard() {
		return ward;
	}


	/**
	 * @param ward the ward to set
	 */
	public void setWard(Ward[] ward) {
		this.ward = ward;
	}


	/**
	 * @return the aP
	 */
	public AdministerPrescription getAP() {
		return AP;
	}


	/**
	 * @param aP the aP to set
	 */
	public void setAP(AdministerPrescription aP) {
		AP = aP;
	}


	/**
	 * @return the logs
	 */
	public List<ActionLog> getLogs() {
		return logs;
	}


	/**
	 * @param logs the logs to set
	 */
	public void setLogs(List<ActionLog> logs) {
		this.logs = logs;
	}


	private void menuFunction() {
		boolean flag = true;
		int choice;
		do {
			System.out.println("Aged Care Facility Management System\n\n"
					+ "Select option to perform task\n"
					+ "'1' to Add Nurse\n"
					+ "'2' to Add Doctor\n"
					+ "'3' to Display Available Medical Staff\n"
					+ "'4' to Add a new Resident to a Bed\n"
					+ "'5' to Display Ward Details\n"
					+ "'6' to Check Details of Resident in a Bed\n"
					+ "'7' to Add New Prescription to Resident in a Bed\n"
					+ "'8' to Move Resident Bed\n"
					+ "'9' to Administer Prescription to Resident in a Bed\n"
					+ "'10' to Display administered Prescription so far\n"
					+ "'11' to Display Logs\n"
					+ "'Any Other Key' to Exit\n");
			choice = sc.nextInt();
			switch(choice){
			case 1:
				addNurseInfo();
				break;
			
			case 2:
				addDoctorInfo();
				break;
				
			case 3:
				availableMedicalStaff();
				break;
			
			case 4:
				addResidentToBed();
				break;
				
			case 5:
				availableBeds();
				break;
				
			case 6:
				residentDetails();
				break;
				
			case 7:
				addPrescription();
				break;
				
			case 8:
				moveResidentBed();
				break;
				
			case 9:
				administerPrescription();
				break;
			
			case 10:
				displayAdministerPrescription();
				break;
				
			case 11:
				displayLogs();
				break;
				
			default:
				flag = false;
				System.out.println("Program Shutting Down...");
				break;
			}
				
		}while(flag);
		
	}
	
	private void displayLogs() {
		for(int i=0;i<logs.size();i++) {
			System.out.println("Staff-ID: "+logs.get(i).getStaffID()+"\tDay:"+logs.get(i).getDay()+"\tTime:"+logs.get(i).getTime()+"\nAction-Performed: "+logs.get(i).getActionPerformed());
		}
	}
	
	
	private void displayAdministerPrescription() {
		Patient tmppat=new Patient();
		Prescription tmp;
		List<Prescription> prescription = new ArrayList<Prescription>();
		List<Patient> administerPatientPrescriptions = AP.getAdministerPatientPrescriptions();
		for(int p=0;p<administerPatientPrescriptions.size();p++) {
		tmppat = administerPatientPrescriptions.get(p);
		prescription = tmppat.getPrescription();
		for(int i=0;i<prescription.size();i++){
			tmp=prescription.get(i);
			System.out.println("Patient-ID: "+tmppat.getPatientID()+"\tMedicine:"+tmp.getMedicine()+"\tTime:"+tmp.getTime()+"\tDose:"+tmp.getDose()+"\tStaff-ID:"+tmp.getStaffID());
		}
		}
	}
	
	private void administerPrescription() {
		System.out.println("Administer a Prescription");
		availableBeds();
		int w,r,b;
		System.out.println("Please Look through above Ward, Room, and Bed details and choose to display Resident Prescription Data to Administer\nEnter Ward ID: ");
		w = sc.nextInt();
		System.out.println("Enter Room Number: ");
		r = sc.nextInt();
		System.out.println("Enter Bed Number: ");
		b = sc.nextInt();
		if(ward[w-1].getRoom(r-1).getBed(b-1).isVacant()) {
			System.out.println("No Resident assigned to this Bed");
		}
		else {
			
			System.out.println(" Patient ID is: "+ward[w-1].getRoom(r-1).getBed(b-1).getPatientAssigned().getPatientID());
			List<Prescription> prescription = ward[w-1].getRoom(r-1).getBed(b-1).getPatientAssigned().getPrescription();
			Prescription tmp;
			for(int i=0;i<prescription.size();i++){
				tmp=prescription.get(i);
				System.out.println("Medicine:"+tmp.getMedicine()+"\tTime:"+tmp.getTime()+"\tDose:"+tmp.getDose()+"\tStaff-ID:"+tmp.getStaffID());
			}
			Patient tmpP =new Patient();
			Prescription tmp1 =new Prescription();
			System.out.println("Enter Patient ID: ");
			tmpP.setPatientID(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter Medicine: ");
			tmp1.setMedicine(sc.nextLine());
			System.out.println("Enter Time: ");
			tmp1.setTime(sc.nextLine());
			System.out.println("Enter Dose: ");
			tmp1.setDose(sc.nextLine());
			System.out.println("Enter Staff-ID: ");
			tmp1.setStaffID(sc.nextInt());
			tmpP.getPrescription().add(tmp1);
			AP.getAdministerPatientPrescriptions().add(tmpP);
			ActionLog tempLog =new ActionLog();
			System.out.println("\n-----Please Log your Information of action for Record----\nEnter Staff-ID:");
			tempLog.setStaffID(sc.nextInt());
			System.out.println("Enter Day:");
			sc.nextLine();
			tempLog.setDay(sc.nextLine());
			System.out.println("Enter Time:");
			tempLog.setTime(sc.nextLine());
			tempLog.setActionPerformed("Staff member Administered a Prescription for Patient ");
			logs.add(tempLog);
		}
		
	}
	
	private void moveResidentBed() {
		availableBeds();
		int sw,sr,sb,dw,dr,db;
		System.out.println("Please Look through above Ward, Room, and Bed details and Choose the Bed to be moved\nEnter Ward ID: ");
		sw = sc.nextInt();
		System.out.println("Enter Room Number: ");
		sr = sc.nextInt();
		System.out.println("Enter Bed Number: ");
		sb = sc.nextInt();
		if(ward[sw-1].getRoom(sr-1).getBed(sb-1).isVacant()) {
			System.out.println("No Resident assigned to this Bed, Resident should be assigned to be moved");
		}
		else {
			System.out.println("Please Look through above Ward, Room, and Bed details and Choose the Bed to move the Resident to:\nEnter Ward ID: ");
			dw = sc.nextInt();
			System.out.println("Enter Room Number: ");
			dr = sc.nextInt();
			System.out.println("Enter Bed Number: ");
			db = sc.nextInt();
			if(ward[dw-1].getRoom(dr-1).getBed(db-1).isVacant()) {
				ward[dw-1].getRoom(dr-1).getBed(db-1).setPatientAssigned(ward[sw-1].getRoom(sr-1).getBed(sb-1).getPatientAssigned());
				ward[dw-1].getRoom(dr-1).getBed(db-1).setVacant(false);
				System.out.println("Bed moved successfully");
				ward[sw-1].getRoom(sr-1).getBed(sb-1).setPatientAssigned(null);
				ward[sw-1].getRoom(sr-1).getBed(sb-1).setVacant(true);
				ActionLog tempLog =new ActionLog();
				System.out.println("\n-----Please Log your Information of action for Record----\nEnter Staff-ID:");
				tempLog.setStaffID(sc.nextInt());
				System.out.println("Enter Day:");
				sc.nextLine();
				tempLog.setDay(sc.nextLine());
				System.out.println("Enter Time:");
				tempLog.setTime(sc.nextLine());
				tempLog.setActionPerformed("Staff member moved the patient bed");
				logs.add(tempLog);
			}
			else {
				System.out.println(" Resident already assigned to this Bed, Bed should be empty to put a new Resident");
				}
			
			}
	}
	
	
	private void addPrescription() {
		
		availableBeds();
		boolean flag = true;
		int w,r,b;
		System.out.println("Please Look through above Ward, Room, and Bed details and choose to Add Prescription for Resident\nEnter Ward ID: ");
		w = sc.nextInt();
		System.out.println("Enter Room Number: ");
		r = sc.nextInt();
		System.out.println("Enter Bed Number: ");
		b = sc.nextInt();
		if(ward[w-1].getRoom(r-1).getBed(b-1).isVacant()) {
			System.out.println("No Resident assigned to this Bed");
		}
		else {do {Prescription tmp = new Prescription();
			sc.nextLine();
			System.out.println("Enter Medicine: ");
			tmp.setMedicine(sc.nextLine());
			System.out.println("Enter Time: ");
			tmp.setTime(sc.nextLine());
			System.out.println("Enter Dose: ");
			tmp.setDose(sc.nextLine());
			System.out.println("Enter Staff-ID: ");
			tmp.setStaffID(sc.nextInt());
//			prescription.add(tmp);
			ward[w-1].getRoom(r-1).getBed(b-1).getPatientAssigned().getPrescription().add(tmp);
			System.out.println("Enter 1 to add another Prescription or Any other Key to go back to Main Menu: ");
			if(sc.nextInt()==1) {flag=true; tmp=null;}
			else {flag=false;}
			}while(flag);
//		ward[w-1].getRoom(r-1).getBed(b-1).getPatientAssigned().setPrescription(prescription);
		ActionLog tempLog =new ActionLog();
		System.out.println("\n-----Please Log your Information of action for Record----\nEnter Staff-ID:");
		tempLog.setStaffID(sc.nextInt());
		System.out.println("Enter Day:");
		sc.nextLine();
		tempLog.setDay(sc.nextLine());
		System.out.println("Enter Time:");
		tempLog.setTime(sc.nextLine());
		tempLog.setActionPerformed("Staff member added a new a Prescription for Patient ");
		logs.add(tempLog);
			}
		}
	
	private void residentDetails() {
		availableBeds();
		int w,r,b;
		System.out.println("Please Look through above Ward, Room, and Bed details and choose to display Resident Data\nEnter Ward ID: ");
		w = sc.nextInt();
		System.out.println("Enter Room Number: ");
		r = sc.nextInt();
		System.out.println("Enter Bed Number: ");
		b = sc.nextInt();
		if(ward[w-1].getRoom(r-1).getBed(b-1).isVacant()) {
			System.out.println("No Resident assigned to this Bed");
		}
		else {
			System.out.println(" Patient ID is: "+ward[w-1].getRoom(r-1).getBed(b-1).getPatientAssigned().getPatientID());
			List<Prescription> prescription = ward[w-1].getRoom(r-1).getBed(b-1).getPatientAssigned().getPrescription();
			Prescription tmp;
			for(int i=0;i<prescription.size();i++){
				tmp=prescription.get(i);
				System.out.println("Medicine:"+tmp.getMedicine()+"\tTime:"+tmp.getTime()+"\tDose:"+tmp.getDose()+"\tStaff-ID:"+tmp.getStaffID());
			}
			
			
		}
	}
	
	private void addResidentToBed() {
		Patient p = new Patient();
		int w,r,b;
		System.out.println("Enter Patient ID: ");
		p.setPatientID(sc.nextInt());
		do {
		availableBeds();
		System.out.println("Please Look through above Ward, Room, and Bed details and choose desired place\nEnter Ward ID: ");
		w = sc.nextInt();
		System.out.println("Enter Room Number: ");
		r = sc.nextInt();
		System.out.println("Enter Bed Number: ");
		b = sc.nextInt();
		if(ward[w-1].getRoom(r-1).getBed(b-1).isVacant()) {
			ward[w-1].getRoom(r-1).getBed(b-1).setPatientAssigned(p);
			ward[w-1].getRoom(r-1).getBed(b-1).setVacant(false);
			System.out.println("Bed assigned successfully");
			ActionLog tempLog =new ActionLog();
			System.out.println("\n-----Please Log your Information of action for Record----\nEnter Staff-ID:");
			tempLog.setStaffID(sc.nextInt());
			System.out.println("Enter Day:");
			sc.nextLine();
			tempLog.setDay(sc.nextLine());
			System.out.println("Enter Time:");
			tempLog.setTime(sc.nextLine());
			tempLog.setActionPerformed("Staff member assigned patient a bed ");
			logs.add(tempLog);
			break;
		}
		else {
			System.out.println("Sorry! Bed is already occupied");
		}
		}while(true);
	}


	private void availableBeds() {
		int numBeds;
		for(int w=0;w<2;w++) {
			System.out.println("-----------Ward: "+ward[w].getWardID()+"-------------");
			for(int r=0;r<6;r++) {
				System.out.println("--Room: "+ward[w].getRoom(r).getRoomID());
				numBeds = ward[w].getRoom(r).getNumBeds();
				for(int b=0;b<numBeds;b++) {
					System.out.println("----Bed: "+ward[w].getRoom(r).getBed(b).getBedID());
					if(ward[w].getRoom(r).getBed(b).isVacant()) {
						System.out.println("------Available");
					}
					else {
						System.out.println("------Not Available");
					}
					
				}
			}
		}
	}

	
	private void addNurseInfo() {
		Location locTemp = new Location();
		Nurse temp = new Nurse();
		System.out.println("Enter Id: ");
		temp.setId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Name: ");
		temp.setName(sc.nextLine());
		System.out.println("Enter Status i.e. Available or Not Available: ");
		temp.setStatus(sc.nextLine());
		boolean timeCheckFlag = true;
		int time;
		while(timeCheckFlag) {
			System.out.println("2-shifts available i.e. \n"
					+ "1: 8am-4pm\n"
					+ "2: 2pm-10pm\n"
					+ "Enter Shift Start Time: ");
			time = sc.nextInt();
			temp.setShiftStart(time);
			System.out.println("Enter Shift End Time: ");
			time = sc.nextInt();
			temp.setShiftEnd(time);
			if (checkCompliance(temp.getShiftStart(), temp.getShiftEnd()))
				timeCheckFlag = false;
		}
		if (temp.getShiftStart() == 8)
			temp.setShiftTiming(""+temp.getShiftStart()+"am to "+temp.getShiftEnd()+"pm");
		else {
			temp.setShiftTiming(""+temp.getShiftStart()+"pm to "+temp.getShiftEnd()+"pm");
		}
		System.out.println("Enter Information of Assignment in Ward i.e 1 for Ward1, 2 for Ward2\nEnter Ward:");
		locTemp.setWard(sc.nextInt());
		System.out.println("Enter Information of Room in Ward i.e Room: 1-6\nEnter Room:");
		locTemp.setRoom(sc.nextInt());
		System.out.println("Enter Information of Bed in Room \ni.e Room:1 has 1-Bed, Room:2 has 2-Beds,and Room:3-6 has 4-Beds\nEnter Room:");
		locTemp.setBed(sc.nextInt());
		temp.setLocationAssignedTo(locTemp);
		if(temp.getStatus().equals("Available")) {
			availableStaff.setAvailableNurse(temp);
		}
		nursesData.add(temp);
		System.out.println("\n\nFollowing Details are Added in the Record");
		displayNurseData(temp);
	}

	public boolean checkCompliance(int shiftStart, int shiftEnd) {
		if(shiftStart == 8) {
			if (shiftEnd == 4) {
				return true;
			}
			else {
				System.out.println("Wrong Shift End Time, Enter Again!");
				return false;
			}
		}
		else if(shiftStart == 2) {
			if (shiftEnd == 10) {
				return true;
			}
			else {
				System.out.println("Wrong Shift End Time, Enter Again!");
				return false;
			}
		}
		
		System.out.println("Wrong Shift Start Time, Enter Again!");
		return false;
	}

	private void displayNurseData(Nurse temp) {
		System.out.println("\nID: "+temp.getId()+"\nName: "+temp.getName()
						+ "\nShift Time: "+temp.getShiftTiming()
						+"\nStatus: "+temp.getStatus()
						+"\nLocation Assigned To, \nWard: "+temp.getLocationAssignedTo().getWard()
						+"\nRoom: "+temp.getLocationAssignedTo().getRoom()+"\n\n");
	}
	

	private void addDoctorInfo() {
		Location locTemp = new Location();
		Doctor temp = new Doctor();
		System.out.println("Enter Id: ");
		temp.setId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Name: ");
		temp.setName(sc.nextLine());
		System.out.println("Enter Status i.e. Available or Not Available: ");
		temp.setStatus(sc.nextLine());
		String time;
		System.out.println("Enter shift Time i.e. between 8am & 10pm"
				+ "\nEnter Shift Start Time: ");
		time = sc.nextLine();
		temp.setShiftTiming(time);
		System.out.println("Enter Information of Assignment in Ward i.e 1 for Ward1, 2 for Ward2\nEnter Ward:");
		locTemp.setWard(sc.nextInt());
		System.out.println("Enter Information of Room in Ward i.e Room: 1-6\nEnter Room:");
		locTemp.setRoom(sc.nextInt());
		System.out.println("Enter Information of Bed in Room \ni.e Room:1 has 1-Bed, Room:2 has 2-Beds,and Room:3-6 has 4-Beds\nEnter Room:");
		locTemp.setBed(sc.nextInt());
		temp.setLocationAssignedTo(locTemp);
		if(temp.getStatus().equals("Available")) {
			availableStaff.setAvailableDoctor(temp);
		}
		doctorsData.add(temp);
		System.out.println("\n\nFollowing Details are Added in the Record");
		displayDoctorData(temp);
		
	}


	private void displayDoctorData(Doctor temp) {
		System.out.println("\nID: "+temp.getId()+"\nName: "+temp.getName()
						+ "\nShift Start Time i.e. 1-Hour Shift: "+temp.getShiftTiming()
						+"\nStatus: "+temp.getStatus()
						+"\nLocation Assigned To, \nWard: "+temp.getLocationAssignedTo().getWard()
						+"\nRoom: "+temp.getLocationAssignedTo().getRoom()+"\n\n");
		
	}

	private void availableMedicalStaff() {
		System.out.println("\nAvailable Doctors are");
		
		for (Doctor doc : availableStaff.getAvailableDoctor()) {
			displayDoctorData(doc);
		}
		
		System.out.println("\nAvailable Nurses are");
		
		for (Nurse doc : availableStaff.getAvailableNurse()) {
			displayNurseData(doc);
		}
		
	}
	
	/**
	 * @return the nursesData
	 */
	public List<Nurse> getNursesData() {
		return nursesData;
	}

	/**
	 * @param nursesData the nursesData to set
	 */
	public void setNursesData(List<Nurse> nursesData) {
		this.nursesData = nursesData;
	}

	/**
	 * @return the doctorsData
	 */
	public List<Doctor> getDoctorsData() {
		return doctorsData;
	}
	
	/**
	 * @param doctorsData the doctorsData to set
	 */
	public void setDoctorsData(List<Doctor> doctorsData) {
		this.doctorsData = doctorsData;
	}
	
	/**
	 * @return the availableStaff
	 */
	public AvailableStaff getAvailableStaff() {
		return availableStaff;
	}

	/**
	 * @param availableStaff the availableStaff to set
	 */
	public void setAvailableStaff(AvailableStaff availableStaff) {
		this.availableStaff = availableStaff;
	}


	public static void main(String[] args) {
		CareHome careHomeObjectSerializable = null;
		CareHome careHomeObject = new CareHome();
		String path = new File("").getAbsolutePath();
		System.out.println(path);
		try {
	         FileInputStream fileIn = new FileInputStream(path+"\\carehome.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         careHomeObjectSerializable = (CareHome) in.readObject();
	         careHomeObject.setNursesData(careHomeObjectSerializable.getNursesData());
	         careHomeObject.setDoctorsData(careHomeObjectSerializable.getDoctorsData());
	         careHomeObject.setAvailableStaff(careHomeObjectSerializable.getAvailableStaff());
	         careHomeObject.setWard(careHomeObjectSerializable.getWard());
	         careHomeObject.setAP(careHomeObjectSerializable.getAP());
	         careHomeObject.setLogs(careHomeObjectSerializable.getLogs());
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
//	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("CareHome class not found");
//	         c.printStackTrace();
	      }
		
		careHomeObject.menuFunction();
		
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(path+"\\carehome.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(careHomeObject);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in: "+path+"\\carehome.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}

}
