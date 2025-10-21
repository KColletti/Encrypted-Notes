
import javax.swing.*; //imports JFrame, JButton and JTextFields =GUI
import java.awt.*; // imports gui components (layout, color, font and sizing)
import java.util.ArrayList; //imports place to store notes in collection
import java.util.List; //imports place to store notes in collection

//Dashboard
//can add or view notes

public class Dashboard extends JFrame{
    private JTextArea notesSection; //notes area
    private JButton lockButton; //encrytion
    private JButton unlockButton; //decrypt
    private JButton saveButton; // save
    private User current; //current user

    public Dashboard(User user){ //constructor
        this.current=user; //saves logged-in user so can use their username and password in future

        setTitle("Time To Type Away");
        setSize(500,400); //dimensions of pad
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop running once closed
        setLocationRelativeTo(null);

        //main popup = hold all parts
        JPanel mainP=new JPanel();
        mainP.setLayout((new BorderLayout(10,10)));
        mainP.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        mainP.setBackground(new Color(230, 230, 250));

        //notes area
        notesSection = new JTextArea(); //area to put the text for the notes
        notesSection.setLineWrap(true); //automatically wraps to the next line
        notesSection.setWrapStyleWord(true);//controls how the wrapping happens, it its true then it wraps at word boundaries (so words to get cut in half)
        JScrollPane scroll =new JScrollPane(notesSection); //allows user to scroll up and down
        mainP.add(scroll, BorderLayout.CENTER);

        //Buttons
        JPanel buttonP=new JPanel(new FlowLayout(FlowLayout.CENTER, 15,15)); //creates row of buttons up top , and centers horizontally
        buttonP.setBackground(mainP.getBackground());

        lockButton=new JButton("Lock");//makes lock button
        unlockButton=new JButton ("Unlock"); //makes unlock button
        saveButton=new JButton("Save"); //makes save button

        //add everything to main window
        buttonP.add(lockButton);
        buttonP.add(unlockButton);
        buttonP.add(saveButton);
        mainP.add(buttonP, BorderLayout.NORTH);
        add(mainP);

        //what the buttons do

        //lock - will encrypt the current note
        lockButton.addActionListener(e-> {
            String etext= notesSection.getText();
            if(!etext.isEmpty())
            {
                String encrypt=Encryption.encrypt(etext, current.getPassword());
                notesSection.setText(encrypt);
            }
        });

        //unlock - decrypt current note by using the users current password
        unlockButton.addActionListener(e ->{
            String encrypted=notesSection.getText();
            if(!encrypted.isEmpty())
            {
                //prompt user for their password
                String inputPass= JOptionPane.showInputDialog(this, "Enter your password to unlock: ");

                //if input is correct it will decrypt if its wrong an error message will be shown
                if(inputPass !=null && !inputPass.isEmpty()) //user didn't cancel
                {

                    try
                    {
                        String decrypted = Encryption.decrypt(encrypted, inputPass);
                        if (decrypted != null)
                        {
                            notesSection.setText(decrypted);
                        } else
                        {
                            JOptionPane.showMessageDialog(this, "Incorrect password! Cannot decrypt!");
                        }
                    } catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(this, "Incorrect password! Cannot decrypt!");
                    }
                }
            }
        });

        //save button - will take the text in the notes section and will wrap it in a Note object, while creatiing a List
        saveButton.addActionListener(e -> {
            String text=notesSection.getText();
            if(!text.isEmpty())
            {
                Note note= new Note("My Note", text);
                List <Note> notes = new ArrayList<>();
                notes.add(note);
                FileStorage.save(notes,current.getUsername(), current.getPassword()); //write the note to a file to that specific user
                JOptionPane.showMessageDialog(this, "Note Save Successfully!"); //if saved successfully a pop up will show
            }
        });

        //load already saved notes: will check if there are any notes saved under that user using FileStorage.load()
        List <Note> loaded = FileStorage.load(current.getUsername());
        if(!loaded.isEmpty()) //if a note exists then it gets diplayed in the text area
        {
            notesSection.setText(loaded.get(0).getText());
        }else //if not then the text area is left blank for the user to start typing
        {
            notesSection.setText(" ");
        }

        setVisible(true); //make everything visible
    }
}
