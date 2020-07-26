package com.ne.notification.util;


import com.ne.notification.configuration.resources.ApplicationProperties;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Predicate;

@ComponentScan(value = "com.ne.notification.model.repository")
public class CommonUtilities {


    public static String changePercent(String oldPrice, String newPrice) {
        Long val1 = Long.valueOf(oldPrice);
        Long val2 = Long.valueOf(newPrice);
        DecimalFormat df = new DecimalFormat("###.###");
        return df.format(((val2 - val1) / (double) val1) * 100);
    }


    public static String changePercent(float oldPrice, float newPrice) {
        DecimalFormat df = new DecimalFormat("###.###");
        return df.format(((newPrice - oldPrice) / (double) oldPrice) * 100);
    }

    public static String HmacSHA256(String secret, String message) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String hash = Hex.encodeHexString(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(hash);
            return hash;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String createMessageByTemplate(String message, String templateContent) {
        try {

            List<Object> args = new ArrayList<Object>();
            String[] param = message.split(",");
            for (String s : param) {
                args.add(s);
            }
            return String.format(templateContent, args.toArray());

        } catch (Exception ex) {
            return message;
        }
    }

    public static String generateRandom() {
        String aToZ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randIndex = rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        }
        return res.toString();
    }

    public static char[] OTP(int len) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }



}
