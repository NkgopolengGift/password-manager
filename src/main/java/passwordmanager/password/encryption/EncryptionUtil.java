package passwordmanager.password.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";

    @Value("${encryption.key}")
    private String encryptionKeyProperty;

    private byte[] key;

    @PostConstruct
    private void initializeKey() {
        try {
            if (encryptionKeyProperty == null) {
                throw new IllegalArgumentException("Encryption key property is not set");
            }
            this.key = Base64.getDecoder().decode(encryptionKeyProperty);
            if (key.length != 16 && key.length != 24 && key.length != 32) {
                throw new IllegalArgumentException("Invalid AES key length: " + key.length);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize encryption key", e);
        }
    }

    public String encrypt(String data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    public String decrypt(String encryptedData) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }
}
