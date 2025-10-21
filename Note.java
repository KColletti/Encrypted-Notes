
//Note
//stores the actual note
public class Note { //declares Note
    private String title; //holds notes name
    private String text; //words inside of document

    //constructor
    public Note (String title, String text) { //constructor called when create a new note
        this.title= title;
        this.text=text;
    }

    //gets the title
    public String getTitle(){
        return title;
    }

    //gets the text
    public String getText(){
        return text;
    }

    @Override // allows it to return readably
    public String toString(){
        return title;
    }

}
