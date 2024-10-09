/***********************************************************************
  * Name(s)
  * @author Princess Alexander
  * @author Samuel L. Rebelsky
  * Class: csc207
  * Assignment name (MP1)
  *
  * Date: 09/11/24
************************************************************************/

package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;


/**
 * A class that demonstrates encoding and decoding using the Caesar cipher with all possible shifts.
 */
public class AllCaesar {
    /**
     * Main method to handle encoding/decoding using Caesar cipher for all shifts from 'a' to 'z'.
     *
     * @param args Command-line arguments: action ("encode" or "decode") and the string to process.
     * @throws Exception If an error occurs during the operation.
     */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);


    // Validate arguments
    if (args.length != 2) {
      pen.println("Error: Incorrect number of parameters.");
      return;
    } // if

    String action = args[0];
    String str = args[1];


    // Validate action and string
    if (!(action.equals("encode") || action.equals("decode"))) {
      pen.println("Error: Invalid option: \"" + action + "\". Options: \"encode\" or \"decode\".");
      return;
    } // if


    if (!str.matches("[a-z]*")) {  // Allow empty strings, but no uppercase or symbols
      pen.println("Error: String contains characters other than lowercase letters.");
      return;
    } // if


    // Handle empty string case
    if (str.isEmpty()) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: \n", ch);
      } // for
      return;
    } // if


    // Perform the operation for all shifts
    for (char ch = 'a'; ch <= 'z'; ch++) {
      if (action.equals("encode")) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
      } else {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(str, ch));
      } // if
    } // for


    pen.close();
  } // main
} // AllCaesar

