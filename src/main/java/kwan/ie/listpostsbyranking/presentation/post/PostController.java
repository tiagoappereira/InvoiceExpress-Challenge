package kwan.ie.listpostsbyranking.presentation.post;

import kwan.ie.listpostsbyranking.presentation.AuthorizationRequired;
import kwan.ie.listpostsbyranking.services.post.PostService;
import kwan.ie.listpostsbyranking.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public final class PostController{
    private final PostService svc;
    private final UserService userSvc;

    public PostController(PostService svc, UserService userSvc) {
        this.svc = svc;
        this.userSvc = userSvc;
    }

    @GetMapping(path = {"/posts"})
    @ResponseStatus(HttpStatus.OK)
    public PostCollection getPostsByRanking(){
        return new PostCollection(Util.orderByRanking(svc.getAll()));
    }

    @AuthorizationRequired
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = {"/posts"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post cretePost(@RequestBody PostBody body, @RequestHeader(value = "Authorization") String authorizationHeader){
        return svc.create(body, userSvc.getCredentials(authorizationHeader));
    }

    @AuthorizationRequired
    @PutMapping(path = {"upvote/{postId}"})
    @ResponseStatus(HttpStatus.OK)
    public void upVotePost(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable Long postId){
        svc.upVote(userSvc.getCredentials(authorizationHeader), postId);
    }

    @AuthorizationRequired
    @PutMapping(path = {"downvote/{postId}"})
    @ResponseStatus(HttpStatus.OK)
    public void downVotePost(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable Long postId){
        svc.downVote(userSvc.getCredentials(authorizationHeader), postId);
    }
}
