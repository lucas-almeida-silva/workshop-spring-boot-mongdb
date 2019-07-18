package com.lucasalmeida.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lucasalmeida.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> { //<tipo da classe de domínio que será gerenciada, tipo do ID na classe>

}
