package com.moviles.practica.marvel.retrofit;

import java.sql.Timestamp;
import java.util.Date;

import retrofit.RequestInterceptor;


public class MarvelRequestInterceptor implements RequestInterceptor {

    private static final String PUBLIC_KEY = "3e72481c635522f1dc8a99c9f4f5cfff";
    private static final String PRIVATE_KEY = "4d3c602baacf0a75478d2e84fc569e643d769660";

    @Override
    public void intercept(RequestFacade request) {
        String timeStamp = generateTimestamp();
        request.addEncodedQueryParam("apikey", PUBLIC_KEY);
        request.addEncodedQueryParam("ts", timeStamp);
        request.addEncodedQueryParam("hash", generateMarvelHash(timeStamp, PRIVATE_KEY, PUBLIC_KEY));
    }

    private String generateTimestamp() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return String.valueOf(timestamp.getTime());
    }

    private String generateMarvelHash(String timeStamp, String privateKey, String publicKey) {
        return MD5(timeStamp + privateKey + publicKey);
    }


    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
