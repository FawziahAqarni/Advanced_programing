package application;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mainCareHome.CareHome;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	CareHome carHome = new CareHome();

	@Override
	public void start(Stage primaryStage) {
		try {
			carHome.init();
			AnchorPane root = FXMLLoader.load(getClass().getResource("carehome.fxml"));
			Scene scene = new Scene(root);
			Button addNurseInfo = (Button) scene.lookup("#addNurseInfo");
			//Event Handler for different UI Buttons
			addNurseInfo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Add Nurse Info");
					carHome.addNurseInfo();
					

				}
			});
			Button addDoctorInfo = (Button) scene.lookup("#addDoctorInfo");
			addDoctorInfo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Add Doctor Info");
					carHome.addDoctorInfo();

				}
			});
			Button availableMedicalStaff = (Button) scene.lookup("#availableMedicalStaff");
			availableMedicalStaff.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Available Medical Staff");
					JOptionPane.showMessageDialog(null, carHome.availableMedicalStaff(), "Available Medical Staff", 1);;

				}
			});
			Button addResidentToBed = (Button) scene.lookup("#addResidentToBed");
			addResidentToBed.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Add resident to Bed");
					carHome.addResidentToBed();

				}
			});
			Button availableBeds = (Button) scene.lookup("#availableBeds");
			availableBeds.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Available beds");
					JOptionPane.showMessageDialog(null, carHome.availableBeds(), "Available beds", 1);
				}
			});
		
			Button addPrescription = (Button) scene.lookup("#addPrescription");
			addPrescription.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Add Precription");
					carHome.addPrescription();

				}
			});
			
			Button residentDetails = (Button) scene.lookup("#residentDetails");
			residentDetails.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Residental Description");
					carHome.residentDetails();

				}
			});
			Button moveResidentBed = (Button) scene.lookup("#moveResidentBed");
			moveResidentBed.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Move resident bed");
					carHome.moveResidentBed();

				}
			});
			Button administerPrescription = (Button) scene.lookup("#administerPrescription");
			administerPrescription.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Administer Prescription");
					carHome.administerPrescription();

				}
			});
			Button displayAdministerPrescription = (Button) scene.lookup("#displayAdministerPrescription");
			displayAdministerPrescription.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Display Administered Prescription");
					carHome.displayAdministerPrescription();

				}
			});
			Button displayLogs = (Button) scene.lookup("#displayLogs");
			displayLogs.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					System.out.println("Display Logs");
					carHome.displayLogs();

				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
