package br.com.bertanj;

import br.com.bertanj.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        logger.info("Finding all People");

        List <Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }



    public Person findById(String id){
        logger.info("Finding one Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mateus");
        person.setLastName("Bertani");
        person.setAddress("Curiúva - Paraná - Brasil");
        person.setGender("Male");

        return person;

    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First name " + i);
        person.setLastName("Last name");
        person.setAddress("Some Address in Brasil");
        person.setGender("Gender");

        return person;
    }

    public Person create(Person person){
        logger.info("creating one Person");
        //acesso o banco nesse ponto aqui
        return person;

    }

    public Person update(Person person){
        logger.info("Updating one Person");

        return person;
    }

    public void delete(String id){
        logger.info("Deleting one Person");
    }
}
