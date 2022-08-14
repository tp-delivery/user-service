package toy.project.delivery.userservice.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.project.delivery.userservice.common.domain.CommonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User extends CommonEntity {
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public static User of(String email, String password) {
        User user = new User();
        user.email = email;
        user.password = password;
        return user;
    }
}
