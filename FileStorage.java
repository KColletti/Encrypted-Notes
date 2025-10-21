
import java.io.*; //imports input and output (file reading and writing)
import java.util.ArrayList; //imports ArrayList (creates dynamic lists)
import java.util.List; //imports interface for List (for a more flexible arraylist)

//File Storage
//saves all notes

public class FileStorage { //saves and loads notes from the notes.txt file

    private static final String FILE_PATH="notes.txt"; //file where the data is actually stored

    //save notes (encrypted)
    public static void save (List<Note> notes, String username, String password) { //opens new notes and wont overwrite already existing ones

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true)))
        {
            for (Note note : notes) //for each note in the list, the note will be encrypted using the users password
            {
                String encrypted = Encryption.encrypt(note.getText(), password);
                writer.write(username + ":" + note.getTitle() + ":" + encrypted);
                writer.newLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    //load notes (need to decrypt)
    public static List <Note> load (String username) { //reads every line within the file

        List<Note> notes = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return notes;

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 3); //splits every lne seperated by :
                if (parts.length == 3)
                {
                    if (parts[0].equals(username)) // if the username matches the current user, it will add that note to the list
                    {
                        notes.add(new Note(parts[1], parts[2]));
                    }
                } else if (parts.length == 2)  //even if it only find 2 parts it will still add the note for compatability
                {
                    // backward-compatible: assume old notes belong to current user
                    notes.add(new Note(parts[0], parts[1]));
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return notes; //will return a List <Note> to the dashboard

        }
}
