package jp.co.scsk.miniapp.sample.api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    /** Token issuer */
    private static final String ISSUER = "SCSK";

    /** Encrypt key */
    private static final String encryptKey = "MiniAppSampleApi";
    /** Encrypt algorithm */
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(encryptKey);

    private static final String JWT_CLAIM_USER_ID = "openUserId";
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

    public static String generateJWTToken(Long userId) {
        Date issueTime = new Date();
        LOGGER.info("=====Generate Token For ID "+userId+" ====Issue At " + issueTime);

        return JWT.create()
                .withIssuer(ISSUER)
                // Issued Time
                .withIssuedAt(issueTime)
                // Claim payload
                .withClaim(JWT_CLAIM_USER_ID, userId)
                .sign(ALGORITHM);
    }

    private static DecodedJWT getTokenInfo(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    public static Long getUserId(String token) {
        return getTokenInfo(token).getClaim(JWT_CLAIM_USER_ID).asLong();
    }

}
