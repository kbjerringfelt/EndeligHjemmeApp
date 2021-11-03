package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class User {
    //Christina, barn 8 uger, hedder julie, født 28+4.

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private int pincode = 1000000;
    private String name;
    private String childName;
    private String birthDate;
    private String dueDate;
    private String id;

    public User(int pincode, String name, String childName, String id, String birthDate, String dueDate){
        this.name = name;
        this.childName = childName;
        this.id = id;
        this.pincode = pincode;
        this.dueDate = dueDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
