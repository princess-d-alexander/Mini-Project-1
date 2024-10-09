package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Main class for running cipher encoding/decoding.
 * Handles Caesar and Vigenère cipher operations.
 *
 * @author Princess Alexander
 *
 */
public class Cipher {
    /**
    * Constant for the expected number of command-line arguments.
    */
  private static final int EXPECTED_NUM_PARAMS = 4;

    /**
     * Main method to parse command-line arguments and perform cipher operations.
     *
     * @param args Command-line arguments: action (-encode/-decode), cipher type
     *             (-caesar/-vigenere), string to process, and cipher key.
     * @throws Exception If an error occurs during the operation.
     */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Validate that there are the expected number of arguments.
    if (args.length != EXPECTED_NUM_PARAMS) {
      pen.println("Error: Expected " + EXPECTED_NUM_PARAMS + "parameters, received" + args.length);
      return;
    } // if

    String action = null;
    String cipher = null;
    String str = null;
    String key = null;

    // Parse arguments (in any order)
    for (String arg : args) {
      if (arg.equals("-encode") || arg.equals("-decode")) {
        action = arg;
      } else if (arg.equals("-caesar") || arg.equals("-vigenere")) {
        cipher = arg;
      } else if (str == null) {
        str = arg;
      } else if (key == null) {
        key = arg;
      } // if
    } // for

    // Handle the case where the string is empty
    if (str != null && str.isEmpty()) {
    // No output for empty strings
      return;
    } // if

    // Check if the string contains only lowercase letters
    if (str != null && !str.matches("[a-z]*")) {
      pen.println("Error: Strings must be only lowercase letters.");
      return;
    } // if

    // Validate action and cipher
    if (action == null || (!action.equals("-encode") && !action.equals("-decode"))) {
      pen.println("Error: No valid action specified. Legal values are '-encode' and '-decode'.");
      return;
    } // if

    if (cipher == null || (!cipher.equals("-caesar") && !cipher.equals("-vigenere"))) {
      pen.println("Error: No valid cipher specified. Legal values are '-caesar' and '-vigenere'.");
      return;
    } // if

    // Perform encryption/decryption based on the cipher type
    if (cipher.equals("-caesar")) {
      if (key == null || key.length() != 1) {
        pen.println("Error: Caesar ciphers require a one-character key.");
        return;
      } // if

      char caesarKey = key.charAt(0);
      if (action.equals("-encode")) {
        pen.println(CipherUtils.caesarEncrypt(str, caesarKey));
      } else {
        pen.println(CipherUtils.caesarDecrypt(str, caesarKey));
      } // if

    } else if (cipher.equals("-vigenere")) {
      if (key == null || key.isEmpty()) {
        pen.println("Error: Empty keys are not permitted.");
        return;
      } // if

      // Proceed with encoding or decoding
      if (action.equals("-encode")) {
        pen.println(CipherUtils.vigenereEncrypt(str, key));
      } else {
        pen.println(CipherUtils.vigenereDecrypt(str, key));
      } // if-else
    } // else-if

    pen.close();
  } // main
} // Cipher

