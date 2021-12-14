package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

//Råd. Matcher databasen
public class Advice {

    private String text;
    private String link;
    private String title;

    public Advice(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
