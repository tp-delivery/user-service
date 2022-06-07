package toy.project.delivery.userservice.user.application.service;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserCommand;
import toy.project.delivery.userservice.user.application.port.out.CreateUserCommand;
import toy.project.delivery.userservice.user.application.port.out.CreateUserPort;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class RegisterUserServiceTest {
    private final CreateUserPort createUserPort = Mockito.mock(CreateUserPort.class);
    private final BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

    private final RegisterUserService registerUserService =
            new RegisterUserService(createUserPort, passwordEncoder);


    @Test
    void registerUser() {
        String email = "test@gmail.com";
        String password = "password";
        String name = "name";
        String encryptedPassword = givenPasswordEncoder(password);
        RegisterUserCommand registerUserCommand = RegisterUserCommand.of(email, password, name);
        CreateUserCommand createUserCommand = CreateUserCommand.of(email, encryptedPassword, name);

        registerUserService.registerUser(registerUserCommand);

        then(createUserPort).should().createUser(createUserCommand);
    }

    private String givenPasswordEncoder(String password) {
        String encryptedPassword = "encryptedPassword";
        given(passwordEncoder.encode(password)).willReturn(encryptedPassword);
        return encryptedPassword;
    }
}