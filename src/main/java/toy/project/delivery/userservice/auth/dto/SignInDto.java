package toy.project.delivery.userservice.auth.dto;

import lombok.Data;

public class SignInDto {
    @Data
    public static class Request {
        private String email;
        private String password;
    }
}
