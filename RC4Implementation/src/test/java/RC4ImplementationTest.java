import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests successful encryption and decryption
 * @author NkolasN
 *
 */
public class RC4ImplementationTest {

   byte[] plaintext;
   byte[] key = new byte[16];
	
   @Before
   public void configure(){
      SecureRandom secureRandom=new SecureRandom();
      secureRandom.nextBytes(key);
      plaintext = "hello world!".getBytes();
   }


   @Test
   public void successfulEncryptionDecryption() throws UnsupportedEncodingException{
      byte[] ciphertext = new RC4Implementation(key).encrypt(plaintext);
      String decryptedPlaintext = new String(new RC4Implementation(key).decrypt((ciphertext)),"UTF-8");
      assertEquals("hello world!",decryptedPlaintext);
   }

}
