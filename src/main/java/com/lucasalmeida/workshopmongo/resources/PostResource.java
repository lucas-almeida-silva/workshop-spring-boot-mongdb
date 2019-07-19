package com.lucasalmeida.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasalmeida.workshopmongo.domain.Post;
import com.lucasalmeida.workshopmongo.resources.util.URL;
import com.lucasalmeida.workshopmongo.services.PostService;

@RestController //indica que a classe é um recurso rest
@RequestMapping(value="/posts") //caminho do endpoint
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){ //PathVariable - para que o id case com o id recebido na url
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Endpoint para fazer a busca pelo título do post
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
		text = URL.decodeParam(text); //decodifica o texto
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	//Endpoint para fazer a busca com vários critérios
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue = "") String text,
			@RequestParam(value="minDate", defaultValue = "") String minDate,
			@RequestParam(value="maxDate", defaultValue = "") String maxDate){
		
		text = URL.decodeParam(text); //decodifica o texto
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
