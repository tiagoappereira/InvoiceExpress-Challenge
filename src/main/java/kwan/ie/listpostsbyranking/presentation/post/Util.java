package kwan.ie.listpostsbyranking.presentation.post;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    public static List<Post> orderByRanking(Stream<Post> posts){
        return posts
                .sorted(new Post.RankingComparator().reversed())
                .collect(Collectors.toList());
    }
}
