package kwan.ie.listpostsbyranking.util;

import kwan.ie.listpostsbyranking.presentation.post.Post;
import kwan.ie.listpostsbyranking.presentation.post.Util;
import kwan.ie.listpostsbyranking.presentation.user.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OrderByRankingTest {

    private static List<Post> mockPosts(User user){
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "p1", "p1", 0, 6, user));
        posts.add(new Post(2, "p2", "p2", 1, 1, user));
        posts.add(new Post(3, "p3", "p3", 3, 3, user));
        posts.add(new Post(4, "p4", "p4", 3, 3, user));
        posts.add(new Post(5, "p5", "p5", 6, 0, user));
        return posts;
    }

    private static List<Post> mockSortedPosts(List<Post> posts){
        ArrayList<Post> sortedPosts = new ArrayList<>();
        sortedPosts.add(0, posts.get(4));
        sortedPosts.add(1, posts.get(2));
        sortedPosts.add(2, posts.get(3));
        sortedPosts.add(3, posts.get(1));
        sortedPosts.add(4, posts.get(0));
        return sortedPosts;
    }

    @Test
    public void testPostsRanking(){
        User user = new User(1, "admin", "admin");
        List<Post> postsUnordered = mockPosts(user);
        List<Post> postsOrderedExpected = mockSortedPosts(postsUnordered);
        assertThat(Util.orderByRanking(postsUnordered.stream()), is(postsOrderedExpected));
    }
}
