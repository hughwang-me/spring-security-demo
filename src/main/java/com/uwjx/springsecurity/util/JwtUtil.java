package com.uwjx.springsecurity.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

    public static void main(String[] args) {
        gen();
        log.warn("===================================================================================");
        valid();
    }

    public static  void gen(){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id" , "27")
                    .withClaim("name" , "wanghuan")
                    .withExpiresAt(new Date())
                    .sign(algorithm);
            log.warn("token-> {}" , token);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    public static  void valid(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsIm5hbWUiOiJ3YW5naHVhbiIsImlkIjoiMjciLCJleHAiOjE1OTY3OTk1MzN9.Q4rWabC9WJgGqmI8bljYk4TYgp6MeGuwnUqQ5gimAmI";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            log.warn("jwt -> {}" , jwt.toString());

            log.warn("jwt -> {}" , jwt.getHeader());
            log.warn("jwt -> {}" , jwt.getPayload());
            log.warn("jwt -> {}" , jwt.getSignature());
            log.warn("jwt -> {}" , jwt.getToken());

            log.warn("jwt -> {}" , jwt.getClaim("id").asString());
            log.warn("jwt -> {}" , jwt.getClaim("name").asString());

            log.warn("jwt -> {}" , jwt.getExpiresAt().toString());
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            log.error("exception : {}" , exception.getMessage());
        }
    }
}
