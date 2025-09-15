package br.com.bertanj.services;

import br.com.bertanj.Exception.ResourceNotFoundException;
import br.com.bertanj.model.Person;
import br.com.bertanj.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;


    public List<Person> findAll(){
        logger.info("Finding all People");


       return repository.findAll();
    }

    public Person findById(Long id){
        logger.info("Finding one Person!");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this ID..."));
    }

    public Person create(Person person){
        logger.info("creating one Person");

        return repository.save(person);

    }

    public Person update(Person person){
        logger.info("Updating one Person");

       Person entity = repository.findById(person.getId()). orElseThrow(() -> new ResourceNotFoundException("No records founds for this ID..."));

       entity.setFirstName(person.getFirstName());
       entity.setLastName(person.getLastName());
       entity.setAddress(person.getAddress());
       entity.setGender(person.getGender());

       return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one Person");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records founds for this ID..."));

        repository.delete(entity);
    }

}
