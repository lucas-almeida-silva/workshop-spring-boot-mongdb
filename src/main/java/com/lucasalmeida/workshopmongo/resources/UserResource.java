package com.lucasalmeida.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){ //PathVariable - para que o id case com o id recebido na url
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){ //RequestBody para que esse endpoint aceite o objeto
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //coloca um cabeçalho na resposta vazia, com a url do recurso criado
		return ResponseEntity.created(uri).build(); //created retorna o código 201, que é o código retornado quando se cria um novo recurso
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){ //PathVariable - para que o id case com o id recebido na url
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){ //RequestBody para que esse endpoint aceite o objeto
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
}
