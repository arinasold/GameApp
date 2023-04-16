package backendLastProject.GamesApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import backendLastProject.GamesApp.domain.User;
import backendLastProject.GamesApp.domain.UserRepository;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	// using test-database for saving objects
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameShouldReturnUser() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setPasswordHash("password");
        user.setRole("ROLE_USER");
        entityManager.persist(user);
        entityManager.flush();

        // When
        User foundUser = userRepository.findByUsername("testuser");

        // Then
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void saveShouldPersistUser() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setPasswordHash("password");
        user.setRole("ROLE_USER");

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }
    
    @Test
    public void testDeleteUserById() {
        // Given
        User user = new User("testuser", "password", "ROLE_USER");
        entityManager.persist(user);
        entityManager.flush(); // saving to test-database

        // When
        userRepository.deleteById(user.getId());

        // Then
        assertNull(entityManager.find(User.class, user.getId()));
    }
}
