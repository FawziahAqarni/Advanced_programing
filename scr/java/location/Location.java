package location;

import java.io.Serializable;



public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ward;
	int room;
	int bed;

	public Location() {
		super();
	}

	/**
	 * @param ward
	 * @param room
	 */
	public Location(int ward, int room) {
		super();
		this.ward = ward;
		this.room = room;
	}

	/**
	 * @return the bed
	 */
	public int getBed() {
		return bed;
	}

	/**
	 * @param bed the bed to set
	 */
	public void setBed(int bed) {
		this.bed = bed;
	}

	/**
	 * @return the ward
	 */
	public int getWard() {
		return ward;
	}

	/**
	 * @param ward the ward to set
	 */
	public void setWard(int ward) {
		this.ward = ward;
	}

	/**
	 * @return the room
	 */
	public int getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(int room) {
		this.room = room;
	}


	
	
}
