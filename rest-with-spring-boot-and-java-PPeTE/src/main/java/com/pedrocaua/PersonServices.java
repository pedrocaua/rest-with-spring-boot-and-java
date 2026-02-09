package com.pedrocaua;

import com.pedrocaua.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();     //simula ID automatico (como se fosse um bando de dados)

    private Logger logger = Logger.getLogger(PersonServices.class.getName());     //Usados para logs, boas práticas de backend

    public List<Person> findAll() {
        logger.info("Finding all persons");
        List<Person> persons = new ArrayList<Person>();
        for(int i=0; i<8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("Finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Pedro");
        person.setLastName("Cauã");
        person.setAddress("São Paulo - São Paulo - Brasil");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person){
        logger.info("Creating one person!");
        return person;

    }

    public Person update(Person person){
        logger.info("Updating one person!");
        return person;

    }

    public void delete(String id) {
        logger.info("Deleting one person!");
    }


    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName " + i);
        person.setLastName("LastName" + i);
        person.setAddress("Some Adress in Brazil");
        person.setGender("Male");

        return person;
    }
}
