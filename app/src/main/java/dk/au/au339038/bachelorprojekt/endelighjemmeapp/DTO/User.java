package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


public class User {
    //Christina, barn 8 uger, hedder julie, født 28+4.

    private String name;
    private String babyname;
    private String babybdate;
    private String duedate;
    private String bweek;
    private String id;
    private String area;

    public User(){}

    public User(String name, String babyname, String babybdate, String dueDate, String bweek, String id, String area){
        this.name = name;
        this.babyname = babyname;
        this.babybdate = babybdate;
        this.duedate = dueDate;
        this.bweek = bweek;
        this.area = area;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBabyname() {
        return babyname;
    }

    public void setBabyname(String babyname) {
        this.babyname = babyname;
    }

    public String getBabybdate() {
        return babybdate;
    }

    public void setBabybdate(String babybdate) {
        this.babybdate = babybdate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getBweek() {
        return bweek;
    }

    public void setBweek(String bweek) {
        this.bweek = bweek;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
