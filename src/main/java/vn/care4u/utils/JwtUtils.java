package vn.care4u.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import vn.care4u.enumeration.ERole;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration.ms}")
	private long jwtExpirationMs;
	
	@Value("${jwt.refresh.expiration.ms}")
	private long jwtRefreshExpirationMs;

	public String generateToken(String username, ERole role) {
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder()
				.setSubject(username)
				.claim("role", role.name())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
	
	public String generateRefreshToken(String username) {
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs * 24)) // Refresh token có thời hạn dài hơn
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}

	// Lấy username từ token
	public String getUsernameFromJwtToken(String token) {
		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build().parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	// Xác thực token (cập nhật để dùng parserBuilder)
	public boolean validateJwtToken(String token) {
		try {
			Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			System.err.println("Invalid JWT signature: " + e.getMessage());
			return false;
		}
	}

	public byte[] getSecretKeyBytes() {
		return jwtSecret.getBytes(StandardCharsets.UTF_8);
	}
	
	public long getJwtExpirationMs() {
		return jwtExpirationMs;
	}
	public long getJwtRefreshExpirationMs() {
		return jwtRefreshExpirationMs;
	}
}
