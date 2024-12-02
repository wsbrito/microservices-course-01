package br.com.wsbrito.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wsbrito.model.Book;
import br.com.wsbrito.repository.BookRepositorty;

@Service
public class BookService {
	
	@Autowired
	private BookRepositorty repositorty;
	
	public Book getById(Long id) {
		return repositorty.getById(id);
	}

}
