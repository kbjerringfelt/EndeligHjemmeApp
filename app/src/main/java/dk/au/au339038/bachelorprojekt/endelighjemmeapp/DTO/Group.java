package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

public class Group {
    private String title, bdate, contact, place, description;
    private int phone;


    public Group(String title, String contact, String bdate, String place, String description){
        this.title = title;
        this.description = description;
        this.bdate = bdate;
        this.contact = contact;
        this.place = place;

    }

    public Group(){
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
