package com.matheushudson.desafiozup.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.matheushudson.desafiozup.domain.Cliente;
import com.matheushudson.desafiozup.dto.ClienteDTO;
import com.matheushudson.desafiozup.dto.ClienteNewDTO;
import com.matheushudson.desafiozup.repositories.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.matheushudson.desafiozup.services.exception.ObjectNotFoundException(
				"Usuario inexistente com este ID: " + id ));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
	
		obj = repo.save(obj);
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
			repo.deleteById(id);
		
	}
	
	public Cliente fromDTO(@Valid ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getDataNascimento(),objDTO.getCpf());
		
		return cli;
	}
	public Cliente fromDTO(@Valid ClienteDTO objDto) {
		return new Cliente(objDto.getId() ,objDto.getNome(), objDto.getEmail(), objDto.getDataNascimento(), objDto.getCpf());
	}
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
