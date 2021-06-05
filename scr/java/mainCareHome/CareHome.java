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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class CareHome implements Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	transient Scanner sc;
	List<Nurse> nursesData;
	List<Doctor> doctorsData;
	AvailableStaff availableStaff;
	Ward ward[];
	AdministerPrescription AP;
	List<ActionLog> logs;

	
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


	public void menuFunction() {
		boolean flag = true;
		int choice;
		do {
			System.out.println("Aged Care Facility Management System\n\n" + "Select option to perform task\n"
					+ "'1' to Add Nurse\n" + "'2' to Add Doctor\n" + "'3' to Display Available Medical Staff\n"
					+ "'4' to Add a new Resident to a Bed\n" + "'5' to Display Ward Details\n"
					+ "'6' to Check Details of Resident in a Bed\n"
					+ "'7' to Add New Prescription to Resident in a Bed\n" + "'8' to Move Resident Bed\n"
					+ "'9' to Administer Prescription to Resident in a Bed\n"
					+ "'10' to Display administered Prescription so far\n" + "'11' to Display Logs\n"
					+ "'Any Other Key' to Exit\n");
			choice = sc.nextInt();
			switch (choice) {
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

		} while (flag);

	}

	public void displayLogs() {
		String temp = "";
		for (int i = 0; i < logs.size(); i++) {
			temp+= "Staff-ID: " + logs.get(i).getStaffID() + "\tDay:" + logs.get(i).getDay() + "\tTime:"
					+ logs.get(i).getTime() + "\nAction-Performed: " + logs.get(i).getActionPerformed() + "\n";
			System.out.println("Staff-ID: " + logs.get(i).getStaffID() + "\tDay:" + logs.get(i).getDay() + "\tTime:"
					+ logs.get(i).getTime() + "\nAction-Performed: " + logs.get(i).getActionPerformed());
		}
		JOptionPane.showMessageDialog(null, temp, "DisplayLogs", 1);
	}

	public void displayAdministerPrescription() {
		Patient tmppat = new Patient();
		Prescription tmp;
		List<Prescription> prescription = new ArrayList<Prescription>();
		List<Patient> administerPatientPrescriptions = AP.getAdministerPatientPrescriptions();
		String temp2 = "";
		for (int p = 0; p < administerPatientPrescriptions.size(); p++) {
			tmppat = administerPatientPrescriptions.get(p);
			prescription = tmppat.getPrescription();
			for (int i = 0; i < prescription.size(); i++) {
				tmp = prescription.get(i);
				temp2 += "Patient-ID: " + tmppat.getPatientID() + "\tMedicine:" + tmp.getMedicine()
				+ "\tTime:" + tmp.getTime() + "\tDose:" + tmp.getDose() + "\tStaff-ID:" + tmp.getStaffID() + "\n";
				System.out.println("Patient-ID: " + tmppat.getPatientID() + "\tMedicine:" + tmp.getMedicine()
						+ "\tTime:" + tmp.getTime() + "\tDose:" + tmp.getDose() + "\tStaff-ID:" + tmp.getStaffID());
			}
		}
		JOptionPane.showMessageDialog(null, temp2, "DisplayAdministerPrescription", 1);
	}

//This function is to Administer Prescription to patients and storing it in the AdministerPrescription class object AP abnd finally logs the action with details
	public void administerPrescription() {
		
		JFrame adinfo = new JFrame("Administer a Prescription");
		adinfo.setBounds(450, 50, 500, 650);
		adinfo.setTitle("Administer a Prescription");         
       
        JLabel award=new JLabel("Ward ID:");
        award.setBounds(20, 30, 130, 30);
        JTextField award_text = new JTextField ();
        award_text.setBounds(170, 30, 270, 30);
        
        JLabel rward=new JLabel("Room Number:");
        rward.setBounds(20, 90, 130, 30);
        JTextField rward_text = new JTextField ();
        rward_text.setBounds(170, 90, 270, 30);

        JLabel broom=new JLabel("Bed Number:");
        broom.setBounds(20, 150, 130, 30);
        JTextField broom_text = new JTextField ();
        broom_text.setBounds(170, 150, 270, 30);   
        
        JLabel id=new JLabel("Patient ID:");
        id.setBounds(20, 210, 130, 30);
        JTextField id_text = new JTextField ();
        id_text.setBounds(170, 210, 270, 30);
        
        JLabel time=new JLabel("Time :");
        time.setBounds(20, 270, 130, 30);
        JTextField time_text = new JTextField ();
        time_text.setBounds(170, 270, 270, 30);
		
        JLabel medicine=new JLabel("Medicine :");
        medicine.setBounds(20, 330, 130, 30);
        JTextField medicine_text = new JTextField ();
        medicine_text.setBounds(170, 330, 270, 30);
        
        
        JLabel dose=new JLabel("Dose :");
        dose.setBounds(20, 390, 130, 30);
        JTextField dose_text = new JTextField ();
        dose_text.setBounds(170, 390, 270, 30);
        
		JLabel sid=new JLabel("Stuff-ID:");
        sid.setBounds(20, 450, 130, 30);
        JTextField sid_text = new JTextField ();
        sid_text.setBounds(170, 450, 270, 30);

        
        JButton OK_butt = new JButton("OK");
		OK_butt.setBounds(150, 550, 200, 40);
		OK_butt.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	

				availableBeds();
				int w, r, b;
				w = Integer.parseInt(award_text.getText());
				r = Integer.parseInt(rward_text.getText());
				b = Integer.parseInt(broom_text.getText());
				if (ward[w - 1].getRoom(r - 1).getBed(b - 1).isVacant()) {
					JOptionPane.showMessageDialog(null, "No Resident assigned to this Bed", "ERROR", 1);
					
				} else {
					JOptionPane.showMessageDialog(null, " Patient ID is: " + ward[w - 1].getRoom(r - 1).getBed(b - 1).getPatientAssigned().getPatientID(),"" , 1);
					List<Prescription> prescription = ward[w - 1].getRoom(r - 1).getBed(b - 1).getPatientAssigned()
							.getPrescription();
					Prescription tmp;
					String temp2 = "";
					for (int i = 0; i < prescription.size(); i++) {
						tmp = prescription.get(i);
						temp2 += "Medicine:" + tmp.getMedicine() + "\tTime:" + tmp.getTime() + "\tDose:"
								+ tmp.getDose() + "\tStaff-ID:" + tmp.getStaffID() + "\n";
					}
					JOptionPane.showMessageDialog(null, temp2,"" , 1);
			
					Patient tmpP = new Patient();
					Prescription tmp1 = new Prescription();
					
					tmpP.setPatientID(Integer.parseInt(id_text.getText()));
					tmp1.setMedicine(medicine_text.getText());
					
					tmp1.setTime(time_text.getText());
					
					tmp1.setDose(dose_text.getText());
					System.out.println("Enter Staff-ID: ");
					tmp1.setStaffID(Integer.parseInt(sid_text.getText()));
					tmpP.getPrescription().add(tmp1);
					AP.getAdministerPatientPrescriptions().add(tmpP);
					ActionLog tempLog = new ActionLog();
		
				}
		    	
		    }
		});
		
		adinfo.add(id);        adinfo.add(id_text);
		adinfo.add(medicine);        adinfo.add(medicine_text);
		adinfo.add(award);        adinfo.add(award_text);
		adinfo.add(rward);        adinfo.add(rward_text);
		adinfo.add(broom);        adinfo.add(broom_text);
		adinfo.add(sid);        adinfo.add(sid_text);
		adinfo.add(dose);		adinfo.add(dose_text);
		adinfo.add(time);        adinfo.add(time_text);
		adinfo.add(OK_butt);
		adinfo.setLayout(null);  
		adinfo.setVisible(true);
        
