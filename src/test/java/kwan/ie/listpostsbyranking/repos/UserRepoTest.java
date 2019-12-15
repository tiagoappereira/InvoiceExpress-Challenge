package kwan.ie.listpostsbyranking.repos;

import kwan.ie.listpostsbyranking.dal.model.UserEntity;
import kwan.ie.listpostsbyranking.dal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("classpath:createUsers.sql")
public class UserRepoTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName(){
        assertThat(userRepository.findByUsername("admin")).isPresent();
    }

    @Test
    public void testFindAll(){
        assertThat(userRepository.findAll()).isNotEmpty().hasSize(6);
    }
}
