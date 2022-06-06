package toy.project.delivery.userservice.user.adapter.out.persistence;

import org.springframework.stereotype.Component;
import toy.project.delivery.userservice.user.domain.User;

@Component
class UserJpaEntityMapper {
    public User mapToDomainEntity(UserJpaEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .encryptedPwd(entity.getEncryptedPwd())
                .build();
    }
}
