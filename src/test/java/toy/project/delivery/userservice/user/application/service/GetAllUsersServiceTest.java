package toy.project.delivery.userservice.user.application.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import toy.project.delivery.userservice.user.application.port.out.LoadAllUsersPort;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class GetAllUsersServiceTest {

    private final LoadAllUsersPort loadAllUsersPort = Mockito.mock(LoadAllUsersPort.class);

    private final GetAllUsersService getAllUsersService =
            new GetAllUsersService(loadAllUsersPort);

    @Test
    void givenEmptyUserList_thenEmptyResult() {
        int offset = 0;
        int max = 10;
        givenEmptyUserList(offset, max);

        List<User> allUsers = getAllUsersService.getAllUsers(offset, max);

        assertThat(allUsers).isEmpty();
    }

    @Test
    void givenTwoUserList_thenEmptyResult() {
        int offset = 0;
        int max = 10;
        List<User> userList = givenTwoUserList(offset, max);

        List<User> allUsers = getAllUsersService.getAllUsers(offset, max);

        assertThat(allUsers).isEqualTo(userList);
    }

    private void givenEmptyUserList(int offset, int max) {
        given(loadAllUsersPort.loadAllUsers(offset, max)).willReturn(List.of());
    }

    private List<User> givenTwoUserList(int offset, int max) {
        List<User> userList = List.of(givenUser(1L), givenUser(2L));
        given(loadAllUsersPort.loadAllUsers(offset, max)).willReturn(userList);
        return userList;
    }

    private User givenUser(Long id) {
        User user = Mockito.mock(User.class);
        given(user.getId()).willReturn(id);
        return user;
    }

}