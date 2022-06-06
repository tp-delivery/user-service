package toy.project.delivery.userservice.user.application.port.in;

import toy.project.delivery.userservice.user.domain.User;

public interface GetUserByIdUseCase {
    User getUserById(Long id);
}
