package location;

import java.io.Serializable;

public class Ward implements Serializable {
	private static final long serialVersionUID = 1L;

	public Room[] getRoom() {
		return room;
	}

	public void setRoom(Room[] room) {
		this.room = room;
	}

	Room room[];
	int wardID;

	/**
	 * 
	 */
	public Ward(int wID) {
		room = new Room[6];
		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				room[i] = new Room(1, i + 1);
			} else if (i == 1) {
				room[i] = new Room(2, i + 1);
			} else {
				room[i] = new Room(4, i + 1);
			}
		}
		wardID = wID;
	}

	/**
	 * @return the room
	 */
	public Room getRoom(int roomNo) {
		return room[roomNo];
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room, int roomNo) {
		this.room[roomNo] = room;
	}

	/**
	 * @return the wardID
	 */
	public int getWardID() {
		return wardID;
	}

	/**
	 * @param wardID the wardID to set
	 */
	public void setWardID(int wardID) {
		this.wardID = wardID;
	}

}

