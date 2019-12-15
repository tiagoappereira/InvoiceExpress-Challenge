package kwan.ie.listpostsbyranking.presentation.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import kwan.ie.listpostsbyranking.presentation.user.User;

import java.util.Comparator;

public class Post {
    private final long id;
    private final String title;
    private final String description;
    private final long upVotes;
    private final long downVotes;
    private final double score;
    private final User user;

    @JsonCreator
    public Post(long id, String title, String description, long upVotes, long downVotes, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.user = user;
        long total = upVotes + downVotes;
        this.score = total == 0 ? 0 : upVotes * 100.0 / total;
    }

    public long getId() {
        return id;
    }

    public long getUpVotes() {
        return upVotes;
    }

    public long getDownVotes() {
        return downVotes;
    }

    public double getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public static class RankingComparator implements Comparator<Post>{

        @Override
        public int compare(Post p1, Post p2) {
            return (p1.score == p2.score)
                    ? Long.compare(p1.upVotes, p2.upVotes)
                    : Double.compare(p1.score, p2.score);
        }
    }

}

