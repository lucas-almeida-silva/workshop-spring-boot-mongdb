package com.lucasalmeida.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasalmeida.workshopmongo.domain.Post;
import com.lucasalmeida.workshopmongo.repository.PostRepository;
import com.lucasalmeida.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired //notação para instanciar automaticamente um objeto no serviço, injeção de dependência automática
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); //para considerar a data final até o final deste dia, ou seja, até a hora 24, e tranforma em milisegundos
		return repo.fullSearch(text, minDate, maxDate);
	}
}
