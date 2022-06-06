package toy.project.delivery.userservice.user.adapter.in.web;

import org.springframework.stereotype.Component;
import toy.project.delivery.userservice.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
class UserWebModelMapper {
    public AllUsersDto mapToWebModel(List<User> userList) {
        return AllUsersDto.of(
            userList.stream()
                    .map(this::mapToWebModel)
                    .collect(Collectors.toList())
        );
    }

    public UserDto mapToWebModel(User user) {
        return UserDto.of(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getEncryptedPwd()
        );
    }
}
