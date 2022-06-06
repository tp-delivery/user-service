package toy.project.delivery.userservice.user.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;

    private String email;

    private String name;

    private String encryptedPwd;
}
