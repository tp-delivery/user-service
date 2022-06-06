package toy.project.delivery.userservice.user.application.port.out;

import toy.project.delivery.userservice.user.domain.User;

public interface CreateUserPort {
    User createUser(CreateUserCommand command);
}
