package location;

import java.io.Serializable;

public class Room implements Serializable{
	private static final long serialVersionUID = 1L;
	Bed bed[]; 
	public Bed[] getBed() {
		return bed;
	}

	public void setBed(Bed[] bed) {
		this.bed = bed;
	}

	int roomID; 
	int numBeds; 

	/**
	 * @param bed
	 */
	public Room(int noOfBeds, int rID) {
		numBeds = noOfBeds;
		bed = new Bed[noOfBeds];
		for(int i=0;i<noOfBeds;i++) {
			bed[i] = new Bed(i+1);
		}
		roomID = rID;
	}

	/**
	 * @return the numBeds
	 */
	public int getNumBeds() {
		return numBeds;
	}

	/**
	 * @param numBeds the numBeds to set
	 */
	public void setNumBeds(int numBeds) {
		this.numBeds = numBeds;
	}

	/**
	 * @return the bed
	 */
	public Bed getBed(int bedNo) {
		return bed[bedNo];
	}

	/**
	 * @param bed the bed to set
	 */
	public void setBed(Bed bed, int bedNo) {
		this.bed[bedNo] = bed;
	}

	/**
	 * @return the roomID
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	
	
}
