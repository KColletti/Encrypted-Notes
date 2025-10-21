
import javax.swing.*; //imports JFrame, JButton and JTextFields
import java.awt.*; // imports gui components (layout, color, font and sizing)


//login in
//username and password authentication

public class Login extends JFrame //need for gui {
    
    //uses swing
    private JTextField username; //username field
    private JPasswordField password; //password field (hides it)
    private JButton loginButton; //login button
    private JButton createAcctButton; //register if don't have an account

    public Login (){
        //visual of the window
        setTitle("Secure Notes- Sign In"); //gives the title of the notes app
        setSize(300,300); //across , up and down 200
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when close window program stops running
        setLocationRelativeTo(null); //centers the window

        //main window
        JPanel mainP= new JPanel (); //main window
        mainP.setLayout(new BoxLayout (mainP, BoxLayout.Y_AXIS));
        mainP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //Username- create the username display and input field
        JPanel userP= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        username =new JTextField(); //creates tet field user can see
        username.setPreferredSize(new Dimension(250,30)); //username input field
        userP.add(new JLabel("Username: "));
        userP.add(username); //actually adds the username field next to the label
        mainP.setBackground(new Color (230, 230, 250)); //give a pop of color for border

        //password- create the password display and input field
        JPanel passP = new JPanel(new FlowLayout(FlowLayout. LEFT, 10,2));
        password= new JPasswordField(); //creates a password input filed (gets hidden)
        password.setPreferredSize(new Dimension(250, 30)); //set size of password field
        passP.add(new JLabel("Password: "));
        passP.add(password); //actually adds the password field next to the label

        //login and register button
        JPanel buttonP = new JPanel(new FlowLayout(FlowLayout. CENTER, 5, 2)); //creats field space for login and register buttons
        loginButton = new JButton ("LOGIN"); //creates log in button
        createAcctButton= new JButton("REGISTER"); //creates register button
        buttonP.add(loginButton); //adds button on top of main window
        buttonP.add(createAcctButton); // adds button on top of main window

        //put all panels onto the main panel
        mainP.add(userP); //username
        mainP.add(passP); //password
        mainP.add(buttonP); //buttons

        add(mainP); //puts everything on the popup (window itself)

        controlLogin(); //call control login method which is the instructions of what happens when the login button gets clicked

        setVisible(true); //so it appears
    }

    //ensures username and password are correct
    private void controlLogin() { //sets up button functionaility
        // Adds the action listener to the login button, code inside will run when clicked
        loginButton.addActionListener(e -> {
            String userInput = username.getText().trim(); //gets username text
            String passInput = new String(password.getPassword()).trim(); //returns the password as a char [], so it can be then converted to a String

            if(userInput.isEmpty()||passInput.isEmpty()) //checks if either field is empty, if yes then the popup of please enter username and password, comes up and will stop running the rest of the code
            {
                JOptionPane.showMessageDialog(this, "Please Enter Username and Password");
                return;
            }

            //calls method from UserData to find a saved username and stores it in User
            User storedUser = UserData.getUser(userInput);

            if (storedUser != null && passInput.equals(storedUser.getPassword())) //if the username exists and the entered matches the saved one then go to dashboard if not then show a pop up saying wrong username or password entered
            {
                // Correct password = open dashboard
                dispose(); // close login window
                new Dashboard(storedUser);
            } else
            {
                // Wrong username or password, show pop up
                JOptionPane.showMessageDialog(this, "Incorrect username or password!");
                password.setText(""); // clear password field
            }
        });

        //when register button gets clicked it will close the login window and opens a new Create Account window
        createAcctButton.addActionListener(e->{
            dispose();
            new CreateAcct(); //calls create account
        });

    }
}
