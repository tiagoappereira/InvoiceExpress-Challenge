package kwan.ie.listpostsbyranking.presentation.post;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class PostCollection {
    private final int size;
    private final List<Post> posts;

    @JsonCreator
    public PostCollection(List<Post> posts) {
        this.size = posts.size();
        this.posts = posts;
    }

    public int getSize() {
        return size;
    }

    public List<Post> getPosts() {
        return posts;
    }

}
