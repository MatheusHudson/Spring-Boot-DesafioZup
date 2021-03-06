package com.matheushudson.desafiozup.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheushudson.desafiozup.domain.Cliente;
import com.matheushudson.desafiozup.dto.ClienteDTO;
import com.matheushudson.desafiozup.dto.ClienteNewDTO;
import com.matheushudson.desafiozup.services.ClienteService;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	  	@Autowired
		private ClienteService service;
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<Cliente> find(@PathVariable Integer id) {	
			Cliente obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert( @Valid @RequestBody ClienteNewDTO objDto) {
			Cliente obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();	
		}
		

		@RequestMapping(value="/{id}",method=RequestMethod.PUT)
		public ResponseEntity<Void> update( @PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO) {
			
			Cliente obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
			
		}
		
		@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
		public ResponseEntity<Void> detele(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
}
