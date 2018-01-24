package it.cosenonjaviste.tomcat;

import it.cosenonjaviste.crypto.BCrypt;
import org.apache.catalina.CredentialHandler;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.security.SecureRandom;

/**
 * <a href="https://it.wikipedia.org/wiki/Bcrypt">BCrypt</a> password encryption for Tomcat Realms
 *
 * Created by acomo on 23/01/18.
 */
public class BCryptoCredentialHandler implements CredentialHandler {

    private final Log logger = LogFactory.getLog(getClass());

    private int strength;

    private SecureRandom random;

    @Override
    public boolean matches(String inputCredentials, String storedCredentials) {
        if (inputCredentials == null || inputCredentials.length() == 0 ||
                storedCredentials == null || storedCredentials.length() == 0) {
            logger.warn("Empty plain or encoded password");
            return false;
        }

        return BCrypt.checkpw(inputCredentials, storedCredentials);
    }

    @Override
    public String mutate(String inputCredentials) {
        String salt;
        if (strength > 0) {
            if (random != null) {
                salt = BCrypt.gensalt(strength, random);
            }
            else {
                salt = BCrypt.gensalt(strength);
            }
        }
        else {
            salt = BCrypt.gensalt();
        }
        return BCrypt.hashpw(inputCredentials, salt);
    }

    public void setRandom(SecureRandom random) {
        this.random = random;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
