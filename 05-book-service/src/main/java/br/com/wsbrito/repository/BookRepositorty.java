package br.com.wsbrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsbrito.model.Book;

@Repository
public interface BookRepositorty extends JpaRepository<Book, Long> {

}
