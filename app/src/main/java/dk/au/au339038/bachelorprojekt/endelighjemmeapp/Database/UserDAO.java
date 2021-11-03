package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    LiveData<User> getUser();

    //@Update
    //void updateUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
}
