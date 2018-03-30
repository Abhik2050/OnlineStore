package com.onlinestore.authentication;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm; 

public class JwtToken {
	   

	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	  static final String SECRET = "ThisIsASecret";
	  static final String TOKEN_PREFIX = "Bearer";
	  static final String HEADER_STRING = "Authorization"; 
	//Sample method to construct a JWT
 public static String addAuthentication(String username) {
		    String JWT = Jwts.builder()
		        .setSubject(username)
		        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
		        .signWith(SignatureAlgorithm.HS512, SECRET)
		        .compact(); 
		    return JWT;
		  }
 public static String doAuthentication(String token) {
	 String user=null;
 if (token != null) {
   // parse the token.
  user = Jwts.parser()
       .setSigningKey(SECRET)
       .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
       .getBody()
       .getSubject();

	}
 return user;
 }
}

