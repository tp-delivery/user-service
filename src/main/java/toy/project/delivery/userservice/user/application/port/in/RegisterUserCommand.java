package toy.project.delivery.userservice.user.application.port.in;

import lombok.Value;

@Value(staticConstructor = "of")
public class RegisterUserCommand {
    String email;
    String password;
    String name;
}
