package com.lucasalmeida.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lucasalmeida.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> { //<tipo da classe de domínio que será gerenciada, tipo do ID na classe>

	List<Post> findByTitleContainingIgnoreCase(String text);
}
