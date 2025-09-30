package br.com.bertanj.repository;

import br.com.bertanj.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByauthor(String author);
    List <Book> findByTitle(String title);
}
