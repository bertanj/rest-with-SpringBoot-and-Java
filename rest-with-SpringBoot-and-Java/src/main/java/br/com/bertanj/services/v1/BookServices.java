package br.com.bertanj.services.v1;

import br.com.bertanj.Exception.RequiredObjectIsNullException;
import br.com.bertanj.Exception.ResourceNotFoundException;
import br.com.bertanj.controllers.v1.BookController;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {
    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository  repository;

    public List<BookDTO> findAll(){
        logger.info("Finding all Books");

        var books = parseListObject(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateosLinks);
        return books;
    }

    public BookDTO findById(Long id){
        logger.info("Finding one Book!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this ID..."));
        var dto = parseObject(entity, BookDTO.class);
        addHateosLinks(dto);
        return dto;
    }

    public List<BookDTO> findByAuthor(String author){

        List<Book> booksFound = repository.findByauthor(author);

        var booksDto = parseListObject(booksFound, BookDTO.class);
        if (booksDto.isEmpty()) throw new ResourceNotFoundException("No records founds for this author...");

        logger.info("Finding all Books by author!");

        booksDto.forEach(this::addHateosLinks);
        return booksDto;
    }

    public List<BookDTO> findByTitle(String title){
        List<Book> booksFound = repository.findByTitle(title);

        var booksDto = parseListObject(booksFound, BookDTO.class);

        if (booksDto.isEmpty()) throw new ResourceNotFoundException("No records founds for this title...");

        logger.info("Finding one Book by title!");
        booksDto.forEach(this::addHateosLinks);
        return booksDto;
    }

    public BookDTO create(BookDTO book){
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one Book");

        var entity = parseObject(book, Book.class);

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateosLinks(dto);
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
        addHateosLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one Book");

        Book entity = repository.findById(id).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));
        repository.delete(entity);
    }
    
    private void addHateosLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findByAuthor(dto.getAuthor())).withRel("findByAuthor").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findByTitle(dto.getTitle())).withRel("findByTitle").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));    }
}
