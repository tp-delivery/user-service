package toy.project.delivery.userservice.user.application.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import toy.project.delivery.userservice.user.application.port.out.LoadUserByIdPort;
import toy.project.delivery.userservice.user.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class GetUserByIdServiceTest {

    private final LoadUserByIdPort loadUserByIdPort = Mockito.mock(LoadUserByIdPort.class);

    private final GetUserByIdService getUserByIdService =
            new GetUserByIdService(loadUserByIdPort);

    @Test
    void getUserById() {
        Long id = 1L;
        User user = givenUser(id);

        User userById = getUserByIdService.getUserById(id);

        assertThat(userById).isEqualTo(user);
    }

    private User givenUser(Long id) {
        User user = Mockito.mock(User.class);
        given(user.getId()).willReturn(id);
        given(loadUserByIdPort.loadUserById(id)).willReturn(user);
        return user;
    }
}