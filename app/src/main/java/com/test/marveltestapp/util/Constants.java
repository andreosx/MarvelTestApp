package com.test.marveltestapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Constants {
    public static final String TS = "andreoliveira";
    public static final String BASE_URL = "https://gateway.marvel.com";
    public static final String PUBLIC_KEY = "f4f5e03c12cdcc5e5505881b7ec1cfee";
    public static final String PRIVATE_KEY = "2805dec96ad84b5fe74a12cde15ee6959ccccd4f";
    public static final int COLUMNS_VIEW_CHARACTERS = 2;
    public static final int COLUMNS_VIEW_COMICS = 1;

    public static String getHash(){
        return Constants.md5(TS+PRIVATE_KEY+PUBLIC_KEY);
    }

    private static String md5(String value) { try {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(value.getBytes());
        byte messageDigest[] = digest.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i=0; i<messageDigest.length; i++)
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
        return "";

    }
}
