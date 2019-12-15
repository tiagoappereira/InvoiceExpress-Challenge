package kwan.ie.listpostsbyranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"kwan.ie.listpostsbyranking.dal.repository"})
public class ListPostsByRankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListPostsByRankingApplication.class, args);
	}

}
