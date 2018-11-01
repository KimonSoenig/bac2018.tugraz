package bac.koenig.findme;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RoomDataAccessObject
{

    @Query("SELECT * FROM RoomIDS")
    List<RoomIDs> getAll();

    //Inserts all Tables
    @Insert
    void insertAll(RoomIDs... roomIDs);



}
