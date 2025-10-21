
import javax.swing.*; //imports JFrame, JButton and JTextFields = GUI
import java.awt.*; // imports gui components (layout, color, font and sizing)

//CreateAcct
//creates the account if the user doesnt already have one

public class CreateAcct extends JFrame { //this is createAccount interface
    private JTextField username; //text field for username
    private JPasswordField password; //text field for password
    private JButton createAcctButton; //button to create account
    private JButton backButton; //button to go back to the main login screen need be

    public CreateAcct (){// set up main create account window
        setTitle("Create Account");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when close this screen stop running this class
        setLocationRelativeTo(null); //will center the window on the screen

        // main window (container), vertical
        JPanel mainP =new JPanel();
        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
        mainP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); //border
        mainP.setBackground(new Color (230, 230, 250)); //give a pop of color for border


        //Username= sub window, aligned left
        JPanel userP= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel userLabel= new JLabel ("Username: ");
        username= new JTextField();
        username.setPreferredSize(new Dimension(250, 30));
        userP.add(userLabel);
        userP.add(username);


        //Password= sub window
        JPanel passP = new JPanel (new FlowLayout(FlowLayout. LEFT, 10,5));
        JLabel passLabel = new JLabel("Password: ");
        password= new JPasswordField(); //hides what the user types
        password.setPreferredSize(new Dimension(250,30));
        passP.add(passLabel);
        passP.add(password);

        //Button Ro= centered horizontally
        JPanel buttonP = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        createAcctButton=new JButton("Create Account");
        backButton=new JButton("Back");
        buttonP.add(createAcctButton);
        buttonP.add(backButton);

        //put everything onto main window
        mainP.add(userP);
        mainP.add(passP);
        mainP.add(buttonP);

        add(mainP); //add it to main frame window


        //Back button brings back to login screen and closes current window
        backButton.addActionListener(e-> {
            dispose();
            new Login ();
        });

        //Create Account Button - once created it will run helper method: controlCreateAcct()
        createAcctButton.addActionListener(e -> controlCreateAcct());

        setVisible(true); //to be able to see everything
    }

    //will obtain text entered from user and converts password into a string
    private void controlCreateAcct (){
        String user = username.getText().trim();
        String pass = new String(password.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) //make sure one of the fields are empty if so print for the user to enter, if not will exit early
        {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            return;
        }

        boolean success = UserData.addUser(new User(user, pass)); //attempt to save the new account created using the UserData class
        if (success) //addUser will return true if it was successfully saved
        {
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            dispose(); //will close create account window
            new Login(); //open login class
        } else //addUser will return false if it was not successfully saved
        {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        }
    }
}
