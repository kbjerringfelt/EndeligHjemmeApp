package dk.au.au339038.bachelorprojekt.endelighjemmeapp.DTO;

public class Group {
    private String groupName;
    private String groupDescription;
    private String groupEMail;

    public Group(String name, String email, String description){
        this.groupName = name;
        this.groupDescription = description;
        this.groupEMail = email;
    }

}
