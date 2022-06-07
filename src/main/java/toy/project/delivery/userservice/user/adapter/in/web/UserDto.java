package toy.project.delivery.userservice.user.adapter.in.web;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserDto {
    Long id;

    String email;

    String name;
}
