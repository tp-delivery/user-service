package toy.project.delivery.userservice.user.adapter.in.web;

import lombok.*;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
class RegisterUserDto {
    private String email;

    private String password;

    private String name;
}
