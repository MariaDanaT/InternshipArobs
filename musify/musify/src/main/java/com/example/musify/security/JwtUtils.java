package com.example.musify.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.data.util.Pair;

import java.util.Calendar;
import java.util.Date;

public class JwtUtils {
    private static String signatureSecret = "myMusifyApp";
    private static String issuer = "musify";
    public static String generateToken(int userId, String email, String role){
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();

        c.add(Calendar.MINUTE, 30);
        Date expireDate = c.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(issuer)
                .withIssuedAt(currentDate)
                .withExpiresAt(expireDate)
                .withClaim("userId", userId)
                .withClaim("email", email)
                .withClaim("role", role)
                .sign(algorithm);
    }
    public static Pair<Integer, String> validateToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(signatureSecret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withSubject(issuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(jwtToken);
        Integer userId = decodedJWT.getClaim("userId").asInt();
        String role = decodedJWT.getClaim("role").asString();

        return Pair.of(userId, role);
    }
}
