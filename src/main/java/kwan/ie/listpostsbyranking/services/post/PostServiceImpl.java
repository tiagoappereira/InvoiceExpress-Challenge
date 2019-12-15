package kwan.ie.listpostsbyranking.services.post;

import kwan.ie.listpostsbyranking.dal.model.PostEntity;
import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.dal.model.VoteEntity;
import kwan.ie.listpostsbyranking.dal.repository.PostRepository;
import kwan.ie.listpostsbyranking.dal.repository.VoteRepository;
import kwan.ie.listpostsbyranking.exception.ResourceNotFoundException;
import kwan.ie.listpostsbyranking.presentation.post.Post;
import kwan.ie.listpostsbyranking.presentation.post.PostBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repo;

    @Autowired
    private VoteRepository voteRepo;

    @Override
    public Post create(PostBody body, UserEntity user) {
        PostEntity post = PostConverters.postBodyToPostEntity(body, user);
        repo.saveAndFlush(post);
        return PostConverters.postEntityToPost(post, voteRepo.findByIdPost(post));
    }

    @Override
    public Stream<Post> getAll() {
        List<PostEntity> list = repo.findAll();
        return list
                .stream()
                .map(post -> PostConverters.postEntityToPost(post, voteRepo.findByIdPost(post)));
    }

    @Override
    public void upVote(UserEntity user, long id) {
        changeVote(user, id, true);
    }

    @Override
    public void downVote(UserEntity user, long id) {
        changeVote(user, id, false);
    }

    private void changeVote(UserEntity user, long id, boolean voteValue) {
        Optional<PostEntity> post = repo.findById(id);
        if (!post.isPresent())
            throw new ResourceNotFoundException(String.format("Post with id %d doesn't exist!", id));

        VoteEntity.VoteKey key = PostConverters.getVoteKey(post.get(), user);
        Optional<VoteEntity> optVote = voteRepo.findById(key);

        VoteEntity vote;
        if (optVote.isPresent()) {
            vote = optVote.get();
            if (vote.getVote() == voteValue) {
                voteRepo.delete(vote);
                return;
            }
        } else {
            vote = new VoteEntity();
            vote.setId(key);
        }
        vote.setVote(voteValue);
        voteRepo.save(vote);
    }
}