package kwan.ie.listpostsbyranking.services.post;

import kwan.ie.listpostsbyranking.dal.model.PostEntity;
import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.dal.model.VoteEntity;
import kwan.ie.listpostsbyranking.presentation.post.Post;
import kwan.ie.listpostsbyranking.presentation.post.PostBody;
import kwan.ie.listpostsbyranking.services.user.UserConverters;

import java.util.Collection;

public class PostConverters {
    public static PostEntity postBodyToPostEntity(PostBody body, UserEntity user){
        PostEntity post = new PostEntity();
        post.setTitle(body.getTitle());
        post.setDescription(body.getDescription());
        post.setUser(user);
        return post;
    }

    public static Post postEntityToPost(PostEntity pe, Collection<VoteEntity> votes){
        return new Post(
                pe.getId(),
                pe.getTitle(),
                pe.getDescription(),
                votes.stream().filter(VoteEntity::getVote).count(),
                votes.stream().filter(vote -> !vote.getVote()).count(),
                UserConverters.userEntityToUser(pe.getUser()));
    }

    public static VoteEntity.VoteKey getVoteKey(PostEntity post, UserEntity user){
        VoteEntity.VoteKey key = new VoteEntity.VoteKey();
        key.setPost(post);
        key.setUser(user);
        return key;
    }

}
