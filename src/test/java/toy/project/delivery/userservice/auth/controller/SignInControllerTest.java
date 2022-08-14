package toy.project.delivery.userservice.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import toy.project.delivery.userservice.auth.service.SignInService;

import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = SignInController.class)
class SignInControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SignInService signInService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signIn() throws Exception {
        String email = "email";
        String password = "password";
        String expectedToken = "expectedToken";
        Map<String, String> requestBody = Map.of(
                "email", email,
                "password", password
        );
        given(signInService.signIn(email, password))
                .willReturn(expectedToken);

        mockMvc.perform(post("/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
                )
                .andExpect(status().isOk())
                .andExpect(header().string("X-AUTH-TOKEN", expectedToken))
                .andExpect(content().string("success"));

        then(signInService).should().signIn(email, password);
    }
}