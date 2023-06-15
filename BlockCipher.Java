
package blockcipher;

/**
 *
 * @author alex
 */
public class BlockCipher {
    //***********************************************************************
    //Encodes a string of ascii characters to hex format using a block cipher.
//After the given initial vector each 8 bit block will be encrypted using the //previous 8 bits.
    //Program displays the final encrypted hex message.
    //***********************************************************************

    public static void main(String[] args) {
        String message = "HTTP/1.1"; //original message
        String key = "10001001"; //initial vector
        String key2 = key;
        String nextKey = ""; //key used for next block
        String messageBin = ""; //original message in binary
        String encryptedBin = ""; //encrypted binary message
        String encryptedHex = ""; //encrypted hex message
        System.out.println("***************************************************************************************\n"
                + "Wei Lu  CS455 Cryptography and Network Security\n"
                + "Midterm Project Programming Question 3 - BlockCipher.java\n"
                + "Encodes a string of ascii characters to hex format using a block cipher.\n"
                + "After the given initial vector each 8 bit block will be encrypted using the previous\n"
                + "8 bits.\n"
                + "Program displays the final encrypted hex message.\n"
                + "***************************************************************************************\n");

        System.out.println("Encrypting \"" + message + "\" using initial vector \"" + key + "\". \n");

        //convert original message to binary
        String[] binMessage = toArr(message);
        binMessage = toBinary(binMessage);
        System.out.println("The binaries are: ");
        out(binMessage);
        //Encrypted
        binMessage = xString(binMessage, key);
        System.out.println("The encrypted binaries are: ");
        out(binMessage);
//        To decrypt
        binMessage = yString(binMessage, key2);
        System.out.println("Decrypted");
        out(binMessage);
        //to hex
        binMessage = toHex(binMessage);
        System.out.println("The encrypted hex is: ");
        out(binMessage);
    }//end main()

    //complete
    public static String[] toArr(String st) {
        String[] thisArr = new String[st.length()];

        for (int x = 0; x < st.length(); x++) {
            thisArr[x] = st.substring(x, x + 1);
        }
        for (int i = 0; i < thisArr.length; i++) {
        }
        return thisArr;
    }

    //complete
    public static String[] toBinary(String[] bytes) {

        String[] thisArray = new String[bytes.length];

        for (int x = 0; x < bytes.length; x++) {
            thisArray[x] = toBinaryS(bytes[x]);
        }

        for (int x = 0; x < bytes.length; x++) {
            while (thisArray[x].length() < 9) {
                thisArray[x] = "0" + thisArray[x];
            }
//            System.out.print(thisArray[x]);
        }
        return thisArray;
    }

    //complete
    public static String toBinaryS(String text) {
        StringBuilder sb = new StringBuilder();

        for (char character : text.toCharArray()) {
            sb.append(Integer.toBinaryString(character) + "\n");
        }
        return sb.toString();
    }

    //complete
    public static String[] xString(String[] bytes, String key) {

        String[] outArr = new String[bytes.length];

        for (int x = 0; x < bytes.length; x++) {
            StringBuffer output = new StringBuffer();
            for (int y = 0; y < bytes[x].length() - 1; y++) {
                output.append(bytes[x].charAt(y) ^ key.charAt(y));
            }

            outArr[x] = output.toString();
            key = output.toString();
        }
        return outArr;
    }

    //complete
    public static String[] toHex(String[] bytes) {
        String[] outPut = new String[bytes.length];

        for (int x = 0; x < bytes.length; x++) {
            int decimal = Integer.parseInt(bytes[x], 0, 8, 2);
            String hexStr = Integer.toString(decimal, 16);
            outPut[x] = hexStr;
        }
        return outPut;
    }

    //complete
    public static String out(String[] bytes) {

        String output = "";

        for (int x = 0; x < bytes.length; x++) {
            output += bytes[x] + " ";
        }
        System.out.println(output + "\n");
        return output;
    }

    //complete
    public static String[] yString(String[] bytes, String key2) {

        String[] outArr = new String[bytes.length];

        for (int x = 0; x < bytes.length; x++){
            StringBuffer output = new StringBuffer();
            for (int y = 0; y < bytes[x].length(); y++) {
                output.append(bytes[x].charAt(y) ^ key2.charAt(y));
            }
            outArr[x] = output.toString();
            key2 = bytes[x];
        }
        return outArr;
    }

    //to binary from hex
        public static String[] toBinaryFHex(String[] bytes) {

        String[] thisArray = new String[bytes.length];

        for (int x = 0; x < thisArray.length; x++) {
            thisArray[x] = Integer.toBinaryString(Integer.parseInt(bytes[x], 16));
        }

        for (int x = 0; x < thisArray.length; x++) {
            while (thisArray[x].length() < 8) {
                thisArray[x] = "0" + thisArray[x];
            }
//            System.out.println(thisArray[x]);
        }
        return thisArray;
    }
}//end BlockCipher.java
