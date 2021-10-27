package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.Pin;

@Database(entities = {Pin.class}, version = 1)
public abstract class AppsDatabase extends RoomDatabase {

    public abstract PinDAO pinDAO();  //mandatory DAO getter
    private static AppsDatabase instance; //database instance for singleton

    public static AppsDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppsDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppsDatabase.class, "database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

}
