package kwan.ie.listpostsbyranking.dal.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Vote")
public class VoteEntity {
    @EmbeddedId
    private VoteKey id;
    public VoteKey getId() {
        return id;
    }
    public void setId(VoteKey id) {
        this.id = id;
    }

    //true for upvote, false for downvote
    private boolean vote;
    public boolean getVote() {
        return vote;
    }
    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public VoteEntity() {
    }

    @Embeddable
    public static class VoteKey implements Serializable {

        @ManyToOne
        @JoinColumn(name="vote_user", referencedColumnName = "id", nullable = false)
        private UserEntity user;
        public UserEntity getUser() {
            return user;
        }
        public void setUser(UserEntity user) {
            this.user = user;
        }

        @ManyToOne
        @JoinColumn(name="post", referencedColumnName = "id", nullable = false)
        private PostEntity post;
        public PostEntity getPost() {
            return post;
        }
        public void setPost(PostEntity post) {
            this.post = post;
        }
    }
}


