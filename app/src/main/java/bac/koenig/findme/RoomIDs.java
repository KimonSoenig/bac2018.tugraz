package bac.koenig.findme;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RoomIDs")
public class RoomIDs {

    @PrimaryKey
    private int room_id;

    private String building;

    private int floor;

    private String room;

    private String position;


    //Creates public RoomIDs method to get Objects of this class instead of values
    public RoomIDs(int room_id, String building, int floor,String room, String position) {
        this.room_id = room_id;
        this.building = building;
        this.floor = floor;
        this.room = room;
        this.position = position;
    }

    //Getter and Setters for every value
    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    //populateData methods sets our Values so it can be called with a callback
    public static RoomIDs[] populateData(){
        return new RoomIDs[] {
                new RoomIDs(1,"Mosaik", 1, "Eingang", "Eingang"),
                new RoomIDs(2,"Mosaik", 1, "Flur", "Nahe Eingang"),
                new RoomIDs(3,"Mosaik", 1, "Flur", "Rechts an Treppe")
        };
    }
}
