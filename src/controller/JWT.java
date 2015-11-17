package controller;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import model.Config;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWT {

    public static String createJWT(String userid, String username){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //Sets expiration to one hour from now
        long expMillis = nowMillis + 1000*60*60;
        Date exp = new Date(expMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Config.getJWTSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Sets claims
        Map claims = new HashMap<>();
        claims.put("userid", userid);
        claims.put("username", username);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .setIssuer("snakeserver")
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }


    public static void parseJWT(String jwt){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(Config.getJWTSecret()))
                            .parseClaimsJws(jwt).getBody();
        } catch (SignatureException e){
            e.printStackTrace();
        }





    }



//    public static void main(String[] args) {
//
//        try {
//            Config.init();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(createJWT("2" ,"awafowfkwpfowjf"));
//
//        parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0NDc3OTU1OTMsInVzZXJuYW1lIjoiYXdhZm93Zmt3cGZvd2pmIiwiaXNzIjoic25ha2VzZXJ2ZXIiLCJ1c2VyaWQiOiIyIiwiaWF0IjoxNDQ3NzkxOTkzfQ.qsTmzfQE1ZehNM9Um2LbX8Ddm_yAUeCiuk0LzF3oZgk");
//
//    }

}
