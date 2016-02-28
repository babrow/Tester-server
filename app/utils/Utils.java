package utils;

import play.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by babrow on 07.02.2016.
 */
public class Utils {
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T extends Number> T parseNumber(String stringValue, Class<T> clazz) {
        return parseNumber(stringValue, clazz, null);
    }

    public static <T extends Number> T parseNumber(String stringValue, Class<T> clazz, T defaultValue) {
        try {
            String val = stringValue.replace(",", ".");
            return clazz.getDeclaredConstructor(String.class).newInstance(val);
        } catch (Exception e) {
            Logger.error("Error parsing " + clazz + " from value=" + stringValue);
        }
        return defaultValue;
    }
}
