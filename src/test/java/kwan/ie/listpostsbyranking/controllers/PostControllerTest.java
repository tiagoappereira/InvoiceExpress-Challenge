package kwan.ie.listpostsbyranking.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.presentation.post.Post;
import kwan.ie.listpostsbyranking.presentation.post.PostBody;
import kwan.ie.listpostsbyranking.presentation.post.PostCollection;
import kwan.ie.listpostsbyranking.presentation.user.User;
import kwan.ie.listpostsbyranking.presentation.user.UserBody;
import kwan.ie.listpostsbyranking.services.post.PostService;
import kwan.ie.listpostsbyranking.services.user.UserConverters;
import kwan.ie.listpostsbyranking.services.user.UserService;
import kwan.ie.listpostsbyranking.util.BasicAuthentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PostService postService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void itShouldReturnCreatedPost() throws Exception {
        UserBody userBody = new UserBody("admin", "admin");
        String token = BasicAuthentication.encode(userBody.getUsername(), userBody.getPassword());
        User userExpected = new User(1, "admin", "admin");
        UserEntity userEntity = UserConverters.userBodyToUserEntity(userBody);
        when(userService.getCredentials(any(String.class))).thenReturn(userEntity);

        PostBody postBody = new PostBody("New post", "New post description");
        Post postExpected = new Post(1, postBody.getTitle(), postBody.getDescription(), 0, 0, userExpected);
        when(postService.create(any(PostBody.class), any(UserEntity.class))).thenReturn(postExpected);

        mockMvc.perform(
                post("/posts")
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(postBody)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(postExpected)));
    }

    @Test
    public void itShouldReturnPostsSortedByRanking() throws Exception {
        User user = new User(1, "user1", "user1");
        Post p1 = new Post(1, "P1", "P1", 60, 40, user);
        Post p2 = new Post(2, "P2", "P2", 600, 400, user);
        Post p3 = new Post(3, "P3", "P3", 60, 0, user);
        Post p4 = new Post(4, "P4", "P4", 0, 40, user);
        Post p5 = new Post(5, "P5", "P5", 60, 40, user);

        List<Post> postsUnordered = Stream.of(p1, p2, p3, p4, p5).collect(Collectors.toList());
        List<Post> postsOrdered = Stream.of(p3, p2, p1, p5, p4).collect(Collectors.toList());
        PostCollection expected = new PostCollection(postsOrdered);
        when(postService.getAll()).thenReturn(postsUnordered.stream());

        mockMvc.perform(
                get("/posts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expected)));
    }

    @Test
    public void itShouldVoteSuccessfully() throws Exception {
        UserBody userBody = new UserBody("admin", "admin");
        String token = BasicAuthentication.encode(userBody.getUsername(), userBody.getPassword());

        mockMvc
                .perform(put("/upvote/1").header(HttpHeaders.AUTHORIZATION, "Basic " + token))
                .andExpect(status().isOk());

        mockMvc
                .perform(put("/downvote/1").header(HttpHeaders.AUTHORIZATION, "Basic " + token))
                .andExpect(status().isOk());
    }
}
