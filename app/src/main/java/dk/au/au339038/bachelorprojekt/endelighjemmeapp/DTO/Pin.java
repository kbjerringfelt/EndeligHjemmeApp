package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pin {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private int pincode;

    public Pin(int pincode){
        this.pincode = pincode;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