//	

	}

//This function is to move the patient from one bed to another bed, finally logs the action
	public void moveResidentBed() {
		JFrame movebed = new JFrame("moveResidentBed");
		movebed.setBounds(450, 50, 500, 690);
		movebed.setTitle("MoveResidentBed");         
       
        JLabel award=new JLabel("From Ward ID:");
        award.setBounds(20, 30, 130, 30);
        JTextField award_text = new JTextField ();
        award_text.setBounds(170, 30, 270, 30);
        
        JLabel rward=new JLabel("From Room Number :");
        rward.setBounds(20, 90, 130, 30);
        JTextField rward_text = new JTextField ();
        rward_text.setBounds(170, 90, 270, 30);

        JLabel broom=new JLabel("From Bed Number:");
        broom.setBounds(20, 150, 130, 30);
        JTextField broom_text = new JTextField ();
        broom_text.setBounds(170, 150, 270, 30);   
        
        JLabel award2=new JLabel("To Ward ID:");
        award2.setBounds(20, 240, 130, 30);
        JTextField award2_text = new JTextField ();
        award2_text.setBounds(170, 240, 270, 30);
        
        JLabel rward2=new JLabel("To Room Number:");
        rward2.setBounds(20, 300, 130, 30);
        JTextField rward2_text = new JTextField ();
        rward2_text.setBounds(170, 300, 270, 30);

        JLabel broom2=new JLabel("To Bed Number:");
        broom2.setBounds(20, 360, 130, 30);
        JTextField broom2_text = new JTextField ();
        broom2_text.setBounds(170, 360, 270, 30);   
        
        JLabel sid=new JLabel("Staff ID:");
        sid.setBounds(20, 420, 130, 30);
        JTextField sid_text = new JTextField ();
        sid_text.setBounds(170, 420, 270, 30);
        
        JLabel day=new JLabel("Day:");
        day.setBounds(20, 480, 130, 30);
        JTextField day_text = new JTextField ();
        day_text.setBounds(170, 480, 270, 30);

        JLabel time=new JLabel("Time:");
        time.setBounds(20, 540, 130, 30);
        JTextField time_text = new JTextField ();
        time_text.setBounds(170, 560, 270, 30);   
        
        
        JButton OK_butt = new JButton("OK");
		OK_butt.setBounds(150, 610, 200, 40);
		OK_butt.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	

		    	availableBeds();
		    	int sw, sr, sb, dw, dr, db;
				sw = Integer.parseInt(award_text.getText());
				sr = Integer.parseInt(rward_text.getText());
				sb = Integer.parseInt(broom_text.getText());
				if (ward[sw - 1].getRoom(sr - 1).getBed(sb - 1).isVacant()) {
					JOptionPane.showMessageDialog(null, "No Resident assigned to this Bed", "ERROR", 1);
				} else {
						
						System.out.println(
								"Please Look through above Ward, Room, and Bed details and Choose the Bed to move the Resident to:\nEnter Ward ID: ");
						dw = Integer.parseInt(award_text.getText());
						dr = Integer.parseInt(rward_text.getText());
						db = Integer.parseInt(broom_text.getText());
						
						if (ward[dw - 1].getRoom(dr - 1).getBed(db - 1).isVacant()) {
							ward[dw - 1].getRoom(dr - 1).getBed(db - 1)
									.setPatientAssigned(ward[sw - 1].getRoom(sr - 1).getBed(sb - 1).getPatientAssigned());
							ward[dw - 1].getRoom(dr - 1).getBed(db - 1).setVacant(false);
							JOptionPane.showMessageDialog(null, "Bed moved successfully", "Success", 1);
							
							ward[sw - 1].getRoom(sr - 1).getBed(sb - 1).setPatientAssigned(null);
							ward[sw - 1].getRoom(sr - 1).getBed(sb - 1).setVacant(true);
							ActionLog tempLog = new ActionLog();
							tempLog.setStaffID(Integer.parseInt(sid_text.getText()));
							
							tempLog.setDay(day_text.getText());
							System.out.println("Enter Time:");
							tempLog.setTime(time_text.getText());
							tempLog.setActionPerformed("Staff member moved the patient bed");
							JOptionPane.showMessageDialog(null, "Staff member moved the patient bed", "Success", 1);
							//logs.add(tempLog);
						} else {
							JOptionPane.showMessageDialog(null, "Resident already assigned to this Bed, Bed should be empty to put a new Resident", "ERROR", 1);
							
						}
				
				}
		    	
		    }
		});
		
		
		movebed.add(award);        movebed.add(award_text);
		movebed.add(rward);        movebed.add(rward_text);
		movebed.add(broom);        movebed.add(broom_text);
		
		movebed.add(award2);        movebed.add(award2_text);
		movebed.add(rward2);        movebed.add(rward2_text);
		movebed.add(broom2);        movebed.add(broom2_text);
		movebed.add(sid);        movebed.add(sid_text);
		movebed.add(day);        movebed.add(day_text);
		movebed.add(time);        movebed.add(time_text);
		movebed.add(OK_butt);
		movebed.setLayout(null);  
		movebed.setVisible(true);
		
		
	}

	public void addPrescription() {

		JFrame addPreinfo = new JFrame("addPrescription");
		addPreinfo.setBounds(450, 50, 500, 650);
		addPreinfo.setTitle("AddPrescription");         
       
        JLabel award=new JLabel("Ward ID:");
        award.setBounds(20, 30, 130, 30);
        JTextField award_text = new JTextField ();
        award_text.setBounds(170, 30, 270, 30);
        
        JLabel rward=new JLabel("Room Number:");
        rward.setBounds(20, 90, 130, 30);
        JTextField rward_text = new JTextField ();
        rward_text.setBounds(170, 90, 270, 30);

        JLabel broom=new JLabel("Bed Number:");
        broom.setBounds(20, 150, 130, 30);
        JTextField broom_text = new JTextField ();
        broom_text.setBounds(170, 150, 270, 30);   
        
        JLabel medicine =new JLabel("Medicine:");
        medicine.setBounds(20, 210, 130, 30);
        JTextField medicine_text = new JTextField ();
        medicine_text.setBounds(170, 210, 270, 30);   
        
        
        JLabel time =new JLabel("Time:");
        time.setBounds(20, 270, 130, 30);
        JTextField time_text = new JTextField ();
        time_text.setBounds(170, 270, 270, 30);  
        
        JLabel dose =new JLabel("Dose:");
        dose.setBounds(20, 330, 130, 30);
        JTextField dose_text = new JTextField ();
        dose_text.setBounds(170, 330, 270, 30);  
        
        
        JLabel sid =new JLabel("Staff-ID:");
        sid.setBounds(20, 390, 130, 30);
        JTextField sid_text = new JTextField ();
        sid_text.setBounds(170, 390, 270, 30);  
        
        JButton OK_butt = new JButton("OK");
		OK_butt.setBounds(150, 500, 200, 40);
		OK_butt.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	

		    	availableBeds();
				int w, r, b;
				w = Integer.parseInt(award_text.getText());
				r = Integer.parseInt(rward_text.getText());
				b = Integer.parseInt(broom_text.getText());
				if (ward[w - 1].getRoom(r - 1).getBed(b - 1).isVacant()) {
					JOptionPane.showMessageDialog(null, "No Resident assigned to this Bed", "ERROR", 1);
					
				} else {
					
					Prescription tmp = new Prescription();
					sc.nextLine();
				
					tmp.setMedicine(medicine_text.getText());
		
					tmp.setTime(time_text.getText());
		
					tmp.setDose(dose_text.getText());

					tmp.setStaffID(Integer.parseInt(sid_text.getText()));
					ward[w - 1].getRoom(r - 1).getBed(b - 1).getPatientAssigned().getPrescription().add(tmp);
					
				}
		    	
		    }
		});
		
		
		addPreinfo.add(award);        addPreinfo.add(award_text);
		addPreinfo.add(rward);        addPreinfo.add(rward_text);
		addPreinfo.add(broom);        addPreinfo.add(broom_text);
		addPreinfo.add(medicine);        addPreinfo.add(medicine_text);
		addPreinfo.add(time);        addPreinfo.add(time_text);
		addPreinfo.add(dose);        addPreinfo.add(dose_text);
		addPreinfo.add(sid);        addPreinfo.add(sid_text);
		addPreinfo.add(OK_butt);
		addPreinfo.setLayout(null);  
		addPreinfo.setVisible(true);
		
	}

