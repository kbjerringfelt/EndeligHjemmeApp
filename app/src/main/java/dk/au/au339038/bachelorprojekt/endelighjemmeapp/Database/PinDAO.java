package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;
import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;

@Dao
public interface PinDAO {

    @Query("SELECT * FROM Pin")
    LiveData<Pin> getPin();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPin(Pin pin);
}
