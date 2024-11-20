package passwordmanager.password.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PasswordRequest {
    private UUID userId;
    private String serviceName;
    private String serviceWebsite;
    private String serviceEmail;
    private String serviceUsername;
    private String servicePassword;
}