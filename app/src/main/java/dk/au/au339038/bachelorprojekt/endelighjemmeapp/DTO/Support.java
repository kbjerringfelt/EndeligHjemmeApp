package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

public class Support {
    private int phone;
    private String phoneHours;

    public Support(){}

    public Support(int phone, String phoneHours){
        this.phone = phone;
        this.phoneHours = phoneHours;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPhoneHours() {
        return phoneHours;
    }

    public void setPhoneHours(String phoneHours) {
        this.phoneHours = phoneHours;
    }
}
