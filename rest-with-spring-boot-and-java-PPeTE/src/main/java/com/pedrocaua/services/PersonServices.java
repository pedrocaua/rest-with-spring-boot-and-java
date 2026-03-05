package com.pedrocaua.services;

import com.pedrocaua.data.dto.PersonDTO;
import com.pedrocaua.exception.ResourceNotFoundException;
import static com.pedrocaua.mapper.ObjectMapper.parseListObjects;
import static com.pedrocaua.mapper.ObjectMapper.parseObject;

//import com.pedrocaua.mapper.custom.PersonMapper;
import com.pedrocaua.model.Person;
import com.pedrocaua.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();     //simula ID automatico (como se fosse um bando de dados)
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());     //Usados para logs, boas práticas de backend

    @Autowired
    PersonRepository repository;

    /*@Autowired
    PersonMapper converter;*/

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person");

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person!");
        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Updating one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);

    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);

    }
}
