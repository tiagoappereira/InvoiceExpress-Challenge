package kwan.ie.listpostsbyranking.dal.repository;

import kwan.ie.listpostsbyranking.dal.model.PostEntity;
import kwan.ie.listpostsbyranking.dal.model.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface VoteRepository extends JpaRepository<VoteEntity, VoteEntity.VoteKey> {
    Collection<VoteEntity> findByIdPost(PostEntity post);
}
