import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

/**
 * @author Hungs
 * @date 2024/2/19
 * @Description Description of the file.
 */
public class JwtUtils {


    @Test
    public void generateToken() {
        long EXPIRATION_TIME = 86400000;
        String token = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setSubject("admin")
                .signWith(Keys.hmacShaKeyFor("E66559580A1ADF48CDD928516062F12E".getBytes(StandardCharsets.UTF_8)))
                .compact();

        System.out.println(token);
    }
}
