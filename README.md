# Encrypted-Notes
A secure notes program built in Java that uses AES encryption and user authentication to protect saved notes.

## Features 
- User registration and login system
- AES-based encrytption and decryption of notes
- Save and load notes securley from local files
- Java Swing GUI

## Technologies 
- Java
- Swing for GUI
- AES encryption
- File input/ output

## Project Structure 
- Login.java = user authentication (verifies username and password)
- CreateAcct.java = handles new user registration
- Dashboard.java = main interface for creating/ managing notes
- Encryption.java = AES encryptiion and decryption logic
- FileStorage.java = handles saving and loading encyrpted files
- UserData.java = handles user account information
- User.java = represents user data
- Note.java = represents note data
- screenshots = demo images

## Screenshots
https://github.com/KColletti/Encrypted-Notes/tree/main/screenshots 

- Sign In
- Create Account
- Account Created Successfully
- Normal Text (input from user) 
- Encyrpted Text within Note Pad 
- Note Saved
- Incorrect Password

## How to Run 
- Compile and Run the files in any Java IDE or terminal
- Create an account, log in, and start writing! Users can encrypt their notes and later unlock them with their password. To save, simply click Save, and the note can be reopened anytime.


