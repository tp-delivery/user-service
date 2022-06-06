package toy.project.delivery.userservice.user.application.port.out;

import lombok.Value;

@Value(staticConstructor = "of")
public class CreateUserCommand {
    String email;
    String encryptedPassword;
    String name;
}
