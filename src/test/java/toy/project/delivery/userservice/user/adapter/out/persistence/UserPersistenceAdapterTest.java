package toy.project.delivery.userservice.user.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({UserPersistenceAdapter.class, UserMapper.class})
class UserPersistenceAdapterTest {
    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;
    @Autowired
    private UserRepository userRepository;

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
}