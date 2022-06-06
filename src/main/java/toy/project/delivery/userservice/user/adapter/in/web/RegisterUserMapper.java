package toy.project.delivery.userservice.user.adapter.in.web;

import org.springframework.stereotype.Component;
import toy.project.delivery.userservice.user.application.port.in.RegisterUserCommand;

@Component
class RegisterUserMapper {
    public RegisterUserCommand mapToCommand(RegisterUserDto registerUserDto) {
        return RegisterUserCommand.of(
                registerUserDto.getEmail(),
                registerUserDto.getPassword(),
                registerUserDto.getName()
        );
    }
}
