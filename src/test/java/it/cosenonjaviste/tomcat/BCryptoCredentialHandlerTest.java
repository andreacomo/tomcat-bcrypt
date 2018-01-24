package it.cosenonjaviste.tomcat;

import org.junit.Test;

import java.security.SecureRandom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by acomo on 23/01/18.
 */
public class BCryptoCredentialHandlerTest {

    private String plainPassword = "admin";

    private String encryptedPassword = "$2a$10$845J70oo3rVMMYuKvyeIquarpTQaN2wjleXLOO6aT8XCR6qathPK2";

    @Test
    public void shouldCheckPassword() {
        BCryptoCredentialHandler credentialHandler = new BCryptoCredentialHandler();

        assertTrue(credentialHandler.matches(plainPassword, encryptedPassword));
    }

    @Test
    public void shouldNotCheckPasswordBecauseInputIsNull() {
        BCryptoCredentialHandler credentialHandler = new BCryptoCredentialHandler();

        assertFalse(credentialHandler.matches(null, null));
        assertFalse(credentialHandler.matches("", null));
        assertFalse(credentialHandler.matches(plainPassword, null));
        assertFalse(credentialHandler.matches(null, ""));
        assertFalse(credentialHandler.matches(null, encryptedPassword));
    }

    @Test
    public void shouldEncryptPassword() {
        BCryptoCredentialHandler credentialHandler = new BCryptoCredentialHandler();

        assertThatEncrypt(credentialHandler);
    }

    @Test
    public void shouldEncryptPasswordWithStrength() {
        BCryptoCredentialHandler credentialHandler = new BCryptoCredentialHandler();
        credentialHandler.setStrength(4);

        assertThatEncrypt(credentialHandler);
    }

    @Test
    public void shouldEncryptPasswordWithSecureRandom() {
        BCryptoCredentialHandler credentialHandler = new BCryptoCredentialHandler();
        credentialHandler.setRandom(new SecureRandom());

        assertThatEncrypt(credentialHandler);
    }

    @Test
    public void shouldEncryptPasswordWithSecureRandomAndStrength() {
        BCryptoCredentialHandler credentialHandler = new BCryptoCredentialHandler();
        credentialHandler.setRandom(new SecureRandom());
        credentialHandler.setStrength(4);

        assertThatEncrypt(credentialHandler);
    }

    private void assertThatEncrypt(BCryptoCredentialHandler credentialHandler) {
        String encrypted = credentialHandler.mutate(plainPassword);
        assertTrue(credentialHandler.matches(plainPassword, encrypted));
    }
}