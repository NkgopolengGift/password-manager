package passwordmanager.users.dto;

//dto=> Data Transfer  Object
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private String email;
    private String password;
    private String username;
}