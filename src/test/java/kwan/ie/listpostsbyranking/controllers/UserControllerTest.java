package kwan.ie.listpostsbyranking.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kwan.ie.listpostsbyranking.presentation.user.Token;
import kwan.ie.listpostsbyranking.presentation.user.UserBody;
import kwan.ie.listpostsbyranking.services.user.UserService;
import kwan.ie.listpostsbyranking.util.BasicAuthentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private UserService userService;

    @Test
    public void itShouldReturnGeneratedToken() throws Exception {
        UserBody body = new UserBody("test", "test");
        Token res = new Token(BasicAuthentication.encode(body.getUsername(), body.getPassword()));
        when(userService.userExists(any(String.class))).thenReturn(false);
        mockMvc.perform(
                post("/users/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(body)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.basicToken").value(res.getBasicToken()));
    }
}
