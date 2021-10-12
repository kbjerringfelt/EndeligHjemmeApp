package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO.IMHP;

public class Psychologist implements IMHP {
    private String name, specialty, insurance;
    private long zip;


    public Psychologist(){}

    public Psychologist(String name, String specialty, String insurance, int zip){
        this.name = name;
        this.specialty = specialty;
        this.insurance = insurance;
        this.zip = zip;
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
