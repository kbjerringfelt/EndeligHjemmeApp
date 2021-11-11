package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pin{
    @PrimaryKey( autoGenerate = true)
    private int pid;

    private String uid;

    private int pin = 1000000;

    public Pin(int pin, String uid){
        this.uid = uid;
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
