package kwan.ie.listpostsbyranking.util;

import javafx.util.Pair;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthentication {
    public static Pair<String, String> decode(String basicToken){
        String token = basicToken.split(" ")[1];
        String userPwd = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        String[] arr = userPwd.split(":");
        return new Pair<>(arr[0], arr[1]);
    }

    public static String encode(String username, String password){
        String userPwd = username + ":" + password;
        byte[] pwdEncoded = Base64.getEncoder().encode(userPwd.getBytes(StandardCharsets.UTF_8));
        return new String(pwdEncoded, StandardCharsets.US_ASCII);
    }
}
