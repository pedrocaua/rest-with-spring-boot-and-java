package com.pedrocaua.services;

import com.pedrocaua.exception.ResourceNotFoundException;
import com.pedrocaua.model.Person;
import com.pedrocaua.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();     //simula ID automatico (como se fosse um bando de dados)
    private Logger logger = Logger.getLogger(PersonServices.class.getName());     //Usados para logs, boas práticas de backend

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all persons");
        return repository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Person create(Person person){
        logger.info("Creating one person!");
        return repository.save(person);

    }

    public Person update(Person person){
        logger.info("Updating one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);

    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);

    }
}
