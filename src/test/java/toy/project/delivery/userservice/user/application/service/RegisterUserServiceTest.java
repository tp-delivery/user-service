package toy.project.delivery.userservice.user.application.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserCommand;
import toy.project.delivery.userservice.user.application.port.out.CreateUserCommand;
import toy.project.delivery.userservice.user.application.port.out.CreateUserPort;

import static org.mockito.BDDMockito.then;

class RegisterUserServiceTest {
    private final CreateUserPort createUserPort = Mockito.mock(CreateUserPort.class);

    private final RegisterUserService registerUserService =
            new RegisterUserService(createUserPort);


    @Test
    void registerUser() {
        String email = "test@gmail.com";
        String password = "password";
        String name = "name";
        RegisterUserCommand registerUserCommand = RegisterUserCommand.of(email, password, name);
        CreateUserCommand createUserCommand = CreateUserCommand.of(email, password, name);

        registerUserService.registerUser(registerUserCommand);

        then(createUserPort).should().createUser(createUserCommand);
    }
}