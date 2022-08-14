package toy.project.delivery.userservice.auth.dto;

import lombok.Data;

public class SignUpDto {
    @Data
    public static class Request {
        private String email;
        private String password;
    }
}
