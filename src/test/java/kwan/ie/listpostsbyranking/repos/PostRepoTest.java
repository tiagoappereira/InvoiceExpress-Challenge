package kwan.ie.listpostsbyranking.repos;

import kwan.ie.listpostsbyranking.dal.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts = {"classpath:createUsers.sql", "classpath:createPosts.sql"})
public class PostRepoTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void testFindAll(){
        assertThat(postRepository.findAll()).isNotEmpty().hasSize(5);
    }
}
