package com.lucasalmeida.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasalmeida.workshopmongo.domain.User;
import com.lucasalmeida.workshopmongo.dto.UserDTO;
import com.lucasalmeida.workshopmongo.services.UserService;

@RestController //indica que a classe é um recurso rest
@RequestMapping(value="/users") //caminho do endpoint
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) //pra dizer que esse método vai ser o endpoint rest do caminho users e define o método get
	public ResponseEntity<List<UserDTO>> findAll(){ //ResponseEntity é um objeto do spring que encapsula uma estrutura para retornar respostas http já com possíveis erros, cabeçalhos etc
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