//This function is display all details of patient, resident of bed
	public void residentDetails() {
		
		JFrame resinfo = new JFrame("residentDetails");
		resinfo.setBounds(450, 250, 500, 320);
		resinfo.setTitle("ResidentDetails");         
       
        JLabel award=new JLabel("Ward ID:");
        award.setBounds(20, 30, 130, 30);
        JTextField award_text = new JTextField ();
        award_text.setBounds(170, 30, 270, 30);
        
        JLabel rward=new JLabel("Room Number:");
        rward.setBounds(20, 90, 130, 30);
        JTextField rward_text = new JTextField ();
        rward_text.setBounds(170, 90, 270, 30);

        JLabel broom=new JLabel("Bed Number:");
        broom.setBounds(20, 150, 130, 30);
        JTextField broom_text = new JTextField ();
        broom_text.setBounds(170, 150, 270, 30);   
        
        
        JButton OK_butt = new JButton("OK");
		OK_butt.setBounds(150, 230, 200, 40);
		OK_butt.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	

		    	availableBeds();
				int w, r, b;
				w = Integer.parseInt(award_text.getText());
				r = Integer.parseInt(rward_text.getText());
				b = Integer.parseInt(broom_text.getText());
				if (ward[w - 1].getRoom(r - 1).getBed(b - 1).isVacant()) {
					JOptionPane.showMessageDialog(null, "No Resident assigned to this Bed", "ERROR", 1);
					
				} else {
					JOptionPane.showMessageDialog(null, " Patient ID is: " + ward[w - 1].getRoom(r - 1).getBed(b - 1).getPatientAssigned().getPatientID(),"" , 1);
					List<Prescription> prescription = ward[w - 1].getRoom(r - 1).getBed(b - 1).getPatientAssigned()
							.getPrescription();
					Prescription tmp;
					String temp2 = "";
					for (int i = 0; i < prescription.size(); i++) {
						tmp = prescription.get(i);
						temp2 += "Medicine:" + tmp.getMedicine() + "\tTime:" + tmp.getTime() + "\tDose:"
								+ tmp.getDose() + "\tStaff-ID:" + tmp.getStaffID() + "\n";
					}
					JOptionPane.showMessageDialog(null, temp2,"" , 1);
			
				}
		    	
		    }
		});
		
		
		resinfo.add(award);        resinfo.add(award_text);
		resinfo.add(rward);        resinfo.add(rward_text);
		resinfo.add(broom);        resinfo.add(broom_text);
		
		resinfo.add(OK_butt);
		resinfo.setLayout(null);  
		resinfo.setVisible(true);
        
		
		
	}

	public void addResidentToBed() {
	
		JFrame patientinfo = new JFrame("addResidentToBed");
		patientinfo.setBounds(450, 50, 500, 700);
		patientinfo.setTitle("AddResidentToBed");         
//        
        JLabel id=new JLabel(" Patient ID:");
        id.setBounds(20, 30, 130, 30);
        JTextField id_text = new JTextField ();
        id_text.setBounds(170, 30, 270, 30);
        
        JTextField result_text = new JTextField();
        result_text.setBounds(50,150,400,100);
        
        JLabel award=new JLabel("Ward ID:");
        award.setBounds(20, 270, 130, 30);
        JTextField award_text = new JTextField ();
        award_text.setBounds(170, 270, 270, 30);
        
        JLabel rward=new JLabel("Room Number:");
        rward.setBounds(20, 330, 130, 30);
        JTextField rward_text = new JTextField ();
        rward_text.setBounds(170, 330, 270, 30);

        JLabel broom=new JLabel("Bed Number:");
        broom.setBounds(20, 390, 130, 30);
        JTextField broom_text = new JTextField ();
        broom_text.setBounds(170, 390, 270, 30);   
        
        JButton Search_butt = new JButton("Search");
		Search_butt.setBounds(150, 90, 200, 40);
		Search_butt.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	Patient p = new Patient();
				p.setPatientID(sc.nextInt());
				result_text.setText(availableBeds());
				
		    }
		});
		
		JLabel sid=new JLabel("Stuff-ID:");
        sid.setBounds(20, 450, 130, 30);
        JTextField sid_text = new JTextField ();
        sid_text.setBounds(170, 450, 270, 30);

        
		JLabel day=new JLabel("Day:");
        day.setBounds(20, 500, 130, 30);
        JTextField day_text = new JTextField ();
        day_text.setBounds(170, 500, 270, 30);
        
        JLabel time=new JLabel("Time:");
        time.setBounds(20, 550, 130, 30);
        JTextField time_text = new JTextField ();
        time_text.setBounds(170, 550, 270, 30);
		
        JButton OK_butt = new JButton("OK");
		OK_butt.setBounds(150, 610, 200, 40);
		OK_butt.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) {
		    	Patient p = new Patient();
				int w, r, b;
				w = Integer.parseInt(award_text.getText());
				r = Integer.parseInt(rward_text.getText());
				b = Integer.parseInt(broom_text.getText());
				if (ward[w - 1].getRoom(r - 1).getBed(b - 1).isVacant()) {
					ward[w - 1].getRoom(r - 1).getBed(b - 1).setPatientAssigned(p);
					ward[w - 1].getRoom(r - 1).getBed(b - 1).setVacant(false);
					
					ActionLog tempLog = new ActionLog();
					
					tempLog.setStaffID(Integer.parseInt(sid_text.getText()));
					tempLog.setDay(day_text.getText());
					tempLog.setTime(time_text.getText());
					tempLog.setActionPerformed("Staff member assigned patient a bed ");
					JOptionPane.showMessageDialog(null, "Staff member assigned patient a bed", "Success", 1);
					logs.add(tempLog);
				} else {
					JOptionPane.showMessageDialog(null, "Sorry! Bed is already occupied", "ERROR", 1);
				}
		    }
		});
		
		patientinfo.add(id);        patientinfo.add(id_text);
		patientinfo.add(Search_butt);
		patientinfo.add(result_text);
		patientinfo.add(award);        patientinfo.add(award_text);
		patientinfo.add(rward);        patientinfo.add(rward_text);
		patientinfo.add(broom);        patientinfo.add(broom_text);
		patientinfo.add(sid);        patientinfo.add(sid_text);
		patientinfo.add(day);        patientinfo.add(day_text);
		patientinfo.add(time);        patientinfo.add(time_text);
		patientinfo.add(OK_butt);
        patientinfo.setLayout(null);  
        patientinfo.setVisible(true);
		
	}

	public String availableBeds() {
		int numBeds;
		String temp = "";
		for (int w = 0; w < 2; w++) {
			temp += "-----------Ward: " + ward[w].getWardID() + "-------------\n";
			for (int r = 0; r < 6; r++) {
				temp += "--Room: " + ward[w].getRoom(r).getRoomID() + "\n";
				numBeds = ward[w].getRoom(r).getNumBeds();
				for (int b = 0; b < numBeds; b++) {
					temp +="----Bed: " + ward[w].getRoom(r).getBed(b).getBedID() +"\t";
					if (ward[w].getRoom(r).getBed(b).isVacant()) {
						temp += "------Available\n";
					} else {
						temp += "------Not Available\n";
					}

				}
			}
		}
		return temp;
	}


	public void addNurseInfo() {
		JFrame nurseinfo = new JFrame("nurseinfo");
		nurseinfo.setBounds(450, 100, 500, 630);
		//nurseinfo.setDefaultCloseOperation(JFrame.NORMAL);
		nurseinfo.setTitle("NurseInfo");         
        
        JLabel id=new JLabel("I D:");
        id.setBounds(20, 30, 130, 30);
        JTextField id_text = new JTextField ();
        id_text.setBounds(170, 30, 270, 30);
                    
        JLabel name=new JLabel("Name:");
        name.setBounds(20, 90, 130, 30);
        JTextField name_text = new JTextField ();
        name_text.setBounds(170, 90, 270, 30);
        
        JLabel status=new JLabel("Status:");
        status.setBounds(20, 150, 130, 30);
        JTextField status_text = new JTextField ("Available or Not Available");
        status_text.setBounds(170, 150, 270, 30);
        
        JLabel stime=new JLabel("Start Time:");
        stime.setBounds(20, 210, 130, 30);
        JTextField stime_text = new JTextField ("8am-4pm");
        stime_text.setBounds(170, 210, 270, 30);
        
        JLabel etime=new JLabel("End Time:");
        etime.setBounds(20, 270, 130, 30);
        JTextField etime_text = new JTextField ("2pm-10pm");
        etime_text.setBounds(170, 270, 270, 30);
        
        JLabel award=new JLabel("Assignment in Ward:");
        award.setBounds(20, 330, 130, 30);
        JTextField award_text = new JTextField ("1 for Ward1, 2 for Ward2");
        award_text.setBounds(170, 330, 270, 30);
        
        JLabel rward=new JLabel("Room in Ward:");
        rward.setBounds(20, 390, 130, 30);
        JTextField rward_text = new JTextField ("1-6");
        rward_text.setBounds(170, 390, 270, 30);

        JLabel broom=new JLabel("Bed in Room:");
        broom.setBounds(20, 450, 130, 30);
        JTextField broom_text = new JTextField ("1, 2, 3-6");
        broom_text.setBounds(170, 450, 270, 30);   
     
        JButton OK_butt = new JButton("Save");
        OK_butt.setBounds(200, 530, 100, 40);
        OK_butt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	Location locTemp = new Location();
        		Nurse temp = new Nurse();
        		 try
    		    {
    			 	temp.setId(Integer.parseInt(id_text.getText()));	        
    		    } catch (NumberFormatException ex)
    		    {
    		    	JOptionPane.showMessageDialog(null, "Please enter ID as an integer", "ERROR", 1);
    		    	return;
    		    }
        		
        		temp.setName(name_text.getText());
        		temp.setStatus(status_text.getText());
        		
        		try
     		    {
        			 temp.setShiftStart(Integer.parseInt(stime_text.getText()));       
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter Start Time as an integer", "ERROR", 1);
     		    	return;
     		    }
        		 
        		try
     		    {
        			temp.setShiftEnd(Integer.parseInt(etime_text.getText()));      
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter End Time as an integer", "ERROR", 1);
     		    	return;
     		    }
        		
        		
        		if (!checkCompliance(temp.getShiftStart(), temp.getShiftEnd()))
        			return;
        		if (temp.getShiftStart() == 8)
        			temp.setShiftTiming("" + temp.getShiftStart() + "am to " + temp.getShiftEnd() + "pm");
        		else {
        			temp.setShiftTiming("" + temp.getShiftStart() + "pm to " + temp.getShiftEnd() + "pm");
        		}
        		
        		try
     		    {
        			locTemp.setWard(Integer.parseInt(award_text.getText()));
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter Assignment in Ward as an integer", "ERROR", 1);
     		    	return;
     		    }
        		
        		try
     		    {
        			locTemp.setRoom(Integer.parseInt(rward_text.getText()));
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter Room in Ward as an integer", "ERROR", 1);
     		    	return;
     		    }
        		 
        		try
     		    {
        			locTemp.setBed(Integer.parseInt(broom_text.getText()));
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter Bed in Room as an integer", "ERROR", 1);
     		    	return;
     		    }
        		
        		temp.setLocationAssignedTo(locTemp);
        		if (temp.getStatus().equals("Available")) {
        			availableStaff.setAvailableNurse(temp);
        		}
        		nursesData.add(temp);
        		
        		JOptionPane.showMessageDialog(null, "Following Details are Added in the Record", "SUCCESS", 1);
        		displayNurseData(temp);
        		JOptionPane.showMessageDialog(null, displayNurseData(temp), "DisplayNurseData", 1);
        		nurseinfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);            	
            }
        });
        
        nurseinfo.add(id);        nurseinfo.add(id_text);
        nurseinfo.add(name);        nurseinfo.add(name_text);
        nurseinfo.add(status);        nurseinfo.add(status_text);
        nurseinfo.add(stime);        nurseinfo.add(stime_text);
        nurseinfo.add(etime);        nurseinfo.add(etime_text);
        nurseinfo.add(award);        nurseinfo.add(award_text);
        nurseinfo.add(rward);        nurseinfo.add(rward_text);
        nurseinfo.add(broom);        nurseinfo.add(broom_text);
        nurseinfo.add(OK_butt);
      
        nurseinfo.setLayout(null);  
        nurseinfo.setVisible(true);
	
	}

	public boolean checkCompliance(int shiftStart, int shiftEnd) {
		if (shiftStart == 8) {
			if (shiftEnd == 4) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Wrong Shift End Time, Enter Again!", "ERROR", 1);				
				return false;
			}
		} else if (shiftStart == 2) {
			if (shiftEnd == 10) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Wrong Shift End Time, Enter Again!", "ERROR", 1);		
				return false;
			}
		}
		JOptionPane.showMessageDialog(null, "Wrong Shift Start Time, Enter Again!", "ERROR", 1);		
		return false;
	}

	
	public String displayNurseData(Nurse temp) {
		String temp2 = "ID: " + temp.getId() + "\nName: " + temp.getName() + "\nShift Time: "
				+ temp.getShiftTiming() + "\nStatus: " + temp.getStatus() + "\nLocation Assigned To, \nWard: "
				+ temp.getLocationAssignedTo().getWard() + "\nRoom: " + temp.getLocationAssignedTo().getRoom();
		
		//JOptionPane.showMessageDialog(null, temp2, "DisplayNurseData", 1);
		return temp2;
		
	}


	public void addDoctorInfo() {
		JFrame doctorinfo = new JFrame("doctorinfo");
		doctorinfo.setBounds(450, 100, 500, 600);
		doctorinfo.setTitle("DoctorInfo");         
        
        JLabel id=new JLabel("I D:");
        id.setBounds(20, 30, 130, 30);
        JTextField id_text = new JTextField ();
        id_text.setBounds(170, 30, 270, 30);
                    
        JLabel name=new JLabel("Name:");
        name.setBounds(20, 90, 130, 30);
        JTextField name_text = new JTextField ();
        name_text.setBounds(170, 90, 270, 30);
        
        JLabel status=new JLabel("Status:");
        status.setBounds(20, 150, 130, 30);
        JTextField status_text = new JTextField ("Available or Not Available");
        status_text.setBounds(170, 150, 270, 30);
        
        JLabel stime=new JLabel("Start Time:");
        stime.setBounds(20, 210, 130, 30);
        JTextField stime_text = new JTextField ("8am-4pm");
        stime_text.setBounds(170, 210, 270, 30);
        
        JLabel award=new JLabel("Assignment in Ward:");
        award.setBounds(20, 270, 130, 30);
        JTextField award_text = new JTextField ("1 for Ward1, 2 for Ward2");
        award_text.setBounds(170, 270, 270, 30);
        
        JLabel rward=new JLabel("Room in Ward:");
        rward.setBounds(20, 330, 130, 30);
        JTextField rward_text = new JTextField ("1-6");
        rward_text.setBounds(170, 330, 270, 30);

        JLabel broom=new JLabel("Bed in Room:");
        broom.setBounds(20, 390, 130, 30);
        JTextField broom_text = new JTextField ("1, 2, 3-6");
        broom_text.setBounds(170, 390, 270, 30);   
     
        JButton OK_butt = new JButton("Save");
        OK_butt.setBounds(200, 500, 100, 40);
        OK_butt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	Location locTemp = new Location();
        		Doctor temp = new Doctor();
        		 try
    		    {
    			 	temp.setId(Integer.parseInt(id_text.getText()));	        
    		    } catch (NumberFormatException ex)
    		    {
    		    	JOptionPane.showMessageDialog(null, "Please enter ID as an integer", "ERROR", 1);
    		    	return;
    		    }
        		
        		temp.setName(name_text.getText());
        		temp.setStatus(status_text.getText());
        		temp.setShiftTiming(stime_text.getText());       
     		     
        		
        		try
     		    {
        			locTemp.setWard(Integer.parseInt(award_text.getText()));
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter Assignment in Ward as an integer", "ERROR", 1);
     		    	return;
     		    }
        		
        		try
     		    {
        			locTemp.setRoom(Integer.parseInt(rward_text.getText()));
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter Room in Ward as an integer", "ERROR", 1);
     		    	return;
     		    }
        		 
        		try
     		    {
        			locTemp.setBed(Integer.parseInt(broom_text.getText()));
     		    } catch (NumberFormatException ex)
     		    {
     		    	JOptionPane.showMessageDialog(null, "Please enter Bed in Room as an integer", "ERROR", 1);
     		    	return;
     		    }
        		
        		temp.setLocationAssignedTo(locTemp);
        		if (temp.getStatus().equals("Available")) {
        			availableStaff.setAvailableDoctor(temp);
        		}
        		doctorsData.add(temp);
        		
        		JOptionPane.showMessageDialog(null, "Following Details are Added in the Record", "SUCCESS", 1);
        		
        		JOptionPane.showMessageDialog(null, displayDoctorData(temp), "DisplayDoctorData", 1);
        		doctorinfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);            	
            }
        });
        
        doctorinfo.add(id);        doctorinfo.add(id_text);
        doctorinfo.add(name);        doctorinfo.add(name_text);
        doctorinfo.add(status);        doctorinfo.add(status_text);
        doctorinfo.add(stime);        doctorinfo.add(stime_text);
        
        doctorinfo.add(award);        doctorinfo.add(award_text);
        doctorinfo.add(rward);        doctorinfo.add(rward_text);
        doctorinfo.add(broom);        doctorinfo.add(broom_text);
        doctorinfo.add(OK_butt);
      
        doctorinfo.setLayout(null);  
        doctorinfo.setVisible(true);
		
	}
	public String displayDoctorData(Doctor temp) {
		String temp2 = "ID: " + temp.getId() + "\nName: " + temp.getName()
		+ "\nShift Start Time i.e. 1-Hour Shift: " + temp.getShiftTiming() + "\nStatus: " + temp.getStatus()
		+ "\nLocation Assigned To, \nWard: " + temp.getLocationAssignedTo().getWard() + "\nRoom: "
		+ temp.getLocationAssignedTo().getRoom() + "\n\n";
		
		//JOptionPane.showMessageDialog(null, temp2, "DisplayDoctorData", 1);
		return temp2;
	}

	public String availableMedicalStaff() {
		
		String temp = "";
		System.out.println("\nAvailable Doctors are :");
		temp += "Available Doctors are :\n"; 
		for (Doctor doc : availableStaff.getAvailableDoctor()) {
			temp += displayDoctorData(doc);
		}

		System.out.println("\nAvailable Nurses are :");
		temp += "\nAvailable Nurses are :\n ";
		for (Nurse doc : availableStaff.getAvailableNurse()) {
			temp += displayNurseData(doc);
		}
		return temp;
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
			FileInputStream fileIn = new FileInputStream(path + "\\carehome.ser");
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

		//careHomeObject.menuFunction();

		try {
			FileOutputStream fileOut = new FileOutputStream(path + "\\carehome.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(careHomeObject);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in: " + path + "\\carehome.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void init() {
		CareHome careHomeObjectSerializable = null;
		CareHome careHomeObject = new CareHome();
		String path = new File("").getAbsolutePath();
		System.out.println(path);
		try {
			FileInputStream fileIn = new FileInputStream(path + "\\carehome.ser");
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

		// careHomeObject.menuFunction();

		try {
			FileOutputStream fileOut = new FileOutputStream(path + "\\carehome.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(careHomeObject);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in: " + path + "\\carehome.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

}
