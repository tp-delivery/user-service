package toy.project.delivery.userservice.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import toy.project.delivery.userservice.auth.service.SignUpService;

import java.util.Map;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = SignUpController.class)
class SignUpControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SignUpService signUpService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signUp() throws Exception {
        String email = "email";
        String password = "password";
        Map<String, String> requestBody = Map.of(
                "email", email,
                "password", password
        );
        mockMvc.perform(post("/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("success"));

        then(signUpService).should().signUp(email, password);
    }
}