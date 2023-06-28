package sbcallmanagement.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
//import sbcallmanagement.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String generateToken(String userId) {
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpiration * 1000);

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}












//@Component
//public class JwtUtil {
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private int expiration;
//
//	/*
//	 * @Autowired
//	 *  public String generateToken(User user) { Date now = new Date();
//	 * Date expiryDate = new Date(now.getTime() + expiration* 1000);
//	 * 
//	 * Key key = Keys.hmacShaKeyFor(secret.getBytes());
//	 * 
//	 * return Jwts.builder() .setSubject(user.getEmail()) .setIssuedAt(now)
//	 * .setExpiration(expiryDate) .signWith(key, SignatureAlgorithm.HS256)
//	 * .compact(); }
//	 * 
//	 * @Autowired
//	 *  public String getEmailFromToken(String token) { Claims claims =
//	 * Jwts.parser() .setSigningKey(secret.getBytes()) .parseClaimsJws(token)
//	 * .getBody();
//	 * 
//	 * return claims.getSubject(); }
//	 * 
//	 * @Autowired
//	 *  public boolean validateToken(String token, User user) { String
//	 * email = getEmailFromToken(token); return email.equals(user.getEmail()); }
//	 */
//    
//    public String generateToken(Long userId) {
//        String token = Jwts.builder()
//                .setSubject(userId.toString())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//
//        return token;
//    }
//
//    public Long extractUserId(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        String userIdStr = claims.getSubject();
//        return Long.parseLong(userIdStr);
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//}
