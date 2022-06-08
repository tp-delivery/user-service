package toy.project.delivery.userservice.user.adapter.in.web;

import org.junit.jupiter.api.Test;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserCommand;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterUserMapperTest {

    private final RegisterUserMapper mapper = new RegisterUserMapper();

    @Test
    void mapToCommand() {
        String email = "email";
        String name = "name";
        String password = "password";
        RegisterUserDto dto = RegisterUserDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();

        RegisterUserCommand command = mapper.mapToCommand(dto);

        assertThat(command).isEqualTo(
                RegisterUserCommand.of(email, password, name)
        );
    }
}