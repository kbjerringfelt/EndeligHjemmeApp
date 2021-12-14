package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Pinkode. Det der bliver puttet i room databasen
@Entity
public class Pin{
    @PrimaryKey( autoGenerate = false)
    @NonNull
    private String uid;

    private int wrongPinCount;

    private String pin = "1000000";

    public Pin(String pin, String uid, int wrongPinCount){
        this.uid = uid;
        this.pin = pin;
        this.wrongPinCount = wrongPinCount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getWrongPinCount() {
        return wrongPinCount;
    }

    public void setWrongPinCount(int wrongPinCount) {
        this.wrongPinCount = wrongPinCount;
    }

}
