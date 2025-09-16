package br.com.bertanj.controllers.v2;

import br.com.bertanj.data.dto.v2.PersonDTOV2;
import br.com.bertanj.model.Person;
import br.com.bertanj.services.v2.PersonServicesV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v2")
public class PersonControllerV2 {

    @Autowired
    private PersonServicesV2 service;

    @GetMapping(name = "/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTOV2> findAll(){
        return service.findAll();
    }

    @GetMapping(name = "/v2", value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV2 findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping(name = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV2 create(@RequestBody Person person){
        return service.create(person);
    }

    @PutMapping(name = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV2 update(@RequestBody PersonDTOV2 person){
        return service.update(person);
    }

    @DeleteMapping(name = "/v2", value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
