package com.lucasalmeida.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasalmeida.workshopmongo.domain.User;

@RestController //indica que a classe é um recurso rest
@RequestMapping(value="/users") //caminho do endpoint
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET) //pra dizer que esse método vai ser o endpoint rest do caminho users e define o método get
	public ResponseEntity<List<User>> findAll(){ //ResponseEntity é um objeto do spring que encapsula uma estrutura para retornar respostas http já com possíveis erros, cabeçalhos etc
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
	}
}
