
//User
//stores multiple users information
public class User { //declares user class
    private String username; //store users name
    private String password; //store users password

    public User(String username, String password){ //constructor, to create each user
        this.username=username;
        this.password=password;
    }

    //returns the stored username
    public String getUsername(){
        return username;
    }

    //return the stored password
    public String getPassword(){
        return password;
    }

    //check password is correct entered= stored
    public boolean check(String input){
        return this.password.equals(input); //will return true or false so Login class can verify
    }
}
