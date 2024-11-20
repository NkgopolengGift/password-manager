package passwordmanager.password.service;

import passwordmanager.password.entity.Password;
import passwordmanager.password.repository.PasswordRepository;
import passwordmanager.users.entity.User;
import passwordmanager.password.encryption.EncryptionUtil;
import passwordmanager.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passwordmanager.password.dto.PasswordRequest;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionUtil encryptionUtil;

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/{}~";

    private static final SecureRandom RANDOM = new SecureRandom();

    public Password createPassword(PasswordRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Password password = new Password();
        password.setUser(user);
        password.setServiceName(encryptField(request.getServiceName()));
        password.setServiceWebsite(encryptField(request.getServiceWebsite()));
        password.setServiceEmail(encryptField(request.getServiceEmail()));
        password.setServiceUsername(encryptField(request.getServiceUsername()));
        password.setServicePassword(encryptField(request.getServicePassword()));
        return passwordRepository.save(password);
    }

    public List<Password> getPasswordsByUserId(UUID userId) {
        List<Password> passwords = passwordRepository.findByUser_UserIdAndIsActiveTrue(userId);
        passwords.forEach(this::decryptFields);
        return passwords;
    }

    public Password updatePassword(UUID passwordId, PasswordRequest request) {
        Password password = passwordRepository.findById(passwordId)
                .orElseThrow(() -> new IllegalArgumentException("Password not found"));

        password.setServiceName(encryptField(request.getServiceName()));
        password.setServiceWebsite(encryptField(request.getServiceWebsite()));
        password.setServiceEmail(encryptField(request.getServiceEmail()));
        password.setServiceUsername(encryptField(request.getServiceUsername()));
        password.setServicePassword(encryptField(request.getServicePassword()));
        return passwordRepository.save(password);
    }

    private String encryptField(String field) {
        try {
            return field != null ? encryptionUtil.encrypt(field) : null;
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    private String decryptField(String field) {
        try {
            return field != null ? encryptionUtil.decrypt(field) : null;
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }

    private void decryptFields(Password password) {
        password.setServiceName(decryptField(password.getServiceName()));
        password.setServiceWebsite(decryptField(password.getServiceWebsite()));
        password.setServiceEmail(decryptField(password.getServiceEmail()));
        password.setServiceUsername(decryptField(password.getServiceUsername()));
        password.setServicePassword(decryptField(password.getServicePassword()));
    }

    public void deletePassword(UUID passwordId) {
        Password password = passwordRepository.findById(passwordId)
                .orElseThrow(() -> new IllegalArgumentException("Password not found"));

        password.setActive(false);
        password.setDeletedAt(LocalDateTime.now());
        passwordRepository.save(password);
    }

    public String generateStrongPassword(int length, boolean includeUppercase, boolean includeNumbers, boolean includeSpecial) {
        length = Math.max(8, Math.min(length, 50));
        StringBuilder characterSet = new StringBuilder(LOWERCASE);

        if (includeUppercase) characterSet.append(UPPERCASE);
        if (includeNumbers) characterSet.append(NUMBERS);
        if (includeSpecial) characterSet.append(SPECIAL_CHARACTERS);

        // Generate password by randomly picking characters from the character set
        List<Character> password = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            password.add(characterSet.charAt(RANDOM.nextInt(characterSet.length())));
        }

        // Shuffle characters to make the password less predictable
        Collections.shuffle(password);

        StringBuilder result = new StringBuilder();
        password.forEach(result::append);

        return result.toString();
    }
}