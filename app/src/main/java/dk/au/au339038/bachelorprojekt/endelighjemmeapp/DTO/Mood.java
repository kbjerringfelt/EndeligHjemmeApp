package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

public class Mood {
    private int mood;
    //private String date;

    public Mood(){

    }

    public Mood(int mood){
        this.mood = mood;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

}
