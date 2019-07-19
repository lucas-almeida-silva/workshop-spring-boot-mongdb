package com.lucasalmeida.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lucasalmeida.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> { //<tipo da classe de domínio que será gerenciada, tipo do ID na classe>

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") //?0 - primeiro parâmetro que vem no método, ?2 é o segudo, etc
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }") //gte - texta se é maior ou igual...lte - testa se é menor ou igual
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
