package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.IMHP;

public class Psychologist implements IMHP {
    private String name, specialty, insurance, city, disability, education, language, moreinfo, sex;
    private long zip, phone, price;


    public Psychologist(){}

    public Psychologist(String name, String specialty, String insurance, String city){
        this.name = name;
        this.specialty = specialty;
        this.insurance = insurance;
        this.city = city;


    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMoreinfo() {
        return moreinfo;
    }

    public void setMoreinfo(String moreinfo) {
        this.moreinfo = moreinfo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getInsurance() {
        return insurance;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getName(){
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
