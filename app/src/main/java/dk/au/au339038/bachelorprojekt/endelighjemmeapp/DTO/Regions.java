package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

import java.util.ArrayList;
//Regioner. Matcher databasen
public class Regions {

    private String region;
    private ArrayList<String> communities;

    public Regions(){}

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<String> getCommunities() {
        return communities;
    }

    public void setCommunities(ArrayList<String> communities) {
        this.communities = communities;
    }
}
