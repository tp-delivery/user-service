package toy.project.delivery.userservice.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import toy.project.delivery.userservice.user.application.port.out.CreateUserCommand;
import toy.project.delivery.userservice.user.application.port.out.CreateUserPort;
import toy.project.delivery.userservice.user.application.port.out.LoadAllUsersPort;
import toy.project.delivery.userservice.user.application.port.out.LoadUserByIdPort;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class UserPersistenceAdapter implements LoadAllUsersPort, CreateUserPort, LoadUserByIdPort {
    private final UserRepository userRepository;
    private final UserJpaEntityMapper userJpaEntityMapper;

    @Override
    public User loadUserById(Long id) {
        UserJpaEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return userJpaEntityMapper.mapToDomainEntity(user);
    }

    @Override
    public List<User> loadAllUsers(int offset, int max) {
        Page<UserJpaEntity> userJpaEntityPage = userRepository.findAll(PageRequest.of(offset, max));

        return userJpaEntityPage.stream()
                .map(userJpaEntityMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(CreateUserCommand command) {
        UserJpaEntity entity = UserJpaEntity.builder()
                .email(command.getEmail())
                .encryptedPwd(command.getEncryptedPassword())
                .name(command.getName())
                .build();
        UserJpaEntity saved = userRepository.save(entity);
        return userJpaEntityMapper.mapToDomainEntity(saved);
    }
}
