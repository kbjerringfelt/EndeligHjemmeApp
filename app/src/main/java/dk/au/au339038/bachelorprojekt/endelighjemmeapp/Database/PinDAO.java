package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;

@Dao
public interface PinDAO {

    @Query("SELECT * FROM pin")
    LiveData<Pin> getPin();

    @Update
    void updatePin(Pin pin);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPin(Pin pin);
}
