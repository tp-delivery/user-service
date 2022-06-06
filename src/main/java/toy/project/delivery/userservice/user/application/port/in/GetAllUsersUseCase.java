package toy.project.delivery.userservice.user.application.port.in;

import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

public interface GetAllUsersUseCase {
    List<User> getAllUsers(int offset, int max);
}
