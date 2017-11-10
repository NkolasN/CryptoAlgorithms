

 /**
  * A toy implementation of the (now insecure) RC4 stream cipher.
  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
  * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
  * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY CLAIM,
  * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
  * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
  * @author NkolasN
  */
 public class RC4Implementation {
	
	private byte[] S = new byte[256];
	private byte[] T = new byte[256];
	
	/**
	 * Validates the key length and initialises vectors S and T 
	 * @param  The private Key
	 * @return None
	 */
	public RC4Implementation(byte[] key){
		validateKey(key);
		initialise(key);
	}
	
	
	/**
	 * Makes sure the key length is between 1-256 bytes
	 * @param key
	 * @return true if key is within range
	 */
	private boolean validateKey(byte[] key){
		
		if(key.length < 1 || key.length > 256){
			throw new IllegalArgumentException("Illegal Key Length: Key must be between 1 and 255 bytes. It was " + key.length + " bytes long");
		} else{
			return true;
		}
	}
	
	/**
	 * Initialise vectors S and T
	 * @param key
	 */
	private void initialise(byte[] key){
		
		for(int i = 0;i<256;i++){
			S[i] = (byte)i;
			T[i] = key[i % key.length];
		}

		int j = 0;
		for(int i = 0;i<256;i++){
			j = (j + (int)S[i] + T[i]) & 0xFF;
			swap(i,j);
		}
	}
	
		
	/**
	 * Encrypts a plaintext
	 * @param plaintext
	 * @return ciphertext as bytes
	 */
	public byte[] encrypt(byte[] plaintext){
	   
	    byte[] ciphertext = new byte[plaintext.length];
	    
	    int i = 0, j = 0;
	    for(int c = 0;c<plaintext.length;c++){
	    	i = i++ & 0xFF;
	    	j = (j + S[i]) & 0xFF;
	    	swap(i,j);
	    	int t = (S[i] + S[j]) & 0xFF;
	    	int k = S[t];

	    ciphertext[c] = (byte) (plaintext[c] ^ k);

	    }
	       return ciphertext;
	}
	   
	   
    	/**
     	* Decrypts a ciphertext by calling encrypt
     	* @param ciphertext
     	* @return plaintext
     	*/
    	public byte[] decrypt(byte[] ciphertext){
	    return encrypt(ciphertext);
	   
	}
   
    	/**
     	* Swaps two values in the state vector S based on index values
     	* @param index1
     	* @param index2
     	*/
    	private void swap(int index1,int index2){
	    byte temp = S[index1];
	    S[index1] = S[index2];
	    S[index2] = temp;
   	}
   	
 }
