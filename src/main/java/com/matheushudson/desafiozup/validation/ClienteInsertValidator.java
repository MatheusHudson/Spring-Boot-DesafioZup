package com.matheushudson.desafiozup.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.matheushudson.desafiozup.domain.Cliente;
import com.matheushudson.desafiozup.dto.ClienteNewDTO;
import com.matheushudson.desafiozup.repositories.ClienteRepository;
import com.matheushudson.desafiozup.resources.exception.FieldMessage;
import com.matheushudson.desafiozup.validation.util.CpfECnpjValidator;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	
	
		
		
		
		List<FieldMessage> list = new ArrayList<>();

		if(!CpfECnpjValidator.isValidCpf(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}	
		
		
		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null ) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		Cliente auxCpf = repo.findByCpf(objDto.getCpf());
		if(auxCpf != null ) {
			list.add(new FieldMessage("cpf", "CPF já existente"));
		}
		
	
		
		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
} // listafor(FieldMessagee:list){context.disableDefaultConstraintViolation();context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();}returnlist.isEmpty();}}