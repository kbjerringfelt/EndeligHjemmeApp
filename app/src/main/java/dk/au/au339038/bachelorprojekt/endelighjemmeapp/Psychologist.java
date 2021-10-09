package dk.au.au339038.bachelorprojekt.endelighjemmeapp;

public class Psychologist implements IMHP {
    private String name, specialty, insurance;
    private int zip;



    public Psychologist(String name, String specialty, String insurance, int zip){
        this.name = name;
        this.specialty = specialty;
        this.insurance = insurance;
        this.zip = zip;
    }



    public String getName(){
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
