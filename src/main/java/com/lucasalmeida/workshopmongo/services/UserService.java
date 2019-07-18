package com.lucasalmeida.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasalmeida.workshopmongo.domain.User;
import com.lucasalmeida.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired //notação para instanciar automaticamente um objeto no serviço, injeção de dependência automática
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
}
