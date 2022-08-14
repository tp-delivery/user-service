package toy.project.delivery.userservice.user.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import toy.project.delivery.userservice.user.domain.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("UserRepositoryTest.sql")
    void existsByEmail() {
        assertThat(userRepository.existsByEmail("user1@gmail.com")).isTrue();
        assertThat(userRepository.existsByEmail("noEmail")).isFalse();
    }

    @Test
    @Sql("UserRepositoryTest.sql")
    void findByEmail() {
        Optional<User> user = userRepository.findByEmail("user1@gmail.com");
        assertThat(user.isPresent()).isTrue();
        assertThat(user.get().getEmail()).isEqualTo("user1@gmail.com");
        assertThat(user.get().getPassword()).isEqualTo("asdfasdgasdg1");
        Optional<User> notExists = userRepository.findByEmail("notExists");
        assertThat(notExists.isPresent()).isFalse();
    }
}