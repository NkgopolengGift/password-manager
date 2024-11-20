package passwordmanager.password.contoller;

import passwordmanager.password.entity.Password;
import passwordmanager.password.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import passwordmanager.password.dto.PasswordRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping
    public ResponseEntity<Password> createPassword(@RequestBody PasswordRequest passwordRequest) {
        Password password = passwordService.createPassword(passwordRequest);
        return ResponseEntity.ok(password);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Password>> getAllPasswords(@PathVariable UUID userId) {
        List<Password> passwords = passwordService.getPasswordsByUserId(userId);
        return ResponseEntity.ok(passwords);
    }

    @PutMapping("/{passwordId}")
    public ResponseEntity<Password> updatePassword(
            @PathVariable UUID passwordId,
            @RequestBody PasswordRequest passwordRequest) {
        Password updatedPassword = passwordService.updatePassword(passwordId, passwordRequest);
        return ResponseEntity.ok(updatedPassword);
    }

    @DeleteMapping("/{passwordId}")
    public ResponseEntity<Void> deletePassword(@PathVariable UUID passwordId) {
        passwordService.deletePassword(passwordId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to generate a strong random password
    @GetMapping("/generate-strong-password")
    public ResponseEntity<String> generateStrongPassword(
            @RequestParam(defaultValue = "8") int length,
            @RequestParam(defaultValue = "true") boolean includeUppercase,
            @RequestParam(defaultValue = "true") boolean includeNumbers,
            @RequestParam(defaultValue = "true") boolean includeSpecial) {
        String generatedPassword = passwordService.generateStrongPassword(length, includeUppercase, includeNumbers, includeSpecial);
        return ResponseEntity.ok(generatedPassword);
    }
}
