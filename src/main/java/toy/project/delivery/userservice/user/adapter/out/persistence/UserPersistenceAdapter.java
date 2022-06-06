package toy.project.delivery.userservice.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import toy.project.delivery.userservice.user.application.port.out.LoadAllUsersPort;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class UserPersistenceAdapter implements LoadAllUsersPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> loadAllUsers(int offset, int max) {
        Page<UserJpaEntity> userJpaEntityPage = userRepository.findAll(PageRequest.of(offset, max));

        return userJpaEntityPage.stream()
                .map(userMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}