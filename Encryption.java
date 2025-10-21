
import javax.crypto.Cipher; //encryption and decryption (scrambles and unscrambles text)
import javax.crypto.spec.SecretKeySpec; //converts password into secret key
import java.util.Base64; //converts the converted back to regular

//encrypt the notes

public class Encryption {
    public static String encrypt(String plainText, String password) { //turn readable notes into secure scrambled text

        try //incase an error gets thrown the whole thing doesn't crash
        {
            byte [] key =password.getBytes("UTF-8"); //converts letters into raw bytes to be able to encypt
            key =padKey(key); //add or trim the key to 16 bytes

            SecretKeySpec secretKey = new SecretKeySpec(key, "AES"); //turns the array into a formal AES key object
            Cipher c = Cipher.getInstance("AES"); //create the cipher (to encrpyt and decrypt)
            c.init(Cipher.ENCRYPT_MODE, secretKey); //uses the secretKey and encrypts it

            byte [] encypted =c.doFinal(plainText.getBytes()); //scrambles
            return Base64.getEncoder().encodeToString(encypted); //gets the Base64 encoder and converts the byte array back into a normal string

        }catch (Exception e) //runs only if the try fails
        {
            e.printStackTrace(); //print error message where it happpend
            return null; //it will return null instead of crashing the system
        }

    }

    //decrypts
    public static String decrypt (String encrypt, String password) {//turn encrypted text back into readable form- ONLY if correct password gets entered
        try{
            byte [] key= password.getBytes("UTF-8"); //converts letters into raw bytes to be able to encypt
            key= padKey(key);  //add or trim the key to 16 bytes

            SecretKeySpec secretKey =new SecretKeySpec(key, "AES"); //turns the array into a formal AES key object
            Cipher c = Cipher.getInstance("AES"); //create the cipher (to encrpyt and decrypt)
            c.init(Cipher.DECRYPT_MODE, secretKey); //uses the secretKey and decrypts it

            byte[] decoded = Base64.getDecoder().decode(encrypt); //decodes it back into raw bytes before decrypting
            byte[] decrypted =c.doFinal(decoded); //cipher actually decrypts the data using the AES key
            return new String (decrypted); //converts the decrypted bytes back into a readable string
        }catch (Exception e)
        {
            e.printStackTrace(); //print error message where it happpend
            return null; //it will return null instead of crashing the system
        }
    }

    //ensures the AES key is exactly 16 bytes
    private static byte [] padKey (byte [] key){
        byte [] newKey = new byte [16]; //create array that is 16 bytes long since AES encryption requires keys of 16 bytes
        for (int i =0; i<newKey.length; i++) //loops through all 16 positions
        {
            if (i<key.length) //if original password is long enough then copy that byte
            {
                newKey [i] =key[i];
            }else //if password it shorter han fill it with zeros
            {
                newKey[i]=0; //pad with zeros
            }
        }
        return newKey; //return the final 16-byte array that is ready to encrypted
    }
}
