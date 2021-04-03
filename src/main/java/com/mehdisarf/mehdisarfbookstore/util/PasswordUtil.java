
package com.mehdisarf.mehdisarfbookstore.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Because Hash algorithm generate a fixed length array of bytes,
        // so u can't pass a string directly to them.

        // update() accept an array of bytes.
        messageDigest.update(password.getBytes());

        // digest() generate an array of bytes for the hashed pass
        byte[] messageDigestArray = messageDigest.digest();

        StringBuilder string = new StringBuilder(messageDigestArray.length * 2);

        /*
        conversely, u cant get a string directly from an array of bytes.
        to work around that , u can use a for loop
        to convert the array of bytes(here for the hashed password) to a string.
         */
        for (byte b : messageDigestArray) {
            int value = b & 0xff;
            if (value < 16) {
                string.append('0');
            }
            string.append(Integer.toHexString(value));
        }
        return string.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(PasswordUtil.hashPassword("salam"));
    }
}
