package br.com.wsbrito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsbrito.model.Book;
import br.com.wsbrito.proxy.CambioProxy;
import br.com.wsbrito.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookService service;
	
	@Autowired
	private CambioProxy proxy;
	
	@Operation(summary = "Find specifc book by your ID")
	@GetMapping("/{id}/{currency}")
	public Book finbook(
			@PathVariable Long id,
			@PathVariable String currency
	) {
		
		var book = service.getById(id);
		if(book == null) {
			throw new RuntimeException("Book not found");
		}
		
		var port = environment.getProperty("local.server.port");		
		
		/*
		 * Before Proxy
		 * 
		HashMap<String,String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);		
		var response = new 
			RestTemplate()
				.getForEntity(
					"http://localhost:8000/cambio-service/{amount}/{from}/{to}", 
					CambioDTO.class,
					params);
		var cambio = response.getBody();
		*/
		var cambio = 
				proxy.getCambio(
					book.getPrice(), 
					"USD", 
					currency);
		
		var bookEnvironment = 
				String.format(
					"Book Port: %s - Cambio Port - %s",
					port,
					cambio.getEnvironment());
		
		book.setEnvironment(bookEnvironment);
		book.setPrice(cambio.getConvertedValue());
		
		return book;
	}

}
