package br.com.bertanj.services.v2;

import br.com.bertanj.data.dto.v2.PersonDTOV2;
import br.com.bertanj.mapper.custom.PersonMapper;
import br.com.bertanj.model.Person;
import br.com.bertanj.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static br.com.bertanj.mapper.ObjectMapper.parseListObject;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;



@Service
public class PersonServicesV2 {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServicesV2.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTOV2> findAll(){
        logger.info("Finding all People");

        return parseListObject(repository.findAll(), PersonDTOV2.class);
    }

    public PersonDTOV2 findById(Long id){
        logger.info("Finding one person");

        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));
        return converter.convertEntityToDTO(entity);
    }

    public PersonDTOV2 create(Person person){
        logger.info("Creating one person");

        var entity = converter.convertDTOToEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTOV2 update(PersonDTOV2 person){
        logger.info("Updating one person");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setBirthDate(person.getBirthDay());

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public void delete(Long id){
        logger.info("Deleting one person");

        Person entity = repository.findById(id).orElseThrow(() -> new RuntimeException("No records founds for this ID..."));

        repository.delete(entity);
    }
}
