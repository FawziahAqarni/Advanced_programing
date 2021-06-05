package logs;

import java.io.Serializable;




public class ActionLog implements Serializable {
	private static final long serialVersionUID = 1L;
	int staffID; 
	String day, time, actionPerformed;
	
	
	/**
	 * 
	 */
	public ActionLog() {
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
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
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
	 * @return the actionPerformed
	 */
	public String getActionPerformed() {
		return actionPerformed;
	}
	/**
	 * @param actionPerformed the actionPerformed to set
	 */
	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}
	
	
}

