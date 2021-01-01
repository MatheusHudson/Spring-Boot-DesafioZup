package com.matheushudson.desafiozup;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheushudson.desafiozup.domain.Cliente;
import com.matheushudson.desafiozup.repositories.ClienteRepository;



@SpringBootApplication
public class DesafioZupApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) throws ParseException {
	
		SpringApplication.run(DesafioZupApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Cliente cliente1 = new Cliente(null, "Matheus Hudson", "matheushudson2013@gmail.com", LocalDate.parse("21/09/1997", formatter),"02267286688");
		clienteRepository.save(cliente1);
	}

}
