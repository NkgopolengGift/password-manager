package passwordmanager.password.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import passwordmanager.password.entity.Password;
import passwordmanager.users.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface PasswordRepository extends JpaRepository<Password, UUID> {
    List<Password> findByUser_UserIdAndIsActiveTrue(UUID userId);
}
