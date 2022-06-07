package toy.project.delivery.userservice.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerUser(RegisterUserCommand command) {
        String encryptedPassword = passwordEncoder.encode(command.getPassword());
        createUserPort.createUser(CreateUserCommand.of(
                command.getEmail(),
                encryptedPassword,
                command.getName()
        ));
    }
}
