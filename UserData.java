import java.io.*; //imports classes for reading and writing files

//UserData
//memory of each user

public class UserData {

    //constant file path that data will be stored in
    private static final String USER_FILE = System.getProperty("user.dir") + File.separator + "users.txt";

    //looks for specific user and returns a User object if found, if not return null
    public static User getUser(String username) {
        File file =new File (USER_FILE); //create file object that points to the users.txt file
        if (!file.exists()) //checks to see if file exists, if not returns null
        {
            return null;
        }

        //Using BufferedReader it opens file to read it (line by line)
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null) //keep reading till no more lines
            {
                String[] split = line.split(":", 2); //splits the line into 2 parts (username : password -> username, password)
                if (split.length == 2 && split[0].trim().equals(username.trim())) //if the line contains both parts and username is one that was being searched for then returns a new User object
                {
                    return new User(split[0].trim(), split[1].trim()); //return user object created from username and password from the file
                }
            }
        } catch (IOException e) //any errors while reading the file will print the stack
         {
            e.printStackTrace();
         }

        return null; // user not found after finishing the loop
    }

    //adds a new user to the users.txt fle
    //will return true if it is successful, false if not
    public static boolean addUser(User user) {

        // Prevent duplicates
        if (getUser(user.getUsername()) != null) //check if that username already exists -> calls getUser(), if it is found then it will return false
        {
            return false; // user already exists
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) //will open a file.. with true so it doesnt over write any existing text
        {
            String line = user.getUsername().trim() + ":" + user.getPassword().trim(); //creates a string in username : password
            writer.write(user.getUsername().trim() + ":" + user.getPassword().trim()); //puts the string into the file
            writer.newLine(); //adds a new line after each user
            writer.flush(); //saves automatically
            return true; //returns true when ran successfully
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false; //if error writing to the fle it will print the stack and return false
    }
}
