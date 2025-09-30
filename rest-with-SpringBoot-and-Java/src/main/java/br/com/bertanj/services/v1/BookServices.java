package br.com.bertanj.services.v1;

import br.com.bertanj.Exception.RequiredObjectIsNullException;
import br.com.bertanj.data.dto.v1.BookDTO;
import br.com.bertanj.model.Book;
import br.com.bertanj.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.bertanj.mapper.ObjectMapper.parseListObject;
import static br.com.bertanj.mapper.ObjectMapper.parseObject;


@Service
public class BookServices {
    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository  repository;

    public List<BookDTO> findAll(){
        logger.info("Finding all Books");

        var books = parseListObject(repository.findAll(), BookDTO.class);
        return books;
    }

    public Book findById(Long id){
        logger.info("Finding one Book!");

        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));
        return entity;
    }

//    public Book findByAuthor(String author){
//        logger.info("Finding one Book!");
//
//        var entity = repository.findByAuthor(author).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));
//        return entity;
//    }

    public BookDTO create(BookDTO book){
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one Book");

        var entity = parseObject(book, Book.class);

        var dto = parseObject(repository.save(entity), BookDTO.class);
        return dto;

    }

    public BookDTO update(BookDTO book){

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book");

        Book entity = repository.findById(book.getId()).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));

        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one Book");

        Book entity = repository.findById(id).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));
        repository.delete(entity);
    }


}
