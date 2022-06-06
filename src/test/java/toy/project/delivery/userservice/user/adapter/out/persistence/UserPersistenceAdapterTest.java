package toy.project.delivery.userservice.user.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import toy.project.delivery.userservice.user.application.port.out.CreateUserCommand;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({UserPersistenceAdapter.class, UserJpaEntityMapper.class})
class UserPersistenceAdapterTest {
    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("UserPersistenceAdapterTest.sql")
    void loadUserById_exists() {
        Long id = 1L;
        User user = userPersistenceAdapter.loadUserById(id);

        assertThat(user.getName()).isEqualTo("user1");
    }

    @Test
    @Sql("UserPersistenceAdapterTest.sql")
    void loadUserById_not_exists() {
        Long id = 43141L;

        assertThatThrownBy(() -> userPersistenceAdapter.loadUserById(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("User Not Found");
    }


    @Test
    @Sql("UserPersistenceAdapterTest.sql")
    void loadAllUsers() {
        int offset = 0;
        int max = 10;
        List<User> users = userPersistenceAdapter.loadAllUsers(offset, max);

        assertThat(userRepository.count()).isEqualTo(5);

        assertThat(users.size()).isEqualTo(5);
        assertThat(users.get(0).getName()).isEqualTo("user1");
    }

    @Test
    @Sql("UserPersistenceAdapterTest.sql")
    void loadAllUsers_offset1_max2() {
        int offset = 1;
        int max = 2;
        List<User> users = userPersistenceAdapter.loadAllUsers(offset, max);

        assertThat(userRepository.count()).isEqualTo(5);

        assertThat(users.size()).isEqualTo(max);
        assertThat(users.get(0).getName()).isEqualTo("user3");
    }

    @Test
    void createUser() {
        String email = "email@gmail.com";
        String password = "password";
        String name = "name";
        User user = userPersistenceAdapter.createUser(
                CreateUserCommand.of(email, password, name)
        );

        assertThat(user.getId()).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getEncryptedPwd()).isEqualTo(password);
        assertThat(user.getName()).isEqualTo(name);
    }

}