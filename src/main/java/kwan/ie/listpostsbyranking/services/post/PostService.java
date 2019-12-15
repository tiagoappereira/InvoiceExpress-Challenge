package kwan.ie.listpostsbyranking.services.post;

import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.presentation.post.Post;
import kwan.ie.listpostsbyranking.presentation.post.PostBody;
import kwan.ie.listpostsbyranking.presentation.user.User;

import java.util.stream.Stream;

public interface PostService {
    Post create(PostBody body, UserEntity user);
    Stream<Post> getAll();
    void upVote(UserEntity user, long id);
    void downVote(UserEntity user, long id);
}
