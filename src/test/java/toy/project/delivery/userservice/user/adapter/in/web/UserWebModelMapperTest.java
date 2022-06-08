package toy.project.delivery.userservice.user.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class UserWebModelMapperTest {
    private final UserWebModelMapper mapper = new UserWebModelMapper();

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(1L, "email1", "name1"),
                Arguments.of(2L, "email2", "name2")
        );
    }

    @Test
    void mapToWebModel_userList() {
        List<User> users = givenUserList();
        AllUsersDto allUsersDto = mapper.mapToWebModel(users);

        assertThat(allUsersDto.getUsers().size()).isEqualTo(users.size());
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void MapToWebModel_user(long id, String email, String name) {
        User user = givenUser(id, email, name);

        UserDto dto = mapper.mapToWebModel(user);

        assertThat(dto).isEqualTo(
                UserDto.of(id, email, name)
        );
    }

    private List<User> givenUserList() {
        return List.of(
                givenUser(1L, "email1", "name1"),
                givenUser(2L, "email2", "name2")
        );
    }

    private User givenUser(long id, String email, String name) {
        User user = Mockito.mock(User.class);
        given(user.getId()).willReturn(id);
        given(user.getEmail()).willReturn(email);
        given(user.getName()).willReturn(name);
        return user;
    }
}