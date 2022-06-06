package toy.project.delivery.userservice.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserCommand;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserUseCase;
import toy.project.delivery.userservice.user.application.port.out.CreateUserCommand;
import toy.project.delivery.userservice.user.application.port.out.CreateUserPort;

@Service
@Transactional
@RequiredArgsConstructor
class RegisterUserService implements RegisterUserUseCase {
    private final CreateUserPort createUserPort;

    @Override
    public void registerUser(RegisterUserCommand command) {
        createUserPort.createUser(CreateUserCommand.of(
                command.getEmail(),
                command.getPassword(),
                command.getName()
        ));
    }
}
