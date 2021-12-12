package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;

@Dao
public interface PinDAO {

    @Query("SELECT * FROM Pin")
    LiveData<Pin> getPin();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPin(Pin pin);

    @Update
    void updatePin(Pin pin);

    @Delete
    void deletePin(Pin pin);
}
