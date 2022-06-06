package toy.project.delivery.userservice.user.application.port.out;

import toy.project.delivery.userservice.user.domain.User;

import java.util.List;

public interface LoadAllUsersPort {
    List<User> loadAllUsers(int offset, int max);
}
