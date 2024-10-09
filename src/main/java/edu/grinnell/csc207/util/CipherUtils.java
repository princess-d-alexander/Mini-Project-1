package edu.grinnell.csc207.util;

/**
 * Utility class for implementing classical encryption methods like Caesar and Vigenère ciphers.
 *
 * @author Princess Alexander
 * class: csc207
 *
 */
public class CipherUtils {

  /**
  * Constant for alphabet length (used in Caesar cipher).
  */
  private static final int ALPHABET_LENGTH = 26;

  /**
  * Converts a lowercase letter to its corresponding integer (0-25).
  * @param letter the letter to convert
  * @return the integer value corresponding to the letter
  */

  private static int letter2int(char letter) {
    return letter - 'a';
  } // letter2int

  /**
   * Converts an integer (0-25) to the corresponding lowercase letter.
   * @param i the integer to convert
   * @return the corresponding lowercase letter
   */

  private static char int2letter(int i) {
    return (char) ('a' + i);
  } // int2letter

    /**
     * Encrypts the string using the Caesar cipher.
     * @param str the string to encrypt
     * @param key the character key for the Caesar cipher
     * @return the encrypted string
     */
  public static String caesarEncrypt(String str, char key) {
    int shift = letter2int(key);
    StringBuilder result = new StringBuilder();

    for (char c : str.toCharArray()) {
      int originalPosition = letter2int(c);
      int newPosition = (originalPosition + shift) % ALPHABET_LENGTH; // Wrap around after 'z'
      result.append(int2letter(newPosition));
    } // for
    return result.toString();
  } // CaesarEncrypt

    /**
     * Decrypts the string using the Caesar cipher.
     * @param str the string to decrypt
     * @param key the character key for the Caesar cipher
     * @return the decrypted string
     */
  public static String caesarDecrypt(String str, char key) {
    int shift = letter2int(key);
    StringBuilder result = new StringBuilder();

    for (char c : str.toCharArray()) {
      int originalPosition = letter2int(c);
      int newPosition = (originalPosition - shift + ALPHABET_LENGTH) % ALPHABET_LENGTH;
      result.append(int2letter(newPosition));
    } // Wrap around before 'a'
    return result.toString();
  } // CaesarDecrypt

    /**
     * Encrypts a string using the Vigenère cipher with a key string.
     * @param str the string to encrypt
     * @param key the key string for the Vigenère cipher
     * @return the encrypted string
     */
  public static String vigenereEncrypt(String str, String key) {
    StringBuilder result = new StringBuilder();
    int keyLen = key.length();

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      char keyChar = key.charAt(i % keyLen);
      result.append(caesarEncrypt(Character.toString(ch), keyChar)); // Caesar encrypt with key
    } // for

    return result.toString();
  } // vigenereEncrypt

    /**
     * Decrypts a Vigenère cipher-encrypted string using a key string.
     * @param str the string to decrypt
     * @param key the key string for the Vigenère cipher
     * @return the decrypted string
     */
  public static String vigenereDecrypt(String str, String key) {
    StringBuilder result = new StringBuilder();
    int keyLen = key.length();

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      char keyChar = key.charAt(i % keyLen);
      result.append(caesarDecrypt(Character.toString(ch), keyChar)); // C-decrypt w/ each key letter
    } // for

    return result.toString();
  } // vigenereDecrypt
} // CipherUtils

