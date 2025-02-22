package com.epicode.GestioneEventiEsSettimanale.security.jwt;


import com.epicode.GestioneEventiEsSettimanale.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;



@Component
public class JwtUtils {



   @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirations;

    public String creaJwtToken(Authentication autenticazione){

        UserDetailsImpl utentePrincipal = (UserDetailsImpl) autenticazione.getPrincipal();

        return Jwts.builder()
                .setSubject(utentePrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirations))
                .signWith(recuperoChiave(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String recuperoUsernameDaToken(String token){
        return Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parseClaimsJwt(token).getBody().getSubject();
    }

    public Date recuperoScadenzaDaToken(String token){
        return Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parseClaimsJwt(token).getBody().getExpiration();
    }

    public boolean validazioneJwtToken(String token){
        Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parse(token);
        return true;
    }

    public Key recuperoChiave(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public String generateJwtToken(Authentication authentication) {
        return null;
    }
}