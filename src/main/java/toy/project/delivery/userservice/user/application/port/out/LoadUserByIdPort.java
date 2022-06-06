package toy.project.delivery.userservice.user.application.port.out;

import toy.project.delivery.userservice.user.domain.User;

public interface LoadUserByIdPort {
    User loadUserById(Long id);
}
